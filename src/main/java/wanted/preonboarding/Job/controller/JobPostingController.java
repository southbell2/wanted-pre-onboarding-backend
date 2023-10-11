package wanted.preonboarding.Job.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import wanted.preonboarding.Job.service.JobPostingService;
import wanted.preonboarding.Job.vo.CreateJobPostingRequest;
import wanted.preonboarding.Job.vo.UpdateJobPostingRequest;

@Controller
@RequiredArgsConstructor
public class JobPostingController {

    private final JobPostingService jobPostingService;

    @PostMapping("/job-posting")
    public ResponseEntity<Void> createJobPosting(
        @RequestBody CreateJobPostingRequest createJobPostingRequest) {
        jobPostingService.createJobPosting(createJobPostingRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/job-posting/{jobPostingId}")
    public ResponseEntity<Void> updateJobPosting(
        @RequestBody UpdateJobPostingRequest updateJobPostingRequest,
        @PathVariable Long jobPostingId) {
        jobPostingService.updateJobPosting(updateJobPostingRequest, jobPostingId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/job-posting/{jobPostingId}")
    public ResponseEntity<Void> deleteJobPosting(@PathVariable Long jobPostingId) {
        jobPostingService.deleteJobPosting(jobPostingId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
