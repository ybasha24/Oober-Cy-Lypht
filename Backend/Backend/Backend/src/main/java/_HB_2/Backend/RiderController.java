package _HB_2.Backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/rider")
public class RiderController {

    @Autowired
    private RiderService riderService;

    @PostMapping("/registerRider")
    String createRiderWithBody(
            @RequestBody Rider r) {

        if (r == null) {
            return "There Was No Rider Sent";
        }

        try{
            riderService.createRider(r);
            return "You successfully registered a Rider with ID# " + r.getId();
        } catch (Exception e) {
            return "there was a problem";
        }
    }

    @GetMapping("/getRider")
    User getRiderById(@RequestParam int id) {

//        if (id == null) {
//            return "no id sent";
//        }
//        try{
//            User u = riderService.getRiderById(id);
//            return u;
//        } catch (Exception e) {
//            //what should i return here?
//            //return "there was a problem";
//        }
        User u = riderService.getRiderbyId(id);
        return u;
    }

    @DeleteMapping("/deleteRider")
    void deleteRiderById(
            @RequestParam int id) {
        riderService.deleteRiderById(id);
    }

}