package com.meal.common.utils;

import com.meal.domain.OrderMaster;
import com.meal.dto.OrderDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by pengm on 2018/1/31.
 */
public class OrderMasterToOrderDTOConverterUtil {
    /**
     * 将单个OrderMaster转换成单个OrderDTO
     * @param orderMaster
     * @return
     */
    public static OrderDTO convert(OrderMaster orderMaster) {
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        return orderDTO;
    }

    /**
     * 将OrderMaster列表转换成OrderDTO列表
     * @param orderMasterList
     * @return
     */
    public static List<OrderDTO> convert(List<OrderMaster> orderMasterList) {
        return orderMasterList.stream().map(e ->
                convert(e)
        ).collect(Collectors.toList());
    }
}