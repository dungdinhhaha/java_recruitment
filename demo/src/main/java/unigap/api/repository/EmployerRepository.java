package unigap.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import unigap.api.model.Employer;
import org.springframework.data.domain.Pageable;
import java.util.List;
@Repository
public interface EmployerRepository extends JpaRepository<Employer, Integer> {

    Page<Employer> findAll(Pageable page);


}
