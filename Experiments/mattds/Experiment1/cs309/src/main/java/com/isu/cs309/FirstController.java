package com.isu.cs309;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
class FirstController {

    User user1 = new User(
            "Matt",
            "Sinnwell",
            "615 10th St. Ames, IA 50010",
            "mattds@iastate.edu",
            "505-400-5981");

    User user2 = new User(
            "John",
            "Doe",
            "615 10th St. Ames, IA 50010",
            "jdoe@iastate.edu",
            "123-456-7890");

    @GetMapping("/")
    public String welcome() {
        return "The TA should give Matt an A for this demo, it's awesome";
    }

    @GetMapping("/{name}")
    public String welcome(@PathVariable String name) {
        return "The person who made this endpoint is: " + name;
    }

    @GetMapping("/object")
    public User returnObject() {
        return user1;
    }

    @GetMapping("/objects")
    public List<User> returnObjects() {
        ArrayList<User> list = new ArrayList<>();
        list.add(user1);
        list.add(user2);
        return list;
    }
}
