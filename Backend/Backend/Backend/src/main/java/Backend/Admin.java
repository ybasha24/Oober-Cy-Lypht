package Backend;

import javax.persistence.Entity;

@Entity
public class Admin extends User{

    public Admin(String firstName, String lastName, String address, String city, String state, String zip, String email, String phoneNumber) {
        super(firstName, lastName, address, city, state, zip, email, phoneNumber);
        this.isAnAdmin = true;
    }

    public Admin() {

    }
}
