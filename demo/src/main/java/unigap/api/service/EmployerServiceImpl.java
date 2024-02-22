package unigap.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import unigap.api.dto.in.PageDtoIn;
import unigap.api.dto.out.EmployerDtoOut;
import unigap.api.dto.out.PageDtoOut;
import unigap.api.model.Employer;
import unigap.api.repository.EmployerRepository;

@Service
public class EmployerServiceImpl implements EmployerSevice {
    @Autowired
    private EmployerRepository employerRepo;


 @Override
    public PageDtoOut<EmployerDtoOut> getAllEmployers(PageDtoIn pageDtoIn) {

     Page<Employer> employers = this.employerRepo.findAll(PageRequest.of(pageDtoIn.getPage() - 1, pageDtoIn.getPageSize(),
             Sort.by("id").ascending()));

        PageDtoOut<EmployerDtoOut> e= PageDtoOut.from(1, 1, 20L );
     return e;
    }

    @Override
    public String getEmployerById() {
        return null;
    }


    @Override
    public String updateEmployer( ) {

            return "update";


    }

    @Override
    public String deleteEmployer() {
        return null;
    }


}
