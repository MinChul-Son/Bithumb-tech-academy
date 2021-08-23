package net.minchul.api.item.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class ItemController {

    @GetMapping("/")
    public String home() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }
}
