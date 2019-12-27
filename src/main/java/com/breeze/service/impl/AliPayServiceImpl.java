package com.breeze.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayTradePayModel;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.breeze.constants.ResultMessage;
import com.breeze.enums.*;
import com.breeze.exception.PaymentException;
import com.breeze.mapper.*;
import com.breeze.pojo.*;
import com.breeze.pojo.bo.OrderPreviewBO;
import com.breeze.pojo.bo.ProductDetailsBO;
import com.breeze.service.IAliPayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author Created by wdnnccey
 * @date 2019-07-06 23:25
 * @Description: 一句话描述该类的功能
 */
@Service
@Slf4j
public class AliPayServiceImpl implements IAliPayService {

    @Value("${aliPay.url}")
    private String url;

    @Value("${aliPay.appid}")
    private String appId;

    @Value("${aliPay.format}")
    private String format;

    @Value("${aliPay.charset}")
    private String charset;

    @Value("${aliPay.sign_type}")
    private String signType;

    @Value("${aliPay.product_no}")
    private String productNo;

    @Value("${aliPay.public_key}")
    private String publicKey;

    @Value("${aliPay.private_key}")
    private String privateKey;

    @Value("${aliPay.time_express}")
    private String timeExpress;

    @Value("${aliPay.ali_public_key}")
    private String aliPublicKey;

    @Value("${aliPay.ali_return_url}")
    private String aliReturnUrl;

    @Value("${aliPay.ali_notify_url}")
    private String aliNotifyUrl;

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


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private DepositMapper depositMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductAskMapper productAskMapper;

    @Autowired
    private ProductBidMapper productBidMapper;

    @Autowired
    private TransactionMapper transcationMapper;

    @Autowired
    private ProductOrderMapper productOrderMapper;

    @Autowired
    private OrderDepositTransactionMapper orderDepositTransactionMapper;

    @Autowired
    private ProductAskDepositTransactionMapper productAskDepositTransactionMapper;

    @Override
    @Transactional
    public String pay(HttpServletRequest request,
                      HttpServletResponse response,
                      Long userId,
                      OrderPreviewBO orderPreviewBO,
                      Integer transcationTypeId,
                      AlipayClient client,
                      BigDecimal biddingPrice,
                      AlipayTradeWapPayRequest alipayRequest,
                      AlipayTradePayModel model) throws PaymentException {
        //如果没有登录 不能交易
        if (userId == null) {
            throw new PaymentException(ResultStatusEnum.ERROR.getCode(), ResultMessage.USER_ID_IS_NULL);
        }
        //如果没有交易类型 不能交易
        if (transcationTypeId == null || transcationTypeId == 0) {
            throw new PaymentException(ResultStatusEnum.ERROR.getCode(), ResultMessage.PAY_EXCEPTION_ERROR);
        }
        //如果没有地址 不能交易
        if (orderPreviewBO.getUserAddressInfo() == null || orderPreviewBO.getUserAddressInfo().getId() == null) {
            throw new PaymentException(ResultStatusEnum.ERROR.getCode(), ResultMessage.USER_ADDRESS_IS_NULL);
        }

        //如果没货不能立刻买
        if (TransactionTypeEnum.NOW_BUY.getCode().equals(transcationTypeId)) {
            ProductDetailsBO productDetails = productMapper.findProductDetailsById(orderPreviewBO.getProductInfo().getProductId());
            if (productDetails.getInventory() <= 0) {
                throw new PaymentException(ResultStatusEnum.ERROR.getCode(), ResultMessage.PAY_EXCEPTION_PRODUCT_SOLD_OUT);
            }
            //如果买家（userId）和卖家（SellUserId）相同，不允许交易
            if (orderPreviewBO.getProductInfo().getSellUserId() != null) {
                if (orderPreviewBO.getProductInfo().getSellUserId().equals(userId)) {
                    throw new PaymentException(ResultStatusEnum.ERROR.getCode(), ResultMessage.PAY_EXCEPTION_PRODUCT_NO_SOLD_ONESELF);
                }
            } else {
                throw new PaymentException(ResultStatusEnum.ERROR.getCode(), ResultMessage.PAY_EXCEPTION_ERROR);
            }
        }
        //如果没有人出价卖 没办法立刻卖
        if (TransactionTypeEnum.NOW_SELL.getCode().equals(transcationTypeId)) {
            List<ProductBid> productBid = productBidMapper.findByProductIdAndStatus(orderPreviewBO.getProductInfo().getProductId(), BidTradingStatusEnum.PRODUCT_LISTED_PAID.getCode());
            if (productBid == null || productBid.size() <= 0) {
                throw new PaymentException(ResultStatusEnum.ERROR.getCode(), ResultMessage.PAY_EXCEPTION_PRODUCT_NO_BID);
            }
            //如果卖家（userId）和买家（BuyerUserId）相同，不允许交易
            if (orderPreviewBO.getProductInfo().getBuyerUserId() != null) {
                if (orderPreviewBO.getProductInfo().getBuyerUserId().equals(userId)) {
                    throw new PaymentException(ResultStatusEnum.ERROR.getCode(), ResultMessage.PAY_EXCEPTION_PRODUCT_NO_SOLD_ONESELF);
                }
            } else {
                throw new PaymentException(ResultStatusEnum.ERROR.getCode(), ResultMessage.PAY_EXCEPTION_ERROR);
            }
        }

        try {
            BigDecimal deport = null;
            //获取用户类型ID，计算费用
            User user = userMapper.findById(userId);
            Integer userTypeId = user.getUserTypeId();

            //生成订单ID
            Random random = new Random();
            SimpleDateFormat allTime = new SimpleDateFormat("yyyyMMddHHmmSSS");
            String subjectNo = allTime.format(new Date()) + random.nextInt(10);
            String orderNo = subjectNo + random.nextInt(5); // 生成订单号
            //获取商品名称
            String subject = orderPreviewBO.getProductInfo().getProductName();
            //订单价格
            String totalAmount;

            //出价买
            if (TransactionTypeEnum.BID_BUY.getCode().equals(transcationTypeId)) {
                alipayRequest.setNotifyUrl(bidBuyNotifyUrl);
                model.setBody("保证金");
                DecimalFormat df = new DecimalFormat("###.##");
                //判断价格（保证价格有值）
                int priceResult = biddingPrice.compareTo(BigDecimal.ZERO);
                if (priceResult == 0 || priceResult < 0) {
                    throw new PaymentException(ResultStatusEnum.ERROR.getCode(), ResultMessage.PAY_EXCEPTION_USER_BID_ERROR);
                }
                //判断用户出价是否为10的倍数
                BigDecimal bidValue = biddingPrice.divide(new BigDecimal(BidFeeEnum.BID_MULTIPLE.getCode()));
                if (new BigDecimal(bidValue.intValue()).compareTo(bidValue) != 0) {
                    //不是10的倍数
                    throw new PaymentException(ResultStatusEnum.ERROR.getCode(), ResultMessage.PAY_EXCEPTION_USER_BID_MULTIPLE_ERROR);
                }
                //获取当前用户是否有出价买
                ProductBid productBidHistory = productBidMapper.findByProductIdAndUserIdAndStatus(orderPreviewBO.getProductInfo().getProductId(), userId, BidTradingStatusEnum.PRODUCT_LISTED_PAID.getCode());
                //当前用户没有出价
                if (productBidHistory == null) {
                    //押金 = 出价*押金比例
                    totalAmount = String.valueOf(biddingPrice.multiply(new BigDecimal(df.format(depositMapper.findDepositByUserTypeId(userTypeId).divide(new BigDecimal("100"), RoundingMode.HALF_UP)))));
                    model.setTotalAmount(totalAmount);
                    updateProductBid(true, orderNo, orderPreviewBO, userId, biddingPrice, null, ResultMessage.SUCCESS);
                } else {
                    //如果两次价格相同
                    if (biddingPrice.compareTo(productBidHistory.getPrice()) == 0) {
                        throw new PaymentException(ResultStatusEnum.ERROR.getCode(), ResultMessage.PAY_EXCEPTION_USER_BID_SAME_ERROR);
                    }
                    //如果出价高于历史出价（补缴差价，修改上次状态）
                    if (biddingPrice.compareTo(productBidHistory.getPrice()) == 1) {
                        System.out.println("出价大于现在历史价格");
                        List<Long> byUserIdAndStatus = productBidMapper.findByUserIdAndStatusAndProductId(userId, BidTradingStatusEnum.PRODUCT_NEW_BIDDING.getCode(), orderPreviewBO.getProductInfo().getProductId());
                        //总计（在没发生订单交易之前，一共缴纳了多少费用）
                        //1+2+3
                        BigDecimal totalAmountHistory = transcationMapper.findCountAmountByOutTradeNo(byUserIdAndStatus);
                        //最新出价 5
                        BigDecimal totalAmountNew = biddingPrice.multiply(new BigDecimal(df.format(depositMapper.findDepositByUserTypeId(userTypeId).divide(new BigDecimal("100"), RoundingMode.HALF_UP))));
                        int compareToValue = totalAmountHistory.stripTrailingZeros().compareTo(totalAmountNew.stripTrailingZeros());
                        if (compareToValue == 1) {//总支付金额>当前出价金额
                            return updateProductBid(false, orderNo, orderPreviewBO, userId, biddingPrice, productBidHistory, ResultMessage.BID_TRADING_PRODUCT_UPDATE_BID_SUCCESS);
                        } else if (compareToValue == -1) {//总支付金额<当前出价金额
                            //最新出价 - 上一次出价 = 需要的差价费用
                            totalAmount = String.valueOf(totalAmountNew.subtract(totalAmountHistory));
                            model.setTotalAmount(totalAmount);
                            updateProductBid(true, orderNo, orderPreviewBO, userId, biddingPrice, productBidHistory, ResultMessage.SUCCESS);
                        } else if (compareToValue == 0) { //总支付金额=当前出价金额
                            return updateProductBid(false, orderNo, orderPreviewBO, userId, biddingPrice, productBidHistory, ResultMessage.BID_TRADING_PRODUCT_UPDATE_BID_SUCCESS);
                        } else {
                            ProductBid productBid = new ProductBid();
                            productBid.setId(Long.valueOf(orderNo));
                            productBid.setProductId(orderPreviewBO.getProductInfo().getProductId());
                            productBid.setUserId(userId);
                            //前台传递价格，用户的想要出价买的金额
                            productBid.setPrice(biddingPrice);
                            productBid.setCreateTime(new Date());
                            Object feeJson = JSONArray.toJSON(orderPreviewBO.getFeeInfo());
                            productBid.setFee(feeJson.toString());
//                            productBid.setStatus(BidTradingStatusEnum.PRODUCT_LISTED_UNPAID.getCode());
                            productBidMapper.add(productBid);
                            log.info("【productBid】当前用户【无出价】，商品出价买记录已生成...");
                        }
                    }
                    //如果出价低于历史价格（修改状态，无需支付）
                    if (biddingPrice.compareTo(productBidHistory.getPrice()) == -1) {
                        return updateProductBid(false, orderNo, orderPreviewBO, userId, biddingPrice, productBidHistory, ResultMessage.BID_TRADING_PRODUCT_UPDATE_BID_SUCCESS);
                    }
                }
            }

            //出价卖
            if (TransactionTypeEnum.BID_SELL.getCode().equals(transcationTypeId)) {
                //判断价格
                int priceResult = biddingPrice.compareTo(BigDecimal.ZERO);

                if (priceResult == 0 || priceResult < 0) {
                    throw new PaymentException(ResultStatusEnum.ERROR.getCode(), ResultMessage.PAY_EXCEPTION_USER_BID_ERROR);
                }
                //判断用户出价是否为10的倍数
                BigDecimal bidValue = biddingPrice.divide(new BigDecimal(BidFeeEnum.BID_MULTIPLE.getCode()));
                if (new BigDecimal(bidValue.intValue()).compareTo(bidValue) != 0) {
                    //不是10的倍数
                    throw new PaymentException(ResultStatusEnum.ERROR.getCode(), ResultMessage.PAY_EXCEPTION_USER_BID_MULTIPLE_ERROR);
                }
                alipayRequest.setNotifyUrl(bidSellNotifyUrl);
//                alipayRequest.setReturnUrl(aliReturnUrl);
                //立即买获取当前订单总价格，无需支付押金
                model.setBody("保证金");
                model.setTotalAmount(String.valueOf(biddingPrice.multiply(depositMapper.findDepositByUserTypeId(userTypeId).divide(new BigDecimal("100"), RoundingMode.HALF_UP))));
                ProductAsk productAsk = new ProductAsk();
                productAsk.setId(Long.valueOf(orderNo));
                productAsk.setUserId(userId);
                productAsk.setCreateTime(new Date());
                //目前都是单个商品 库存为"1"
                productAsk.setInventory(1);
                productAsk.setPrice(biddingPrice);
                productAsk.setProductId(orderPreviewBO.getProductInfo().getProductId());
                String feeJson = JSONArray.toJSONString(orderPreviewBO.getFeeInfo());
                productAsk.setFee(feeJson);
//                productAsk.setStatus(BidTradingStatusEnum.PRODUCT_LISTED_UNPAID.getCode());
                productAskMapper.add(productAsk);
                log.info("【productAsk】商品出价卖记录已生成...");
            }

            //立刻买（付商品钱，与出价卖对应）
            if (TransactionTypeEnum.NOW_BUY.getCode().equals(transcationTypeId)) {
                alipayRequest.setNotifyUrl(nowBuyNotifyUrl);
//                alipayRequest.setReturnUrl(aliReturnUrl);
                //立即买获取当前订单总价格，无需支付押金
                totalAmount = String.valueOf(orderPreviewBO.getTotalAmount());
                model.setBody("商品费用");
                //设置支付宝需要支付的总金额
                model.setTotalAmount(totalAmount);
                //设置该订单产生的总金额
                Order order = new Order();
                order.setId(Long.valueOf(orderNo));
                order.setPrice(new BigDecimal(totalAmount));
//                order.setStatusId(OrderStatusEnum.ORDER_UNPAID.getCode());
                order.setCreateTime(new Date());
                order.setSellerId(orderPreviewBO.getProductInfo().getSellUserId());
                order.setBuyerId(userId);
                Object feeJson = JSONArray.toJSON(orderPreviewBO.getFeeInfo());
                order.setBuyerFee(feeJson.toString());
                order.setSellerFee("");
//                order.setAddressId(orderPreviewBO.getUserAddressInfo().getId());
                orderMapper.add(order);
                log.info("【order】已经生成订单...");
                ProductOrder productOrder = new ProductOrder();
                productOrder.setOrderId(Long.valueOf(orderNo));
                productOrder.setProductId(orderPreviewBO.getProductInfo().getProductId());
                //所有产品现阶段都只有1
                productOrder.setQuantity(1);
                //商品成交价格
                productOrder.setSoldPrice(orderPreviewBO.getProductInfo().getPrice());
                productOrderMapper.add(productOrder);
                log.info("【productOrder】已经生成产品订单...");
                //获取product_ask_id
//            ProductAsk productAsk = productAskMapper.findTranscationIdByUserIdAndProductId(orderPreviewBO.getProductInfo().getSellUserId(), orderPreviewBO.getProductInfo().getProductId());
//            ProductAskDepositTransaction productAskDepositTransaction = productAskDepositTransactionMapper.findTranscationIdByProductAskId(productAsk.getId());
                OrderDepositTransaction orderDepositTransaction = new OrderDepositTransaction();
                orderDepositTransaction.setOrderId(Long.valueOf(orderNo));
                orderDepositTransaction.setTransactionId(orderPreviewBO.getProductInfo().getSellTransactionId());
                orderDepositTransaction.setCreateTime(new Date());
                orderDepositTransaction.setIsSeller(0);
                orderDepositTransactionMapper.add(orderDepositTransaction);
                log.info("【orderDepositTransaction】已经关联相关押金费用...");
            }

            //立刻卖（找到当前出价最高的人来匹配交易，与出价买对应）
            if (TransactionTypeEnum.NOW_SELL.getCode().equals(transcationTypeId)) {
                alipayRequest.setNotifyUrl(nowSellNotifyUrl);
//                alipayRequest.setReturnUrl(aliReturnUrl);
                //立刻卖需要先支付押金
                model.setBody("保证金");
                BigDecimal deportPrice = orderPreviewBO.getProductInfo().getPrice().multiply(depositMapper.findDepositByUserTypeId(userTypeId).divide(new BigDecimal("100"), RoundingMode.HALF_UP));
                model.setTotalAmount(String.valueOf(deportPrice));
                Order order = new Order();
                order.setId(Long.valueOf(orderNo));
                order.setPrice(orderPreviewBO.getProductInfo().getPrice());
//            order.setPrice(new BigDecimal(totalAmount));
//                order.setStatusId(OrderStatusEnum.ORDER_UNPAID.getCode());
                order.setCreateTime(new Date());
                order.setSellerId(userId);
                order.setBuyerId(orderPreviewBO.getProductInfo().getBuyerUserId());
//                order.setAddressId(orderPreviewBO.getUserAddressInfo().getId());
                Object jsonObject = JSONArray.toJSON(orderPreviewBO.getFeeInfo());
                order.setBuyerFee("");
//                order.setAddressId(0L);
                order.setSellerFee(jsonObject.toString());
                orderMapper.add(order);
                log.info("【order】已经生成订单...");
            }

            //配置
            model.setProductCode(productNo); // 设置销售产品码
            model.setOutTradeNo(orderNo); // 设置订单号
            model.setSubject(subject); // 订单名称
            model.setTimeoutExpress(timeExpress);//支付时间限制
            alipayRequest.setBizModel(model);
            String form = client.pageExecute(alipayRequest).getBody(); // 生成表单
            System.out.println(form);
            return form;
        } catch (Exception e) {
            throw new PaymentException(ResultStatusEnum.ERROR.getCode(), e.getMessage());
        }

    }

    private String updateProductBid(boolean isPayDeposit, String orderNo, OrderPreviewBO orderPreviewBO, Long userId, BigDecimal biddingPrice, ProductBid productBidHistory, String msg) {
        ProductBid productBid = new ProductBid();
        //如果需要缴纳保证金
        if (isPayDeposit) {
//            productBid.setStatus(BidTradingStatusEnum.PRODUCT_LISTED_UNPAID.getCode());
        } else {
//            productBid.setStatus(BidTradingStatusEnum.PRODUCT_LISTED_PAID.getCode());
            if (productBidHistory != null) {
//                productBidHistory.setStatus(BidTradingStatusEnum.PRODUCT_NEW_BIDDING.getCode());
                productBidMapper.update(productBidHistory);
                log.info("【productBid】商品状态已经修改：" + BidTradingStatusEnum.PRODUCT_NEW_BIDDING.getMessage());
            }
        }


        //创建新的出价信息
        productBid.setId(Long.valueOf(orderNo));
        productBid.setProductId(orderPreviewBO.getProductInfo().getProductId());
        productBid.setUserId(userId);
        //前台传递价格，用户的想要出价买的金额
        productBid.setPrice(biddingPrice);
        productBid.setCreateTime(new Date());
        Object feeJson = JSONArray.toJSON(orderPreviewBO.getFeeInfo());
        productBid.setFee(feeJson.toString());
        productBidMapper.add(productBid);
        log.info("【productBid】商品出价买记录已生成...");
        return msg;
//        return ResultStatusEnum.SUCCESS.getName();
    }
}
