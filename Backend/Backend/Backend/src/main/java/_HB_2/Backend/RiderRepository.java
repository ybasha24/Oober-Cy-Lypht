package _HB_2.Backend;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface RiderRepository extends JpaRepository<User, Integer> {

    User findById(int id);

//    @Query("SELECT u FROM User u WHERE u.email = ?1")
    @Query(
        value = "SELECT * FROM Users u WHERE u.email = ?1",
        nativeQuery = true)
    User findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.firstName = ?1")
    User findByFirstName(String firstname);

    @Transactional
    void deleteById(int id);
}
