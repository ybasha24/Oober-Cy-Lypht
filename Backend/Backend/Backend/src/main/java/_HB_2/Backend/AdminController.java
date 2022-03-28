package _HB_2.Backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;

    @PostMapping("/registerAdmin")
    User createAdminWithBody(
            @RequestBody Admin a
    ) {
        User u = adminService.getAdminbyEmail(a.email);
        if (u == null){
            adminService.createAdmin(a);
            return a;
        }
        else{
            User empty = new Admin();
            return empty;
        }
    }

    @GetMapping("/getAdmin")
    User getAdminById(
            @RequestParam int id
    ) {
        User u = adminService.getAdminById(id);
        return u;
    }

    //used for deleting a user, regardless if they are a rider, driver or both!
    @DeleteMapping("/deleteUser")
    void deleteUser(@RequestParam int id){
       userService.deleteUserById(id);
    }

    @PutMapping("removeUserAsRider")
    User removeUserAsRider(@RequestParam int id){
        return userService.removeAsRider(id);
    }

    @PutMapping("removeUserAsDriver")
    User removeUserAsDriver(@RequestParam int id){
        return userService.removeAsDriver(id);
    }

    @PutMapping("/editUser")
    User editUser(@RequestParam int id,
                  @RequestBody User u){
        return userService.editUser(id, u);
    }

    @PutMapping("/addUserAsRider")
    User addUserAsRider(@RequestParam int id,
                        @RequestParam boolean rider){
        return userService.addAsRider(id, rider);
    }

    @PutMapping("/addUserAsDriver")
    User addUserAsDriver(@RequestParam int id,
                        @RequestParam boolean driver){
        return userService.addAsDriver(id, driver);
    }




}