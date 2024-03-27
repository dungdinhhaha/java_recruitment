package unigap.api.dto.in;


import lombok.*;

import unigap.api.model.Job_Field;
import unigap.api.model.Job_Province;

import java.util.Date;
import java.util.List;

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

    private List<Job_Field> fieldId;

    private  List<Job_Province> provincedIds;

    private Integer salary;

    private Date expired ;
}
