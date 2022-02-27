package _HB_2.Backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/registerAdmin")
    User createAdminWithBody(
            @RequestBody Admin a
    ) {
        //if we receive empty info from frontend
        if (a == null) {
            //return an empty Admin
            User u = new Admin();
            return u;
        }

        try{
            Admin admin = adminService.createAdmin(a);
            return admin;
        } catch (Exception e) {
            //there was a problem, return empty user
            User u = new Admin();
            return u;
        }
    }

    @GetMapping("/getAdmin")
    User getAdminById(
            @RequestParam int id
    ) {

//        if (id == null) {
//            return "no id sent";
//        }
//        try{
//            User u = driverService.getDriverById(id);
//            return u;
//        } catch (Exception e) {
//            //what should i return here?
//            //return "there was a problem";
//        }
        User u = adminService.getAdminById(id);
        return u;
    }

    @DeleteMapping("/deleteAdmin")
    void deleteAdminById(
            @RequestParam int id) {
        adminService.deleteAdminById(id);
    }
}