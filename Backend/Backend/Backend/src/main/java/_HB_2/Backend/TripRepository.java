package _HB_2.Backend;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Integer> {

    Trip findById(int id);


    //Get all uncompleted lists from a driver given their id!
    List<Trip> getUncompletedTrips(int driverID);

    @Transactional
    void deleteById(int id);
}
