package io.github.feiyizhan;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

/**
 * instanceof  测试
 * @author 徐明龙 XuMingLong 2021-03-12
 */
public class InstanceOfTests {

    @Test
    public void test1(){
        Object obj1 = "测试字符串";
        Object obj2 = Integer.valueOf(10);
        Object obj3 = new BigDecimal("233.11");
        if(obj1 instanceof String str){
            System.out.println("obj1是字符串,字符串长度为："+str.length());
        }else{
            System.out.println("obj1不是字符串");
        }
        if(obj1 instanceof Integer int1){
            System.out.println("obj1是整数对象："+int1);
        }else{
            System.out.println("obj1不是整数对象");
        }

        if(obj2 instanceof Integer int1){
            System.out.println("obj2是整数对象："+int1);
        }else{
            System.out.println("obj2不是整数对象");
        }

        if(obj3 instanceof BigDecimal bg1){
            System.out.println("obj3是BigDecimal对象："+bg1);
        }else{
            System.out.println("obj3不是BigDecimal对象");
        }

    }

}
