package _HB_2.Backend;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface DriverRepository extends JpaRepository<User, Long> {

        User findById(int id);

        Driver save(Driver driver);

        @Transactional
        void deleteById(int id);
}
