package com.breeze.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.breeze.mapper.*;
import com.breeze.pojo.*;
import com.breeze.pojo.bo.*;
import com.breeze.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;


/**
 * @author tx
 */
@Service
public class OrderServiceImpl implements IOrderService {
    @Value("${uploadImage.serverUrlPrefix}")
    private String serverUrlPrefix;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private UserMapper userMapper;


    @Autowired
    private UserAddressMapper userAddressMapper;

    @Autowired
    private ProductOrderMapper productOrderMapper;

    @Autowired
    private OrderPaymentTransactionMapper orderPaymentTransactionMapper;

    @Override
    public int deleteById(Long id) {
        return orderMapper.deleteById(id);
    }

    @Override
    public int add(Order record) {
        return orderMapper.add(record);
    }

    @Override
    public Order findById(Long id) {
        return orderMapper.findById(id);
    }

    @Override
    public int update(Order record) {
        return orderMapper.update(record);
    }

    @Override
    public List<OrderBO> findAll() {
        return orderMapper.findAll();
    }


    /**
     * 查所有
     *
     * @return
     */
    @Override
    public List<OrderBO> findOrderByStatusId(Long status) {
        return orderMapper.findOrderByStatusId(status);
    }

    /**
     * 订单详情页
     *
     * @param orderId
     * @return
     */
    @Override
    public OrderDetailsBO findOrderId(Long orderId) {
        //查找订单
        Order order = orderMapper.findById(orderId);
        OrderDetailsBO orderDetailsBO = new OrderDetailsBO();
        DecimalFormat df = new DecimalFormat("###.##");
        if (order != null) {
            //查买家相关信息(BuyUserId)
            User user = userMapper.findById(order.getBuyerId());
            ProductOrderBO orderBO = productOrderMapper.findByOrderId(order.getId());
            if (orderBO == null) {
                return new OrderDetailsBO();
            }
            //查询付款押金
            AmountBO payment = orderPaymentTransactionMapper.findByOrderId(order.getId());
            //默认值
            if (payment == null) {
                BigDecimal bigDecimal = new BigDecimal(df.format(0));
                payment.setAmount((bigDecimal));
                payment.setCreateTime(new Date());
            }
            //组装成BO
            orderDetailsBO.setOrderId(order.getId());
            orderDetailsBO.setProductId(orderBO.getProductId());
            orderDetailsBO.setUserName(user.getName());
            orderDetailsBO.setUserPhone(user.getPhone());
            orderDetailsBO.setUserAddress("" + orderBO.getProvince() + " " + orderBO.getCity() + " " + orderBO.getAddress());
            orderDetailsBO.setProductName(orderBO.getProductName());
            orderDetailsBO.setMasterPicture(serverUrlPrefix + orderBO.getProductImage());
            orderDetailsBO.setProductTotalPrice(orderBO.getSoldPrice());
            orderDetailsBO.setFee(JSONArray.parse(order.getBuyerFee()));
            orderDetailsBO.setOrderTotalPrice(order.getPrice());
            orderDetailsBO.setActualPayment(payment.getAmount());
            orderDetailsBO.setCreateTime(order.getCreateTime());
            orderDetailsBO.setPaymentTime(payment.getCreateTime());
            //发货时间 TODO
            orderDetailsBO.setShipmentsTime(order.getCreateTime());
        }
        return orderDetailsBO;
    }

    /**
     * 用户销售单数
     *
     * @param userId
     * @return
     */
    @Override
    public Long findSalesQuantity(Long userId) {
        return orderMapper.findSalesQuantity(userId);
    }

    /**
     * 用户购买单数
     *
     * @param userId
     * @return
     */
    @Override
    public Long findPurchaseQuantity(Long userId) {
        return orderMapper.findPurchaseQuantity(userId);
    }
    /**
     * 查找买家出价买支付的押金
     *
     * @param buyerId
     * @return
     */
    @Override
    public AmountBO findBuyerPaysDeposit(Long buyerId) {
        return orderMapper.findBuyerPaysDeposit(buyerId);
    }

    @Override
    public AmountBO findSellerPaysDeposit(Long sellerId) {
        return orderMapper.findSellerPaysDeposit(sellerId);
    }
}
