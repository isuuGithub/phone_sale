package com.suge.service.impl;

import com.suge.bean.Page;
import com.suge.bean.Phone;
import com.suge.dao.PhoneDao;
import com.suge.dao.impl.PhoneDaoImpl;
import com.suge.service.PhoneService;

import java.util.List;

/**
 * @author suu
 * @create 2020-12-07 22:44
 */
public class PhoneServiceImpl implements PhoneService {

    PhoneDao phoneDao = new PhoneDaoImpl();

    @Override
    public void addPhone(Phone phone) {
        phoneDao.addPhone(phone);

    }

    @Override
    public void deletePhone(Integer id) {
        phoneDao.deletePhoneById(id);
    }

    @Override
    public void updataPhone(Phone phone) {
        phoneDao.updatePhone(phone);
    }

    @Override
    public Phone queryPhoneById(Integer id) {
        return phoneDao.queryPhoneById(id);
    }

    @Override
    public List<Phone> queryPhone() {
        return phoneDao.queryPhones();
    }

    @Override
    public Page<Phone> doPage(int pageNo, int pageSize) {

        Page<Phone> page = new Page<>();
        page.setPageSize(pageSize);

        // 求总记录数
        Integer itemTotalCount = phoneDao.queryForItemTotalCount();
        page.setItemTotalCount(itemTotalCount);

        // 求总页码
        Integer pageTotal = itemTotalCount / pageSize;
        if (itemTotalCount % pageSize > 0){
            pageTotal++;
        }
        // 设置总页码
        page.setPageTotal(pageTotal);

        page.setPageNo(pageNo);


        // 求当前页数据的开始索引
        int begin = (page.getPageNo() - 1) * pageSize;
        // 求当前页数据
        List<Phone> items = phoneDao.queryForPageItems(begin, pageSize);
        // 设置当前页数据
        page.setItems(items);

        return page;
    }

    @Override
    public Page<Phone> doPageByKey(int pageNo, int pageSize , String key) {
        Page<Phone> page = new Page<>();
        page.setPageSize(pageSize);

        // 求总记录数
        Integer itemTotalCount = phoneDao.queryForItemTotalCountByKey(key);
        page.setItemTotalCount(itemTotalCount);

        // 求总页码
        Integer pageTotal = itemTotalCount / pageSize;
        if (itemTotalCount % pageSize > 0){
            pageTotal++;
        }
        // 设置总页码
        page.setPageTotal(pageTotal);

        page.setPageNo(pageNo);


        // 求当前页数据的开始索引
        int begin = (page.getPageNo() - 1) * pageSize;
        // 求当前页数据
        List<Phone> items = phoneDao.queryForPageItemsByKey(begin, pageSize, key);
        // 设置当前页数据
        page.setItems(items);

        return page;
    }


}
