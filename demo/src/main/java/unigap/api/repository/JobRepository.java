package unigap.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unigap.api.dto.out.JobOut;
import unigap.api.model.Job;

import java.util.Optional;
@Repository
public interface JobRepository extends JpaRepository<Job,Integer> {
    @Override
    Page<Job> findAll(Pageable pageable);

    @Override
    Optional<Job> findById(Integer interger);
}
