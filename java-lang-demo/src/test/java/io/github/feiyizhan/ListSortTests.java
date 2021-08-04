package io.github.feiyizhan;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.RandomUtil;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author 徐明龙 XuMingLong 2021-06-30
 */
public class ListSortTests {


    @Test
    public void test(){
        List<String> list = new ArrayList<>();
        list.add("2021-03-23");
        list.add("2021-03-15");
        Console.log(list);
        list.sort(Comparator.naturalOrder());
        Console.log(list);
    }

    @Test
    public void test2(){
        final int size = 10000;
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
        List<String> list = new ArrayList<>(size);
        for(int i=0;i<size;i++){
            list.add(LocalDate.of(RandomUtil.randomInt(1949,2050),RandomUtil.randomInt(1,13),RandomUtil.randomInt(1,29))
                .format(formatter));
        }
        Console.log(list);
        Console.log("开始排序");
        long begin = System.currentTimeMillis();
        list.sort(Comparator.naturalOrder());
        long end = System.currentTimeMillis();

        Console.log(list);
        Console.log("排序完成,耗时【{}】",end-begin);
    }

    @Test
    public void test3(){
        final int size = 10000;
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
        List<LocalDate> list = new ArrayList<>(size);
        for(int i=0;i<size;i++){
            list.add(LocalDate.of(RandomUtil.randomInt(1949,2050),RandomUtil.randomInt(1,13),RandomUtil.randomInt(1,29)));
        }
        Console.log(list);
        Console.log("开始排序");
        long begin = System.currentTimeMillis();
        list.sort(Comparator.naturalOrder());
        long end = System.currentTimeMillis();

        Console.log(list);
        Console.log("排序完成,耗时【{}】",end-begin);
    }
}
