package Backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// import java.util.HashMap;

@Service
public class DriverService {

    @Autowired
    DriverRepository driverRepository;

    public void createDriver(Driver driver) {

        //set the driver flag
        driver.isADriver = true;
        driverRepository.save(driver);
    }

    public User getDriverById(int id) {
        return driverRepository.findById(id);
    }
}
