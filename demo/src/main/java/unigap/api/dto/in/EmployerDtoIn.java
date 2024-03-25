package unigap.api.dto.in;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.*;




@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class EmployerDtoIn
{
    @Getter
    @NotEmpty
    @Size(max = 255)
    private String email;
    @NotEmpty
    @Size(max=100)
    private String name;
    @NotEmpty
    @Size(max=100)
    private Integer province;
    private String description;
    private String avatar;

}
