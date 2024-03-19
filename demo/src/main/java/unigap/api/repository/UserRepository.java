package unigap.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import unigap.api.model.User1;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User1, Long> {
    boolean existsByPhoneNumber(String phoneNumber);
    Optional<User1> findByPhoneNumber(String phoneNumber);
    //SELECT * FROM users WHERE phoneNumber=?
}
