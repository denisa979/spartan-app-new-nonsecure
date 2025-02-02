package com.spartan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({"/", "/home", "/home/", "/web", "/web/", "/web/home", "/web/home/"})
    public String home() {
        return "home";
    }

}
