package wanted.preonboarding.Job.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import wanted.preonboarding.Job.service.JobPostingService;
import wanted.preonboarding.Job.vo.CreateJobPostingRequest;

@Controller
@RequiredArgsConstructor
public class JobPostingController {

    private final JobPostingService jobPostingService;

    @PostMapping("/job-posting")
    public ResponseEntity<Void> createJobPosting(
        @RequestBody CreateJobPostingRequest jobPostingRequest) {
        jobPostingService.createJobPosting(jobPostingRequest);
        return ResponseEntity.ok().build();
    }
}
