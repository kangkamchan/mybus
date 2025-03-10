package net.mybus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bus")
public class MyBusController {
    @GetMapping
    public String myBus() {
        return "bus/index";
    }
}
