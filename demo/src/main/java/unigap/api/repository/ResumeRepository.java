package unigap.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import unigap.api.model.Resume;

public interface ResumeRepository extends JpaRepository<Resume,Integer> {
}
