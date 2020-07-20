package io.github.feiyizhan.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统
 * @author 徐明龙 XuMingLong 2019-12-03
 */
@RestController
@RequestMapping("/test/")
@Log4j2
public class TestController {


    /**
     * 直接返回内容
     * @author 徐明龙 XuMingLong 2019-12-03
     * @param content
     * @return org.springframework.http.ResponseEntity<java.lang.String>
     */
    @GetMapping("/echo/{content}")
    public ResponseEntity<String> echo(@PathVariable String content){
        // 设置返回结果
        return ResponseEntity.ok(content);
    }
}
