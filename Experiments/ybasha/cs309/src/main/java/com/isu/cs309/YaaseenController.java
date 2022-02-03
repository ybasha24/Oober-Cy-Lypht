package com.isu.cs309;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class YaaseenController {

    @GetMapping("/")
    public String welcome() { return "Hello World! This is a test!";}

    @GetMapping("/food")
    public String welcome2() { return "It is cold.";}
}
