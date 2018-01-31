package com.meal.service.impl;

import com.meal.dto.OrderDTO;
import com.meal.service.BuyerService;
import com.meal.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by pengm on 2018/1/31.
 */
@Service("buyerService")
public class BuyerServiceImpl implements BuyerService{
    private  final Logger logger = LoggerFactory.getLogger(BuyerServiceImpl.class);
    @Autowired
    private OrderService orderService;

    /**
     * 检查订单是否属于该用户
     * @param openid
     * @param orderId
     * @return OrderDTO
     */
    private OrderDTO checkOrderOwner(String openid, String orderId) {
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (orderDTO == null) {
            return null;
        }
        //判断是否是自己的订单
        if (!orderDTO.getBuyerOpenid().equalsIgnoreCase(openid)) {
            logger.error("【查询订单】订单的openid不一致. openid={}, orderDTO={}", openid, orderDTO);
            return null;
        }
        return orderDTO;
    }


    @Override
    public OrderDTO findOneOrder(String openid, String orderId) {
        return checkOrderOwner(openid, orderId);

    }

    @Override
    public OrderDTO cancelOrder(String openid, String orderId) {
        OrderDTO orderDTO = checkOrderOwner(openid, orderId);
        if (orderDTO == null) {
            logger.error("【取消订单】查不到改订单, orderId={}", orderId);
            return null;
        }
        return orderService.cancel(orderDTO);
    }
}