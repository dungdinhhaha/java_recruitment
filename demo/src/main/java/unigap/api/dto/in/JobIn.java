package unigap.api.dto.in;


import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class JobIn {

    private String title;

    private Integer employer_id;

    private Integer quanlity;

    private String description;

    private  Integer fieldId;

    private  Integer provincedIds;

    private Integer salary;

    private Date expired ;
}
