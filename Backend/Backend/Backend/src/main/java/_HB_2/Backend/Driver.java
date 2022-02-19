package _HB_2.Backend;

import javax.persistence.Entity;

@Entity
public class Driver extends User {
    public Driver(String firstName, String lastName, String address, String state, String zip, String email, String phoneNumber) {
        super(firstName, lastName, address, state, zip, email, phoneNumber);
        this.isADriver = true;
    }

    public Driver() {

    }
}

//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//
//@Entity
//public class Driver {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//
//    String firstName;
//    String lastName;
//    String address;
//    String state;
//    String zip;
//    String email;
//    String phoneNumber;
//    Boolean isADriver;
//
//    public Driver(String firstName, String lastName, String address, String state, String zip, String email, String phoneNumber) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.address = address;
//        this.state = state;
//        this.zip = zip;
//        this.email = email;
//        this.phoneNumber = phoneNumber;
//        this.isADriver = true;
//    }
//
//    public Driver() {
//
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public String getState() {
//        return state;
//    }
//
//    public void setState(String state) {
//        this.state = state;
//    }
//
//    public String getZip() {
//        return zip;
//    }
//
//    public void setZip(String zip) {
//        this.zip = zip;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPhoneNumber() {
//        return phoneNumber;
//    }
//
//    public void setPhoneNumber(String phoneNumber) {
//        this.phoneNumber = phoneNumber;
//    }
//
//    public Boolean getADriver() {
//        return isADriver;
//    }
//
//    public void setADriver(Boolean ADriver) {
//        isADriver = ADriver;
//    }
//}
