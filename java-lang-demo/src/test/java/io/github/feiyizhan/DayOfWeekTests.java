package io.github.feiyizhan;

import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * DayOfWeek
 * @author 徐明龙 XuMingLong 2021-08-06
 */
public class DayOfWeekTests {

    @Test
    public void testGetDisplayName(){
        LocalDateTime now = LocalDateTime.now();
        DayOfWeek dayOfWeek = now.getDayOfWeek();
        System.out.println(dayOfWeek.getDisplayName(TextStyle.FULL, Locale.CHINA));
    }

}
