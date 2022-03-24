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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int numberOfRiders;

    @ManyToOne
    @JoinColumn(name = "Rider_ID")
    private Rider rider;

//    List<Integer> riderIds;

    //needs an empty constructor
    public TripRiders() {
    }

    public TripRiders(int numberOfRiders, List<Integer> listOfRiderIds) {
        this.numberOfRiders = numberOfRiders;

        for (Integer riderId : listOfRiderIds) {
            RiderRepository riderRepository = null;
            riderRepository.findById(riderId);
//            riderIds.add(riderId);
        }
    }

    public int getNumberOfRiders() {
        return numberOfRiders;
    }

    public void setNumberOfRiders(int numberOfRiders) {
        this.numberOfRiders = numberOfRiders;
    }
}
