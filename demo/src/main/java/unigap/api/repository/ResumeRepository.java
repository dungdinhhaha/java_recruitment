package unigap.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unigap.api.model.Resume;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResumeRepository extends JpaRepository<Resume,Integer> {
    Page<Resume> findAll(Pageable pageable);

    Optional<Resume> findById(Integer integer);
}
