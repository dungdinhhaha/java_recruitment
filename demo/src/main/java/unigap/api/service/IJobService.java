package unigap.api.service;

import unigap.api.dto.in.JobIn;
import unigap.api.dto.in.PageDtoIn;
import unigap.api.dto.out.JobOut;
import unigap.api.dto.out.PageDtoOut;

public interface IJobService {
    public PageDtoOut<JobOut> getAllJob(PageDtoIn pageDtoIn);
    public JobOut getJobByID (long id);

    public JobOut createJob(JobIn jobIn);

    public JobOut updateJob(long id , JobIn jobIn);

    public String deleteJob(long id);
}
