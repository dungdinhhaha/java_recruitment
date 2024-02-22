package unigap.api.model;

import lombok.*;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "employer")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Employer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String email;
    @Column(length = 255)
    private String name;
    @Column(name = "province_id")
    private Integer province;
    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "created_at")
    private Date created_at;

    @Column(name = "updated_at")
    private Date updated_at;
    @Column(name = "AVATAR")
    private String avatar;
}
