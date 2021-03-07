package com.suge.service;

import com.suge.bean.Order;
import com.suge.bean.OrderItem;

import java.util.List;

/**
 * @author suu
 * @create 2020-12-13 21:04
 */
public interface OrderService {
    /**
     * 处理 给某个user生成一张订单 业务
     * @param userId
     * @return
     */
    public String createOrder(Integer userId);

    /**
     * 查看我的订单
     * @param userId
     * @return
     */
    public List<Order> showMyOrders(int userId);

    /**
     * 查看订单详情
     * @param orderId
     * @return
     */
    List<OrderItem> showOrderDetail(String orderId);

    /**
     * 确认收货
     * @param orderId
     */
    void receivedOrder(String orderId);

    /**
     * 管理员查看所有订单
     * @return
     */
    public List<Order> showAllOrders();

    /**
     * 发货
     * @param orderId
     */
    public void sendOrder(String orderId);



}
