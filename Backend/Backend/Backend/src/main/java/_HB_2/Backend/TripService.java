package _HB_2.Backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TripService {

    @Autowired
    TripRepository tripRepository;

    public Trip createTripByDriver(Trip trip) {

        //uses driver constructor
        Trip t = new Trip(
                trip.DriverId,
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

    public User editUser(int id, Trip newTripoInfo) {

        User newUser = userRepository.findById(id);
        newUser.setFirstName(newUserInfo.firstName);
        newUser.setLastName(newUserInfo.lastName);
        newUser.setAddress(newUserInfo.address);
        newUser.setCity(newUserInfo.city);
        newUser.setState(newUserInfo.state);
        newUser.setZip(newUserInfo.zip);
        newUser.setEmail(newUserInfo.email);
        newUser.setPhoneNumber(newUserInfo.phoneNumber);
        newUser.setPassword(newUserInfo.password);

        userRepository.save(newUser);

        return getUserById(id);
    }


    public Trip editTripById(int id, Trip t) {

        Trip newTrip = tripRepository.findById(id);
        newTrip.
    }
}
