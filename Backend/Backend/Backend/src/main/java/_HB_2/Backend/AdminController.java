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