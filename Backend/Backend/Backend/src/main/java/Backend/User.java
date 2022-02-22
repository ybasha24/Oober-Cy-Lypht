package Backend;

import javax.persistence.*;

@Entity
//we will need to define the name of the table where we store the users
//@Table(name = "users")
public abstract class User {

    /*
     * The annotation @ID marks the field below as the primary key for the table created by springboot
     * The @GeneratedValue generates a value if not already present, The strategy in this case is to start from 1 and increment for each table
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    String firstName;
    String lastName;
    String address;
    String city;
    String state;
    String zip;
    String email;
    String phoneNumber;
    Boolean isADriver;
    Boolean isARider;
    Boolean isAnAdmin;

    //no Boolean Values for User-These should be set in the subclass constructors
    public User(String firstName, String lastName, String address, String city, String state, String zip, String email, String phoneNumber) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    //https://stackoverflow.com/questions/18099127/java-entity-why-do-i-need-an-empty-constructor
    public User() {}

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean getADriver() {
        return isADriver;
    }

    public void setADriver(Boolean ADriver) {
        isADriver = ADriver;
    }

    public Boolean getARider() {
        return isARider;
    }

    public void setARider(Boolean ARider) {
        isARider = ARider;
    }

    public Boolean getAnAdmin() {
        return isAnAdmin;
    }

    public void setAnAdmin(Boolean anAdmin) {
        isAnAdmin = anAdmin;
    }
}
