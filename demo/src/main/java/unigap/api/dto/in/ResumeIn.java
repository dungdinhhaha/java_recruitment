package unigap.api.dto.in;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ResumeIn {
    @NotEmpty
    private Integer seekerId;
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
