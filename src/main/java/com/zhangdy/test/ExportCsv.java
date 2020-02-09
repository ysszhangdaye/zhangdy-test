package com.zhangdy.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.google.common.collect.Lists;
import com.zhangdy.util.DateUtil;
import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * <Description> <br>
 *
 * @author 001<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2019/06/24 15:54 <br>
 * @see com.zhangdy.test <br>
 */
public class ExportCsv {

    static ValueFilter valueFilter = new ValueFilter() {
        @Override
        public Object process(Object object, String name, Object value) {

            if (value != null && value instanceof BigDecimal) {
                return ((BigDecimal) value).stripTrailingZeros().toPlainString();
            }


            if ("repayDate".equalsIgnoreCase(name)) {
                try {
                    return DateUtil.getDateFormat(DateUtil.parseZiheDate((Long) value), DateUtil.DATE_FORMAT_TYPE_DATE_AND_TIME);
                } catch (Exception e) {
                }
            }
            return value;
        }
    };

    public static void main(String[] args) throws Exception {
        int s = 0;
        for (int a = 0; a < 10; a++) {

            for (int j = 0; j < 10; j++) {
                List<?> users = Lists.newArrayList();
                String u = "zhangsan";
                for (int i = 0; i < 22; i++) {

                }
                JSONArray docs = new JSONArray(JSON.toJSONString(users, valueFilter,
                        SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullNumberAsZero));

                JSONObject var1 = docs.optJSONObject(0);

                String data;
                if (s == 0) {
                    data = CDL.toString(docs);
                } else {
                    JSONArray var2 = var1.names();
                    data = CDL.toString(var2, docs);
                }
                s++;
                export(data);

            }

        }


    }

    public static void export(String str) {
        FileOutputStream out = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;
        File file = new File("d://test1.csv");
        try {
            out = new FileOutputStream(file, true);
            osw = new OutputStreamWriter(out);
            bw = new BufferedWriter(osw);
            bw.append(str);

        } catch (Exception e) {
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                    bw = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (osw != null) {
                try {
                    osw.close();
                    osw = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                    out = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
