package com.zhangdy.test.mysql;

import com.zhangdy.util.DbBase;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class TestLock2 {


    private static final String URL         = "jdbc:mysql://localhost:3306/test_sharding_01?characterEncoding=utf-8&useSSL=false";
    private static final String USER_NAME   = "root";
    private static final String PASSWORD    = "root";

    public static void main(String[] args) {
        remove();
    }

    public static void remove(){
        Connection connection = DbBase.getConnection(URL, USER_NAME, PASSWORD);
//        String delSql  = "DELETE FROM test_demo WHERE biz_id=?";
        String delSql  = "update test_demo set balance=200  WHERE biz_id=?";

        PreparedStatement preparedStatement = null;
        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(delSql);
            preparedStatement.setLong(1, 2L);
            preparedStatement.execute();

//            ThreadUtil.SET_SECONDS(50);

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            DbBase.closeStatement(preparedStatement);
            DbBase.closeConn(connection);

        }

    }


}
