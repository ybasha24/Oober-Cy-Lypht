package _HB_2.Backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//@RestController
//@RequestMapping( "/driver")
//public class DriverController {
//
////    @Autowired
////    private DriverService driverService;
//
//
//    @Autowired
//    DriverRepository driverRepository;
//
//
//    @PostMapping("/registerDriver")
//    String createDriver(
//            @RequestBody Driver d
//    )
////    {
////        try{
////            driverService.createDriver(d);
////            return "You successfully registered a Driver with ID# " + d.getId();
////        } catch (Exception e) {
////            return "there was a problem";
////        }
////    }
//    {
//        if (d == null) {
//            return "There was an error";
//        }
//        //Driver driver = new Driver(d.firstName,d.lastName,d.address,d.state,d.zip,d.email,d.phoneNumber);
//        driverRepository.save(d);
//        return "You have succeeded";
//    }
//}

@RestController
public class DriverController {
    @Autowired
    DriverRepository driverRepository;

    @PostMapping(path = "/driver/registerDriver")
    String createUser(@RequestBody Driver d){
        if (d == null)
            return "You Didn't Make It";
        driverRepository.save(d);
        return "You are a hero";
    }
}