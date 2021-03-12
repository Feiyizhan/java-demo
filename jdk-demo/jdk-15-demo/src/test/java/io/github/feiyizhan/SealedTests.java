package io.github.feiyizhan;

import org.junit.jupiter.api.Test;

/**
 * Sealed 封闭类 测试
 * @author 徐明龙 XuMingLong 2021-03-12
 */
public class SealedTests {

    public abstract class Food{
        private String name;
        public Food(){
        }
        public Food(String name){
            this.name = name;
        }
        public void eat(){
            if(name==null){
                System.out.println("吃食物");
            }else{
                System.out.println("吃"+name);
            }
        }
    }

    public non-sealed class Apple extends Fruit{
        public Apple() {
            super("苹果");
        }
        public Apple(String name) {
            super(name);
        }
    }

    public sealed class Banana extends Fruit{
        public Banana() {
            super("香蕉");
        }
        public Banana(String name) {
            super(name);
        }
    }

    public class Potato extends Food{
        public Potato() {
            super("土豆");
        }
    }

    public abstract sealed class Fruit extends Food permits Banana,Apple {
        public Fruit() {
            super("水果");
        }
        public Fruit(String name){
            super(name);
        }
    }

    public class YellowApple extends Apple{
        public YellowApple() {
            super("黄色的苹果");
        }
    }

    public final class BigBanana extends Banana{
        public BigBanana() {
            super("大的香蕉");
        }
    }

    @Test
    public void test1(){
        Apple apple = new Apple();
        apple.eat();
        Banana banana = new Banana();
        banana.eat();
        Potato potato = new Potato();
        potato.eat();
    }

    @Test
    public void test2(){
        Apple apple = new Apple();
        apple.eat();
        Banana banana = new Banana();
        banana.eat();
        Potato potato = new Potato();
        potato.eat();
    }

    @Test
    public void test3(){
        Apple apple = new Apple();
        apple.eat();
        Banana banana = new Banana();
        banana.eat();
        Potato potato = new Potato();
        potato.eat();

        Apple yellowApple = new YellowApple();
        yellowApple.eat();

        Banana bigBanana = new BigBanana();
        bigBanana.eat();
    }
}
