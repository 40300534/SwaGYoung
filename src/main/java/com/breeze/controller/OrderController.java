package com.breeze.controller;

import com.breeze.enums.BidTradingStatusEnum;
import com.breeze.enums.TransactionTypeEnum;
import com.breeze.pojo.ProductBid;
import com.breeze.pojo.User;
import com.breeze.pojo.UserAddress;
import com.breeze.pojo.bo.*;
import com.breeze.service.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

/**
 * @author wdnnccey
 */
@RestController
@RequestMapping(value = "/order")
@Api(tags = {"订单相关"})
public class OrderController {
    @Value("${uploadImage.serverUrlPrefix}")
    private String serverUrlPrefix;

    @Autowired
    private IFeeService feeService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IProductService productService;

    @Autowired
    private IDepositService depositService;

    @Autowired
    private IProductBidService productBidService;

    @Autowired
    private IProductAskService productAskService;

    @Autowired
    private IUserAddressService userAddressService;

    @Autowired
    private IProductBidService productUserBuyService;

    @Autowired
    private IProductAskService productUserSellService;


    /**
     * 订单买入预览
     *
     * @param productId 当前订单的产品Id
     * @param userId    当前操作的用户Id
     * @return OrderPreviewBO 包含商品信息、费用信息、地址信息
     */
    @ApiOperation("订单预览（买）")
    @PostMapping(value = "/buyerOrderPreview")
    public OrderPreviewBO orderBuyPreview(@RequestParam("productId") Long productId,
                                          @RequestParam(value = "userId", required = false) Long userId) {
        OrderPreviewBO orderPreviewBO = new OrderPreviewBO();
        Integer userTypeId;
        //获取用户信息
        User user = userService.findById(userId);
        if (user != null) {
            userTypeId = user.getUserTypeId();
        } else {
            userTypeId = 3;
        }
        //获取商品基本信息
        ProductBO productInfo = productService.findProductBuyBaseInfoByProductId(productId);
        //设置商品基本信息
        orderPreviewBO.setProductInfo(productInfo);
        //获取费用信息
        List<FeeBO> fees = feeService.findFeeByUserTypeIdAndIsSeller(userTypeId, 0);
        //设置费用信息
        orderPreviewBO.setFeeInfo(fees);
        //根据当前交易类型进行价格计算
        BigDecimal totalAmount = feeService.countAmount(userTypeId, productId, productInfo.getPrice(), TransactionTypeEnum.NOW_BUY.getCode(), 0);
        //设置订单总金额
        orderPreviewBO.setTotalAmount(totalAmount);
        ProductBid productUserBuy = productBidService.findHighBidByProductId(productId);
        if (productUserBuy == null) {
            orderPreviewBO.setHighestBidBuy(new BigDecimal("0"));
        } else {
            //设置当前产品出价最高的想买入价格
            orderPreviewBO.setHighestBidBuy(productUserBuy.getPrice());
        }
        //获取当前用户默认地址
        UserAddress userAddress = userAddressService.findDefaultAddressByUserId(userId);
        //设置保证金
        orderPreviewBO.setDeposit(depositService.findDepositByUserTypeId(userTypeId));
        //获取当前用户是否有出价买
        ProductBid productBid = productBidService.findByProductIdAndUserIdAndStatus(productId, userId, BidTradingStatusEnum.PRODUCT_LISTED_PAID.getCode());
        if (productBid != null) {
            orderPreviewBO.setBiding(true);
            orderPreviewBO.setBiddingPrice(productBid.getPrice());
        } else {
            orderPreviewBO.setBiding(false);
        }
        orderPreviewBO.setUserAddressInfo(userAddress);

        return orderPreviewBO;
    }

    /**
     * 订单出售预览
     *
     * @param productId 产品id
     * @param userId    用户id
     * @return OrderPreviewBO
     */
    @ApiOperation("订单预览（卖）")
    @PostMapping(value = "/sellerOrderPreview")
    public OrderPreviewBO orderSellPreview(@RequestParam(value = "productId") Long productId,
                                           @RequestParam(value = "userId", required = false) Long userId) {

        OrderPreviewBO orderPreviewBO = new OrderPreviewBO();
        //获取用户信息
        User user = userService.findById(userId);
        Integer userTypeId;
        if (user != null) {
            userTypeId = user.getUserTypeId();
        } else {
            userTypeId = 3;
        }
//        userTypeId = user.getUserTypeId();
        //商品基本信息
        ProductBO productInfo = productService.findProductSellBaseInfoByProductId(productId);
        orderPreviewBO.setProductInfo(productInfo);

        //获取当前商品出价最高的金额
        BigDecimal highestBid = productAskService.findHighestAsk(productId);
        BigDecimal lowestBid = productAskService.findLowestAsk(productId);
        orderPreviewBO.setHighestBidSell(highestBid);
        orderPreviewBO.setLowestBidSell(lowestBid);

        //出价卖费用(计算费用，出价是有用户传入过来,所以只传递了费用信息)
        //获取费用信息
//        List<FeeBO> fee = feeService.findFeeByUserTypeId(userTypeId);
        List<FeeBO> fees = feeService.findFeeByUserTypeIdAndIsSeller(userTypeId, 1);
        //设置费用信息
        orderPreviewBO.setFeeInfo(fees);
        //获取当前用户默认地址
        UserAddress userAddress = userAddressService.findDefaultAddressByUserId(userId);
        orderPreviewBO.setUserAddressInfo(userAddress);
        //立即卖费用
        BigDecimal totalAmount = feeService.countAmount(userTypeId, productId, highestBid, TransactionTypeEnum.NOW_SELL.getCode(), 1);
        orderPreviewBO.setTotalAmount(totalAmount);
        orderPreviewBO.setDeposit(depositService.findDepositByUserTypeId(userTypeId));
        return orderPreviewBO;
    }


    /**
     * 查询订单详情
     *
     * @param orderId
     * @return
     */
//    @LoginRequired
    @ApiOperation("订单详情")
    @GetMapping(value = "/orderDetails/{orderId}")
    public OrderDetailsBO findOrderDetails(@PathVariable("orderId") Long orderId) {
        return orderService.findOrderId(orderId);
    }

    //    @Admin
//    @LoginRequired
    @ApiOperation("订单管理页面")
    @GetMapping(value = "/orderManagementPage/{status}")
    public PageInfo<OrderBO> orderDetails(@PathVariable("status") Long status, @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
                                          @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<OrderBO> orderList = orderService.findOrderByStatusId(status);
        orderList.forEach(order -> {
            order.setMasterPicture(serverUrlPrefix + order.getMasterPicture());
            order.setStatusUpdateTime(new Date());
            //出价买 付的押金
            AmountBO buyerPaysDeposit = orderService.findBuyerPaysDeposit(order.getBuyerId());
            //出价卖  付的押金
            AmountBO sellerPaysDeposit = orderService.findSellerPaysDeposit(order.getSellerId());
            DecimalFormat df = new DecimalFormat("###.##");
            order.setBuyerDeposit(buyerPaysDeposit != null ? buyerPaysDeposit.getAmount() : new BigDecimal(df.format(new BigDecimal(0.00))));
            order.setSellerDeposit(sellerPaysDeposit != null ? sellerPaysDeposit.getAmount() : new BigDecimal(df.format(new BigDecimal(0.00))));
            //TODO
            order.setDrawBack("退款按钮");
            order.setLogisticsInformation("物流信息");
        });
        return new PageInfo<>(orderList);
    }

}
