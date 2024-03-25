package unigap.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import unigap.api.dto.in.JobIn;
import unigap.api.dto.in.PageDtoIn;
import unigap.api.dto.out.JobOut;
import unigap.api.dto.out.PageDtoOut;
import unigap.api.model.Employer;
import unigap.api.model.Job;
import unigap.api.repository.EmployerRepository;
import unigap.api.repository.JobRepository;
import unigap.common.ErrorCode;
import unigap.common.response.ApiException;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements IJobService{
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private EmployerRepository employerRepository;
    @Override
    public PageDtoOut<JobOut> getAllJob(PageDtoIn pageDtoIn) {
       Page<Job> s= jobRepository.findAll(PageRequest.of(pageDtoIn.getPage()-1, pageDtoIn.getPageSize()));
       PageDtoOut<JobOut> e = PageDtoOut.from(pageDtoIn.getPage(), pageDtoIn.getPageSize(), (long) (pageDtoIn.getPageSize()* pageDtoIn.getPage()),s.stream().map(JobOut::from).toList());
        return e;
    }

    @Override
    public JobOut getJobByID(long id) {
        Job job =jobRepository.findById((int) id).orElseThrow(
                () -> new ApiException(ErrorCode.NOT_FOUND, HttpStatus.BAD_REQUEST,"Cant Find")
        );
        return JobOut.from(job);
    }

    @Override
    public JobOut createJob(JobIn jobIn) {
        LocalDateTime localDateTime = LocalDateTime.now();
        Employer employer = employerRepository.findById(jobIn.getEmployer_id()).orElse(null);
    Job job = Job.builder()
                     .title(jobIn.getTitle())
                     .quanlity(jobIn.getQuanlity())
                     .description(jobIn.getDescription())
                     .expired_at(jobIn.getExpired())
                     .fields(jobIn.getFieldId())
                     .salary(jobIn.getSalary())
                     .province(jobIn.getProvincedIds())
                     .employer(employer)
                     .created_at( Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant()))
                     .updated_at(Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant()))
                     .build();

        jobRepository.save(job);
        return JobOut.from(job);
    }


    @Override
    public JobOut updateJob(long id, JobIn jobIn) {
        Job job = jobRepository.findById((int )id).orElseThrow(
                () -> new ApiException(ErrorCode.NOT_FOUND,HttpStatus.BAD_REQUEST,"Cant find Id")
        );
        job.setTitle(jobIn.getTitle());
        job.setFields(job.getFields());
        job.setUpdated_at(jobIn.getExpired());
        job.setDescription(job.getDescription());
        jobRepository.save(job);
        return JobOut.from(job);

    }

    @Override
    public String deleteJob(long id) {
        jobRepository.deleteById((int ) id);
        return "Da Xoa";
    }
}
