package unigap.api.dto.in;

import jakarta.validation.constraints.*;
import lombok.*;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class AuthRequestDto
{
    @Getter
    @NotEmpty
    @Email
    private String name;
    @NotEmpty
    @Size(max=100)
    private String email;
    @NotEmpty
    private String password;


}
