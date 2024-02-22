package unigap.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "job")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private  String title;
    @ManyToOne
    @JoinColumn(name = "employer_id")
    private Employer employer_id;
    @Column(name = "province_id")
    private Integer province;
    @Column(name = "quanlity")
    private  int quanlity;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(name = "salary")
    private int salary;
    @Column(name = "fields")
    private  int fields;

    @Column(name = "created_at")
    private Date created_at;

    @Column(name = "updated_at")
    private Date updated_at ;
    @Column(name = " expired_at")
    private Date expired_at ;

}
