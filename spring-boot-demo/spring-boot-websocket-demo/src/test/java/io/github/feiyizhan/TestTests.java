package io.github.feiyizhan;

import io.github.feiyizhan.service.ITestService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author 徐明龙 XuMingLong 2019-12-03
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@WebIntegrationTest
@Log4j2
public class TestTests {

    @Autowired
    private ITestService testService;

    @Test
    public void test(){
        String string = "aaaaa";
        assertThat(testService.echo(string)).isNotBlank();
        try {
            TimeUnit.SECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("over");
    }
}
