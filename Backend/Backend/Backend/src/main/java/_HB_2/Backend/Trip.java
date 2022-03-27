package _HB_2.Backend;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    LocalDateTime scheduledStartDate;
    LocalDateTime scheduledEndDate;

    LocalDateTime actualStartDate;
    LocalDateTime actualEndDate;

    boolean hasARider;
    boolean hasADriver;
    boolean isConfirmed;
    boolean hasStarted;
    boolean isCompleted;

    String originAddress;

    String destAddress;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "Driver_ID")
    User tripDriver;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "Trip_Riders",
                joinColumns = @JoinColumn(name = "trip_id"),
                inverseJoinColumns = @JoinColumn(name = "rider_id"))
    private Set<User> riders;

    int maxNumberOfRiders;

    int numberOfRiders;

    //represent distances from driver start location
    //that the driver is willing to pick up/drop off a rider
    int radius;

    //Empty Constructor
    public Trip() {
    }

    public Trip(LocalDateTime scheduledStartDate, LocalDateTime scheduledEndDate, LocalDateTime actualStartDate, LocalDateTime actualEndDate, boolean hasARider, boolean hasADriver, boolean isConfirmed, boolean hasStarted, boolean isCompleted, String originAddress, String destAddress, User tripDriver, Set<User> riders, int maxNumberOfRiders, int numberOfRiders, int radius) {
        this.scheduledStartDate = scheduledStartDate;
        this.scheduledEndDate = scheduledEndDate;
        this.actualStartDate = actualStartDate;
        this.actualEndDate = actualEndDate;
        this.hasARider = hasARider;
        this.hasADriver = hasADriver;
        this.isConfirmed = isConfirmed;
        this.hasStarted = hasStarted;
        this.isCompleted = isCompleted;
        this.originAddress = originAddress;
        this.destAddress = destAddress;
        this.tripDriver = tripDriver;
        this.riders = riders;
        this.maxNumberOfRiders = maxNumberOfRiders;
        this.numberOfRiders = numberOfRiders;
        this.radius = radius;
    }

//    //create trip by Driver
//    public Trip(LocalDateTime scheduledStartDate,
//                LocalDateTime scheduledEndDate,
//                boolean hasARider,
//                boolean hasADriver,
//                boolean isConfirmed,
//                boolean hasStarted,
//                boolean isCompleted,
//                String originAddress,
//                String destAddress,
//                User tripDriver,
//                int radius,
//                int maxNumberOfRiders,
//                int numberOfRiders) {
//
//        this.scheduledStartDate = scheduledStartDate;
//        this.scheduledEndDate = scheduledEndDate;
//        this.hasARider = hasARider;
//        this.hasADriver = hasADriver;
//        this.isConfirmed = isConfirmed;
//        this.hasStarted = hasStarted;
//        this.isCompleted = isCompleted;
//        this.originAddress = originAddress;
//        this.destAddress = destAddress;
//        this.tripDriver = tripDriver;
//        this.radius = radius;
//        //we won't set any riders but need to instatiate the set
//        this.riders = new HashSet<User>();
//        this.maxNumberOfRiders = maxNumberOfRiders;
//        this.numberOfRiders = numberOfRiders;
//    }

    public List<Integer> getRiderIds() {
        List<Integer> riderIds = new ArrayList<>();

        for (User user : riders ) {
            riderIds.add(user.getId());
        }

        return riderIds;
    }

    public void addRider(User rider) {
        //This might need to throw an error
            //check to see if the rider/user isARider
            //what if we don't have room for the rider?
        if (numberOfRiders < maxNumberOfRiders) {

            riders.add(rider);
            numberOfRiders++;
            hasARider = true;
        }
    }

    public void removeRiderById(int riderId){
        //Probably need to add try/catch to avoid trying to remove rider from trip that doesn't have a rider
        User user = new UserService().getUserById(riderId);
        riders.remove(user);
        numberOfRiders--;

        if (numberOfRiders == 0) {
            hasARider = false;
        }

    }

    public int getId() {return id;}

    public LocalDateTime getScheduledStartDate() {
        return scheduledStartDate;
    }

    public void setScheduledStartDate(LocalDateTime scheduledStartDate) {
        this.scheduledStartDate = scheduledStartDate;
    }

    public LocalDateTime getScheduledEndDate() {
        return scheduledEndDate;
    }

    public void setScheduledEndDate(LocalDateTime scheduledEndDate) {
        this.scheduledEndDate = scheduledEndDate;
    }

    public LocalDateTime getActualStartDate() {
        return actualStartDate;
    }

    public void setActualStartDate(LocalDateTime actualStartDate) {
        this.actualStartDate = actualStartDate;
    }

    public LocalDateTime getActualEndDate() {
        return actualEndDate;
    }

    public void setActualEndDate(LocalDateTime actualEndDate) {
        this.actualEndDate = actualEndDate;
    }

    public boolean isHasARider() {
        return hasARider;
    }

    public void setHasARider(boolean hasARider) {
        this.hasARider = hasARider;
    }

    public boolean isHasADriver() {
        return hasADriver;
    }

    public void setHasADriver(boolean hasADriver) {
        this.hasADriver = hasADriver;
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(boolean confirmed) {
        isConfirmed = confirmed;
    }

    public boolean isHasStarted() {
        return hasStarted;
    }

    public void setHasStarted(boolean hasStarted) {
        this.hasStarted = hasStarted;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public String getOriginAddress() {
        return originAddress;
    }

    public void setOriginAddress(String originAddress) {
        this.originAddress = originAddress;
    }

    public String getDestAddress() {
        return destAddress;
    }

    public void setDestAddress(String destAddress) {
        this.destAddress = destAddress;
    }

    public User getTripDriver() {
        return tripDriver;
    }

    public void setTripDriver(User tripDriver) {
        this.tripDriver = tripDriver;
    }

    public Set<User> getRiders() {
        return riders;
    }

    public void setRiders(Set<User> riders) {
        this.riders = riders;
    }

    public int getMaxNumberOfRiders() {
        return maxNumberOfRiders;
    }

    public void setMaxNumberOfRiders(int maxNumberOfRiders) {
        this.maxNumberOfRiders = maxNumberOfRiders;
    }

    public int getNumberOfRiders() {
        return numberOfRiders;
    }

    public void setNumberOfRiders(int numberOfRiders) {
        this.numberOfRiders = numberOfRiders;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
