package _HB_2.Backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TripService {

    @Autowired
    TripRepository tripRepository;

    @Autowired
    DriverRepository driverRepository;

    @Autowired
    RiderRepository riderRepository;

    public Trip createTripByDriver(int Id, Trip trip) {

        User d =  driverRepository.getById(Id);
        //uses driver constructor
        Trip t = new Trip(trip.scheduledStartDate,
                        trip.scheduledEndDate,
                        //has a driver
                        true,
                        trip.originAddress,
                        trip.destAddress,
                        d,
                        trip.radius);

        tripRepository.save(t);
        return t;
    }

    public Trip getTripById(int id) {
        return tripRepository.findById(id);

    }

    public Trip editTripById(int tripId, int riderId, int driverId, Trip newTripInfo) {

        Trip newTrip = tripRepository.findById(tripId);

        User r =  riderRepository.getById(riderId);
        User d =  driverRepository.getById(driverId);
        newTrip.setRiderId(r);
        newTrip.setDriverId(d);

        newTrip.setScheduledStartDate(newTripInfo.scheduledStartDate);
        newTrip.setScheduledEndDate(newTripInfo.scheduledEndDate);

        newTrip.setActualStartDate(newTripInfo.actualStartDate);
        newTrip.setActualEndDate(newTripInfo.actualEndDate);

        newTrip.setHasARider(newTripInfo.hasARider);
        newTrip.setHasADriver(newTripInfo.hasADriver);
        newTrip.setConfirmed(newTripInfo.isConfirmed);
        newTrip.setConfirmed(newTripInfo.hasStarted);
        newTrip.setCompleted(newTripInfo.isConfirmed);

        newTrip.setOriginAddress(newTripInfo.originAddress);
        newTrip.setDestAddress(newTripInfo.destAddress);

        newTrip.setRadius(newTripInfo.radius);

        tripRepository.save(newTrip);

        return getTripById(tripId);
    }

    public List<Trip> getAllTrips() {
        List<Trip> list = new ArrayList<>();
        list = tripRepository.findAll();
        return list;
    }

    //returns a list of all trips that have not been completed
    public List<Trip> getAllActiveTrips() {
        List<Trip> list = new ArrayList<>();
        list = tripRepository.findAll();

        List<Trip> activeTrips = new ArrayList<>();
        for(Trip trip: list) {
            if (!trip.isCompleted) {
                activeTrips.add(trip);
            }
        }

        return activeTrips;
    }

    //we should consider speeding this up by writing a sql query to only retrieve
    //the trips that we want from the database rather than filtering them here.
    public List<Trip> getTripsForRider(LocalDateTime startDate, LocalDateTime endData) {

        List<Trip> allTrips = tripRepository.findAll();

        List<Trip> validTrips = new ArrayList<>();
        for(Trip trip: allTrips) {
            //need this check for trips in database with null start and end times.
            //avoids null pointer exceptions
            if (trip.scheduledStartDate != null && trip.scheduledEndDate != null) {
                if (!trip.hasStarted && trip.scheduledStartDate.equals(startDate) && trip.scheduledEndDate.equals(endData)) {
                    validTrips.add(trip);
                }
            }
        }

        return validTrips;

    }
}
