package com.cur1osity.task2restclient.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ClockController {

    @GetMapping
    public String findClock() {

        return "index";
    }
}
