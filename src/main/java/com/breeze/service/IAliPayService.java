package com.breeze.service;

import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayTradePayModel;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.breeze.exception.PaymentException;
import com.breeze.pojo.bo.OrderPreviewBO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

/**
 * @author Created by wdnnccey
 * @date 2019-07-06 23:20
 * @Description: 支付封装
 */
public interface IAliPayService {

    String pay(HttpServletRequest request,
               HttpServletResponse response,
               Long userId,
               OrderPreviewBO orderPreviewBO,
               Integer transcationTypeId,
               AlipayClient client,
               BigDecimal biddingPrice,
               AlipayTradeWapPayRequest alipayRequest,
               AlipayTradePayModel model) throws PaymentException;

}
