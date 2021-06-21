package io.github.feiyizhan;

import cn.hutool.core.date.TimeInterval;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 徐明龙 XuMingLong 2021-04-07
 */
public class StreamTests {

    @Test
    public void test(){
        int size = 100*100*100;
        List<Person> personList = new ArrayList<>(size);
        for(int i=0;i<size;i++){
            personList.add(new Person("i->"+i,i));
        }
        String name = "i->"+(size-1);
        TimeInterval timer1 = new TimeInterval(true);
        personList.stream().filter(r->r.getName().equals(name)).findFirst().ifPresent(System.out::println);
        System.out.println(timer1.intervalMs());

        timer1.restart();
        personList.parallelStream().filter(r->r.getName().equals(name)).findFirst().ifPresent(System.out::println);
        System.out.println(timer1.intervalMs());

        timer1.restart();
        for(int i=0;i<size;i++){
            Person person =personList.get(i);
            if( person.getName().equals(name)){
                System.out.println(person);
                break;
            }
        }
        System.out.println(timer1.intervalMs());




    }
    @Data@NoArgsConstructor@AllArgsConstructor
    class Person{
        String name;
        int age;
    }
}
