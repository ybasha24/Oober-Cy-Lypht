package _HB_2.Backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping( "/trip")
public class TripController {

    @Autowired
    private TripService tripService;

    @Autowired
    private TripRepository tripRepository;

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

    //returns a list of all trips that have not been completed
    @GetMapping("/getAllActiveTripsFromDriverId")
    List<Trip> getAllActiveTrips(
            @RequestParam int driverId
    ) {
        return tripRepository.getAllUncompletedTripsByDriverId(driverId);
    }

    @GetMapping("/getAllTrips")
    List<Trip> getAllTrips() {
        return tripService.getAllTrips();
    }

    //returns a list of all trips that have not been completed
    @GetMapping("/getAllActiveTrips")
    List<Trip> getAllActiveTrips() {
        List<Trip> list = new ArrayList<>();
        return tripService.getAllActiveTrips();
    }

}
