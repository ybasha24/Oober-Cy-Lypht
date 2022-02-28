package Backend;

import javax.persistence.Entity;

@Entity
public class Rider extends User{

    public Rider(String firstName, String lastName, String address, String city, String state, String zip, String email, String phoneNumber) {
        super(firstName, lastName, address, city, state, zip, email, phoneNumber);
        this.isARider = true;
    }

    public Rider() {

    }
}
