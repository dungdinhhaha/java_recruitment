package unigap.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import unigap.api.dto.in.EmployerDtoIn;
import unigap.api.dto.in.EmployerJobRequest;
import unigap.api.dto.in.PageDtoIn;
import unigap.api.dto.out.EmployerDtoOut;
import unigap.api.dto.out.PageDtoOut;

public interface EmployerSevice {

    public PageDtoOut<EmployerDtoOut> getAllEmployers(PageDtoIn pageDtoIn) throws JsonProcessingException;
    public EmployerDtoOut getEmployerById(long id);
    public EmployerDtoOut createEmployers(EmployerJobRequest employerJobRequest);
    public EmployerDtoOut updateEmployer( long id , EmployerDtoIn employerDtoIn);
    public String deleteEmployer(long id);
}
