package unigap.api.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "job")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Getter
@Setter
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "title")
    private  String title;
    @ManyToOne
    @JoinColumn(name="employer_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Employer employer;
    @Column(name = "province_id")
    private Integer province;
    @Column(name = "quanlity")
    private Integer quanlity;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(name = "salary")
    private Integer salary;
    @Column(name = "fields")
    private Integer fields;
    @Column(name = "created_at")
    private Date created_at;
    @Column(name = "updated_at")
    private Date updated_at ;
    @Column(name = "expired_at")
    private Date expired_at ;
}
