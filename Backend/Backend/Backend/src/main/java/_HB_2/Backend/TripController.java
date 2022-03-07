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
            @RequestParam int driverId,
            @RequestBody Trip trip) {
        Trip t = tripService.createTripByDriver(driverId, trip);

        return t;
    }

//    @GetMapping("/getDriverTrips")
//    List<Trip> getListOfTripsByDriverId(
//            @RequestParam int id) {
//
//        List list = tripService.getTripsByDriverId(id);
//        return list;
//    }

    @GetMapping("/getTrip")
    Trip getListOfTripsByDriverId(
            @RequestParam int id) {

        Trip trip = tripService.getTripById(id);
        return trip;
    }

    @PutMapping("/editTrip")
    Trip getTripById(
            @RequestParam int tripId,
            @RequestParam int riderId,
            @RequestParam int driverId,
            @RequestBody Trip t) {
        return tripService.editTripById(tripId, riderId, driverId, t);
    }

}
