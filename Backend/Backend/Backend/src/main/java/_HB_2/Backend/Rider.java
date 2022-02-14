package _HB_2.Backend;

public class Rider extends User{

    public Rider(String firstName, String lastName, String address, String state, String zip, String email, String phoneNumber) {
        super(firstName, lastName, address, state, zip, email, phoneNumber);
    }

    public Rider(int id, String firstName, String lastName, String address, String state, String zip, String email, String phoneNumber) {
        super(id, firstName, lastName, address, state, zip, email, phoneNumber);
    }
}
