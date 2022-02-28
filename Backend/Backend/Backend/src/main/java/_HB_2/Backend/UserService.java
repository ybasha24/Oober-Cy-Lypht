package _HB_2.Backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import javax.persistence.Column;
import java.util.HashMap;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    DriverRepository driverRepository;


    public User getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public User getUserById(int id) {
        return userRepository.findById(id);
    }

    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }

    //Not sure why this doesn't work
    //See notes in userController
//    public User editUser(int id, User newUserInfo) {
//
//        User newUser = userRepository.findById(id);
//        newUser.setFirstName(newUserInfo.firstName);
//        newUser.setLastName(newUserInfo.lastName);
//        newUser.setAddress(newUserInfo.address);
//        newUser.setCity(newUserInfo.city);
//        newUser.setState(newUserInfo.state);
//        newUser.setZip(newUserInfo.zip);
//        newUser.setEmail(newUserInfo.email);
//        newUser.setPhoneNumber(newUserInfo.phoneNumber);
//        newUser.setPassword(newUserInfo.password);
//        newUser.setADriver(newUserInfo.isADriver);
//        newUser.setARider(newUserInfo.isARider);
//        newUser.setAnAdmin(newUserInfo.isAnAdmin);
//
//        userRepository.save(newUser);
//
//        return getUserById(id);
//    }
}
