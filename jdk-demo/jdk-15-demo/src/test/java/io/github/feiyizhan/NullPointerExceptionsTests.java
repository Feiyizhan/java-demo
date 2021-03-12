package io.github.feiyizhan;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * NullPointerExceptions 测试
 * @author 徐明龙 XuMingLong 2021-03-12
 */
public class NullPointerExceptionsTests {

    @Test
    public void test1(){
        Object obj = null;
        System.out.println(obj.toString());
    }

    @Test
    public void test2(){
        Object obj = null;
        if(obj==null && obj.toString()!=null){
            System.out.println(obj.toString());
        }
    }

    @Test
    public void test3(){
        Object obj = null;
        List<Object> list = new ArrayList<>();
        list.add(obj);
        List<String> stringList = list.stream().map(Object::toString).collect(Collectors.toList());
    }

    @Test
    public void test4(){
        Object obj = null;
        List<Object> list = new ArrayList<>();
        list.add(obj);
        List<String> stringList = list.stream().map(r->r.toString()).collect(Collectors.toList());
    }

    @Test
    public void test5(){
        Object obj = null;
        List<Object> list = new ArrayList<>();
        list.add(obj);
        for(Object o:list){
            System.out.println(o.toString());
        }
    }
}
