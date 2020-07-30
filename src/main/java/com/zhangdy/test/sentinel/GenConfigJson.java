//package com.zhangdy.test.sentinel;
//
//
//import com.alibaba.fastjson.JSON;
//import com.google.common.collect.Lists;
//import lombok.*;
//
//import java.io.File;
//import java.lang.reflect.Method;
//import java.net.URL;
//import java.util.List;
//
//public class GenConfigJson {
//
//
//
//    public static void main(String[] args) {
//
//
//    }
//
//    private void doScanner(String scanPackage) {
//        URL url = this.getClass().getClassLoader().getResource(scanPackage.replaceAll("\\.","/"));
//        File classDir = new File(url.getFile());
//        List<PConfig> configs1 = Lists.newArrayList();
//        List<FConfig> configs2 = Lists.newArrayList();
//        for (File file : classDir.listFiles()) {
//            if(file.isDirectory()){
//                doScanner(scanPackage + "." +  file.getName());
//            }else {
//                if(!file.getName().endsWith(".class")){
//                    continue;
//                }
//                String clazzName = (scanPackage + "." + file.getName().replace(".class",""));
//                try {
//                    Class<?> clazz = Class.forName(clazzName);
//                    Method[] methods = clazz.getDeclaredMethods();
//                    for (Method method : methods) {
//                        SentinelResource sentinelResource = method.getAnnotation(SentinelResource.class);
//                        if (sentinelResource == null) {
//                            continue;
//                        }
//                        configs1.add(builderPConfig( sentinelResource.value(), 10));
//                        configs2.add(builderFConfig( sentinelResource.value(), 10));
//                        log.info("ctrl:{},method:{},sentinelResource:{}", clazz.getCanonicalName(), method.getName(), sentinelResource.value());
//                    }
//
//                }catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        log.info("单机限流配置JSON:{}", JSON.toJSONString(configs1));
//        log.info("用户限流配置JSON:{}", JSON.toJSONString(configs2));
//    }
//
//
//    public  PConfig builderPConfig(String resource, int count){
//        return PConfig.builder()
//                .resource("p-"+resource)
//                .count(count)
//                .grade(1)
//                .paramIdx(0)
//                .limitApp("default")
//                .clusterMode(Boolean.FALSE)
//                .build();
//    }
//
//
//    public  FConfig builderFConfig(String resource, int count){
//        return FConfig.builder()
//                .resource("f-"+resource)
//                .count(count)
//                .grade(1)
//                .strategy(0)
//                .limitApp("default")
//                .clusterMode(Boolean.FALSE)
//                .build();
//    }
//
//    @Getter
//    @Setter
//    @AllArgsConstructor
//    @NoArgsConstructor
//    @Builder
//    public static class PConfig {
//        private String resource;
//        private int count;
//        private int grade;
//        private int paramIdx;
//        private String limitApp;
//        private boolean clusterMode;
//    }
//    @Getter
//    @Setter
//    @AllArgsConstructor
//    @NoArgsConstructor
//    @Builder
//    public static class FConfig {
//        private String resource;
//        private int count;
//        private int grade;
//        private int strategy;
//        private String limitApp;
//        private boolean clusterMode;
//    }
//
//}
