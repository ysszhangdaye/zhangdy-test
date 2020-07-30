package com.zhangdy.test.spot;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.google.common.collect.Lists;
import com.icc.match.api.MatchAssistApiService;
import com.zhangdy.util.DbBase;
import lombok.Getter;
import lombok.Setter;
import org.apache.flink.table.expressions.E;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class TestExistsOrders {

    private static final String URL      = "jdbc:mysql://58.33.9.130:5001/icc_trade_coin?characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai";
    private static final String NAME     = "root";
    private static final String PASSWORD = "5uHyFAgDvmfL9esV";

    public static void main(String[] args) {
        List<ExistsOrders> ordersList = getOrdersList();
        MatchAssistApiService matchAssistApiService = getApi("10.10.23.207:22837");
        for (ExistsOrders existsOrders : ordersList) {
            boolean b = matchAssistApiService.hasOrderAtHandicap(existsOrders.instrument, existsOrders.id);
            if (b) {
                System.out.println("############### " + existsOrders.id);
            }
        }
    }

    public static MatchAssistApiService getApi(String node){
        String url = "dubbo://".concat(node).concat("/").concat(MatchAssistApiService.class.getName());
        ApplicationConfig application = new ApplicationConfig();
        application.setName("icc-trade-coin-app");
        ReferenceConfig<MatchAssistApiService> reference = new ReferenceConfig();
        reference.setApplication(application);
        reference.setUrl(url);
        reference.setGroup("match");
        reference.setCheck(false);
        reference.setRetries(0);
        reference.setInterface(MatchAssistApiService.class);
        reference.setVersion("1.0.0");
        reference.setTimeout(10000);
        return  reference.get();
    }



    public static List<ExistsOrders> getOrdersList(){
        List<ExistsOrders>  result           = Lists.newArrayList();
        String              sql              = " select id, instrument_id as instrument from coin_orders where user_id=1041177958208552960 and created_on >'2020-07-02 00:00:00' and status<>0";
        Connection          connection       = DbBase.getConnection(URL, NAME, PASSWORD);
        Statement           statement        = null;
        ResultSet           resultSet        = null;
        int                 idIdx            = 1;
        int                 instrumentIdx    = 2;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                result.add(new ExistsOrders(resultSet.getLong(idIdx), resultSet.getLong(instrumentIdx)));
            }
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbBase.closeStatement(statement);
            DbBase.closeConn(connection);
        }
        return result;
    }


    @Getter
    @Setter
    public static class ExistsOrders {
        private long instrument;
        private long id;

        public ExistsOrders(long id, long instrument ){
            this.id = id;
            this.instrument = instrument;
        }
    }


}
