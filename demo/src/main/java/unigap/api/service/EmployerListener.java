package unigap.api.service;

import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PreRemove;
import lombok.AllArgsConstructor;
import unigap.api.model.Employer;
import unigap.api.service.redis.IRedisService;

@AllArgsConstructor
public class EmployerListener {
private final IRedisService employerRedisService;
    @PostPersist
    public void postPersist(Employer employer){
    employerRedisService.clear();
    }

    @PostUpdate
    public void postUpdate(Employer employer) {


        employerRedisService.clear();
    }
    @PreRemove
    public void preRemove(Employer employer) {

        employerRedisService.clear();
    }
}
