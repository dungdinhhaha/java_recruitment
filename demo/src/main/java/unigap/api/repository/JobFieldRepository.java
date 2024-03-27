package unigap.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unigap.api.model.Job_Field;

import java.util.Optional;

@Repository
public interface JobFieldRepository extends JpaRepository<Job_Field, Integer> {
    @Override
    Optional<Job_Field> findById(Integer integer);
}
