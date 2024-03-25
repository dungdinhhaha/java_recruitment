package unigap.api.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;
import unigap.api.model.Employer;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
public interface EmployerRepository extends JpaRepository<Employer, Integer> {

    Page<Employer> findAll(Pageable page);


    @Query("SELECT e FROM Employer e WHERE e.email = ?1")
    Optional<Employer> findByEmail(String email);
}
