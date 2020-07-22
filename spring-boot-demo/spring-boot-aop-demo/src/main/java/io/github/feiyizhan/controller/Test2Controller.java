package io.github.feiyizhan.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 徐明龙 XuMingLong 2020-07-21
 */
@RestController
@RequestMapping("/test2")
public class Test2Controller {

    @GetMapping("/text")
    public ResponseEntity<String> getText(@ModelAttribute String text){
        return ResponseEntity.ok(text);
    }
}
