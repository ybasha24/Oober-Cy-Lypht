package _HB_2.Backend;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    int RiderId;
    int DriverId;

    LocalDateTime scheduledStartDate;
    LocalDateTime scheduledEndDate;

    LocalDateTime actualStartDate;
    LocalDateTime actualEndDate;

    boolean hasARider;
    boolean hasADriver;
    boolean isConfirmed;
    boolean hasStarted;
    boolean isCompleted;

    String startAddress;
    String startCity;
    String startState;
    String startZip;

    String endAddress;
    String endCity;
    String endState;
    String endZip;

    //represent distances from driver start location
    //that the driver is willing to pick up/drop off a rider
    int driverPickupRadius;
    int driverDropOffRadius;

    public Trip() {
    }

    //Constructor with all attributes
    public Trip(int riderId, int driverId, LocalDateTime scheduledStartDate, LocalDateTime scheduledEndDate, LocalDateTime actualStartDate, LocalDateTime actualEndDate, boolean hasARider, boolean hasADriver, boolean isConfirmed, boolean hasStarted, boolean isCompleted, String startAddress, String startCity, String startState, String startZip, String endAddress, String endCity, String endState, String endZip, int driverPickupRadius, int driverDropOffRadius) {
        RiderId = riderId;
        DriverId = driverId;
        this.scheduledStartDate = scheduledStartDate;
        this.scheduledEndDate = scheduledEndDate;
        this.actualStartDate = actualStartDate;
        this.actualEndDate = actualEndDate;
        this.hasARider = hasARider;
        this.hasADriver = hasADriver;
        this.isConfirmed = isConfirmed;
        this.hasStarted = hasStarted;
        this.isCompleted = isCompleted;
        this.startAddress = startAddress;
        this.startCity = startCity;
        this.startState = startState;
        this.startZip = startZip;
        this.endAddress = endAddress;
        this.endCity = endCity;
        this.endState = endState;
        this.endZip = endZip;
        this.driverPickupRadius = driverPickupRadius;
        this.driverDropOffRadius = driverDropOffRadius;
    }

    //create trip by Driver
    public Trip(int driverId, LocalDateTime scheduledStartDate, LocalDateTime scheduledEndDate, boolean hasADriver, String startAddress, String startCity, String startState, String startZip, String endAddress, String endCity, String endState, String endZip, int driverPickupRadius, int driverDropOffRadius) {
        DriverId = driverId;
        this.scheduledStartDate = scheduledStartDate;
        this.scheduledEndDate = scheduledEndDate;
        this.hasADriver = hasADriver;
        this.startAddress = startAddress;
        this.startCity = startCity;
        this.startState = startState;
        this.startZip = startZip;
        this.endAddress = endAddress;
        this.endCity = endCity;
        this.endState = endState;
        this.endZip = endZip;
        this.driverPickupRadius = driverPickupRadius;
        this.driverDropOffRadius = driverDropOffRadius;
    }

    public int getRiderId() {
        return RiderId;
    }

    public void setRiderId(int riderId) {
        RiderId = riderId;
    }

    public int getDriverId() {
        return DriverId;
    }

    public void setDriverId(int driverId) {
        DriverId = driverId;
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

    public String getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(String startAddress) {
        this.startAddress = startAddress;
    }

    public String getStartCity() {
        return startCity;
    }

    public void setStartCity(String startCity) {
        this.startCity = startCity;
    }

    public String getStartState() {
        return startState;
    }

    public void setStartState(String startState) {
        this.startState = startState;
    }

    public String getStartZip() {
        return startZip;
    }

    public void setStartZip(String startZip) {
        this.startZip = startZip;
    }

    public String getEndAddress() {
        return endAddress;
    }

    public void setEndAddress(String endAddress) {
        this.endAddress = endAddress;
    }

    public String getEndCity() {
        return endCity;
    }

    public void setEndCity(String endCity) {
        this.endCity = endCity;
    }

    public String getEndState() {
        return endState;
    }

    public void setEndState(String endState) {
        this.endState = endState;
    }

    public String getEndZip() {
        return endZip;
    }

    public void setEndZip(String endZip) {
        this.endZip = endZip;
    }

    public int getDriverPickupRadius() {return driverPickupRadius;}

    public void setDriverPickupRadius(int driverPickupRadius) {this.driverPickupRadius = driverPickupRadius;}

    public int getDriverDropOffRadius() {return driverDropOffRadius;}

    public void setDriverDropOffRadius(int driverDropOffRadius) {this.driverDropOffRadius = driverDropOffRadius;}
}
