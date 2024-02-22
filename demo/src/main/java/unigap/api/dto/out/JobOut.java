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
    private String title;
    private Employer employerID;
    private int quanlity;
    private String description;
    private  int fieldId;
    private  int provincedIds;
    private int salary;
    private Date expired ;
    public  static JobOut from (Job u){
    return JobOut.builder()
            .title(u.getTitle())
            .employerID(u.getEmployer_id())
            .quanlity(u.getQuanlity())
            .salary(u.getSalary())
            .fieldId(u.getProvince())
            .provincedIds(u.getFields())
            .expired(u.getExpired_at())
            .build();
    };
}
