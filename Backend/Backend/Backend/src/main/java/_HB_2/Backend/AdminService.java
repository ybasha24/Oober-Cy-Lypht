package _HB_2.Backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AdminService {

    @Autowired
    AdminRepository adminRepository;

    public void createAdmin(Admin admin) {

        //set the driver flag
        admin.isAnAdmin = true;
        adminRepository.save(admin);
    }

    public User getAdminById(int id) {
        return adminRepository.findById(id);
    }
}
