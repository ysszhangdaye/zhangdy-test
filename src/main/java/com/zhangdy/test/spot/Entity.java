package com.zhangdy.test.spot;

import lombok.*;
import org.apache.ibatis.jdbc.SQL;

import java.lang.reflect.Field;
import java.math.BigDecimal;

public class Entity {

    @Getter @Setter @AllArgsConstructor @Builder @NoArgsConstructor
    public static class Entrust{
        /** 金融工具编码*/
        private String code;
        /**  指令 */
        private String side;
        /** 订单类型 */
        private String type;
        /** 价格 */
        private BigDecimal price = BigDecimal.ZERO;
        /** 数量  */
        private BigDecimal qty = BigDecimal.ZERO;
        public String toFromStr(){
            Field[] fields = this.getClass().getDeclaredFields();
            String result = "";
            for (Field field : fields) {
                field.setAccessible(true);
                try {
                    Object o = field.get(this);
                    if ( o instanceof BigDecimal ) {
                        result += "&" + field.getName() + "="+ ((BigDecimal) o).toPlainString();
                    } else {
                        result += "&" + field.getName() + "="+ o;
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }

            return result;
        }

    }

}
