package wanted.preonboarding.Job.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "job_posting")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobPosting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "VARCHAR(255)")
    private String jobPosition;

    @Column(columnDefinition = "INT")
    private Integer compensation;

    @Column(columnDefinition = "VARCHAR(255)")
    private String techStack;

    @Column(columnDefinition = "VARCHAR(255)")
    private String jobDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

}
