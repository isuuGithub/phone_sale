package com.suge.dao;

import com.suge.bean.Order;

import java.util.List;

/**
 * @author suu
 * @create 2020-12-13 21:03
 */
public interface OrderDao {
    /**
     * 保存订单到数据库
     * @param order
     * @return
     */
    public int saveOrder(Order order);

    /**
     * 查看所有订单（管理员）
     * @return
     */
    public List<Order> queryOrders();

    /**
     * 修改订单状态
     * @param orderId
     * @param status 0未发货，1已发货，2表示已签收
     */
    public void changeOrderStatus(String orderId, int status);

    /**
     * 根据用户id查找相应的订单
     * @param id
     * @return
     */
    public List<Order> queryOrdersByUserId(int id);
}
