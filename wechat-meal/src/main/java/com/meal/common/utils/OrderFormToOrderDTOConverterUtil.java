package com.meal.common.utils;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.meal.common.enums.ResultEnum;
import com.meal.domain.OrderDetail;
import com.meal.dto.OrderDTO;
import com.meal.exception.MyException;
import com.meal.from.OrderForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.ArrayList;
import java.util.List;
/**
 * Created by pengm on 2018/1/31.
 */
public class OrderFormToOrderDTOConverterUtil {
    private static final Logger logger = LoggerFactory.getLogger(OrderFormToOrderDTOConverterUtil.class);

    public static OrderDTO convert(OrderForm orderForm) {
        Gson gson = new Gson();
        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());

        List<OrderDetail> orderDetailList = new ArrayList<>();
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>() {
                    }.getType());
        } catch (Exception e) {
            logger.error("【对象转换】错误, string={}", orderForm.getItems());
            throw new MyException(ResultEnum.PARAM_ERROR)
        }
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }
}