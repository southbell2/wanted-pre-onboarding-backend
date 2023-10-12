package wanted.preonboarding.Job.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplyRequest {

    private Long memberId;
    private Long jobPostingId;
}
