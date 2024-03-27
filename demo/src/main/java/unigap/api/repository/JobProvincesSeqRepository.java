package unigap.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import unigap.api.model.Job_Province_seq;

import java.util.List;

@Repository
public interface JobProvincesSeqRepository extends JpaRepository<Job_Province_seq,Integer> {
    @Query("SELECT jp.name FROM Job_Province jp JOIN Job_Province_seq jps ON jp.id = jps.id.provinceId WHERE jps.id.jobId = :id")
    List<String> findProvinceName(@Param("id") Integer jobId);
}
