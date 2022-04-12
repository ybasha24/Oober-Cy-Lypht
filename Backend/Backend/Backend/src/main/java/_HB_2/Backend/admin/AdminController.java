package _HB_2.Backend.admin;

import _HB_2.Backend.trip.Trip;
import _HB_2.Backend.user.User;
import _HB_2.Backend.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "AdminController", description = "REST APIs related to Admins")
@RestController
@RequestMapping( "/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;

    @ApiOperation(value = "Enter a new admin", response = User.class)
    @PostMapping("/registerAdmin")
    User createAdminWithBody(
            @RequestBody Admin a
    ) {
        User u = adminService.getAdminbyEmail(a.getEmail());
        if (u == null){
            adminService.createAdmin(a);
            return a;
        }
        else{
            User empty = new Admin();
            return empty;
        }
    }

    @ApiOperation(value = "Retrieve an admin", response = User.class)
    @GetMapping("/getAdmin")
    User getAdminById(
            @RequestParam int id
    ) {
        User u = adminService.getAdminById(id);
        return u;
    }

    //used for deleting a user, regardless if they are a rider, driver or both!
    @ApiOperation(value = "Delete a user", response = String.class)
    @DeleteMapping("/deleteUser")
    String deleteUser(@RequestParam int id){

        userService.deleteUserById(id);
        return "You have deleted User " + id;
    }

    @ApiOperation(value = "Remove user as a rider", response = User.class)
    @PutMapping("removeUserAsRider")
    User removeUserAsRider(@RequestParam int id){
        return adminService.removeAsRider(id);
    }

    @ApiOperation(value = "Remove user as a driver", response = User.class)
    @PutMapping("removeUserAsDriver")
    User removeUserAsDriver(@RequestParam int id){
        return adminService.removeAsDriver(id);
    }

    @ApiOperation(value = "Edit a given user", response = User.class)
    @PutMapping("/editUser")
    User editUser(@RequestParam int id,
                  @RequestBody User u){
        return userService.editUser(id, u);
    }

    @ApiOperation(value = "Add a user as a rider", response = User.class)
    @PutMapping("/addUserAsRider")
    User addUserAsRider(@RequestParam int id){
        return adminService.addAsRider(id);
    }

    @ApiOperation(value = "Add a user as a driver", response = User.class)
    @PutMapping("/addUserAsDriver")
    User addUserAsDriver(@RequestParam int id){
        return adminService.addAsDriver(id);
    }




}