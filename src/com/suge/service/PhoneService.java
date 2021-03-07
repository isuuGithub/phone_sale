package com.suge.service;

import com.suge.bean.Page;
import com.suge.bean.Phone;

import java.util.List;

/**
 * @author suu
 * @create 2020-12-07 22:36
 */
public interface PhoneService {
    /**
     * 处理添加商品业务
     * @param phone
     */
    public void addPhone(Phone phone);

    /**
     * 处理删除商品业务
     * @param id
     */
    public void deletePhone(Integer id);

    /**
     * 处理更新商品业务
     * @param phone
     */
    public void updataPhone(Phone phone);

    /**
     * 处理按id查找商品业务
     * @param id
     * @return
     */
    public Phone queryPhoneById(Integer id);

    /**
     * 处理查找全部商品业务
     * @return
     */
    public List<Phone> queryPhone();

    /**
     * 处理分页业务
     * @param pageNo
     * @param pageSize
     * @return 返回一个分页对象
     */
    public Page<Phone> doPage(int pageNo,int pageSize );

    /**
     * 处理模糊搜索的分页功能
     * @param pageNo
     * @param pageSize
     * @param key
     * @return 返回一个分页对象
     */
    public Page<Phone> doPageByKey(int pageNo,int pageSize ,String key);
}
