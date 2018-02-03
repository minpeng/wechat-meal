package com.meal.controller;

import com.meal.common.enums.ResultEnum;
import com.meal.common.utils.OrderFormToOrderDTOConverterUtil;
import com.meal.common.utils.ResultVOUtil;
import com.meal.dto.OrderDTO;
import com.meal.from.OrderForm;
import com.meal.service.BuyerService;
import com.meal.service.OrderService;
import com.meal.vo.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * 买家订单接口
 * Created by pengm on 2018/1/31.
 */
@RestController
@RequestMapping("/buyer/order")
public class BuyerOrderController {

    private  final Logger logger = LoggerFactory.getLogger(BuyerOrderController.class);
    @Autowired
    private OrderService orderService;

    @Autowired
    private BuyerService buyerService;

    /**
     * 买家创建订单
     *
     * @param orderForm
     * @param bindingResult
     * @return
     */
    @PostMapping("/create")
    public ResultVO createOrder(@Valid OrderForm orderForm,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.error("创建订单--参数不正确，orderForm={}", orderForm);
            return ResultVOUtil.error(ResultEnum.PARAM_ERROR.getMessage());
//            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
//                    bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDTO orderDTO = OrderFormToOrderDTOConverterUtil.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            logger.error("【创建订单】购物车不能为空");
            return ResultVOUtil.error(ResultEnum.CART_EMPTY.getMessage());
            //throw new SellException(ResultEnum.CART_EMPTY);
        }

        OrderDTO createResult = orderService.create(orderDTO);

        Map<String, String> map = new HashMap<>();
        map.put("orderId", createResult.getOrderId());

        return ResultVOUtil.success(map);
    }


    /**
     * 获取订单列表
     *
     * @return
     */
    @GetMapping("/list")
    public ResultVO getOrderList(@RequestParam String openid,
                                 @RequestParam(value = "page", defaultValue = "0") Integer page,
                                 @RequestParam(value = "size", defaultValue = "10") Integer size) {
        if (StringUtils.isEmpty(openid)) {
            logger.error("【查询订单列表】openid为空,openid={}", openid);
            //throw new SellException(ResultEnum.PARAM_ERROR);
            return ResultVOUtil.error(ResultEnum.PARAM_ERROR.getMessage());
        }
        PageRequest request = new PageRequest(page, size);
        Page<OrderDTO> orderDTOPage = orderService.findList(openid, request);
        return ResultVOUtil.success(orderDTOPage.getContent());
    }


    /**
     * 获取指定订单详情
     *
     * @param openid
     * @param orderId
     * @return
     */
    @GetMapping("/detail")
    public ResultVO getOrderDetail(@RequestParam("openid") String openid,
                                   @RequestParam("orderId") String orderId) {
        OrderDTO orderDTO = buyerService.findOneOrder(openid, orderId);
        return ResultVOUtil.success(orderDTO);
    }

    /**
     * 取消指定订单id
     *
     * @param openid
     * @param orderId
     * @return
     */
    @PostMapping("/cancel")
    public ResultVO cancel(@RequestParam("openid") String openid,
                           @RequestParam("orderId") String orderId) {
        buyerService.cancelOrder(openid, orderId);
        return ResultVOUtil.success();
    }

}