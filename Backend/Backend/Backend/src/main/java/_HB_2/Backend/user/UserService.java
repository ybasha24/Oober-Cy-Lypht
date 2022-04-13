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
        newUser.setFirstName(newUserInfo.getFirstName());
        newUser.setLastName(newUserInfo.getLastName());
        newUser.setAddress(newUserInfo.getAddress());
        newUser.setCity(newUserInfo.getCity());
        newUser.setState(newUserInfo.getState());
        newUser.setZip(newUserInfo.getZip());
        newUser.setEmail(newUserInfo.getEmail());
        newUser.setPhoneNumber(newUserInfo.getPhoneNumber());
        newUser.setPassword(newUserInfo.getPassword());

        userRepository.save(newUser);

        return getUserById(id);
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        list = userRepository.findAll();
        return list;

    }

    public String setProfilePicture(int userId, String path) {
        userRepository.findById(userId).setProfilePicture(path);
        String picturePath = userRepository.findById(userId).getProfilePicture();
        return picturePath;
    }

    public String deleteProfilePicture(int userId) {
        userRepository.findById(userId).setProfilePicture(null);
        String picturePath = userRepository.findById(userId).getProfilePicture();
        if (picturePath.equals(null)) {
            return "You have successfully removed the profile picture for user# " + userId;
        } else {
            return "There was a problem removing the profile picture for user# " + userId;
        }
    }
}
