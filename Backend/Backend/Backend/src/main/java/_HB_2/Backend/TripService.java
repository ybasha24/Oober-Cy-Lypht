package _HB_2.Backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Trip addRiderToTrip(int tripId, int riderId){
        Trip addRiderToThis = tripRepository.findById(tripId);
        User r = driverRepository.getById(riderId);
        addRiderToThis.setRiderId(r);
        addRiderToThis.setHasARider(true);
        return  addRiderToThis;
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
    public List<Trip> getTripsForRider(LocalDateTime startDate, LocalDateTime endDate) {

        List<Trip> allTrips = tripRepository.findAll();

        Map<String, LocalDateTime> timeWindows = getTimeWindows(startDate, endDate);

        List<Trip> validTrips = new ArrayList<>();
        for(Trip trip: allTrips) {
            //need this check for trips in database with null start and end times.
            //avoids null pointer exceptions- this check could be deleted if all rows in
            //database had scheduled start dates and scheduled end dates.
            if (trip.scheduledStartDate != null && trip.scheduledEndDate != null) {
                //check to see if the start date and end dates of the trip are the same as our request
                //currently we ignore the time of day.  If the day is the same the trip is valid.
                if (    !trip.hasStarted &&
                        trip.scheduledStartDate.isAfter(timeWindows.get("earliestStartDate")) &&
                        trip.scheduledStartDate.isBefore(timeWindows.get("latestStartDate")) &&
                        trip.scheduledEndDate.isAfter(timeWindows.get("earliestEndDate")) &&
                        trip.scheduledEndDate.isBefore(timeWindows.get("latestEndDate"))) {

                    validTrips.add(trip);
                }
            }
        }

        return validTrips;

    }

    //returns a map of the windows for the trip we want
    private Map<String, LocalDateTime> getTimeWindows(LocalDateTime startDate, LocalDateTime endDate) {
        Map<String, LocalDateTime> timeWindows = new HashMap<>();

        LocalDateTime earliestStartDate = beginningOfDay(startDate);
        LocalDateTime latestStartDate = endOfDay(startDate);
        LocalDateTime earliestEndDate = beginningOfDay(endDate);
        LocalDateTime latestEndDate = endOfDay(endDate);

        timeWindows.put("earliestStartDate", earliestStartDate);
        timeWindows.put("latestStartDate", latestStartDate);
        timeWindows.put("earliestEndDate", earliestEndDate);
        timeWindows.put("latestEndDate", latestEndDate);

        return timeWindows;
    }

    //edits a LocalDateTime to the beginning of the day
    private LocalDateTime beginningOfDay(LocalDateTime startDate) {
        LocalDateTime answer = startDate.withHour(00).withMinute(00).withSecond(00);

        return answer;
    }

    //edits a LocalDateTime to the end of the day
    private LocalDateTime endOfDay(LocalDateTime startDate) {
        LocalDateTime answer = startDate.withHour(23).withMinute(59).withSecond(59);

        return answer;
    }
}
