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

    public User getDriverbyEmail(String email){
        return driverRepository.findByEmail(email);
    }

    public User editDriver(int id, User newUserInfo) {

        User newUser = driverRepository.findById(id);
        newUser.setFirstName(newUserInfo.firstName);
        newUser.setLastName(newUserInfo.lastName);
        newUser.setAddress(newUserInfo.address);
        newUser.setCity(newUserInfo.city);
        newUser.setState(newUserInfo.state);
        newUser.setZip(newUserInfo.zip);
        newUser.setEmail(newUserInfo.email);
        newUser.setPhoneNumber(newUserInfo.phoneNumber);
        newUser.setPassword(newUserInfo.password);
        newUser.setADriver(newUserInfo.isADriver);
        newUser.setARider(newUserInfo.isARider);
        newUser.setAnAdmin(newUserInfo.isAnAdmin);

        driverRepository.save(newUser);

        return getDriverById(id);
    }
}
