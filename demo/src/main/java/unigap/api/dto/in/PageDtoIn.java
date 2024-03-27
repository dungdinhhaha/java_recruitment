package unigap.api.dto.in;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class PageDtoIn {

    @Min(value = 1)
    private Integer page = 1;


    @Min(value = 1)
    @Max(value = 500)
    private Integer pageSize = 10;
}
