package io.github.feiyizhan.controller;

import io.github.feiyizhan.service.ITest1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 徐明龙 XuMingLong 2020-07-21
 */
@RestController
@RequestMapping("/test1")
public class Test1Controller {

    @Autowired
    private ITest1Service test1Service;

    @GetMapping("/text")
    public ResponseEntity<String> getText(@RequestParam String text){
        return ResponseEntity.ok(test1Service.getText(text));
    }

    @GetMapping("/hello")
    public ResponseEntity<String> getHello(){
        return ResponseEntity.ok("Hello");
    }


    @GetMapping("/say-hello")
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok(test1Service.sayHello("西西","未知","28"));
    }
}
