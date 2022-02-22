package Backend;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AdminRepository extends JpaRepository<User, Integer> {

    User findById(int id);

    @Transactional
    void deleteById(int id);
}
