package _HB_2.Backend.trip;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Integer> {

    Trip findById(int id);

    @Query("SELECT t FROM Trip t WHERE t.tripDriver.id = ?1 AND t.isCompleted = false")
    List<Trip> getAllUncompletedTripsByDriverId(int driverId);

    @Transactional
    void deleteById(int id);
}
