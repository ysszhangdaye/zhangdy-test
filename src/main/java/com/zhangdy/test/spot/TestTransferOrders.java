package com.zhangdy.test.spot;

import com.zhangdy.util.DbBase;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestTransferOrders extends Thread{

    public static void main(String[] args) {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1, new BasicThreadFactory.Builder().namingPattern("transfer-orders-Execute-Pool-%d--").build());
        scheduledThreadPoolExecutor.scheduleWithFixedDelay(new TestTransferOrders(), 0, 1, TimeUnit.MINUTES);
    }

    @Override
    public void run() {
        String url = "jdbc:mysql://10.10.23.99:3306/icc_trade_coin?characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai";
        String userName = "dssj";
        String password = "dssj@DSSJ123";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DbBase.getConnection(url, userName, password);
            preparedStatement = connection.prepareStatement("CALL transfer_orders (1006068752134238208, 2000)");
            preparedStatement.execute();
            System.out.println(Thread.currentThread().getName() + "执行数据迁移");
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            DbBase.closeConn(connection);
        }
    }
}
