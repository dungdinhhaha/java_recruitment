package unigap.api.model;

import lombok.*;

import jakarta.persistence.*;
import unigap.api.service.EmployerListener;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

@Entity
@Table(name = "employer")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Getter
@Setter
@EntityListeners(EmployerListener.class)
public class Employer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 255, nullable = false, unique = true)
    private String email;

    @Column(length = 255)
    private String name;

    @Column(name = "province_id")
    private Integer provinceId;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "password")
    private String password;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "AVATAR")
    private String avatar;

    @OneToMany(mappedBy = "employer", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Builder.Default
    private Collection<Job> jobs = new HashSet<>();

    public void addJob(Job job) {
        jobs.add(job);
    }
}


