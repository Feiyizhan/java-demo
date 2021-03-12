package io.github.feiyizhan;

import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * JDK Records 的测试
 * @author 徐明龙 XuMingLong 2021-03-12
 */
public class RecordTests {

    /**
     * 定义ClassRoom Record
     * @author 徐明龙 XuMingLong 2021-03-12
     */
    public record ClassRoom(String name, int id, List<Student> studentList) {}

    /**
     * 测试单个 Record
     * @author 徐明龙 XuMingLong 2021-03-12
     * @return void
     */
    @Test
    public void test1(){
        Student student1 = new Student("张三",1,24);
        Student student2 = new Student("李四",2,30);
        System.out.println(student1);
        System.out.println(student2);
        System.out.println(student1.name() + " " + student1.id() + " " + student1.age());
    }

    /**
     * 测试混合Record
     * @author 徐明龙 XuMingLong 2021-03-12
     * @return void
     */
    @Test
    public void test2(){
        Student student1 = new Student("张三",1,24);
        Student student2 = new Student("李四",2,30);
        ClassRoom classRoom = new ClassRoom("测试班",1,List.of(student1,student2));
        System.out.println(classRoom);
    }

    @Test
    public void test3(){
        Student student1 = new Student("张三",1,24);
        Student student2 = new Student("李四",2,10);
        System.out.println(student1);
        System.out.println(student2);
    }

}
