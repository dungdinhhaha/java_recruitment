package unigap.api.controllerr;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unigap.api.dto.in.JobIn;
import unigap.api.dto.in.PageDtoIn;
import unigap.api.dto.out.EmployerDtoOut;
import unigap.api.dto.out.JobOut;
import unigap.api.dto.out.PageDtoOut;
import unigap.api.service.JobServiceImpl;
import unigap.api.service.redis.JobRedisService;
import unigap.common.controller.AbstractResponseController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class JobController extends AbstractResponseController {
    @Autowired
    private JobServiceImpl jobService;
    @Autowired
    private JobRedisService jobRedisService;
    @GetMapping("/jobs")
    public ResponseEntity<?> getAllJob(@RequestBody  PageDtoIn pageDtoIn) throws JsonProcessingException {
        List<?> jobs= jobRedisService.getAll("job","getall", PageRequest.of(pageDtoIn.getPage()-1, pageDtoIn.getPageSize()));

        PageDtoOut<JobOut> s;
        if(jobs != null){

             s =  PageDtoOut.from(pageDtoIn.getPage(),
                    pageDtoIn.getPageSize(),
                    3L,
                     (List<JobOut>) jobs);
        }
        else {
            s = jobService.getAllJob(pageDtoIn);
            jobRedisService.saveAll(s.getData(),"employer","all", PageRequest.of(pageDtoIn.getPage(), pageDtoIn.getPageSize()));
        }
        PageDtoOut<JobOut> finalS = s;
        return responseEntity(
                ()->{ return finalS;
                });
    }

    @GetMapping("/job/{id}")
    public ResponseEntity<?> getJobById(@PathVariable long id ) {
        return responseEntity(
                ()-> {
                    return jobService.getJobByID(id);
                }
        );
    }

    @PostMapping("/job/create")
    public ResponseEntity<?> createJob(@RequestBody JobIn jobIn) {
        return responseEntity(
                ()-> {
                    return jobService.createJob(jobIn);
                }
        );
    }
    @PutMapping("/job/update/{id}")
    public ResponseEntity<?> updateJob(@PathVariable long id, @RequestBody JobIn jobIn)
    {
        return responseEntity(
                () ->{
                    return jobService.updateJob(id,jobIn);
                }
        );
    }
    @DeleteMapping("/job/delete/{id}")
    public  ResponseEntity<?> deleteJob (@PathVariable long id){
        return responseEntity(
                () ->{
                    return  jobService.deleteJob(id);
                }
        );
    }
}
