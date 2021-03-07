package com.suge.dao;

import com.suge.bean.Phone;


import java.util.List;

/**
 * @author suu
 * @create 2020-12-07 21:53
 */
public interface PhoneDao {

    /**
     * 添加商品
     * @param phone
     * @return
     */
    public int addPhone(Phone phone);

    /**
     * 按照id删除商品
     * @param id
     * @return
     */
    public int deletePhoneById(Integer id);

    /**
     * 更新商品信息
     * @param phone
     * @return
     */
    public int updatePhone(Phone phone);

    /**
     * 按照id查找商品
     * @param id
     * @return
     */
    public Phone queryPhoneById(Integer id);

    /**
     * 查找所有商品
     * @return
     */
    public List<Phone> queryPhones();

    /**
     * 查询总商品数
     * @return
     */
    Integer queryForItemTotalCount();

    /**
     * 查询当前页面数据
     * @param begin 开始查询编号
     * @param pageSize 当前页面显示项目的数量
     * @return
     */
    List<Phone> queryForPageItems(int begin, int pageSize);


    /**
     * 根据关键字查询商品 的总数量
     * @param key
     * @return
     */
    Integer queryForItemTotalCountByKey(String key);


    /**
     * 根据关键字查询商品 的当前页面数据
     * @param begin
     * @param pageSize
     * @param key
     * @return
     */
   List<Phone> queryForPageItemsByKey(int begin, int pageSize, String key);

}
