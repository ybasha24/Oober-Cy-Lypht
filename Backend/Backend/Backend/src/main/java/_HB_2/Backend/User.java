package _HB_2.Backend;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
//we will need to define the name of the table where we storer the users
//@Table(name = "users")
public abstract class User {

    @Id
    //this would be the strategy by which the id is generated
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String firstName;
    String lastName;
    String address;
    String state;
    String zip;
    String email;
    String phoneNumber;
    Boolean isADriver;
    Boolean isARider;
    Boolean isAnAdmin;

    //https://stackoverflow.com/questions/18099127/java-entity-why-do-i-need-an-empty-constructor
    public User() {}

    //no Boolean Values for User-These should be set in the subclass constructors
    public User(String firstName, String lastName, String address, String state, String zip, String email, String phoneNumber) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.state = state;
        this.zip = zip;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
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
