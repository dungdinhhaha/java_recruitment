package unigap.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import unigap.api.model.Seeker;

public interface SeekerRepository extends JpaRepository<Seeker,Integer> {
}
