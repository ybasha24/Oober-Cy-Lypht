package _HB_2.Backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "/trip")
public class TripController {

    @Autowired
    private TripService tripService;

    @PostMapping("/createTripByDriver")
    Trip createTripByDriver(
            @RequestBody Trip trip) {
        Trip t = tripService.createTripByDriver(trip);

        return t;
    }

}