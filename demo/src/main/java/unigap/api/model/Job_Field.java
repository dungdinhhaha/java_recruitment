package unigap.api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "job_Field")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Getter
@Setter
public class Job_Field {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "title")
    private  String name;

}
