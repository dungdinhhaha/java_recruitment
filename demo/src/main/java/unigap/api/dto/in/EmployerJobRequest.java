package unigap.api.dto.in;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class EmployerJobRequest {
    @Getter
    @Setter
    private EmployerDtoIn employerDtoIn;
    @Getter
    @Setter
    private List<JobIn> jobIn;
}
