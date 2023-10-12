package wanted.preonboarding.Job.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "job_application")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobApplication {

    @Id
    private String id;

    @Column(name = "posting_id", columnDefinition = "BIGINT NOT NULL")
    private Long jobPostingId;

    @Column(name = "member_id", columnDefinition = "BIGINT NOT NULL")
    private Long memberId;
}
