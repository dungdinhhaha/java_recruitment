package unigap.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unigap.api.model.Seeker;
@Repository
public interface SeekerRepository extends JpaRepository<Seeker,Integer> {
}
