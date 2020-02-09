package com.zhangdy.util;

import java.io.*;
import java.util.Properties;

/**
 * @author 12933
 * @Title: ConfigProperties
 * @ProjectName zhangdy-test
 * @Description: TODO
 * @date 2019/1/2513:14
 */
public class ConfigProperties {

    private Properties properties = new Properties();

    public ConfigProperties(String filePath) {
        try {
//
//            InputStream ips = getClass().getResourceAsStream("/jdbc.properties");
//            BufferedReader ipss=new BufferedReader(new InputStreamReader(ips));
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
//            InputStream is = getClass().getResourceAsStream(file);


            this.properties.load(fis);
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getValue(String name, String defaultVal) {
        return this.properties.getProperty(name, defaultVal);
    }

    public String getValue(String name) {
        return getValue(name, "");
    }


}
