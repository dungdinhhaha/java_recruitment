package unigap.api.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import unigap.api.model.Employer;
import unigap.api.model.Job;

import java.util.Date;

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
    private  Integer fieldId;
    private  Integer provincedIds;
    private Integer salary;
    private Date expired ;
    public static JobOut from (Job u){
    return JobOut.builder()
            .id(u.getId())
            .title(u.getTitle())
            .employerID(u.getEmployer().getId())
            .quanlity(u.getQuanlity())
            .salary(u.getSalary())
            .fieldId(u.getFields())
            .provincedIds(u.getFields())
            .expired(u.getExpired_at())
            .build();
    };
}
