package _HB_2.Backend.rider;

import _HB_2.Backend.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/rider")
public class RiderController {

    @Autowired
    private RiderService riderService;

    @PostMapping("/registerRider")
    User createRiderWithBody(
            @RequestBody Rider r) {
        User u = riderService.getRiderbyEmail(r.getEmail());
        if (u == null){
            riderService.createRider(r);
            return r;
        }
        else{
            User empty = new Rider();
            return empty;
        }
    }

    @GetMapping("/getRider")
    User getRiderById(@RequestParam int id) {
        User u = riderService.getRiderById(id);
        return u;
    }

    @GetMapping("/getRiderByEmail")
    User getRiderByEmail(@RequestParam String email) {
        User u = riderService.getRiderbyEmail(email);
        return u;
    }



}