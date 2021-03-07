package com.suge.test;


import com.suge.dao.CartItemDao;
import com.suge.dao.impl.CartItemDaoImpl;
import org.junit.Test;

import java.math.BigDecimal;

public class CartTest {

    @Test
    public void addItem() {
//        Cart cart = new Cart();

//        cart.addItem(new CartItem(1, "iPhone8", 1, new BigDecimal(1000),new BigDecimal(1000)));
//        cart.addItem(new CartItem(1, "iPhone8", 1, new BigDecimal(1000),new BigDecimal(1000)));
//        cart.addItem(new CartItem(2, "iphonex", 1, new BigDecimal(100),new BigDecimal(100)));

//        System.out.println(cart);

    }

    @Test
    public void deleteItem() {

//        Cart cart = new Cart();

//        cart.addItem(new CartItem(1, "iPhone8", 1, new BigDecimal(1000),new BigDecimal(1000)));
//        cart.addItem(new CartItem(1, "iPhone8", 1, new BigDecimal(1000),new BigDecimal(1000)));
//        cart.addItem(new CartItem(2, "iphonex", 1, new BigDecimal(100),new BigDecimal(100)));
//        cart.deleteItem(1);

//        System.out.println(cart);

    }

    @Test
    public void clear() {
//        Cart cart = new Cart();

//        cart.addItem(new CartItem(1, "iPhone8", 1, new BigDecimal(1000),new BigDecimal(1000)));
//        cart.addItem(new CartItem(1, "iPhone8", 1, new BigDecimal(1000),new BigDecimal(1000)));
//        cart.addItem(new CartItem(2, "iphonex", 1, new BigDecimal(100),new BigDecimal(100)));

//        cart.deleteItem(1);

//        cart.clear();

//        System.out.println(cart);
    }

    @Test
    public void updateCount() {

//        Cart cart = new Cart();

//        cart.addItem(new CartItem(1, "iPhone8", 1, new BigDecimal(1000),new BigDecimal(1000)));
//        cart.addItem(new CartItem(1, "iPhone8", 1, new BigDecimal(1000),new BigDecimal(1000)));
//        cart.addItem(new CartItem(2, "iphonex", 1, new BigDecimal(100),new BigDecimal(100)));

//        cart.deleteItem(1);

//        cart.clear();

//        cart.addItem(new CartItem(1, "xiaomi6", 1, new BigDecimal(1000),new BigDecimal(1000)));
//        cart.addItem(new CartItem(null, "xiaomi7", 1, new BigDecimal(1000),new BigDecimal(1000)));
//        cart.addItem(new CartItem(4, "xiaomi67", 1, new BigDecimal(1000),new BigDecimal(1000)));

//        cart.updateCount(1, 10);


//        System.out.println(cart);

    }
    @Test
    public void getTotalPrice() {
        CartItemDao cartItemDao = new CartItemDaoImpl();
        BigDecimal totalPrice = cartItemDao.getTotalPrice(27);
        System.out.println(totalPrice);
    }

}