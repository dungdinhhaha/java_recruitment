package unigap.api.controllerr;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unigap.api.dto.in.EmployerDtoIn;
import unigap.api.dto.in.EmployerJobRequest;
import unigap.api.dto.in.PageDtoIn;

import unigap.api.dto.out.EmployerDtoOut;
import unigap.api.dto.out.PageDtoOut;
import unigap.api.service.EmployerServiceImpl;

import unigap.api.service.redis.EmployerRedisService;
import unigap.common.controller.AbstractResponseController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployerController extends  AbstractResponseController{
 Logger logger = LoggerFactory.getLogger(EmployerController.class);
  @Autowired
  private EmployerServiceImpl employerService;
  @Autowired
  private EmployerRedisService employerRedisService;
  @Operation(
          summary = "Lấy danh sách employers",
          responses = {
                  @ApiResponse(
                          responseCode = "200",

                          content = @Content(
                                  schema = @Schema(
                                          implementation = ResponsePageUser.class
                                  )
                          )
                  )
          }
  )

  @GetMapping("/employers")
  public ResponseEntity<?>  getAllEmployers( PageDtoIn pageDtoIn) throws JsonProcessingException {
    String keyword ="employer" ;
    int totalPages = 0;
    PageDtoOut<EmployerDtoOut> s;
    PageRequest pageRequest = PageRequest.of(pageDtoIn.getPage(), pageDtoIn.getPageSize());

    List<?> employerDtoOuts = employerRedisService.getAll(keyword, "all", pageRequest);

    if(employerDtoOuts != null){

      s=PageDtoOut.from(pageDtoIn.getPage(),
              pageDtoIn.getPageSize(),
              (long) totalPages,
              (List<EmployerDtoOut>) employerDtoOuts);
    }
   else {
      s = employerService.getAllEmployers(pageDtoIn);
      employerRedisService.saveAll(s.getData(),"employer","all", pageRequest);
    }
    PageDtoOut<EmployerDtoOut> finalS = s;
    return responseEntity(
            ()->{ return finalS;
    });




  }



  @Operation(
          summary = "Lấy thông tin user theo employer",
          responses = {
                  @ApiResponse(
                          responseCode = "200",
                          content = {@Content(
                                  mediaType = "application/json",
                                  schema = @Schema(
                                          implementation = ResponseEmployer.class
                                  )
                          )}
                  )
          }
  )
  @GetMapping("/employers/{id}")
  public ResponseEntity<?> getEmployerById(@PathVariable long id) {
  return responseEntity(() -> {
    return employerService.getEmployerById(id);
  });

  }
    @Operation(
            summary = "Thêm mới employer",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = {@Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = ResponseEmployer.class
                                    )
                            )}
                    )
            }
    )
  @PostMapping("/employer/create")
  public ResponseEntity<?> createEmployer(@RequestBody EmployerJobRequest employerJobRequest){
    return responseEntity(() -> {
      return employerService.createEmployers(employerJobRequest);
    });
}
    @Operation(
            summary = "Cập nhật employer",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = {@Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = ResponseEmployer.class
                                    )
                            )}
                    )
            }
    )
  @PutMapping("/updateEmployer/{id}")
  public ResponseEntity<?> updateEmployer(@PathVariable(value = "id") long id, @RequestBody EmployerDtoIn employerDtoIn){

    return responseEntity(() -> {
      return employerService.updateEmployer(id,employerDtoIn);
    });
  }
    @Operation(
            summary = "xóa employer",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = {@Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            type = "string"
                                    )
                            )}
                    )
            }
    )

  @DeleteMapping("/deleteEmployer/{id}")
  public ResponseEntity<?> deleteEmployer(@PathVariable(value = "id") long id) {

    return responseEntity(() -> {
      return employerService.deleteEmployer(id);
    });
  }

  private class ResponsePageUser extends unigap.common.response.ApiResponse<PageDtoOut<EmployerDtoOut>> {
  }

  private class ResponseEmployer  extends unigap.common.response.ApiResponse<EmployerDtoOut> {
  }
}