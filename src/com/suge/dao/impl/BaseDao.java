package com.suge.dao.impl;

import com.suge.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 定义一个用来被继承的对数据库进行基本操作的Dao工具类
 *
 * @author suu
 * @create 2020-12-01 19:25
 */
public abstract class BaseDao {

    /**
     * 使用 DbUtils 的QueryRunner对象操作数据库
     */
    private QueryRunner queryRunner = new QueryRunner();


    /**
     * update() 方法用来执行：Insert\Update\Delete 语句
     *
     * @return 如果返回-1,说明执行失败<br/>返回其他表示影响的行数
     */
    public int update(String sql,Object...args){
        Connection connection = JdbcUtils.getConnection();

        try {
            return queryRunner.update(connection,sql,args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {

            JdbcUtils.close(connection);
        }

        return -1;
    }

    /**
     * 查询返回一个 javaBean 的 sql 语句
     *
     * @param type 返回的对象类型(供创建handler的空参构造器使用)
     * @param sql 执行的 sql 语句
     * @param args sql 对应的参数值
     * @param <T> 返回的类型的泛型（实际调用时 T 应与type类型一致 ）
     * @return 返回一个bean对象
     */
    public <T> T queryForOne(Class<T> type,String sql, Object...args){
        Connection conn = JdbcUtils.getConnection();

        try {
            //BeanHandler处理器：将结果集中的第一行数据封装到一个对应的JavaBean实例中。
            return queryRunner.query(conn, sql, new BeanHandler<T>(type), args);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtils.close(conn);
        }

        return null;
    }

    /**
     * 查询返回多个 javaBean 的方法
     *
     * @param type 返回的对象类型
     * @param sql 执行的 sql 语句
     * @param args sql 对应的参数值
     * @param <T> 返回的类型的泛型
     * @return 返回一个链表
     */
    public <T> List<T> queryForList(Class<T> type , String sql , Object...args){
        Connection conn = JdbcUtils.getConnection();

        try {

            //BeanListHandler：将结果集中的每一行数据都封装到一个对应的JavaBean实例中，存放到List里。
            return queryRunner.query(conn, sql, new BeanListHandler<T>(type), args);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtils.close(conn);
        }

        return null;
    }


    /**
     * 获取一个但一值得方法，专门用来执行像 select count(*)...这样的sql语句
     * @param sql 执行的 sql 语句
     * @param args sql 对应的参数值
     * @return 返回一个值
     */
    public Object queryForSingleValue(String sql, Object...args){
        Connection conn = JdbcUtils.getConnection();

        try {
            //ScalarHandler：查询单个值对象
            return queryRunner.query(conn, sql, new ScalarHandler<>(), args);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtils.close(conn);
        }

        return  null;
    }

}
