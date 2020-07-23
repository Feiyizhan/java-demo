package io.github.feiyizhan.controller;

import org.springframework.stereotype.Controller;

/**
 * @author 徐明龙 XuMingLong 2020-07-21
 */
@Controller
public class HomeController {

    public String home() {
        return "redirect:/swagger-ui/index.html";
    }
}
