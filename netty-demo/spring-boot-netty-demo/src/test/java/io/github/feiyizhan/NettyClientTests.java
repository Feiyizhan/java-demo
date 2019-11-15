package io.github.feiyizhan;

import io.github.feiyizhan.beans.NettyClient;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoField;

/**
 * @author 徐明龙 XuMingLong 2019-11-13
 */
public class NettyClientTests {

    @Test
    public void test(){
        try {
            NettyClient nettyClient = new NettyClient();
            nettyClient.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testTime(){
        Instant now = Instant.now();
        LocalDateTime now2= LocalDateTime.ofInstant(now, ZoneId.of("UTC+8"));
        //获取当前秒的纳秒 SSSSSSSSS 9位数字
        System.out.println(now.getLong(ChronoField.NANO_OF_SECOND));
        //获取当前时间的自1970-01-01T00:00Z（ISO）开始的秒数
        System.out.println(now.getLong(ChronoField.INSTANT_SECONDS));

        //获取东八区的当前秒的纳秒 SSSSSSSSS 9位数字
        System.out.println(now2.getNano());
        //获取东八区的当前分钟的秒 2位数字
        System.out.println(now2.getSecond());
        //获取东八区的当前分钟的秒 2位数字
        System.out.println(now2.getLong(ChronoField.SECOND_OF_MINUTE));
        //获取东八区的当天的秒  范围0->24*60*60-1
        System.out.println(now2.getLong(ChronoField.SECOND_OF_DAY));


        //获取因为长度不足丢失精度的纳秒，这个只能用来测量经过的时间，而不能用于计算两个时间直接的差。
        System.out.println(System.nanoTime());
        //获取当前时间自1970-01-01T00:00Z（ISO）开始的毫秒数
        System.out.println(System.currentTimeMillis());

        //计算自1970-01-01T00:00Z（ISO）开始的纳秒数
        BigDecimal second = new BigDecimal(now.getLong(ChronoField.INSTANT_SECONDS));
        BigDecimal nano = new BigDecimal(now.getLong(ChronoField.NANO_OF_SECOND));

        BigDecimal nanoTimes1 = second.multiply(new BigDecimal(1000*1000*1000)).add(nano);
        BigDecimal nanoTimes2 = new BigDecimal(String.format("%d%09d",
            now.getLong(ChronoField.INSTANT_SECONDS),
            now.getLong(ChronoField.NANO_OF_SECOND)));
        System.out.println(nanoTimes1);
        System.out.println(nanoTimes2);
    }

    public static void main(String[] args) {
        try {
            NettyClient nettyClient = new NettyClient();
            nettyClient.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
