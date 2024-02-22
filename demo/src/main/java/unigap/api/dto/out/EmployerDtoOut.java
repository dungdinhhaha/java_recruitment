package unigap.api.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import unigap.api.model.Employer;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployerDtoOut {
    private Long id;
    private String email;
    private String name;
    private String avatar;

    public static EmployerDtoOut from (Employer u){
    return  EmployerDtoOut.builder()
            .id(u.getId())
            .email(u.getEmail())
            .name(u.getName())
            .avatar(u.getAvatar())
            .build();

    }
}
