package io.github.feiyizhan;

/**
 * @author 徐明龙 XuMingLong 2021-03-12
 */
public record Student(String name, int id, int age) {


    /**
     * 测试Compact constructor
     * @author 徐明龙 XuMingLong 2021-03-12
     * @param name
     * @param id
     * @param age
     * @return
     */
    public Student{
        if (age < 18) {
            throw new IllegalArgumentException( "未成年人不接受!");
        }
    }
}