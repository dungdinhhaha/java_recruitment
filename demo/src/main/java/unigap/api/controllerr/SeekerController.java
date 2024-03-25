package unigap.api.controllerr;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unigap.api.dto.in.JobIn;
import unigap.api.dto.in.PageDtoIn;
import unigap.api.dto.in.SeekerIn;
import unigap.api.dto.out.JobOut;
import unigap.api.dto.out.PageDtoOut;
import unigap.api.dto.out.SeekerOut;
import unigap.api.service.JobServiceImpl;
import unigap.api.service.ResumeServiceImpl;
import unigap.api.service.SeekerImpl;
import unigap.api.service.redis.SeekerRedisService;
import unigap.common.controller.AbstractResponseController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SeekerController extends AbstractResponseController {
    @Autowired
    private SeekerImpl seekerService;
    @Autowired
    private SeekerRedisService seekerRedisService;
    @GetMapping("/seekers")
    public ResponseEntity<?> getAllSeeker(PageDtoIn pageDtoIn) throws JsonProcessingException {
        List<?> seekers =  seekerRedisService.getAll("seeker","getall", PageRequest.of(pageDtoIn.getPage(), pageDtoIn.getPageSize()));
        PageDtoOut<SeekerOut> s;
        if (seekers!=null)
        {
            s=PageDtoOut.from(pageDtoIn.getPage(), pageDtoIn.getPageSize(), 3L,(List<SeekerOut>) seekers);

        }
        else {
            s=seekerService.getAllSeeker(pageDtoIn);
        }
        return responseEntity(
                () -> {
                    return s;
                }
        );
    }

    @GetMapping("/seeker/{id}")
    public ResponseEntity<?> getSeekerById(@PathVariable long id ) {
        return responseEntity(
                ()-> {
                    return seekerService.getSeekerByID(id);
                }
        );
    }

    @PostMapping("/seeker/create")
    public ResponseEntity<?> createSeeker(@RequestBody SeekerIn seekerIn) {
        return responseEntity(
                ()-> {
                    return seekerService.createSeeker(seekerIn);
                }
        );
    }
    @PutMapping("/seeker/update/{id}")
    public ResponseEntity<?> updateSeeker(@PathVariable long id, @RequestBody SeekerIn seekerIn)
    {
        return responseEntity(
                () ->{
                    return seekerService.updateSeeker(id,seekerIn);
                }
        );
    }
    @DeleteMapping("/seeker/delete/{id}")
    public  ResponseEntity<?> deleteSeeker (@PathVariable long id){
        return responseEntity(
                () ->{
                    return  seekerService.deleteSeeker(id);
                }
        );
    }
}
