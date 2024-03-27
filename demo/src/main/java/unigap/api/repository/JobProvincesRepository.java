package unigap.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import unigap.api.model.Job_Field;
import unigap.api.model.Job_Province;

import java.util.List;
import java.util.Optional;
@Repository
public interface JobProvincesRepository  extends JpaRepository<Job_Province, Integer> {
    @Override
    Optional<Job_Province> findById(Integer integer);
}

