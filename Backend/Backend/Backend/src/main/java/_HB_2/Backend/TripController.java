package _HB_2.Backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/getDriverTrips")
    List<Trip> getListOfTripsByDriverId(
            @RequestParam int id) {

        List list = tripService.getTripsByDriverId(id);
        return list;
    }

}
