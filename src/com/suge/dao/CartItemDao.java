package com.suge.dao;

import com.suge.bean.CartItem;
import com.suge.bean.OrderItem;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author suu
 * @create 2020-12-15 21:45
 */
public interface CartItemDao {

    /**
     * 添加商品项
     *cartItem对象自带useid
     * @param cartItem
     */
    public void addItem(CartItem cartItem);

    /**
     * 根据商品号删除某人的某个购物车中的项
     *
     * @param id
     * @param userId
     */
    public void deleteItem(Integer id ,Integer userId);

    /**
     * 清空某人的购物车
     * @param userId
     */
    public void clearByUserId(Integer userId);

    /**
     * 修改某人的某个商品数量
     * @param id
     * @param count
     * @param userId
     */
    public void updateCount(Integer id,Integer count,Integer userId);

    /**
     * 修改某人的某个商品总金额
     * @param id
     * @param totalPrice
     * @param userId
     */
    public void updateTotalPrice(Integer id, BigDecimal totalPrice,Integer userId);

    /**
     * 根据商品编号查找某人的购物车中的项
     * @param id
     * @param userId
     * @return
     */
    public CartItem queryCartItemByIdAndUserId(Integer id , Integer userId);

    /**
     * 查询购物车中商品项
     * @param userId
     * @return
     */
    public List<CartItem> queryCartItemsByUserId(Integer userId);


    /**
     * 获取某人的购物车商品的总数量
     * @param userId
     * @return
     */
    public Integer getTotalCount(Integer userId);

    /**
     * 获取某人购物车所有商品是总金额
     * @param userId
     * @return
     */
    public BigDecimal getTotalPrice(Integer userId);
}
