package com.zhangdy.test.spot.matchrecord;

import com.google.common.collect.Lists;
import com.zhangdy.test.annotation.Column;
import com.zhangdy.test.annotation.ID;
import com.zhangdy.test.annotation.TableName;
import com.zhangdy.util.GenerateSqlUtil;
import lombok.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class TestMatchRecord {


    public static void main(String[] args) {
        test("D:\\match_history_2020623");
    }

    public static void test(String path) {
        List<File> files = Lists.newArrayList();
        try {
            List<String> sqlList = Lists.newArrayList();
            File file = new File(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String lineTxt = null;
            String regex = "\t";
            int status = 0;
            Date createdOn = new Date();
            int version = 0;
            while ((lineTxt = br.readLine()) != null) {
                String[] array = lineTxt.split(regex);
                long id = Long.parseLong(array[0]);
                long price = Long.parseLong(array[7]);
                long qty = Long.parseLong(array[4]);
                BigDecimal marketBuyCancelQuantity = BigDecimal.ZERO;
                long matchId = Long.parseLong(array[1]);
                long time = Long.parseLong(array[10]);
                long takerOrderId = Long.parseLong(array[2]);
                long makerOrderId = Long.parseLong(array[3]);
                long instrumentId = Long.parseLong(array[8]);

                MatchRecord matchRecord = MatchRecord.builder()
                        .id(id)
                        .price(price)
                        .qty(qty)
                        .marketBuyCancelQuantity(marketBuyCancelQuantity)
                        .matchId(matchId)
                        .time(time)
                        .takerOrderId(takerOrderId)
                        .makerOrderId(makerOrderId)
                        .instrumentId(instrumentId)
                        .status(status)
                        .createdOn(createdOn)
                        .version(version)
                        .build();
                System.out.println(GenerateSqlUtil.generateInsertSql(matchRecord));
            }
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @TableName("coin_match_record")
    public static class MatchRecord {
        @ID @Column(format = "%s")
        private Long id;
        @Column(format = "%s")
        private Long price;
        @Column(format = "%s")
        private Long qty;
        @Column(format = "%s", value = "market_buy_cancel_quantity")
        private BigDecimal marketBuyCancelQuantity;
        @Column(format = "%s", value = "match_id")
        private Long matchId;
        @Column(format = "%s")
        private Long time;
        @Column(format = "%s", value = "taker_order_id")
        private Long takerOrderId;
        @Column(format = "%s", value = "maker_order_id")
        private Long makerOrderId;
        @Column(format = "%s")
        private Integer status;
        @Column(format = "%s", value = "instrument_id")
        private Long instrumentId;
        @Column(value = "created_on", format = "'%tF %tT'")
        private Date createdOn;
        @Column(format = "%s")
        private Integer version;
    }
}
