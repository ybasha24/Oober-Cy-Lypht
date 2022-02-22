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
    String createAdminWithBody(
            @RequestBody Admin a
    ) {
        if (a == null) {
            return "There Was No Admin Sent";
        }

        try {
            adminService.createAdmin(a);
            return "You successfully registered an Admin with ID# " + a.getId();
        } catch (Exception e) {
            return "there was a problem";
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
}