package com.ecit.service;

import com.ecit.mapper.OrderMapper;
import com.ecit.model.Order;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

@Service
public class OrderServiceImpl implements IOrderService {

    @Reference
    private IAccountService accountService;
    @Autowired
    private OrderMapper orderMapper;

    public void create(String userId, String commodityCode, Integer count) {
        BigDecimal orderMoney = new BigDecimal(count).multiply(new BigDecimal(5));
        Order order = new Order();
        order.setUserId(userId);
        order.setCommodityCode(commodityCode);
        order.setCount(count);
        order.setMoney(orderMoney);

        orderMapper.insert(order);

        accountService.debit(userId, orderMoney);

    }
}
