package io.github.feiyizhan;

import cn.hutool.core.lang.Console;
import org.hibernate.validator.internal.util.TypeHelper;
import org.junit.Test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Java 
 * @author 徐明龙 XuMingLong 2021-06-29
 */
public class GenericTests {

    @Test
    public void test1(){
        System.out.println(new ArrayList<Map<String, List<String>>>().getClass().getGenericSuperclass());
        System.out.println(new ArrayList<Map<String, List<String>>>(){}.getClass().getGenericSuperclass());
        Type t = new ArrayList<Map<String, List<String>>>() {}.getClass().getGenericSuperclass();
        Type targ = ((ParameterizedType) t).getActualTypeArguments()[0];
        System.out.println(targ);
        System.out.println(t);


    }
    @Test
    public void test2(){
        ArrayList<Map<String, List<String>>> list = new ArrayList<Map<String, List<String>>>();
        list.add(new HashMap<>());
        getGenericSuperclass(list);
        getGenericSuperclass(list.getClass());

        ArrayList<Map<String, List<String>>> list2 = new ArrayList<Map<String, List<String>>>(){};
        list2.add(new HashMap<>());
        getGenericSuperclass(list2);
        getGenericSuperclass(list2.getClass());

    }

    @Test
    public void test3(){
        ArrayList<Map<String, List<String>>> list = new ArrayList<Map<String, List<String>>>();
        Console.log(TypeHelper.getComponentType(list.getClass().getGenericSuperclass()));
        Console.log(TypeHelper.getErasedType(list.getClass().getGenericSuperclass()));
        Console.log(TypeHelper.getErasedReferenceType(list.getClass().getGenericSuperclass()));

        Console.log(TypeHelper.getComponentType(new ArrayList<Map<String, List<String>>>().getClass().getGenericSuperclass()));
        Console.log(TypeHelper.getErasedType(new ArrayList<Map<String, List<String>>>().getClass().getGenericSuperclass()));
        Console.log(TypeHelper.getErasedReferenceType(new ArrayList<Map<String, List<String>>>().getClass().getGenericSuperclass()));
    }



    private <T> void getGenericSuperclass(T arg){
        Type t = arg.getClass().getGenericSuperclass();
        Type targ = ((ParameterizedType) t).getActualTypeArguments()[0];
        System.out.println(targ);
        System.out.println(t);
        Console.log(arg.getClass());

    }


    private <T> void getGenericSuperclass(Class<T> clazz){
        Type t = clazz.getGenericSuperclass();
        Type targ = ((ParameterizedType) t).getActualTypeArguments()[0];
        System.out.println(targ);
        System.out.println(t);
        Console.log(clazz);
    }
}
