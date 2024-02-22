package unigap.api.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import unigap.api.model.Resume;
import unigap.api.model.Seeker;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResumeOut {
    private Seeker seekerId;
    private String careerObj;
    private String title;
    private int salary;
    private int fieldIds;
    private int provinceIds;
    public static ResumeOut from(Resume u){
        return ResumeOut.builder()
                .seekerId(u.getSeeker_id())
                .careerObj(u.getCareerObjective())
                .fieldIds(u.getFields())
                .title(u.getTitle())
                .salary(u.getSalary())
                .build();
    }
}
