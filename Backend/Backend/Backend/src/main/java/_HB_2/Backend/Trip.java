package _HB_2.Backend;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @ManyToOne
    @JoinColumn(name = "Driver_ID")
    User tripDriver;

    @ManyToOne
    @JoinColumn(name = "Rider_ID", nullable = true)
    User tripRider;


    //represent distances from driver start location
    //that the driver is willing to pick up/drop off a rider
    int radius;

    public Trip() {
    }

    //Constructor with all attributes


    public Trip(LocalDateTime scheduledStartDate, LocalDateTime scheduledEndDate, LocalDateTime actualStartDate, LocalDateTime actualEndDate, boolean hasARider, boolean hasADriver, boolean isConfirmed, boolean hasStarted, boolean isCompleted, String originAddress, String destAddress, User tripDriver, User tripRider, int radius) {
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
        this.tripRider = tripRider;
        this.radius = radius;
    }

    //create trip by Driver
    public Trip(LocalDateTime scheduledStartDate, LocalDateTime scheduledEndDate, boolean hasADriver, String originAddress, String destAddress, User tripDriver, int radius) {
        this.scheduledStartDate = scheduledStartDate;
        this.scheduledEndDate = scheduledEndDate;
        this.hasADriver = hasADriver;
        this.originAddress = originAddress;
        this.destAddress = destAddress;
        this.tripDriver = tripDriver;
        this.radius = radius;
    }

    public int getRiderId() {
        if(tripRider == null){
            return 0;
        }
        return tripRider.getId();
    }

    public void setRiderId(User newRider) {
        tripRider = newRider;
    }

    public void removeRiderId(int riderId){
        if(tripRider.getId() == riderId){
            tripRider = null;
        }
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
