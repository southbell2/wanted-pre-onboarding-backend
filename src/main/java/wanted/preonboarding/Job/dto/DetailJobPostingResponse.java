package wanted.preonboarding.Job.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetailJobPostingResponse {

    private Long jobPostingId;
    private String companyName;
    private String country;
    private String region;
    private String jobPosition;
    private Integer compensation;
    private String techStack;
    private String jobDescription;
    private List<Long> otherJobPostingId;
}
