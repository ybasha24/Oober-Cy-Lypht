package _HB_2.Backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/driver")
public class DriverController {

    @Autowired
    private DriverService driverService;

    @PostMapping("/registerDriver")
    User createDriverWithBody(
            @RequestBody Driver d) {

        //if we receive empty info from frontend
        if (d == null) {
            //return an empty driver
            User u = new Driver();
            return u;
        }

        try{
            Driver driver = driverService.createDriver(d);
            return driver;
        } catch (Exception e) {
            //there was a problem, return empty user
            User u = new Driver();
            return u;
        }
    }

    @GetMapping("/getDriver")
    User getDriverById(
            @RequestParam int id) {

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
        User u = driverService.getDriverById(id);
        return u;
    }

    @DeleteMapping("/deleteDriver")
    void deleteDriverById(
            @RequestParam int id) {
        driverService.deleteDriverById(id);
    }

    @PutMapping("/editDriver")
    User editDriver(
            @RequestParam int id,
            @RequestBody Driver d) {
        return driverService.editDriver(id, d);
    }

}