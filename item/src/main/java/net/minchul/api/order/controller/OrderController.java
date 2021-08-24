package net.minchul.api.order.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class OrderController {

    @GetMapping("/")
    public String home() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }
}
