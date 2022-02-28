package Backend;

import javax.persistence.Entity;

@Entity
public class Driver extends User {

    public Driver(String firstName, String lastName, String address, String city, String state, String zip, String email, String phoneNumber) {
        super(firstName, lastName, address, city, state, zip, email, phoneNumber);
        this.isADriver = true;
    }

    public Driver() {

    }
}
