package _HB_2.Backend.riderstop;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RiderStopService {

    private RiderStopRepository riderStopRepository;

    public List<RiderStop> getRiderStopsByTripId(int tripId) {

//        List<RiderStop> allRiderStops = riderStopRepository.findAll();
        List<RiderStop> riderStopsForThisTrip = riderStopRepository.getRiderStopsByTripId(tripId);

//        for(RiderStop riderStop : allRiderStops) {
//            if ()
//        }

        return riderStopsForThisTrip;
    }
}
