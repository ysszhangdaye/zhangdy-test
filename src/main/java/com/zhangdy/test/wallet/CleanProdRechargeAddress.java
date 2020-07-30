package com.zhangdy.test.wallet;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.zhangdy.util.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CleanProdRechargeAddress {

    public static void main(String[] args) {
        cleanAddress("E:\\icc\\wallet\\address_pool.txt");
        cleanPayment("E:\\icc\\wallet\\payment_account.txt");
    }

    public static void cleanPayment(String path){
        Map<String, Set<String>> map = Maps.newHashMap();
        map.put("BTC", Sets.newHashSet());
        map.put("USDT", Sets.newHashSet());
        map.put("ETH", Sets.newHashSet());
        map.put("ETC", Sets.newHashSet());
        map.put("LTC", Sets.newHashSet());

        try {
            File file = new File(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String lineTxt = null;
            while ((lineTxt = br.readLine()) != null) {
                String[] array = lineTxt.split("\t");
                String currency = array[1];
                pushAddress(map, array[4].replaceAll("\"", ""), currency.replaceAll("\"", ""));
            }
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        map.forEach((currency, addressSet) -> {
            FileUtils.writeToTxt(Lists.newArrayList(addressSet), "E:\\icc\\wallet\\address_pool_" + currency.toLowerCase() + ".txt");
        });
    }

    public static void cleanAddress(String path){
        Map<String, Set<String>> map = Maps.newHashMap();
        map.put("BTC", Sets.newHashSet());
        map.put("USDT", Sets.newHashSet());
        map.put("ETH", Sets.newHashSet());
        map.put("ETC", Sets.newHashSet());
        map.put("LTC", Sets.newHashSet());

        try {
            File file = new File(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String lineTxt = null;
            while ((lineTxt = br.readLine()) != null) {
                String[] array = lineTxt.split("\t");
                String currency = array[2];
                pushAddress(map, array[1].replaceAll("\"", ""), currency.replaceAll("\"", ""));
            }
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        map.forEach((currency, addressSet) -> {
            FileUtils.writeToTxt(Lists.newArrayList(addressSet), "E:\\icc\\wallet\\address_pool_" + currency.toLowerCase() + ".txt");
        });

    }

    public static void pushAddress(Map<String, Set<String>> map, String address, String currency){
        Set<String> addressSet = map.get(currency);
        if (addressSet == null) {
            return;
        }
        addressSet.add(address);
        map.put(currency, addressSet);
    }


}

