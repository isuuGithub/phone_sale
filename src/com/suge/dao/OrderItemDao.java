package com.suge.dao;

import com.suge.bean.Order;
import com.suge.bean.OrderItem;

import java.util.List;

/**
 * @author suu
 * @create 2020-12-13 21:04
 */
public interface OrderItemDao {
    /**
     * 保存订单项
     * @param orderItem
     * @return
     */
    public int saveOrderItem(OrderItem orderItem);

    /**
     * 根据订单id查找相应的订单项
     * @return
     */
    public List<OrderItem> queryOrderItemByOrderId(String orderId);
}
