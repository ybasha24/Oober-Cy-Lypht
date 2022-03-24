package _HB_2.Backend;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

//What if we just got rid of the entity and id?
//could we just make this an object and not a table in the database?
//we could just have a list of riderId's and not tie it to the database.
@Entity
public class TripRiders {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;

    //would this be the join table class?
    //that is why we would have two primary keys
    @Id
    Integer tripId;

    @Id
    Integer riderId;

    private int numberOfRiders;

//    can you represent a list in a database?
//    @OneToMany
//    @JoinColumn(name = "Rider_ID")
//    private List<Rider> riders;

    //do this in the trip service class
//    @Autowired
//    RiderRepository riderRepository;

//    List<Integer> riderIds;

    //needs an empty constructor
    public TripRiders() {
    }

    public TripRiders(int numberOfRiders, List<Integer> listOfRiderIds) {
        this.numberOfRiders = numberOfRiders;

        for (Integer riderId : listOfRiderIds) {
            RiderRepository riderRepository = null;
            Rider rider = riderRepository.findById(riderId);
            riders.add(rider);
        }
    }

    public int getNumberOfRiders() {
        return numberOfRiders;
    }

    public void setNumberOfRiders(int numberOfRiders) {
        this.numberOfRiders = numberOfRiders;
    }
    
    public List<Integer> getRiderIds() {
        
    }

    public void addRiderId(int riderId) {
        
    }

    public void removeRiderId(int riderId) {
    }
}
