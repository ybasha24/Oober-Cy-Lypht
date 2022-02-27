package _HB_2.Backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AdminService {

    @Autowired
    AdminRepository adminRepository;

    public Admin createAdmin(Admin admin) {

        //set the Admin flag
        admin.isAnAdmin = true;
        Admin a = adminRepository.save(admin);
        return a;
    }

    public User getAdminById(int id) {
        return adminRepository.findById(id);
    }
}
