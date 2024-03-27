package unigap.api.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import unigap.api.model.Employer;
import unigap.api.model.Job;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobOut {
    private Integer id;
    private String title;
    private Integer employerID;
    private  Integer quanlity;
    private String description;
    private List<String>  fieldId;
    private  List<String> provincedIds;
    private Integer salary;
    private Date expired ;

    public static  JobOut from (Job u, List<String> fieldId ,List<String> provincedIds){

        Integer employerID1 = null;
        Employer employer = u.getEmployer();
        if (employer != null && employer.getId() != null) {
            employerID1 = employer.getId();
        }

    return JobOut.builder()
            .id(u.getId())
            .title(u.getTitle())
            .employerID(employerID1)
            .quanlity(u.getQuanlity())
            .salary(u.getSalary())
            .expired(u.getExpired_at())
            .fieldId(fieldId)
            .provincedIds(provincedIds)
            .build();
    };
}
