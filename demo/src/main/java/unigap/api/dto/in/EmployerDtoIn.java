package unigap.api.dto.in;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployerDtoIn
{
    @Getter
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    @Size(max=100)
    private String name;
    @NotEmpty
    @Size(max=100)
    private Integer province;

    private String description;

}
