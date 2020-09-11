package com.zhangdy.flink;

import lombok.*;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

import java.math.BigDecimal;

public class KlineTest {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStream<String> stringDataStream = env.readTextFile("/Users/zhangdaye/workspace/github/zhangdy-test/src/main/java/com/zhangdy/flink/csv/trade.txt");

        SingleOutputStreamOperator<Kline> klineSingleOutputStreamOperator = stringDataStream.flatMap(new FlatMapFunction<String, Kline>() {

            @Override
            public void flatMap(String value, Collector<Kline> out) throws Exception {
                for (String word : value.split("\n")) {
                    String[] array = word.split(",");
                    out.collect(Kline.create(Long.parseLong(array[0]), new BigDecimal(array[1]), new BigDecimal(array[2])));
                }
            }
        });


        // 打印结果
        klineSingleOutputStreamOperator.print().setParallelism(1);

        env.execute("Socket Window WordCount");

    }


    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Getter
    @Setter
    public static class Kline {
        private long timestamp;
        private BigDecimal high;
        private BigDecimal low;
        private BigDecimal open;
        private BigDecimal close;
        private BigDecimal volume;
        public static Kline create(long timestamp, BigDecimal price, BigDecimal volume){

            return Kline.builder()
                    .timestamp(timestamp)
                    .open(price)
                    .low(price)
                    .high(price)
                    .close(price)
                    .volume(volume)
                    .build();


        }

        @Override
        public String toString() {
            return "Kline{" +
                    "timestamp=" + timestamp +
                    ", high=" + high +
                    ", low=" + low +
                    ", open=" + open +
                    ", close=" + close +
                    ", volume=" + volume +
                    '}';
        }
    }

}
