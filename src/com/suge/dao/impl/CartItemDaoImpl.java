package com.suge.dao.impl;



import com.suge.bean.CartItem;
import com.suge.dao.CartItemDao;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author suu
 * @create 2020-12-15 21:54
 */
public class CartItemDaoImpl extends  BaseDao implements CartItemDao {
    @Override
    public void addItem(CartItem cartItem) {
        String sql = "insert into t_cart_item(`id`,`name`,`count`,`price`,`total_price`,`user_id`) values(?,?,?,?,?,?)";
        update(sql, cartItem.getId(),cartItem.getName(), cartItem.getCount(), cartItem.getPrice(), cartItem.getTotalPrice(),cartItem.getUserId());
    }

    @Override
    public void deleteItem(Integer id , Integer userId) {
        String sql = "delete from t_cart_item where id = ? and user_id = ?";
        update(sql, id ,userId);
    }

    @Override
    public void clearByUserId(Integer userId) {
        String sql = "delete from t_cart_item where user_id= ?";
        update(sql, userId);
    }

    @Override
    public void updateCount(Integer id, Integer count ,Integer userId) {
        String sql = "update t_cart_item set count = ? where id = ? and user_id = ?";
        update(sql,count , id ,userId);
    }

    @Override
    public void updateTotalPrice(Integer id, BigDecimal totalPrice , Integer userId) {
        String sql = "update t_cart_item set total_price = ? where id = ? and user_id = ?";
        update(sql,totalPrice , id, userId);
    }

    @Override
    public List<CartItem> queryCartItemsByUserId(Integer userId) {

        String sql = "select `id`,`name` ,`count`, `price` , `total_price` totalPrice, `user_id`  userId from t_cart_item where user_id = ?";
        return queryForList(CartItem.class, sql ,userId);

    }

    @Override
    public Integer getTotalCount(Integer userId) {
        String sql = "select count(*) from t_cart_item where user_id = ?";
        //类型转换
        Object value = queryForSingleValue(sql, userId);
        Number count = (Number) value;
        //类型转换
        return count.intValue();
    }

    @Override
    public BigDecimal getTotalPrice(Integer userId) {
        String sql = "select sum(total_price) from t_cart_item where user_id = ?";
        return (BigDecimal) queryForSingleValue(sql , userId);
    }

    @Override
    public CartItem queryCartItemByIdAndUserId(Integer id , Integer userId) {
        String sql = "select `id`,`name` ,`count`, `price` , `total_price` totalPrice, `user_id`  userId from t_cart_item where id = ? and user_id = ?";
        return queryForOne(CartItem.class, sql, id , userId);
    }
}
