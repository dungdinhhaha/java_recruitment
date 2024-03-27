package unigap.api.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "job_province_seq")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Getter
@Setter
public class Job_Province_seq {
    @Embeddable
    public static class JobProvinceSeq implements Serializable {
        @Column(name = "job_id")
        private Integer jobId;

        @Column(name = "province_id")
        private Integer provinceId;

        // Equals and hashCode methods
    }

    @EmbeddedId
    private Job_Province_seq.JobProvinceSeq id;

    // Define many-to-one relationship with Job_Field

    // Getters and setters
}
