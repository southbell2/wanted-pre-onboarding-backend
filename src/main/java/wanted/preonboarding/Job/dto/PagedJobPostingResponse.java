package wanted.preonboarding.Job.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagedJobPostingResponse {

    private Long jobPostingId;
    private String companyName;
    private String country;
    private String region;
    private String jobPosition;
    private Integer compensation;
    private String techStack;
}
