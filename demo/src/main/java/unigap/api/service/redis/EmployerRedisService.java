package unigap.api.service.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import unigap.api.dto.out.EmployerDtoOut;
import unigap.api.model.Employer;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployerRedisService implements IRedisService {
    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper redisObjectMapper;

    @Override
    public void clear() {

    }

    @Override
    public String getKeyFrom(String keyword, String method, PageRequest pageRequest) {
        int pageNumber = pageRequest.getPageNumber();
        int pageSize = pageRequest.getPageSize();


        String key = String.format("all_products:%s:%s:%d:%d",
                keyword, method, pageNumber, pageSize);
        return key;
    }

    @Override
    public List<Employer> getAll(String keyword, String method, PageRequest pageRequest) throws JsonProcessingException {

        String key = this.getKeyFrom(keyword, method, pageRequest);
        String json=null;
        json = (String) redisTemplate.opsForValue().get(key);
        List<Employer> productResponses = null;
        try {
            if (json != null) {
                productResponses = redisObjectMapper.readValue(json, new TypeReference<List<Employer>>() {});
            }
        } catch (JsonProcessingException e) {

             }
        return productResponses;
    }
    @Override
    public void saveAll(List<?> employerDtoOuts, String keyword, String method, PageRequest pageRequest) throws JsonProcessingException {
        String key = this.getKeyFrom(keyword, method, pageRequest);
        String json = redisObjectMapper.writeValueAsString(employerDtoOuts);
        redisTemplate.opsForValue().set(key, json);
    }
    
}
