package io.github.feiyizhan;

import io.github.feiyizhan.service.IUserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @author 徐明龙 XuMingLong 2020-07-22
 */
@ExtendWith(SpringExtension.class) // Junit5
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AnnotationAopTests {

    /**
     * 获取随机的端口
     * @author 徐明龙 XuMingLong 2020-07-22
     */
    @LocalServerPort
    private int port;

    @Autowired
    private IUserService userService;


    @Test
    public void testAop1() {
        System.out.println(userService.save("张三"));
    }

    @Test
    public void testAop2() {
        System.out.println(userService.save("a"));
    }

    @Test
    public void testAop3() {
        userService.testAnnotationAop();
    }
}
