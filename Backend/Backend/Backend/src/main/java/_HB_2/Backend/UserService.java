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

    public User editUser(int id, User newUserInfo) {
        userRepository.getById(id).setFirstName(newUserInfo.firstName);
        userRepository.getById(id).setLastName(newUserInfo.lastName);
        userRepository.getById(id).setAddress(newUserInfo.address);
        userRepository.getById(id).setCity(newUserInfo.city);
        userRepository.getById(id).setState(newUserInfo.state);
        userRepository.getById(id).setZip(newUserInfo.zip);
        userRepository.getById(id).setEmail(newUserInfo.email);
        userRepository.getById(id).setPhoneNumber(newUserInfo.phoneNumber);
        userRepository.getById(id).setPassword(newUserInfo.password);
        userRepository.getById(id).setADriver(newUserInfo.isADriver);
        userRepository.getById(id).setARider(newUserInfo.isARider);
        userRepository.getById(id).setAnAdmin(newUserInfo.isAnAdmin);

        //do we need to save first?

        return userRepository.getById(id);
    }
}
