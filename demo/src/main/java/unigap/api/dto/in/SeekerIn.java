package unigap.api.dto.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
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


