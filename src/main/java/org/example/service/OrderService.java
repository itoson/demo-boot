package org.example.service;

import org.example.entity.Order;
import org.example.mapper.OrderMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OrderService {
    @Resource
    private OrderMapper orderMapper;

    public void createOrder(Order order) {
        orderMapper.insert(order);
    }
}
