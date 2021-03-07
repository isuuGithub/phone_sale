package com.suge.test;

import com.suge.utils.JdbcUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLOutput;

public class jabcUtilsTest {

    @Test
    public void testJdbcUtils(){
        for (int i = 0; i < 100; i++) {
            Connection connection = JdbcUtils.getConnection();
            System.out.println(connection);
            JdbcUtils.close(connection);
        }
    }



}

