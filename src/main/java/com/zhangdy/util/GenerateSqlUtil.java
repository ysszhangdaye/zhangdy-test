package com.zhangdy.util;

import com.zhangdy.test.annotation.Colume;
import com.zhangdy.test.annotation.Format;
import com.zhangdy.test.annotation.TableName;
import com.zhangdy.test.wallet.IccWalletConfig;
import org.apache.ibatis.jdbc.SQL;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class GenerateSqlUtil {

//    static final Map<String,String> valuesStrMap = new HashMap() {
//        {
//            put(BigDecimal.class.getSimpleName(), "%s");
//            put(String.class.getSimpleName(), "'%s'");
//            put(Long.class.getSimpleName(),  "%s");
//            put(Integer.class.getSimpleName(),  "%s");
//            put(Boolean.class.getSimpleName(),  "%s");
//        }
//    };


    public static String genSql(Object object) throws Exception{
        TableName tableName = object.getClass().getAnnotation(TableName.class);
        Field[] fields = object.getClass().getDeclaredFields();
        SQL sql = new SQL(){{
            INSERT_INTO(tableName.value());
            for (Field field : fields) {

                Colume colume = field.getAnnotation(Colume.class);
                Format format = field.getAnnotation(Format.class);
                String columeName = field.getName();
                if (colume != null && colume.value() == null && !"".equalsIgnoreCase(colume.value())) {
                    columeName = colume.value();
                }

                String formatStr = "'%s'";
                if (format != null && format.value() == null && !"".equalsIgnoreCase(format.value())) {
                    formatStr = format.value();
                }

                field.setAccessible(true);
                Object value = field.get(object);
                if (value == null) {
                    continue;
                }
                VALUES(columeName, genValues(value, formatStr));
            }

        }};
        return sql.toString() + ";";
    }
    public static String genValues(Object obj, String format){
        return String.format(format, obj);
    }

}
