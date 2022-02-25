package _HB_2.Backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


    public User getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public User getUserBbyId(int id) {
        return userRepository.findById(id);
    }

    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }
}
