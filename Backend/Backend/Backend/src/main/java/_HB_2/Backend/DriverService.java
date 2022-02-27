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
        driverRepository.getById(id).setFirstName(newUserInfo.firstName);
        driverRepository.getById(id).setLastName(newUserInfo.lastName);
        driverRepository.getById(id).setAddress(newUserInfo.address);
        driverRepository.getById(id).setCity(newUserInfo.city);
        driverRepository.getById(id).setState(newUserInfo.state);
        driverRepository.getById(id).setZip(newUserInfo.zip);
        driverRepository.getById(id).setEmail(newUserInfo.email);
        driverRepository.getById(id).setPhoneNumber(newUserInfo.phoneNumber);
        driverRepository.getById(id).setPassword(newUserInfo.password);
        driverRepository.getById(id).setADriver(newUserInfo.isADriver);
        driverRepository.getById(id).setARider(newUserInfo.isARider);
        driverRepository.getById(id).setAnAdmin(newUserInfo.isAnAdmin);

        return driverRepository.save(id);
        //return oldUserInfo;
    }
}
