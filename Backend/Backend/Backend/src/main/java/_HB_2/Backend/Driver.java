package _HB_2.Backend;

public class Driver extends User{
    public Driver(String firstName, String lastName, String address, String state, String zip, String email, String phoneNumber) {
        super(firstName, lastName, address, state, zip, email, phoneNumber);
        this.isADriver = true;
    }
}
