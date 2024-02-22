package unigap.api.dto.in;

import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import unigap.api.model.Employer;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobIn {
    @NotEmpty
    private String title;
    @NotEmpty

    private int employerID;
    @NotEmpty
    private int quanlity;
    @NotEmpty
    private String description;
    @NotEmpty
    private  int fieldId;
    @NotEmpty
    private  int provincedIds;
    @NotEmpty
    private int salary;
    @NotEmpty
    private Date expired ;
}
