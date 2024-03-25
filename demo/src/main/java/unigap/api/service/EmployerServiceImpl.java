package unigap.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import unigap.api.dto.in.EmployerDtoIn;
import unigap.api.dto.in.EmployerJobRequest;
import unigap.api.dto.in.JobIn;
import unigap.api.dto.in.PageDtoIn;
import unigap.api.dto.out.EmployerDtoOut;
import unigap.api.dto.out.PageDtoOut;
import unigap.api.model.Employer;
import unigap.api.model.Job;
import unigap.api.repository.EmployerRepository;
import unigap.api.service.redis.EmployerRedisService;
import unigap.common.ErrorCode;
import unigap.common.response.ApiException;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployerServiceImpl implements EmployerSevice {
    @Autowired
    private EmployerRepository employerRepo;
   @Autowired
   private EmployerRedisService employerRedisService;

 @Override
    public PageDtoOut<EmployerDtoOut> getAllEmployers(PageDtoIn pageDtoIn) throws JsonProcessingException {

        Page<Employer> employers = this.employerRepo.findAll(PageRequest.of(pageDtoIn.getPage() - 1, pageDtoIn.getPageSize(),
                Sort.by("id").ascending()));

     PageDtoOut<EmployerDtoOut> e= PageDtoOut.from(pageDtoIn.getPage(), pageDtoIn.getPageSize(), employers.stream().count(),
             employers.stream().map(EmployerDtoOut::from).toList());
     return e;
    }

    @Override
    public EmployerDtoOut getEmployerById(long id) {

    Employer employer = employerRepo.findById((int) id).orElseThrow(
            () -> new ApiException(ErrorCode.NOT_FOUND, HttpStatus.NOT_FOUND, "user not found")
    );
     return EmployerDtoOut.from(employer);
    }

    @Override
    public EmployerDtoOut createEmployers(EmployerJobRequest employerDtoIn) {
        employerRepo.findByEmail(employerDtoIn.getEmployerDtoIn().getEmail())
                .ifPresent(user -> {
                    throw new ApiException(ErrorCode.BAD_REQUEST, HttpStatus.BAD_REQUEST, "email already existed");
                });
        LocalDateTime localDateTime = LocalDateTime.now();
        Set<Job> jobs = new HashSet<>();
        Employer employer = Employer.builder()
                .email(employerDtoIn.getEmployerDtoIn().getEmail())
                .name(employerDtoIn.getEmployerDtoIn().getName())
                .avatar(employerDtoIn.getEmployerDtoIn().getAvatar())
                .description(employerDtoIn.getEmployerDtoIn().getDescription())
                .createdAt( Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant()))
                .updatedAt(Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant()))
                .build();
        List<JobIn> jobIns = employerDtoIn.getJobIn(); // Lấy danh sách các công việc từ EmployerJobRequest
        for (JobIn jobIn : jobIns) {
            Job job = Job.builder()
                    .fields(jobIn.getFieldId())
                    .title(jobIn.getTitle())
                    .created_at( Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant()))
                    .updated_at(Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant()))
                    .expired_at(jobIn.getExpired())
                    .employer(employer)
                    .province(jobIn.getProvincedIds())
                    .description(jobIn.getDescription())
                    .salary(jobIn.getSalary())
                    .build();
            employer.addJob(job);
        }
        employerRepo
                .save(employer);
        return EmployerDtoOut.from(employer);
    }


    @Override
    public EmployerDtoOut updateEmployer( long id , EmployerDtoIn employerDtoIn) {
        Employer user = employerRepo.findById((int) id).orElseThrow(
                () -> new ApiException(ErrorCode.NOT_FOUND, HttpStatus.NOT_FOUND, "user not found")
        );
        user.setName(employerDtoIn.getName());
        user.setEmail(employerDtoIn.getEmail());
        user.setAvatar(employerDtoIn.getAvatar());

        user = employerRepo.save(user);
            return EmployerDtoOut.from(user);


    }

    @Override
    public String deleteEmployer( long id) {
     employerRepo.deleteById((int) id);
        return "Đã Xóa";
    }


}
