package unigap.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import unigap.api.model.Job_Field_seq;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobFieldSeqRepository extends JpaRepository<Job_Field_seq, Integer> {

    @Query("SELECT jf.name FROM Job_Field_seq jfs JOIN Job_Field jf ON jf.id = jfs.id.fieldId WHERE jfs.id.jobId = :id")
   List<String> findFieldName(@Param("id") Integer jobId);


}


