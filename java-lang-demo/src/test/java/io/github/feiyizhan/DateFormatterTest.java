package io.github.feiyizhan;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.TextStyle;
import java.time.temporal.ChronoField;
import java.util.Locale;

/**
 * @author 徐明龙 XuMingLong 2021-08-06
 */
public class DateFormatterTest {

    @Test
    public void testDAY_OF_MONTH(){
        TextStyle style = TextStyle.FULL;
        System.out.println(new DateTimeFormatterBuilder().appendText(ChronoField.DAY_OF_MONTH, style).toFormatter(Locale.CHINA).format(
            LocalDateTime.now()));
    }

    @Test
    public void testMONTH_OF_YEAR(){
        TextStyle style = TextStyle.FULL;
        System.out.println(new DateTimeFormatterBuilder().appendText(ChronoField.MONTH_OF_YEAR, style).toFormatter(Locale.CHINA).format(
            LocalDateTime.now()));
    }

}
