package wanted.preonboarding.Job.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateJobPostingRequest {

    private String jobPosition;
    private Integer compensation;
    private String jobDescription;
    private String techStack;
}
