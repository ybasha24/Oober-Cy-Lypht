package _HB_2.Backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class TripRiderService {

    @Autowired
    DriverRepository driverRepository;

    public User getDriverById(int id) {
        return driverRepository.findById(id);
    }

    public User getDriverbyEmail(String email){
        return driverRepository.findByEmail(email);
    }

}
