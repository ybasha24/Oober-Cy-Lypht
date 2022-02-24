package _HB_2.Backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class RiderService {

    @Autowired
    RiderRepository riderRepository;

    public void createRider(Rider rider) {

        //set the rider flag
        rider.isARider = true;
        riderRepository.save(rider);
    }

    public User getRiderbyEmail(String email){
        return riderRepository.findByEmail(email);
    }

    public User getRidberbyFirstname(String firstName){
        return riderRepository.findByFirstName(firstName);
    }

    public User getRiderbyId(int id) {
        return riderRepository.findById(id);
    }

    public void deleteRiderById(int id) {
        riderRepository.deleteById(id);
    }
}
