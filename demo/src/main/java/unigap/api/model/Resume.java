package unigap.api.model;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "resume")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    @JoinColumn(name = "seeker_id")
    private Seeker seeker;

    @Column(name = "career_obj", columnDefinition = "TEXT")
    private String careerObjective;

    @Column(name = "title", columnDefinition = "TEXT")
    private String title;

    @Column(name = "salary")
    private int salary;

    @Column(name = "fields", columnDefinition = "TEXT")
    private int fields;

    @Column(name = "provinces", columnDefinition = "TEXT")
    private Integer provinces;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

}
