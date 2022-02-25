package _HB_2.Backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/user")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/getUser")
    User getUserById(@RequestParam int id) {
        User u = userService.getUserById(id);
        return u;
    }

    @GetMapping("/getUserByEmail")
    User getUserByEmail(@RequestParam String email) {
        User u = userService.getUserByEmail(email);
        return u;
    }

    @DeleteMapping("/deleteUser")
    void deleteUserById(
            @RequestParam int id) {
        userService.deleteUserById(id);
    }

}