package unigap.api.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import unigap.api.model.Employer;
import unigap.api.model.Job;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployerDtoOut {
    private Integer id;
    private String email;
    private String name;
    private String avatar;

    private String description;
    private Date create_at;
    private Date update_at;

    public static EmployerDtoOut from (Employer u){

    return  EmployerDtoOut.builder()
            .id(u.getId())
            .email(u.getEmail())
            .name(u.getName())
            .avatar(u.getAvatar())
            .description(u.getDescription())
            .create_at(u.getCreatedAt())
            .update_at(u.getUpdatedAt())
            .build();


    }
}
