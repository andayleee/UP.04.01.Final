package com.example.upAksenovPrac2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class mainPageController {
    @GetMapping("/main")
    public String flights(Model model) {return "mainPage";}
}
