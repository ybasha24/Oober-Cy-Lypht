package com.isu.cs309;

import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

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

    @GetMapping("/api/foos")
    @ResponseBody
    public String getFoos(@RequestParam(required = false) String id) {
        return "ID: " + id;
    }

    @GetMapping("/parameter")
    @ResponseBody
    public User getParameter(@RequestParam Long id){
        user1.setId(1L);
        user2.setId(2L);
        HashMap<Long,User > mapOfUsers = new HashMap<>();
        mapOfUsers.put(1L, user1);
        mapOfUsers.put(2L, user2);
        return mapOfUsers.get(id);
    }
}
