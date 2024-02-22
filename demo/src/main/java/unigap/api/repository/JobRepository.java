package unigap.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import unigap.api.model.Job;

public interface JobRepository extends JpaRepository<Job,Integer> {
}
