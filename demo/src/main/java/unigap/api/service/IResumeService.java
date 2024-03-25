package unigap.api.service;

import unigap.api.dto.in.PageDtoIn;
import unigap.api.dto.in.ResumeIn;
import unigap.api.dto.out.PageDtoOut;
import unigap.api.dto.out.ResumeOut;

public interface IResumeService {
    public PageDtoOut<ResumeOut> getAllResume(PageDtoIn pageDtoIn);
    public ResumeOut getResumeByID (long id);

    public ResumeOut createResume(ResumeIn resumeIn);

    public ResumeOut updateResume(long id , ResumeIn resumeIn);

    public String deleteResume(long id);
}
