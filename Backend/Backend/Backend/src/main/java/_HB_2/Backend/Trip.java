package _HB_2.Backend;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    //This is accurate
    @ManyToOne
    @JoinColumn(name = "Driver_ID")
    User tripDriver;

    @OneToMany
    @JoinColumn(name = "TripRider_ID", nullable = true)
//    User tripRider;
    TripRiders tripRiders;

    int numberOfRiders;


    //represent distances from driver start location
    //that the driver is willing to pick up/drop off a rider
    int radius;

    //Empty Constructor
    public Trip() {
    }

    //Constructor with all attributes


    public Trip(LocalDateTime scheduledStartDate, LocalDateTime scheduledEndDate, LocalDateTime actualStartDate, LocalDateTime actualEndDate, boolean hasARider, boolean hasADriver, boolean isConfirmed, boolean hasStarted, boolean isCompleted, String originAddress, String destAddress, User tripDriver, List<Integer> riderIds, int radius, int numberOfRiders) {
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
        //here we make a call to make a new tripRider
        this.tripRiders = new TripRiders(numberOfRiders, riderIds);
        this.radius = radius;
        this.numberOfRiders = numberOfRiders;
    }

    //create trip by Driver
    public Trip(LocalDateTime scheduledStartDate, LocalDateTime scheduledEndDate, boolean hasADriver, String originAddress, String destAddress, User tripDriver, int radius, int numberOfRiders) {
        this.scheduledStartDate = scheduledStartDate;
        this.scheduledEndDate = scheduledEndDate;
        this.hasADriver = hasADriver;
        this.originAddress = originAddress;
        this.destAddress = destAddress;
        this.tripDriver = tripDriver;
        this.radius = radius;
        this.numberOfRiders = numberOfRiders;
    }

    public List<Integer> getRiderIds() {
        List<Integer> riderIds = new ArrayList<>();

        if(tripRiders == null) {
            riderIds.add(0);
            return riderIds;
        }

        for (int i = 0; i < numberOfRiders; i++) {
            riderIds = tripRiders.getRiderIds;
            return riderIds;
        }
    }

    public void addRiderId(int riderId) {
        tripRiders.addRiderId(riderId);
    }

    public void removeRider(int riderId){
        //Probably need to add try/catch to avoid trying to remove rider from trip that isn't a rider
        tripRiders.removeRiderId(riderId);
    }

    public int getDriverId() {
        return tripDriver.getId();
    }

    public void setDriverId(User newDriver) {
        tripDriver = newDriver;
    }

    public LocalDateTime getScheduledStartDate() {return scheduledStartDate;}

    public void setScheduledStartDate(LocalDateTime scheduledStartDate) {this.scheduledStartDate = scheduledStartDate;}

    public LocalDateTime getScheduledEndDate() {return scheduledEndDate;}

    public void setScheduledEndDate(LocalDateTime scheduledEndDate) {this.scheduledEndDate = scheduledEndDate;}

    public LocalDateTime getActualStartDate() {return actualStartDate;}

    public void setActualStartDate(LocalDateTime actualStartDate) {this.actualStartDate = actualStartDate;}

    public LocalDateTime getActualEndDate() {return actualEndDate;}

    public void setActualEndDate(LocalDateTime actualEndDate) {this.actualEndDate = actualEndDate;}

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

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getOriginAddress() {return originAddress;}

    public void setOriginAddress(String originAddress) {this.originAddress = originAddress;}

    public String getDestAddress() {return destAddress;}

    public void setDestAddress(String destAddress) {this.destAddress = destAddress;}

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
