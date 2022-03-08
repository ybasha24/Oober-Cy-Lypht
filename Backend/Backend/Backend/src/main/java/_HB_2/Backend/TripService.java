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
        Trip t = new Trip(
                d,
                trip.scheduledStartDate,
                trip.scheduledEndDate,
                //hasADriver
                true,
                trip.startAddress,
                trip.startCity,
                trip.startState,
                trip.startZip,
                trip.endAddress,
                trip.endCity,
                trip.endState,
                trip.endZip,
                trip.radius);

        tripRepository.save(t);
        return t;
    }

//    public List getTripsByDriverId(int id) {
//        List list = new ArrayList<>();
//
//        return list;
//    }

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

        newTrip.setStartAddress(newTripInfo.startAddress);
        newTrip.setStartCity(newTripInfo.startCity);
        newTrip.setStartState(newTripInfo.startState);
        newTrip.setStartZip(newTripInfo.startZip);

        newTrip.setEndAddress(newTripInfo.endAddress);
        newTrip.setEndCity(newTripInfo.endCity);
        newTrip.setEndCity(newTripInfo.endState);
        newTrip.setEndZip(newTripInfo.endZip);

        newTrip.setRadius(newTripInfo.radius);

        tripRepository.save(newTrip);

        return getTripById(tripId);
    }


}
