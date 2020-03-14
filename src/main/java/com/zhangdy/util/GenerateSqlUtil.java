package com.zhangdy.util;

import com.zhangdy.test.annotation.Column;
import com.zhangdy.test.annotation.AutoGenerateValue;
import com.zhangdy.test.annotation.TableName;
import com.zhangdy.test.enums.GenerateTypeEnum;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;

import java.lang.reflect.Field;
import java.util.UUID;

public class GenerateSqlUtil {

    public static String genSql(Object object) throws Exception{
        TableName tableName = object.getClass().getAnnotation(TableName.class);
        if (tableName == null || StringUtils.isBlank(tableName.value())) {
            throw new RuntimeException("表名不能为空！！！");
        }
        Field[] fields = object.getClass().getDeclaredFields();
        SQL sql = new SQL(){{
            INSERT_INTO(tableName.value());
            for (Field field : fields) {
                field.setAccessible(true);
                Object value = getValue(field, object);
                if (value == null) {
                    continue;
                }
                Column column = field.getAnnotation(Column.class);
                String columnName = field.getName();
                String formatStr = "'%s'";

                if (column != null) {
                    if (StringUtils.isNotBlank(column.value())) {
                        columnName = column.value();
                    }
                    formatStr = column.format();
                }
                VALUES(columnName, String.format(formatStr, value));
            }
        }};
        return sql.toString() + ";";
    }


    public static Object getValue(Field field, Object object){
        AutoGenerateValue generateId = field.getAnnotation(AutoGenerateValue.class);
        if (generateId != null) {
            GenerateTypeEnum type = generateId.value();
            if (GenerateTypeEnum.IDS.equals(type)) {
                return IDS.uniqueID();
            } else {
                return UUID.randomUUID().toString().replaceAll("-", "");
            }
        }
        try {
            return field.get(object);
        }catch (Exception e){
        }
        return null;
    }

}
