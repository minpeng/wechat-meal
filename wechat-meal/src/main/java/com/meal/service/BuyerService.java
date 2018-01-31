package com.meal.service;

import com.meal.dto.OrderDTO;

/**
 * Created by pengm on 2018/1/31.
 */
public interface BuyerService {
    /**
     * 查询订单
     * @param openid
     * @param orderId
     * @return
     */
    OrderDTO findOneOrder(String openid, String orderId);

    /**
     * 取消订单
     * @param openid
     * @param orderId
     * @return
     */
    OrderDTO cancelOrder(String openid, String orderId);
}