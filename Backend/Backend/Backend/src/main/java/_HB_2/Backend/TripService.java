package _HB_2.Backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TripService {

    @Autowired
    TripRepository tripRepository;

    public Trip createTripByDriver(Trip trip) {

        //uses driver constructor
        Trip t = new Trip(
                trip.DriverId,
//                trip.scheduledStartDate,
//                trip.scheduledEndDate,
                true,
                trip.startAddress,
                trip.startCity,
                trip.startState,
                trip.startZip,

                trip.endAddress,
                trip.endCity,
                trip.endState,
                trip.endZip);

        tripRepository.save(t);
        return t;
    }

}
