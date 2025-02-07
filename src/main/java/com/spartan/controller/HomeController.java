package com.spartan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({"/", "/v2", "/v2/home", "/v2/home/", "/web/v2", "/web/v2/", "/web/v2/home", "/web/v2/home/"})
    public String home() {
        return "home";
    }

}
