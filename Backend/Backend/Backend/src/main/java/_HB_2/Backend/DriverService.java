package _HB_2.Backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class DriverService {

    @Autowired
    DriverRepository driverRepository;

    public Driver createDriver(Driver driver) {

        //set the driver flag
        driver.isADriver = true;
        Driver d = driverRepository.save(driver);
        return d;
    }

    public User getDriverById(int id) {
        return driverRepository.findById(id);
    }

    public void deleteDriverById(int id) {
        driverRepository.deleteById(id);
    }

    public User editDriver(int id, User newUserInfo) {
        User oldUserInfo = driverRepository.getById(id);
        oldUserInfo.setFirstName(newUserInfo.firstName);
        oldUserInfo.setLastName(newUserInfo.lastName);
        oldUserInfo.setAddress(newUserInfo.address);
        oldUserInfo.setCity(newUserInfo.city);
        oldUserInfo.setState(newUserInfo.state);
        oldUserInfo.setZip(newUserInfo.zip);
        oldUserInfo.setEmail(newUserInfo.email);
        oldUserInfo.setPhoneNumber(newUserInfo.phoneNumber);
        oldUserInfo.setPassword(newUserInfo.password);
        oldUserInfo.setADriver(newUserInfo.isADriver);
        oldUserInfo.setARider(newUserInfo.isARider);
        oldUserInfo.setAnAdmin(newUserInfo.isAnAdmin);

        return oldUserInfo;
    }
}
