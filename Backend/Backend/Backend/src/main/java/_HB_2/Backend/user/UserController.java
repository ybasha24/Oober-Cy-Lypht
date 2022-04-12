package _HB_2.Backend.user;

import _HB_2.Backend.driver.Driver;
import _HB_2.Backend.rider.Rider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "UserController", description = "REST APIs related to user")
@RestController
@RequestMapping( "/user")
public class UserController {

    @Autowired
    private UserService userService;


    @ApiOperation(value = "Get a user by id", response = Iterable.class, tags = "getUser")
    @GetMapping("/getUser")
    User getUserById(@RequestParam int id) {
        User u = userService.getUserById(id);
        return u;
    }

    @ApiOperation(value = "Get a user by email", response = Iterable.class, tags = "getUserByEmail")
    @GetMapping("/getUserByEmail")
    User getUserByEmail(@RequestParam String email) {
        User u = userService.getUserByEmail(email);
        return u;
    }

    @ApiOperation(value = "Delete a user by Id", response = Iterable.class, tags = "deleteUser")
    @DeleteMapping("/deleteUser")
    String deleteUserById(
            @RequestParam int id) {
        userService.deleteUserById(id);
        return "You have deleted User " + id;
    }

    //Not sure why this doesn't work
    //Either delete this delete the function in the UserService, and create matching enpoints for Rider and Admin
    //Or fix and delete endpoint in DriverController and function in UserService
    @ApiOperation(value = "Edit a user", response = Iterable.class, tags = "editUser")
    @PutMapping("/editUser")
    User editUser(
            @RequestParam int id,
            @RequestBody User u) {
        return userService.editUser(id, u);
    }

    //SIGN IN CHECKPOINT!!!

    @ApiOperation(value = "Get a user by sign in info", response = Iterable.class, tags = "getUserSignIn")
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

    @ApiOperation(value = "Get a list of all users", response = Iterable.class, tags = "getAllUsers")
    @GetMapping("/getAllUsers")
    List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/setProfilePicture")
    String setProfilePicture(@RequestParam int userId, String path) {
        return userService.setProfilePicture(userId, path);
    }

}