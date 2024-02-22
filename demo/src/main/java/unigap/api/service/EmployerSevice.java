package unigap.api.service;

import org.springframework.http.ResponseEntity;
import unigap.api.dto.in.PageDtoIn;
import unigap.api.dto.out.EmployerDtoOut;
import unigap.api.dto.out.PageDtoOut;

public interface EmployerSevice {

    public PageDtoOut<EmployerDtoOut> getAllEmployers(PageDtoIn pageDtoIn);
    public String getEmployerById();
    public String updateEmployer( );
    public String deleteEmployer();
}
