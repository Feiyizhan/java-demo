package io.github.feiyizhan;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Map;

/**
 * @author 徐明龙 XuMingLong 2020-07-22
 */
@ExtendWith(SpringExtension.class) // Junit5
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LogAspectTests {

    /**
     * 获取随机的端口
     * @author 徐明龙 XuMingLong 2020-07-22
     */
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void test_1_Hello() {
//        String requestResult = restTemplate.getForObject("http://127.0.0.1:" + port + "/test2/hello",
//            String.class,Map.of("name","西西"));
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://127.0.0.1:" + port + "/test2/hello",
            String.class,Map.of("name","西西"));
//        restTemplate.exchange("http://127.0.0.1:" + port + "/test2/hello", HttpMethod.GET,)
        Assertions.assertThat(responseEntity.getBody()).contains("西西");
//        Assertions.assertThat(requestResult).contains("西西");
    }

    @Test
    public void test_2_HelloException() {
        String requestResult = restTemplate.getForObject("http://127.0.0.1:" + port + "/test2/hello-exception",
            String.class, Map.of("name","西西"));
        Assertions.assertThat(requestResult).contains("Hello 西西");
    }

    @Test
    public void test_3_HelloException() {
        String requestResult = restTemplate.getForObject("http://127.0.0.1:" + port + "/test2/hello-exception",
            String.class);
        Assertions.assertThat(requestResult).contains("Hello 西西");
    }

    @Test
    public void test_4_Hi() {
        String requestResult = restTemplate.getForObject("http://127.0.0.1:" + port + "/test2/hi",
            String.class);
        Assertions.assertThat(requestResult).contains("Hi");
    }

}
