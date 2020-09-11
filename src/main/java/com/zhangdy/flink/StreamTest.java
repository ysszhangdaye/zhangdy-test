package com.zhangdy.flink;

import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.expressions.E;

public class StreamTest {

    public static void main(String[] args) throws Exception {

        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();


        DataStreamSource<String> dataStreamSource = env.socketTextStream("localhost", 9000);


        SingleOutputStreamOperator<Tuple2<String, Integer>> word = dataStreamSource.flatMap(new FirstTest1.LineSplitter())
                .keyBy(0)
                .sum(1);

        word.print().setParallelism(2);

        env.execute("test");

    }

}
