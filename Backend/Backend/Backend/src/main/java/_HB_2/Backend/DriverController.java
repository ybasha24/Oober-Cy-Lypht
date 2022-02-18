package _HB_2.Backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping( "/driver")
public class DriverController {

    @Autowired
    private DriverService driverService;

    @PostMapping("/registerDriver")
    public @ResponseBody String createDriver(
            @RequestBody Driver d
    ){
        try{
            driverService.createDriver(d);
            return "You successfully registered a Driver with ID# " + d.getId();
        } catch (Exception e) {
            return "there was a problem";
        }
    }
}
