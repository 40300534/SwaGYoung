package com.breeze.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.breeze.constants.ResultMessage;
import com.breeze.enums.*;
import com.breeze.exception.PaymentException;
import com.breeze.pojo.*;
import com.breeze.pojo.bo.OrderPreviewBO;
import com.breeze.pojo.bo.ProductOrderBO;
import com.breeze.pojo.vo.ResultVO;
import com.breeze.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Created by wdnnccey
 * @date 2019-07-02 09:23
 * @Description: 支付宝支付接口
 */
@Slf4j
@RestController
@RequestMapping(value = "/pay")
@Api(tags = {"支付-支付宝"})
public class AliPayController {

    @Value("${aliPay.url}")
    private String url;

    @Value("${aliPay.appid}")
    private String appId;

    @Value("${aliPay.private_key}")
    private String privateKey;

    @Value("${aliPay.public_key}")
    private String publicKey;

    @Value("${aliPay.ali_public_key}")
    private String aliPublicKey;

    @Value("${aliPay.sign_type}")
    private String signType;

    @Value("${aliPay.product_no}")
    private String productNo;

    @Value("${aliPay.charset}")
    private String charset;

    @Value("${aliPay.format}")
    private String format;

    @Value("${aliPay.ali_return_url}")
    private String aliReturnUrl;

    @Value("${aliPay.ali_notify_url}")
    private String aliNotifyUrl;

    @Value("${aliPay.time_express}")
    private String timeExpress;

    /**
     * 出价买
     */
    @Value("${aliPay.bid_buy_notify_url}")
    private String bidBuyNotifyUrl;

    /**
     * 出价卖
     */
    @Value("${aliPay.bid_sell_notify_url}")
    private String bidSellNotifyUrl;

    /**
     * 立刻买
     */
    @Value("${aliPay.now_buy_notify_url}")
    private String nowBuyNotifyUrl;

    /**
     * 立刻卖
     */
    @Value("${aliPay.now_sell_notify_url}")
    private String nowSellNotifyUrl;

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IAliPayService aliPayService;

    @Autowired
    private IProductService productService;

    @Autowired
    private IProductAskService productAskService;

    @Autowired
    private IProductBidService productBidService;

    @Autowired
    private ITransactionService transcationService;

    @Autowired
    private IProductOrderService productOrderService;

    @Autowired
    private IOrderPaymentTransactionService orderPaymentTransactionService;

    @Autowired
    private IProductBidDepositTransactionService productBidDepositTransactionService;

    /**
     * 支付请求
     * 用户id，商品id，商品最新价格，订单名称，商品描述，支付状态
     *
     * @param userId            当前用户Id
     * @param orderPreviewBO    封装了订单的信息
     * @param transcationTypeId 当前交易类型Id
     * @param biddingPrice      用户出价买出价卖的金额 （根据不同的类型ID处理方式不同）
     * @return String 支付宝的支付表单
     * @throws PaymentException 支付异常
     */
    @ApiOperation("订单支付（支付宝）")
    @PostMapping(value = "/alipay", produces = {"application/json;charset=UTF-8"})
    public ResultVO pay(HttpServletRequest request,
                        HttpServletResponse response,
                        @RequestBody OrderPreviewBO orderPreviewBO,
                        @RequestParam(value = "userId") Long userId,
                        @RequestParam(value = "transcationTypeId") Integer transcationTypeId,
                        @RequestParam(value = "biddingPrice", required = false, defaultValue = "0") BigDecimal biddingPrice
    ) throws PaymentException {

        AlipayClient client = new DefaultAlipayClient(url, appId, privateKey, format, charset, publicKey, signType);
        //创建支付请求
        AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();
//        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        //创建支付模式
        AlipayTradePayModel model = new AlipayTradePayModel();
        //发起支付
        String result = aliPayService.pay(request, response, userId, orderPreviewBO, transcationTypeId, client, biddingPrice, alipayRequest, model);

        if (result == null || "".equals(result)) {
            return new ResultVO(false, ResultStatusEnum.ERROR.getCode(), ResultMessage.SERVER_ERROR);
        } else if (ResultMessage.BID_TRADING_PRODUCT_UPDATE_BID_SUCCESS.equals(result)) {
            return new ResultVO(true, ResultStatusEnum.SUCCESS.getCode(), ResultMessage.BID_TRADING_PRODUCT_UPDATE_BID_SUCCESS);
        } else {
            return new ResultVO(true, ResultStatusEnum.SUCCESS.getCode(), ResultMessage.SUCCESS, result);
        }
    }

    /**
     * 同步跳转
     */
    /*@RequestMapping("/returnUrl")
    public void returnUrl(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();
        // 获取支付宝GET过来反馈信息（官方固定代码）
        Map<String, String> params = this.getResult(request);
        // 调用SDK验证签名
        boolean signVerified = AlipaySignature.rsaCheckV1(params, aliPublicKey, charset, signType);

        // 返回界面
        if (signVerified) {
            System.out.println("前往支付成功页面...");
        } else {
            System.out.println("前往支付失败页面...");
            mav.setViewName("error");
        }
    }*/

    /**
     * 异步回调结果：出价买
     */
    @RequestMapping("/bidBuyNotifyUrl")
    @Transactional
    public void bidBuyNotifyUrl(HttpServletRequest request, HttpServletResponse response) throws Exception {
        PrintWriter out = response.getWriter();
        try {
            // 获取支付宝GET过来反馈信息
            Map<String, String> params = getResult(request);
            boolean signVerified = AlipaySignature.rsaCheckV1(params, aliPublicKey, charset, signType); // 调用SDK验证签名
            if (signVerified) { // 验证成功 更新订单信息
                Transaction transaction = createTransaction(params);
                log.info("出价买记录生成........................");
                if (transaction != null) {
                    ProductBidDepositTransaction bidDepositTransactionServiceByProductBidId = productBidDepositTransactionService.findByProductBidId(transaction.getOutTradeNo());
                    //创建订单出价押金交易记录信息
                    if (bidDepositTransactionServiceByProductBidId == null) {
                        ProductBidDepositTransaction productBidDepositTransaction = new ProductBidDepositTransaction();
                        productBidDepositTransaction.setTransactionId(transaction.getId());
                        productBidDepositTransaction.setProductBidId(transaction.getOutTradeNo());
                        productBidDepositTransaction.setCreateTime(new Date());
                        productBidDepositTransactionService.add(productBidDepositTransaction);
                        log.info("出价押金已缴纳，变更状态。");
                        //查询上一次出价的相关信息，更新状态
                        ProductBid productBidInfo = productBidService.findById(transaction.getOutTradeNo());
                        ProductBid history = productBidService.findByProductIdAndUserIdAndStatus(productBidInfo.getProductId(), productBidInfo.getUserId(), BidTradingStatusEnum.PRODUCT_LISTED_PAID.getCode());
                        if (history != null) {
//                            history.setStatus(BidTradingStatusEnum.PRODUCT_NEW_BIDDING.getCode());
                            productBidService.update(history);
                            log.info("出价历史状态已更新为：" + BidTradingStatusEnum.PRODUCT_NEW_BIDDING.getCode());
                        }
                        //修改最新提交的出价支付状态
                        ProductBid productBid = productBidService.findById(transaction.getOutTradeNo());
//                        productBid.setStatus(BidTradingStatusEnum.PRODUCT_LISTED_PAID.getCode());
                        productBidService.update(productBid);
                        log.info("最新出价状态已更新为：" + BidTradingStatusEnum.PRODUCT_LISTED_PAID.getCode());
                        out.println("success");
                    }
                } else {
                    out.println("fail");
                }
            } else {
                log.info("异步通知失败");
                out.println("fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.println("fail");
        } finally {
            out.flush();
            out.close();
        }
    }

    /**
     * 异步回调结果：出价卖
     */
    @RequestMapping("/bidSellNotifyUrl")
    public void bidSellNotifyUrl(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        try {
            // 获取支付宝GET过来反馈信息
            Map<String, String> params = getResult(request);
            boolean signVerified = AlipaySignature.rsaCheckV1(params, aliPublicKey, charset, signType); // 调用SDK验证签名
            if (signVerified) { // 验证成功 更新订单信息
                Transaction transaction = createTransaction(params);
                if (transaction != null) {
                    ProductAskDepositTransaction productAskDepositTransaction = new ProductAskDepositTransaction();
//                    productAskDepositTransaction.setCreateTime(new Date());
                    productAskDepositTransaction.setProductAskId(transaction.getOutTradeNo());
                    productAskDepositTransaction.setTransactionId(transaction.getId());
                    //修改出价卖的状态
                    ProductAsk productAsk = productAskService.findById(transaction.getOutTradeNo());
//                    productAsk.setStatus(BidTradingStatusEnum.PRODUCT_LISTED_PAID.getCode());
                    productAskService.update(productAsk);
                    log.info("修改出价卖的状态为：已支付");
                } else {
                    out.println("fail");
                }
            } else {
                log.info("异步通知失败");
                out.println("fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.println("fail");
        } finally {
            out.flush();
            out.close();
        }
    }

    /**
     * 异步回调结果：立刻买
     */
    @RequestMapping("/nowBuyNotifyUrl")
    public void nowBuyNotifyUrl(HttpServletRequest request, HttpServletResponse response) throws Exception {
        PrintWriter out = response.getWriter();
        // 获取支付宝GET过来反馈信息
        try {
            Map<String, String> params = getResult(request);
            boolean signVerified = AlipaySignature.rsaCheckV1(params, aliPublicKey, charset, signType); // 调用SDK验证签名
            if (signVerified) { // 验证成功 更新订单信息
                Transaction transaction = createTransaction(params);
                if (transaction != null) {
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    OrderPaymentTransaction orderPaymentTransaction = new OrderPaymentTransaction();
                    orderPaymentTransaction.setOrderId(Long.valueOf(params.get("out_trade_no")));
                    orderPaymentTransaction.setTransactionId(transaction.getId());
                    orderPaymentTransaction.setCreateTime(formatter.parse(params.get("gmt_create")));
                    orderPaymentTransactionService.add(orderPaymentTransaction);
                    //修改order状态
                    Order order = orderService.findById(Long.valueOf(params.get("out_trade_no")));
                    log.info("修改立刻买的状态为：已支付");
                    //支付成功后需要进行扣库存操作
                    ProductOrderBO productOrder = productOrderService.findByOrderId(order.getId());
                    ProductAsk productAsk = productAskService.findByUserIdAndProductId(order.getSellerId(), productOrder.getProductId());
                    int stock = productAskService.decreaseStock(productAsk.getId());
                    //扣库存失败，退款
                    if (stock == 0) {
                        refund(order.getId());
                        //已经没有库存，设置状态为"已退款"
//                        order.setStatusId(OrderStatusEnum.ORDER_REFUND.getCode());
                        log.info("立刻买失败，已退款。 设置【订单】状态为：" + OrderStatusEnum.ORDER_REFUND.getName());

                    } else {
                        //成功扣库存，设置状态为"已支付"
//                        order.setStatusId(OrderStatusEnum.ORDER_PAID.getCode());
                        log.info("立刻买成功，设置【订单】状态为：" + OrderStatusEnum.ORDER_PAID.getName());
                    }
                    orderService.update(order);
                    //修改卖家出售的状态[已预订]
//                    productAsk.setStatus(BidTradingStatusEnum.PRODUCT_BOOKING.getCode());
                    productAskService.update(productAsk);
                    log.info("立刻买成功，修改【出价卖】状态为：" + OrderStatusEnum.ORDER_PAID.getName());

                } else {
                    out.println("fail");
                }
            } else {
                log.info("异步通知失败");
                out.println("fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.println("fail");
        } finally {
            out.flush();
            out.close();
        }
    }

    /**
     * 异步回调结果：立刻卖
     */
    @RequestMapping("/nowSellNotifyUrl")
    public void nowSellNotifyUrl(HttpServletRequest request, HttpServletResponse response) throws Exception {
        PrintWriter out = response.getWriter();
        // 获取支付宝GET过来反馈信息
        try {
            Map<String, String> params = getResult(request);
            boolean signVerified = AlipaySignature.rsaCheckV1(params, aliPublicKey, charset, signType); // 调用SDK验证签名

            if (signVerified) { // 验证成功 更新订单信息
                Transaction transaction = createTransaction(params);
                if (transaction != null) {
                    OrderDepositTransaction orderDepositTransaction = new OrderDepositTransaction();
                    orderDepositTransaction.setOrderId(Long.valueOf(params.get("out_trade_no")));
                    orderDepositTransaction.setCreateTime(new Date());
                    orderDepositTransaction.setTransactionId(transaction.getId());
                    //立刻卖 是卖家
                    orderDepositTransaction.setIsSeller(1);
                    //修改order状态
                    Order order = orderService.findById(Long.valueOf(params.get("out_trade_no")));
//                    order.setStatusId(OrderStatusEnum.ORDER_PAID.getCode());
                    orderService.update(order);
                    //支付成功后需要把对应的出价设置成已有人拍下
                    ProductOrderBO productOrder = productOrderService.findByOrderId(order.getId());
                    ProductBid productBid = productBidService.findByProductIdAndUserIdAndStatus(productOrder.getProductId(), order.getSellerId(), 0);
//                    productBid.setStatus(BidTradingStatusEnum.PRODUCT_BOOKING.getCode());
                    productBidService.update(productBid);

                    //TODO 如果用户立刻卖，需要给出价买的人 进行一次订单生成， 支付尾款。

                } else {
                    out.println("fail");
                }
            } else {
                log.info("异步通知失败");
                out.println("fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.println("fail");
        } finally {
            out.flush();
            out.close();
        }
    }

    /**
     * 退款
     */
    @GetMapping(value = "/refund")
    private void refund(Long outTradeNo) throws AlipayApiException {
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(url, appId, privateKey, format, charset, aliPublicKey, signType);
        //设置请求参数
        AlipayTradeRefundRequest alipayRequest = new AlipayTradeRefundRequest();
        //商户订单号，必填
//        String out_trade_no = "20190702133742644";
//        需要退款的金额，该金额不能大于订单金额，必填：（通过订单号查询对应付款的价格，进行全额退款）
        Order order = orderService.findById(outTradeNo);
        String refund_amount = String.valueOf(order.getPrice());
        //标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传
        String outRequestNo = UUID.randomUUID().toString();

        alipayRequest.setBizContent("{\"out_trade_no\":\"" + outTradeNo + "\","
                + "\"refund_amount\":\"" + refund_amount + "\","
                + "\"out_request_no\":\"" + outRequestNo + "\"}");
        //请求
        String result = alipayClient.execute(alipayRequest).getBody();

    }

    /**
     * 封装支付宝返回的结果
     */
    private Map<String, String> getResult(HttpServletRequest request) {
        // 获取支付宝GET过来反馈信息
        Map<String, String> params = new HashMap<>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (String name : requestParams.keySet()) {
            String[] values = requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }
        return params;
    }

    /**
     * 创建订单公用方法
     */
    private Transaction createTransaction(Map<String, String> params) {
        try {
            Transaction transaction = transcationService.findByOutTradeNo(Long.valueOf(params.get("out_trade_no")));
            if (transaction == null) {
                //创建交易记录
                Transaction transcation = new Transaction();
                //交易金额（商品价格的百分之十押金）
                DecimalFormat df = new DecimalFormat("###.##");
                BigDecimal totalAmount = new BigDecimal(df.format(new BigDecimal(params.get("total_amount"))));
                transcation.setAmount(totalAmount);
                //交易方式
                transcation.setPaymentTypeId(PaymentTypeEnum.ALIPAY.getCode());
                //支付宝订单号
                transcation.setTradeNo(params.get("trade_no"));
                //商家订单号
                transcation.setOutTradeNo(Long.valueOf(params.get("out_trade_no")));
                //卖家账户Id
                transcation.setSellerAccountId(params.get("seller_id"));
                //交易时间
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                transcation.setCreateTime(formatter.parse(params.get("gmt_create")));
                transcationService.add(transcation);
                return transcation;
            } else {
                return transaction;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}