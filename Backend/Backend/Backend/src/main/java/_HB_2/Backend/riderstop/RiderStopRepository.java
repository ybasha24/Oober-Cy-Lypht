package _HB_2.Backend.riderstop;

import _HB_2.Backend.trip.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface RiderStopRepository extends JpaRepository<RiderStop, Integer> {

    RiderStop findById(int id);

    @Transactional
    void deleteById(int id);

//    @Modifying
//    @Query("DELETE FROM RiderStop r where r.tripId = :tripId and r.riderId = :riderId")
//    void deleteByTripIdAndRiderId(int tripId, int riderId);

    @Modifying
    @Query("UPDATE RiderStop r set r.tripId = 0 where r.tripId = :tripId and r.riderId = :riderId")
    int deleteByTripIdAndRiderId(int tripId, int riderId);

    @Query("SELECT r FROM RiderStop r WHERE r.tripId = :tripId")
    List<RiderStop> getRiderStopsByTripId(int tripId);

//    @Query("SELECT t FROM Trip t WHERE t.tripDriver.id = ?1 AND t.isCompleted = false")
//    List<Trip> getAllUncompletedTripsByDriverId(int driverId);
//    @Query("SELECT r FROM RiderStop r WHERE r.riderId = ?2 and r.tripId = ?1")
//    RiderStop findByTripIdAndRiderId(int tripId, int riderId);
}
