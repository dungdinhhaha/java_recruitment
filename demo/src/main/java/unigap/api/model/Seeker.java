package unigap.api.model;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "seeker")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Seeker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", columnDefinition = "TEXT")
    private String name;

    @Column(name = "birthday", columnDefinition = "TEXT")
    private Date birthday;

    @Column(name = "address", columnDefinition = "TEXT")
    private String address;

    @Column(name = "province")
    private Integer province;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;
}
