package _HB_2.Backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class DriverService {

    @Autowired
    DriverRepository driverRepository;

    public void createDriver(Driver driver) {

        driverRepository.save(driver);
    }
}
