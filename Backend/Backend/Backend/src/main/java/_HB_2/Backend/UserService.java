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


    public User getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public User getUserById(int id) {
        return userRepository.findById(id);
    }

    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }

//    public User editUser(int id, User newUserInfo) {
//        User oldUserInfo = userRepository.getById(id);
//        oldUserInfo.setFirstName(newUserInfo.firstName);
//        oldUserInfo.setLastName(newUserInfo.lastName);
//        oldUserInfo.setAddress(newUserInfo.address);
//        oldUserInfo.setCity(newUserInfo.city);
//        oldUserInfo.setState(newUserInfo.state);
//        oldUserInfo.setZip(newUserInfo.zip);
//        oldUserInfo.setEmail(newUserInfo.email);
//        oldUserInfo.setPhoneNumber(newUserInfo.phoneNumber);
//        oldUserInfo.setPassword(newUserInfo.password);
//        oldUserInfo.setADriver(newUserInfo.isADriver);
//        oldUserInfo.setARider(newUserInfo.isARider);
//        oldUserInfo.setAnAdmin(newUserInfo.isAnAdmin);
//
//        return oldUserInfo;
//    }
}
