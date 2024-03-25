package unigap.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import unigap.api.dto.in.PageDtoIn;
import unigap.api.dto.in.ResumeIn;
import unigap.api.dto.out.JobOut;
import unigap.api.dto.out.PageDtoOut;
import unigap.api.dto.out.ResumeOut;
import unigap.api.model.Job;
import unigap.api.model.Resume;
import unigap.api.model.Seeker;
import unigap.api.repository.ResumeRepository;
import unigap.api.repository.SeekerRepository;
import unigap.common.ErrorCode;
import unigap.common.response.ApiException;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Service
public class ResumeServiceImpl implements IResumeService{
    @Autowired
    private ResumeRepository resumeRepository;
    @Autowired
    private SeekerRepository seekerRepository;
    @Override
    public PageDtoOut<ResumeOut> getAllResume(PageDtoIn pageDtoIn) {
        Page<Resume> s= resumeRepository.findAll(PageRequest.of(pageDtoIn.getPage()-1, pageDtoIn.getPageSize(), Sort.by("id")));
        PageDtoOut<ResumeOut> e = PageDtoOut.from(pageDtoIn.getPage(), pageDtoIn.getPageSize(), 3L,s.stream().map(ResumeOut::from).toList());
        return e;
    }

    @Override
    public ResumeOut getResumeByID(long id) {
        Resume resume = resumeRepository.findById((int) id).orElseThrow(
                () -> new ApiException(ErrorCode.NOT_FOUND, HttpStatus.BAD_REQUEST,"Cant Find Resume By Id")
        );

        return ResumeOut.from(resume);
    }

    @Override
    public ResumeOut createResume(ResumeIn resumeIn) {
        LocalDateTime localDateTime = LocalDateTime.now();
        Seeker seeker = seekerRepository.findById(resumeIn.getSeekerId()).orElse(null);
       Resume resume= Resume.builder()
               .title(resumeIn.getTitle())
               .fields(resumeIn.getFieldIds())
               .salary(resumeIn.getSalary())
               .seeker(seeker)
               .provinces(resumeIn.getProvinceIds())
               .createdAt( Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant()))
               .updatedAt(Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant()))
               .careerObjective(resumeIn.getCareerObj())
               .build();
       resumeRepository.save(resume);
        return ResumeOut.from(resume);
    }

    @Override
    public ResumeOut updateResume(long id, ResumeIn resumeIn) {
        Resume resume = resumeRepository.findById((int) id).orElseThrow(
                () -> new ApiException(ErrorCode.NOT_FOUND,HttpStatus.BAD_REQUEST,"Cant Find ResumeByID")
        );
        resume.setSalary(resumeIn.getSalary());
        resume.setFields(resumeIn.getFieldIds());
        resume.setTitle(resumeIn.getTitle());
        resume.setCareerObjective(resumeIn.getCareerObj());

        return ResumeOut.from(resume);
    }

    @Override
    public String deleteResume(long id) {
        resumeRepository.deleteById((int) id);
        return "Đã Xóa";
    }
}
