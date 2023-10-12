package wanted.preonboarding.Job.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import wanted.preonboarding.Job.service.ApplyService;
import wanted.preonboarding.Job.vo.ApplyRequest;

@Controller
@RequiredArgsConstructor
public class ApplyController {

    private final ApplyService applyService;

    @PostMapping("/apply")
    public ResponseEntity<Void> apply(@RequestBody ApplyRequest applyRequest) {
        applyService.apply(applyRequest.getMemberId(), applyRequest.getJobPostingId());
        return ResponseEntity.ok().build();
    }
}
