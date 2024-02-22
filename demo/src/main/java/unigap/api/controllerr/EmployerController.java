package unigap.api.controllerr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unigap.api.dto.in.EmployerDtoIn;
import unigap.api.dto.in.PageDtoIn;
import unigap.api.dto.out.EmployerDtoOut;
import unigap.api.dto.out.PageDtoOut;
import unigap.api.model.Employer;
import unigap.api.reponse.ResponseHandler;

import unigap.api.service.EmployerServiceImpl;

import javax.sql.DataSource;
import javax.validation.Valid;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.List;
import unigap.common.controller.AbstractResponseController;

@RestController
@RequestMapping("/api")
public class EmployerController extends  AbstractResponseController{

  @Autowired
  private EmployerServiceImpl employerService;


  @GetMapping("/employers")
  public ResponseEntity<?>  getAllEmployers( PageDtoIn pageDtoIn) {
    return responseEntity(() -> {
      return this.employerService.getAllEmployers(pageDtoIn);
    });
  }


  @Autowired
  private DataSource dataSource;

  @GetMapping("/check-database-connection")
  public String checkDatabaseConnection() {
    try {
      Connection connection = dataSource.getConnection();
      connection.close();
      return "Database connection successful!";
    } catch (SQLException e) {
      return "Failed to connect to database: " + e.getMessage();
    }
  }

  @GetMapping("/employers/getById/{id}")
  public ResponseEntity<Object> getEmployerById(@PathVariable long id) {
    return null;

  }

  @PutMapping("/updateEmployer")
  public ResponseEntity<Object> updateEmployer(@RequestBody EmployerDtoIn employerDtoIn) {
    return null;

  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Object> deleteEmployer(@PathVariable long id) {

    return null;
  }

}