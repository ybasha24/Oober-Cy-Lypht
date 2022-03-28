package _HB_2.Backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AdminService {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    public Admin createAdmin(Admin admin) {

        //set the Admin flag
        admin.isAnAdmin = true;
        Admin a = adminRepository.save(admin);
        return a;
    }

    public User getAdminById(int id) {
        return adminRepository.findById(id);
    }

    public User getAdminbyEmail(String email){
        return adminRepository.findByEmail(email);
    }

    public User addAsRider(int id){
        User newUser = userRepository.findById(id);
        if(newUser.isARider == false){
            newUser.setARider(true);
        }
        userRepository.save(newUser);

        return userService.getUserById(id);
    }

    public User addAsDriver(int id){
        User newUser = userRepository.findById(id);
        if(newUser.isADriver == false){
            newUser.setADriver(true);
        }
        userRepository.save(newUser);

        return userService.getUserById(id);
    }

    public User removeAsRider(int id){
        User newUser = userRepository.findById(id);
        newUser.setARider(false);
        if (newUser.isADriver == false & newUser.isAnAdmin == false){
            userRepository.deleteById(id);
        }
        else {
            userRepository.save(newUser);
        }

        return userService.getUserById(id);
    }

    public User removeAsDriver(int id){
        User newUser = userRepository.findById(id);
        newUser.setADriver(false);
        if (newUser.isARider == false & newUser.isAnAdmin == false){
            userRepository.deleteById(id);
        }
        else {
            userRepository.save(newUser);
        }

        return userService.getUserById(id);
    }
}
