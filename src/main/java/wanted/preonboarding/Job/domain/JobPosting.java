package wanted.preonboarding.Job.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import wanted.preonboarding.Job.vo.PagedJobPostingResponse;
import wanted.preonboarding.Job.vo.UpdateJobPostingRequest;

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

    public void updateJobPosting(UpdateJobPostingRequest updateJobPostingRequest) {
        jobPosition = updateJobPostingRequest.getJobPosition();
        compensation = updateJobPostingRequest.getCompensation();
        techStack = updateJobPostingRequest.getTechStack();
        jobDescription = updateJobPostingRequest.getJobDescription();
    }

    public PagedJobPostingResponse toPagedJobPostingResponse() {
        PagedJobPostingResponse pagedJobPostingResponse = new PagedJobPostingResponse();
        pagedJobPostingResponse.setJobPosition(jobPosition);
        pagedJobPostingResponse.setJobPostingId(id);
        pagedJobPostingResponse.setCountry(company.getCountry());
        pagedJobPostingResponse.setRegion(company.getRegion());
        pagedJobPostingResponse.setCompanyName(company.getName());
        pagedJobPostingResponse.setTechStack(techStack);
        pagedJobPostingResponse.setCompensation(compensation);

        return pagedJobPostingResponse;
    }
}
