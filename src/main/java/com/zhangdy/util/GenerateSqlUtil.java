package com.zhangdy.util;

import com.zhangdy.test.annotation.TableName;
import com.zhangdy.test.wallet.IccWalletConfig;
import org.apache.ibatis.jdbc.SQL;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class GenerateSqlUtil {

    static final Map<String,String> valuesStrMap = new HashMap() {
        {
            put(BigDecimal.class.getSimpleName(), "%s");
            put(String.class.getSimpleName(), "'%s'");
            put(Long.class.getSimpleName(),  "%s");
            put(Integer.class.getSimpleName(),  "%s");
            put(Boolean.class.getSimpleName(),  "%s");
        }
    };


    public static String genSql(Object object) throws Exception{
        TableName tableName = object.getClass().getAnnotation(TableName.class);
        Field[] fields = object.getClass().getDeclaredFields();
        SQL sql = new SQL(){{
            INSERT_INTO(tableName.value());
            for (Field field : fields) {
                field.setAccessible(true);
                Object value = field.get(object);
                if (value == null) {
                    continue;
                }
                VALUES(field.getName(), genValues(value));
            }

        }};
        return sql.toString() + ";";
    }
    public static String genValues(Object obj){
        String format = valuesStrMap.get(obj.getClass().getSimpleName());
        return String.format(format, obj);
    }

}
