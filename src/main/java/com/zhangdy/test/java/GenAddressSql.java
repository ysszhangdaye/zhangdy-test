package com.zhangdy.test.java;

import com.google.common.collect.Lists;
import com.zhangdy.util.RsaUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;

public class GenAddressSql {

    public static void main(String[] args) {
        test("C:\\Users\\it\\Desktop\\2.sql");

    }

    public static void test(String path) {
        List<File> files = Lists.newArrayList();
        String SQL = "update address_pool set sign='%s' where id=%s; ";
        try {
            File file = new File(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String lineTxt = null;
            while ((lineTxt = br.readLine()) != null) {
                String id = lineTxt.substring(lineTxt.indexOf("values('") + 8, lineTxt.indexOf("','"));
                String address = lineTxt.substring(lineTxt.indexOf("','") + 3, lineTxt.indexOf("')"));

                String sign = RsaUtils.signData(address, "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALdHgRLS3ZaQkoKmidO+ICYJ8KM+IWU6lX2tY0DxdI3+q54ZjfSYNDD25WC+8acpMooIQHll56nF81tEeaUIW0NopZF+rxRzYT8GxlMCK56lHuegkk1oFW9uyum6enOaPmMh8iGR02+lGjf2I7RqgRcv7+yXFPioGC24JdSsPU7rAgMBAAECgYB2WS7ox88cnYht4zpWx4uc5IhF8SXBZz4G5IKV+hSoaE3LjnbS/cGd6KAfg80pgvnGliOEAKDCSTemtKXUQnwjCC+/0jwfyvCN+fVxdLe2pEwqDfo8xG322Kg4/A/2Q34Mmrn4Up3usQYZ1VRgZ6RgHJtYYovGDiSnQVjzph7XYQJBAOCPR8eY6fci3LhxdUpqkhTcCj3Yl6xxgV8gLR5yT2HSL1iZ4HqIOaMBxI0s6pG99Mtj8CDiuqoB0czBQn1NfZECQQDQ8KSl8wNrKhheUTdCKS963z+782Ko+U7XDqezkFn3MoJ1etypw4YCpbJVB8OusNSnRhe7idBqiOZmX6vOxja7AkEAzuLSk86Kx3ij9334PK5Ald0/xkj1PBd6xIQlbWaNQideHXfvO5Sj3U00o4hdOBbVIEmbowCgTx7MBqOKuVyaMQJAbUaulix3CUFG5W1MnUm1D2pbhw1P+FgLp+Z+RyekJelA2ficGB2upQvMLTSWX3MUhTffibIWUuGuvrQiw05fqQJAJr6/FvoR6Q1v/EfrXNoHtGnSj8knEK8655mXxxQ6/V+QF1q1W9OzoLTfEpv8XM4sQEhxqRqtQLrTuZDMe104lQ==");

                String format = String.format(SQL, sign, id);
                System.out.println(format);

            }
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
