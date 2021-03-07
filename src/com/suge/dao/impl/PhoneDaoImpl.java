package com.suge.dao.impl;

import com.suge.bean.Phone;
import com.suge.dao.PhoneDao;


import java.util.List;

/**
 * @author suu
 * @create 2020-12-07 21:59
 */
public class PhoneDaoImpl extends BaseDao implements PhoneDao {

    @Override
    public int addPhone(Phone phone) {
        String sql = "insert into t_phone(`name`,`brand`,`price`,`sales`,`stock`,`img_path`)" +
                        "values(?,?,?,?,?,?)";
        return update(sql, phone.getName(), phone.getBrand(), phone.getPrice(), phone.getSales(), phone.getStock(),phone.getImgPath()+phone.getName()+".jpg");
    }

    @Override
    public int deletePhoneById(Integer id) {
        String sql = "delete from t_phone where id = ?";
        return update(sql, id);
    }

    @Override
    public int updatePhone(Phone phone) {
        String sql = "update t_phone set `name`=?,`brand`=?,`price`=?,`sales`=?,`stock`=?,`img_path`=? where id = ?";
        return update(sql,phone.getName(),phone.getBrand(), phone.getPrice(), phone.getSales(), phone.getStock(), phone.getImgPath(),phone.getId());

    }

    @Override
    public Phone queryPhoneById(Integer id) {
        String sql = "select `id` , `name` , `brand` , `price` , `sales` , `stock` , `img_path` imgPath from t_phone where id = ?";
        return queryForOne(Phone.class,sql, id);
    }

    //该查询用于list展示，使用分页功能后不用list
    @Override
    public List<Phone> queryPhones() {
        String sql = "select `id` , `name` , `brand` , `price` , `sales` , `stock` , `img_path` imgPath from t_phone";
        return queryForList(Phone.class, sql);
    }

    @Override
    public Integer queryForItemTotalCount() {

        String sql = "select count(*) from t_phone";

        //类型转换
        Object value = queryForSingleValue(sql);
        Number count = (Number) value;
        //类型转换
        return count.intValue();
    }

    @Override
    public List<Phone> queryForPageItems(int begin, int pageSize) {

        String sql = "select `id` , `name` , `brand` , `price` , `sales` , `stock` , `img_path` imgPath from t_phone limit ?,?";
        return queryForList(Phone.class,sql, begin, pageSize);
    }

    @Override
    public Integer queryForItemTotalCountByKey(String key) {
        String sql = "select count(*) from t_phone where name like ?";
        Number count = (Number) queryForSingleValue(sql,"%"+key+"%");
        return count.intValue();
    }

    @Override
    public List<Phone> queryForPageItemsByKey(int begin, int pageSize, String key) {
        //按照销量降序排列
        String sql = "select `id`,`name`,`brand`,`price`,`sales`,`stock`,`img_path` imgPath from t_phone where name like ? order by sales DESC limit ?,?";
        return queryForList(Phone.class, sql, "%"+key+"%",begin,pageSize);
    }
}
