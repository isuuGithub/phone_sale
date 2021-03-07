package com.suge.test;



import com.suge.bean.Order;
import com.suge.bean.OrderItem;
import com.suge.dao.OrderDao;
import com.suge.dao.OrderItemDao;
import com.suge.dao.impl.OrderDaoImpl;
import com.suge.dao.impl.OrderItemDaoImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class OrderDaoTest {

    @Test
    public void saveOrder() {

        OrderDao orderDao = new OrderDaoImpl();

        orderDao.saveOrder(new Order("1234567891",new Date(),new BigDecimal(100),0, 1));

    }
    @Test
    public void queryForListByUserId() {

        OrderDao orderDao = new OrderDaoImpl();

        List<Order> orders = orderDao.queryOrdersByUserId(1);
        for (Order order:orders){
            System.out.println(order);
        }


    }






    @Test
    public void saveOrderItem() {
        OrderItemDao orderItemDao = new OrderItemDaoImpl();
        orderItemDao.saveOrderItem(new OrderItem(null,"小米4", 1,new BigDecimal(100),new
                BigDecimal(100),"1234567891"));
        orderItemDao.saveOrderItem(new OrderItem(null,"小米8", 2,new
                BigDecimal(100),new BigDecimal(200),"1234567891"));
        orderItemDao.saveOrderItem(new OrderItem(null,"红米9", 1,new BigDecimal(100),new
                BigDecimal(100),"1234567891"));
    }


    @Test
    public void queryOrderItemByOrderId() {

        OrderItemDao orderItemDao = new OrderItemDaoImpl();

        List<OrderItem> orders = orderItemDao.queryOrderItemByOrderId("1234567891");
        for (OrderItem orderItem:orders){
            System.out.println(orderItem);
        }


    }
}