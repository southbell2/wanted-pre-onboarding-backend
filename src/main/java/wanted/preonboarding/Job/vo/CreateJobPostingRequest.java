package wanted.preonboarding.Job.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateJobPostingRequest {

    private Long companyId;
    private String jobPosition;
    private Integer compensation;
    private String jobDescription;
    private String techStack;
}
