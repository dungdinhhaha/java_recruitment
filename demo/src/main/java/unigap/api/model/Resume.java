package unigap.api.model;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "resume")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "seeker_id")
    private Seeker seeker_id;

    @Column(name = "career_obj", columnDefinition = "TEXT")
    private String careerObjective;

    @Column(name = "title", columnDefinition = "TEXT")
    private String title;

    @Column(name = "salary")
    private int salary;

    @Column(name = "fields", columnDefinition = "TEXT")
    private int fields;

    @Column(name = "provinces", columnDefinition = "TEXT")
    private String provinces;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

}
