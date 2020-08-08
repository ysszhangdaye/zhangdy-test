package com.zhangdy.flink;

import com.zhangdy.flink.entity.SensorReading;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.timestamps.BoundedOutOfOrdernessTimestampExtractor;
import org.apache.flink.streaming.api.windowing.time.Time;

public class WindowTest {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment environment = StreamExecutionEnvironment.getExecutionEnvironment();
        environment.setParallelism(1);
        // 设置事件时间
        environment.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
        environment.getConfig().setAutoWatermarkInterval(500);

        DataStreamSource<String> inputStream = environment.socketTextStream("localhost", 7777);

        SingleOutputStreamOperator<SensorReading> dataStream = inputStream.map((data) -> {
            String[] array = data.trim().split(",");
            return SensorReading.builder()
                    .id(array[0].trim())
                    .timestamp(Long.parseLong(array[1].trim()))
                    .temperature(Double.parseDouble(array[2].trim()))
                    .build();

        }).assignTimestampsAndWatermarks(new BoundedOutOfOrdernessTimestampExtractor<SensorReading>(Time.seconds(1)) {
            @Override
            public long extractTimestamp(SensorReading o) {
                return o.getTimestamp() * 1000;
            }
        }).keyBy("id")
                .timeWindow(Time.seconds(10), Time.seconds(3))
                .reduce((result, data) ->
                        SensorReading.builder()
                                .id(result.getId())
                                .temperature(Double.max(result.getTemperature(), data.getTemperature()))
                                .build()
                );


        dataStream.print();

        environment.execute("window test");

    }

}
