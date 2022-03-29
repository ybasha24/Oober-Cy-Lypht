package _HB_2.Backend.trip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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

    @PutMapping("/addRiderToTrip")
    String addRiderToTrip(@RequestParam int tripId,
                          @RequestParam int riderId) {
        tripService.addRiderToTrip(tripId, riderId);
        return "Successfully added rider with id " + riderId + " to trip";
    }

    @PutMapping("/removeRiderFromTrip")
    String removeRiderFromTrip(@RequestParam int tripId,
                               @RequestParam int riderId) {
        tripService.removeRiderFromTrip(tripId, riderId);
        return "Successfully removed rider with id " + riderId + " from trip";
    }

        @PutMapping("/completeTrip")
    Trip completeTripById(
            @RequestParam int id) {
        return tripService.completeTripById(id);
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

    @GetMapping("/getTripsForRider")
    List<Trip> getTripsForRider(
            @RequestParam("scheduledStartDate")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime scheduledStartDate,

            @RequestParam("scheduledEndDate")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime scheduledEndDate) {

        return tripService.getTripsForRider(scheduledStartDate,scheduledEndDate);
    }

    @DeleteMapping("/deleteTripById")
    void deleteTripById(
            @RequestParam int id) {

        tripService.deleteTripById(id);
    }

}
