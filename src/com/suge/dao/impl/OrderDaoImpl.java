package com.suge.dao.impl;

import com.suge.bean.Order;
import com.suge.dao.OrderDao;

import java.util.List;

/**
 * @author suu
 * @create 2020-12-13 21:14
 */
public class OrderDaoImpl extends BaseDao implements OrderDao {
    @Override
    public int saveOrder(Order order) {
        String sql = "insert into t_order(`order_id`,`create_time`,`price`,`status`,`user_id`) values(?,?,?,?,?)";
        return update(sql, order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());
    }

    @Override
    public List<Order> queryOrders() {

        String sql = "select `order_id` orderId , `create_time` createTime, `price` , `status` , `user_id`  userId from t_order";
        return queryForList(Order.class, sql);
    }

    @Override
    public List<Order> queryOrdersByUserId(int id) {
        String sql = "select `order_id` orderId , `create_time` createTime, `price` , `status` , `user_id`  userId from t_order where user_id = ?";
        List<Order> orders = queryForList(Order.class, sql, id);
        return orders;
    }

    @Override
    public void changeOrderStatus(String orderId, int status){
        String sql = "update t_order set status = ? where order_id = ?";
        update(sql,status, orderId);
    }
}
