package unigap.api.service;

import unigap.api.dto.in.JobIn;
import unigap.api.dto.in.PageDtoIn;
import unigap.api.dto.in.SeekerIn;
import unigap.api.dto.out.JobOut;
import unigap.api.dto.out.PageDtoOut;
import unigap.api.dto.out.SeekerOut;

public interface ISeeker {
    public PageDtoOut<SeekerOut> getAllSeeker(PageDtoIn pageDtoIn);
    public SeekerOut getSeekerByID (long id);

    public SeekerOut createSeeker(SeekerIn seekerIn);

    public SeekerOut updateSeeker(long id , SeekerIn seekerIn);

    public String deleteSeeker(long id);
}
