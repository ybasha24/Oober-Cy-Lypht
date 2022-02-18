package _HB_2.Backend;

import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class DriverService {

    public void save(Driver driver) {
        HashMap<Long, User> mapOfUsers = new HashMap<>();

        mapOfUsers.put(1L,driver);

    }
}
