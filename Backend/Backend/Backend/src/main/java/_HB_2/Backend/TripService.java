package _HB_2.Backend;

import org.springframework.beans.factory.annotation.Autowired;

public class TripService {

    @Autowired
    TripRepository tripRepository;

    public Trip createTrip(Trip trip) {

        //do we need to check the driver and rider flags here?
        Trip t = tripRepository.save(trip);
        return t;
    }

}
