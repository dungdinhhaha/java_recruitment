package unigap.api.dto.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResumeIn {
    @NotEmpty
    private Long seekerId;
    @NotEmpty
    private String careerObj;
    @NotEmpty
    private String title;
    @NotEmpty
    private int salary;
    @NotEmpty
    private int fieldIds;
    @NotEmpty
    private int provinceIds;
}
