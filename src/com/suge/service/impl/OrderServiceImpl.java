package com.suge.service.impl;

import com.suge.bean.*;
import com.suge.dao.CartItemDao;
import com.suge.dao.OrderDao;
import com.suge.dao.OrderItemDao;
import com.suge.dao.PhoneDao;
import com.suge.dao.impl.CartItemDaoImpl;
import com.suge.dao.impl.OrderDaoImpl;
import com.suge.dao.impl.OrderItemDaoImpl;
import com.suge.dao.impl.PhoneDaoImpl;
import com.suge.service.OrderService;

import java.util.Date;
import java.util.List;

/**
 * @author suu
 * @create 2020-12-13 21:35
 */
public class OrderServiceImpl implements OrderService {

    private CartItemDao cartItemDao = new CartItemDaoImpl();

    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();

    private PhoneDao phoneDao = new PhoneDaoImpl();


    @Override
    public String createOrder(Integer userId) {
        // 订单号===唯一性
        String orderId = System.currentTimeMillis()+""+userId;

        // 创建一个订单对象
        Order order = new Order(orderId,new Date(), cartItemDao.getTotalPrice(userId), 0,userId);

        // 保存订单
        orderDao.saveOrder(order);

        // 遍历购物车中每一个商品项转换成为订单项保存到数据库
        for (CartItem cartItem : cartItemDao.queryCartItemsByUserId(userId)){

            // 转换为每一个订单项
            OrderItem orderItem = new
                    OrderItem(null,cartItem.getName(),cartItem.getCount(),cartItem.getPrice(),cartItem.getTotalPrice(),
                    orderId);
            // 保存订单项到数据库
            orderItemDao.saveOrderItem(orderItem);

            // 更新库存和销量
            Phone phone = phoneDao.queryPhoneById(cartItem.getId());
            phone.setSales( phone.getSales() + cartItem.getCount() );
            phone.setStock( phone.getStock() - cartItem.getCount() );
            phoneDao.updatePhone(phone);

        }

        // 清空购物车
        cartItemDao.clearByUserId(userId);

        return orderId;
    }

    @Override
    public List<Order> showMyOrders(int userId) {

        List<Order> orders = orderDao.queryOrdersByUserId(userId);
        return orders;
    }

    @Override
    public List<OrderItem> showOrderDetail(String orderId) {
        return orderItemDao.queryOrderItemByOrderId(orderId);
    }

    @Override
    public void receivedOrder(String orderId){
        orderDao.changeOrderStatus(orderId, 2);
    }

    @Override
    public List<Order> showAllOrders() {
        return orderDao.queryOrders();
    }

    @Override
    public void sendOrder(String orderId) {
        orderDao.changeOrderStatus(orderId, 1);
    }
}
