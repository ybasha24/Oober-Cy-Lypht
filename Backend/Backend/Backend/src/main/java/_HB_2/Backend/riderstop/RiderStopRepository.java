package _HB_2.Backend.riderstop;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface RiderStopRepository extends JpaRepository<RiderStop, Integer> {

    RiderStop findById(int id);

    @Transactional
    void deleteById(int id);

}
