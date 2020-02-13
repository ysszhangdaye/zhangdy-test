package com.zhangdy.test.java;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;

public class MethodDemo {
    public static void main(String[] args) {
        Method[] methods = SampleClass.class.getMethods();
        Annotation[] annotations = methods[0].getDeclaredAnnotations();

        Method method = methods[0];
        boolean annotationPresent = method.isAnnotationPresent(CustomAnnotation.class);
        for(Annotation annotation : annotations){
            if(annotation instanceof CustomAnnotation){
                CustomAnnotation customAnnotation = (CustomAnnotation) annotation;
                System.out.println("name: " + customAnnotation.name());
                System.out.println("value: " + customAnnotation.value());
            }
        }
    }
}

@CustomAnnotation(name="SampleClass",  value = "Sample Class Annotation")
class SampleClass {
    private String sampleField;

//    @CustomAnnotation(name="getSampleMethod",  value = "Sample Method Annotation")
    public String getSampleField() {
        return sampleField;
    }

    public void setSampleField(String sampleField) {
        this.sampleField = sampleField;
    }
}

@Retention(RetentionPolicy.RUNTIME)
@interface CustomAnnotation {
    public String name();
    public String value();
}//原文出自【易百教程】，商业转载请联系作者获得授权，非商业请保留原文链接：https://www.yiibai.com/javareflect/javareflect_method_getdeclaredannotations.html

