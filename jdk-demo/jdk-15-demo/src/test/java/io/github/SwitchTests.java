package io.github;

import org.junit.jupiter.api.Test;

/**
 * Switch 语句测试
 * @author 徐明龙 XuMingLong 2021-03-12
 */
public class SwitchTests {

    @Test
    public void test1(){
        String str = "A";
        switch (str){
            case "A" -> System.out.println("结果是A");
            case "B" -> System.out.println("结果是B");
            case "C" -> System.out.println("结果是C");
        }

        String result = switch (str){
            case "A" -> "结果是A";
            case "B" -> "结果是B";
            case "C" -> "结果是C";
            default -> "不支持的结果";
        };

        System.out.println(result);

    }
}
