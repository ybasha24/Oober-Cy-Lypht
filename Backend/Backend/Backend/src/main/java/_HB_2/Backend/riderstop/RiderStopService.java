package _HB_2.Backend.riderstop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RiderStopService {

    @Autowired
    private RiderStopRepository riderStopRepository;

    public List<RiderStop> getRiderStopsByTripId(int tripId) {

//        List<RiderStop> allRiderStops = new ArrayList<>();
//        allRiderStops = riderStopRepository.findAll();
//        List<RiderStop> riderStopsForThisTrip = new ArrayList<>();
//
//        for(RiderStop riderStop : allRiderStops) {
//            if (riderStop.getTripId() == tripId) {
//                riderStopsForThisTrip.add(riderStop);
//            }
//        }

        List<RiderStop> riderStopsForThisTrip = riderStopRepository.getRiderStopsByTripId(tripId);
        return riderStopsForThisTrip;
    }
}
