package unigap.api.controllerr;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unigap.api.dto.in.JobIn;
import unigap.api.dto.in.PageDtoIn;
import unigap.api.dto.in.ResumeIn;
import unigap.api.dto.out.JobOut;
import unigap.api.dto.out.PageDtoOut;
import unigap.api.dto.out.ResumeOut;
import unigap.api.repository.ResumeRepository;
import unigap.api.service.ResumeServiceImpl;
import unigap.api.service.redis.ResumeRedisService;
import unigap.common.controller.AbstractResponseController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ResumeController extends AbstractResponseController {
    @Autowired
    private ResumeServiceImpl resumeService;
    @Autowired
    private ResumeRedisService resumeRedisService;
    @GetMapping("/resume")
    public ResponseEntity<?> getAllResume(PageDtoIn pageDtoIn) throws JsonProcessingException {
        List<?> resumes =  resumeRedisService.getAll("resume","getall", PageRequest.of(pageDtoIn.getPage(), pageDtoIn.getPageSize()));
        PageDtoOut<ResumeOut> s = null;
        if(resumes != null){
         s=PageDtoOut.from(pageDtoIn.getPage(), pageDtoIn.getPageSize(), s.getTotalPages(), (List<ResumeOut>) resumes);
        }
        else {
            s=resumeService.getAllResume(pageDtoIn);
            resumeRedisService.saveAll((List<?>) s,"resume","getall",PageRequest.of(pageDtoIn.getPage(), pageDtoIn.getPageSize()));
        }
        return responseEntity(
                () -> {
                    return resumeService.getAllResume(pageDtoIn);
                }
        );
    }

    @GetMapping("/resume/{id}")
    public ResponseEntity<?> getResume(@PathVariable long id ) {
        return responseEntity(
                ()-> {
                    return resumeService.getResumeByID(id);
                }
        );
    }

    @PostMapping("/resume/create")
    public ResponseEntity<?> createResume(@RequestBody ResumeIn resumeIn) {
        return responseEntity(
                ()-> {
                    return resumeService.createResume(resumeIn);
                }
        );
    }
    @PutMapping("/resume/update/{id}")
    public ResponseEntity<?> updateResume(@PathVariable long id, @RequestBody ResumeIn resumeIn)
    {
        return responseEntity(
               () ->{
                    return resumeService.updateResume(id,resumeIn);
                }
        );
    }
    @DeleteMapping("/resume/delete/{id}")
    public  ResponseEntity<?> deleteResume (@PathVariable long id){
        return responseEntity(
                () ->{
                    return  resumeService.deleteResume(id);
                }
        );
    }
}
