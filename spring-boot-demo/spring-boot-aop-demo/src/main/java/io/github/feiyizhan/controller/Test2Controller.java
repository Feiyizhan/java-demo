package io.github.feiyizhan.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author 徐明龙 XuMingLong 2020-07-21
 */
@RestController
@RequestMapping("/test2")
public class Test2Controller {

    @GetMapping("/hi")
    public ResponseEntity<String> getHi(){
        return ResponseEntity.ok("Hi");
    }


    @GetMapping("/text")
    public ResponseEntity<String> getText(@RequestParam String text){
        return ResponseEntity.ok(text);
    }


    @GetMapping("/hello")
    public ResponseEntity<String> hello(@RequestParam String name){
        return ResponseEntity.ok("Hello "+name);
    }

    @GetMapping("/hello-exception")
    public ResponseEntity<String> helloException(@RequestParam String name){
        if(name==null){
            throw new RuntimeException("姓名不能为空");
        }
        return ResponseEntity.ok("Hello "+name);
    }
}
