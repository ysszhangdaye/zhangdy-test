package com.zhangdy.test.decimal;


import com.infra.tools.RsaUtils;
import com.infra.tools.ThreeDES;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.UUID;

public class TestDecimalPow {

    private static final String PUBLIC_KEY ="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDTTAxkch8OdOl1IsB18T9RUcHA2weoW0MlZjVrDemtUzN2LP7cvuojR2ZIPUNLOguRRJixxRhLyvVUYyaVXCbSz781pFFx4ye8CvZ8pe7srgeIUi61JLJBrITrUs3ceR5l5hRh7bN33mW42/8GAnokL00n/GGmUrI0tEFvJZxH3QIDAQAB";

    private static final String PRIVATE_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBANNMDGRyHw506XUiwHXxP1FRwcDbB6hbQyVmNWsN6a1TM3Ys/ty+6iNHZkg9Q0s6C5FEmLHFGEvK9VRjJpVcJtLPvzWkUXHjJ7wK9nyl7uyuB4hSLrUkskGshOtSzdx5HmXmFGHts3feZbjb/wYCeiQvTSf8YaZSsjS0QW8lnEfdAgMBAAECgYBxs2CPxKFVpC/kanjqfpE17yq13qc2PzXYsDFalDgEntr4Yqoam1le9pLhmKXXmzqSRzlrn8W9M0/kwx4GDXqNyAfBDL1b+fvqZm3igRT/1SFP96+21hqBNs4L5V1yZRy4H6OujrxPutsh9zKxOucs1u9qN/ufWT5N1uAOwDRawQJBAOzaX2oQAqLMAdnMLtpw9CsGNMxR4c57Hn/BVlw9ZUYUA8o1sjcECLn3FVLpKuOkAs5mol4mFByQXvkxVsF8eBkCQQDkYMzYXubBeEC64hVbAicSHhuijY1cN1h57wtdFROv9jzLXiBBhE/GQrrOSfzg1zTj5Yp1AIfEPTkSByAvfNZlAkEAzCzHGZgpnYr9gXHZZeb/JOOZuhRP8n4B4aGZv7QRTGLOjY7STUjF+QF9OVbU6R2maaTiDQUIS0NNC8WzguG4aQJBAI5Dgreyfnq8p04DchwsPhx6Jure1NVCINpsP57vd0Eg2gQxkVRe0LcHcIj67A0/4lGo9tQlzW1AWiHR0InkcUUCQG/6JvyPw0ySeumv7NemN9GgZ+aXtpoOwcVRh3UIKsWpfzJ9PtBSrx6neiX9CuctxXiq+LnsXSL9JX6nuz9x2IY=";


    private static final String THREE_DES_KEY = "S2#%@^ADOWFM@$#$@!";

    public static void main(String[] args) throws Exception {
//        System.out.println(sqrt(new BigDecimal("4"), 8));
//        System.out.println(sqrt(new BigDecimal("3"), 8));
//        System.out.println(sqrt(new BigDecimal("2"), 8));
//        System.out.println(sqrt(new BigDecimal("4"), 8));
//        System.out.println(sqrt(new BigDecimal("5"), 8));
//        System.out.println(sqrt(new BigDecimal("6"), 8));
//        302c02145bfe4a9f53e0b09f84ae8e4a2a85dc828a32cfca021429dfcb29ff885efb10cc37a275b0d88e0715e5de
        String s = UUID.randomUUID().toString().replaceAll("-", "");

        String data = s+"&" + System.currentTimeMillis();
        System.out.println("元数据 -> "+data);
        String sign = RsaUtils.signData(data, PRIVATE_KEY);
        System.out.println("sign -> " +sign);
        data += "&" + sign;
        System.out.println("data -> " +data);

        String s1 = ThreeDES.encryptDESCBC(data, THREE_DES_KEY);
        System.out.println("3des -> " + s1 + " #### length -> " + s1.getBytes("utf-8").length);



    }


    public static BigDecimal sqrt(BigDecimal value, int scale){
        BigDecimal num2 = BigDecimal.valueOf(2);
        int precision = 100;
        MathContext mc = new MathContext(precision, RoundingMode.HALF_UP);
        BigDecimal deviation = value;
        int cnt = 0;
        while (cnt < precision) {
            deviation = (deviation.add(value.divide(deviation, mc))).divide(num2, mc);
            cnt++;
        }
        deviation = deviation.setScale(scale, BigDecimal.ROUND_HALF_UP);
        return deviation;
    }

}
