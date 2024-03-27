package unigap.api.model;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "job_field_seq")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Getter
@Setter
public class Job_Field_seq {

    @Embeddable
    public static class JobFieldSeqId implements Serializable {
        @Column(name = "job_id")
        private Integer jobId;

        @Column(name = "field_id")
        private Integer fieldId;

        // Equals and hashCode methods
    }

    @EmbeddedId
    private JobFieldSeqId id;

    // Define many-to-one relationship with Job_Field

    // Getters and setters
}