package _HB_2.Backend.user;

import _HB_2.Backend.driver.Driver;
import _HB_2.Backend.rider.Rider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    String deleteUserById(
            @RequestParam int id) {
        userService.deleteUserById(id);
        return "You have deleted User " + id;
    }

    //Not sure why this doesn't work
    //Either delete this delete the function in the UserService, and create matching enpoints for Rider and Admin
    //Or fix and delete endpoint in DriverController and function in UserService
    @PutMapping("/editUser")
    User editUser(
            @RequestParam int id,
            @RequestBody User u) {
        return userService.editUser(id, u);
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
            if(u.getPassword().equals(password)){
                return u;
            }
            else{
                User empty = new Rider(); //If rider flag is set, you know that the password is incorrect for that user
                return empty;
            }
        }
    }

    @GetMapping("/getAllUsers")
    List<User> getAllUsers() {
        return userService.getAllUsers();
    }

}