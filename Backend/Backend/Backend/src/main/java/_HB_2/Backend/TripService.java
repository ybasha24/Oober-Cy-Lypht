package _HB_2.Backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class TripService {

    @Autowired
    TripRepository tripRepository;

    @Autowired
    DriverRepository driverRepository;

    @Autowired
    RiderRepository riderRepository;

    //there should be no riders in the trip initially
    public Trip createTripByDriver(int Id, Trip trip) {

        User d =  driverRepository.getById(Id);
        //uses trip constructor by driver
        //trip constructor by driver instantiates an empty set of riders
        Trip t = new Trip(trip.scheduledStartDate,
                        trip.scheduledEndDate,
                        //has a rider
                        false,
                        //has a driver
                        true,
                        //isConfirmed
                        false,
                        //hasStarted
                        false,
                        //isCompleted
                        false,
                        trip.originAddress,
                        trip.destAddress,
                        d,
                        trip.radius,
                        trip.maxNumberOfRiders,
                        //number of riders
                        0);

        tripRepository.save(t);
        return t;
    }

    public Trip getTripById(int id) {
        return tripRepository.findById(id);

    }

    public Trip addRiderToTripById(int tripId, int riderId){
        //need to do a try/catch here
            //what if we try to add a rider but the trip is already full???
        Trip addRiderToThis = tripRepository.findById(tripId);
        addRiderToThis.addRiderById(riderId);
        addRiderToThis.setHasARider(true);
        tripRepository.save(addRiderToThis);
        return tripRepository.findById(tripId);
    }

    public Trip removeRiderFromTripById(int tripId, int riderId){
        Trip removeRiderFromThis = tripRepository.findById(tripId);
        removeRiderFromThis.removeRiderById(riderId);
        if(removeRiderFromThis.numberOfRiders == 0){
            removeRiderFromThis.setHasARider(false);
        }
        tripRepository.save(removeRiderFromThis);
        return tripRepository.findById(tripId);
    }

    public Trip editTripById(int tripId, Trip newTripInfo) {

        Trip newTrip = tripRepository.findById(tripId);

        newTrip.setScheduledStartDate(newTripInfo.scheduledStartDate);
        newTrip.setScheduledEndDate(newTripInfo.scheduledEndDate);

        newTrip.setActualStartDate(newTripInfo.actualStartDate);
        newTrip.setActualEndDate(newTripInfo.actualEndDate);

        //need to adjust maxNumberOfRiders before we adjust the riders
        newTrip.maxNumberOfRiders = newTripInfo.maxNumberOfRiders;

        //Set the numberOfRiders to 0
        //the addRiderById funtion in the trip class will increment as we add riders below
        newTrip.numberOfRiders = 0;

        newTrip.hasARider = newTripInfo.hasARider;
        if (newTripInfo.hasARider) {
            List<Integer> newriderIds =  newTripInfo.getRiderIds();
            for ( Integer riderIds : newriderIds) {
                newTrip.addRiderById(riderIds);
            }
        }

        newTrip.hasADriver = newTripInfo.hasADriver;
        if (newTripInfo.hasADriver) {
            newTrip.setDriverId(newTripInfo.tripDriver);
        }

        newTrip.isConfirmed = newTripInfo.isConfirmed;
        newTrip.hasStarted = newTripInfo.hasStarted;
        newTrip.isCompleted = newTripInfo.isCompleted;

        newTrip.originAddress = newTripInfo.originAddress;
        newTrip.destAddress = newTripInfo.destAddress;

        newTrip.radius = newTripInfo.radius;

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
                    //check to make sure the trip has room for another rider
                    if (trip.numberOfRiders < trip.maxNumberOfRiders) {
                        validTrips.add(trip);
                    }
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

    public Trip completeTripById(int tripId) {
        Trip tripToBeCompleted = tripRepository.findById(tripId);
        tripToBeCompleted.setCompleted(true);
        tripRepository.save(tripToBeCompleted);
        return tripRepository.findById(tripId);
    }
}
