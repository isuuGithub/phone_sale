package com.suge.service.impl;

import com.suge.bean.CartItem;
import com.suge.dao.CartItemDao;
import com.suge.dao.impl.CartItemDaoImpl;
import com.suge.service.CartService;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author suu
 * @create 2020-12-15 22:27
 */
public class CartServiceImpl implements CartService {

    CartItemDao cartItemDao = new CartItemDaoImpl();

    @Override
    public void addItem(CartItem cartItem) {
        cartItemDao.addItem(cartItem);
    }

    @Override
    public void deleteItem(Integer id ,Integer userId) {

        cartItemDao.deleteItem(id , userId);
    }

    @Override
    public void clearByUserId(Integer userId) {
        cartItemDao.clearByUserId(userId);
    }

    @Override
    public void updateTotalPrice(Integer id, BigDecimal totalPrice , Integer userId) {
        cartItemDao.updateTotalPrice(id , totalPrice ,userId);
    }

    @Override
    public void updateCount(Integer id, Integer count ,Integer userId) {
        cartItemDao.updateCount(id, count , userId);
    }

    @Override
    public CartItem queryCartItemByIdAndUserId(Integer id , Integer userId) {
        return cartItemDao.queryCartItemByIdAndUserId(id , userId);
    }

    @Override
    public List<CartItem> showCartItems(Integer userId) {
        return cartItemDao.queryCartItemsByUserId(userId);
    }

    @Override
    public Integer getTotalCount(Integer userId) {
        return cartItemDao.getTotalCount(userId);
    }

    @Override
    public BigDecimal getTotalPrice(Integer userId) {
        return cartItemDao.getTotalPrice(userId);
    }
}
