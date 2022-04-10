package _HB_2.Backend.reports;

import _HB_2.Backend.rating.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ReportsRepository extends JpaRepository<Reports, Integer>  {

    @Transactional
    void deleteById(int id);
}
