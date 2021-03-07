package com.suge.test;


import com.suge.service.OrderService;
import com.suge.service.impl.OrderServiceImpl;
import org.junit.Test;

public class OrderServiceTest {

    @Test
    public void createOrder() {

//        Cart cart = new Cart();

//        cart.addItem(new CartItem(1, "小米6", 1, new BigDecimal(1000),new BigDecimal(1000)));
//        cart.addItem(new CartItem(1, "小米6", 1, new BigDecimal(1000),new BigDecimal(1000)));
//        cart.addItem(new CartItem(2, "iphone8", 1, new BigDecimal(100),new BigDecimal(100)));

        OrderService orderService = new OrderServiceImpl();

        System.out.println( "订单号是：" + orderService.createOrder(1) );

    }
}