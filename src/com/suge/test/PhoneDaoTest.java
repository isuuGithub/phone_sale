package com.suge.test;

import com.suge.bean.Phone;
import com.suge.dao.PhoneDao;
import com.suge.dao.impl.PhoneDaoImpl;
import org.junit.Test;


import java.math.BigDecimal;

/**
 * @author suu
 * @create 2020-12-07 22:15
 */
public class PhoneDaoTest {

    private PhoneDao phoneDao = new PhoneDaoImpl();
    @Test
    public void addPhone() {
        phoneDao.addPhone(new Phone(null,"oppoFindX2兰博基尼版","oppo",new BigDecimal(6999),100,10,null));
    }
    @Test
    public void deletePhoneById() {
        phoneDao.deletePhoneById(2);
    }
    @Test
    public void updatePhone() {
        phoneDao.updatePhone(new Phone(8,"oppoFindX2兰博基尼版","oppo",new BigDecimal(16999),1001,0,null));
    }
    @Test
    public void queryPhoneById() {
//        System.out.println( PhoneDao.queryPhoneById(21) );
    }
    @Test
    public void queryPhones() {
//        for (Phone queryPhone : PhoneDao.queryPhones()) {
//            System.out.println(queryPhone);
//        }
    }

    @Test
    public void queryForItemTotalCountByKey() {
//        String sql = "select count(*) from t_phone where id like '%?%'";
//        System.out.println(sql);
        System.out.println( phoneDao.queryForItemTotalCountByKey("米") );
    }
    @Test
    public void queryForPageItemsByKey() {

        for (Phone phone: phoneDao.queryForPageItemsByKey(0,40,"米")){
            System.out.println( phone );
        }


    }


}
