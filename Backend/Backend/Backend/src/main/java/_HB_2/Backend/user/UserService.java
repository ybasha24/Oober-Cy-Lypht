package _HB_2.Backend.user;

import _HB_2.Backend.driver.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public User editUser(int id, User newUserInfo) {

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

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        list = userRepository.findAll();
        return list;

    }
}