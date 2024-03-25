package unigap.api.dto.in;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SeekerIn {
    @NotEmpty
    private String name;
    @NotEmpty
    private Date birthday;

    private String address;
    @NotEmpty
    private Long provinceId;

}


