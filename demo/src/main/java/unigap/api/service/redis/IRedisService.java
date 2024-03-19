package unigap.api.service.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.domain.PageRequest;
import unigap.api.dto.out.EmployerDtoOut;

import java.util.List;

public interface IEmployerRedisService {
    void clear();//clear cache
    public String getKeyFrom(String keyword,
                                  String method,
                                  PageRequest pageRequest);

    public List<?> getAll(String keyword,
                                                String method,
                                                PageRequest pageRequest) throws JsonProcessingException;




    void saveAll(List<?> employerDtoOuts,
                         String keyword,
                         String method,
                         PageRequest pageRequest) throws JsonProcessingException;

}
