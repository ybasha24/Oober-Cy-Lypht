package _HB_2.Backend;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Integer> {

    Trip findById(int id);

    @Transactional
    void deleteById(int id);
}
