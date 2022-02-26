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

    //SIGN IN CHECKPOINT!!!

    @GetMapping("/getUserSignIn")
    User getUserBySignIn(@RequestParam String email, String password) {
        User u = userService.getUserByEmail(email);
        if(u == null){
            User empty = new Driver(); //If driver flag is set, you know that there is no user w that email
            return empty;
        }
        else{
            if(u.password.equals(password)){
                return u;
            }
            else{
                User empty = new Rider(); //If rider flag is set, you know that the password is incorrect for that user
                return empty;
            }
        }
    }

}