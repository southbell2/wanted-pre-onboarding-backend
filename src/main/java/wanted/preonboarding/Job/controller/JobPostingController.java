package wanted.preonboarding.Job.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import wanted.preonboarding.Job.service.JobPostingService;
import wanted.preonboarding.Job.vo.CreateJobPostingRequest;
import wanted.preonboarding.Job.vo.DetailJobPostingResponse;
import wanted.preonboarding.Job.vo.PagedJobPostingResponse;
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

    @GetMapping("/job-posting")
    public ResponseEntity<List<PagedJobPostingResponse>> showPagedJobPostings(
        @RequestParam(defaultValue = "1") int page) {
        List<PagedJobPostingResponse> pagedJobPostingResponse = jobPostingService.showPagedJobPostings(
            page);
        return ResponseEntity.ok(pagedJobPostingResponse);
    }

    @GetMapping("/job-posting/search")
    public ResponseEntity<List<PagedJobPostingResponse>> showSearchJobPostings(
        @RequestParam(defaultValue = "1") int page, @RequestParam String keyword) {
        List<PagedJobPostingResponse> pagedJobPostingResponse = jobPostingService.showSearchJobPostings(
            keyword, page);
        return ResponseEntity.ok(pagedJobPostingResponse);
    }

    @GetMapping("/job-posting/{jobPostingId}")
    public ResponseEntity<DetailJobPostingResponse> showDetailJobPosting(
        @PathVariable Long jobPostingId) {
        DetailJobPostingResponse detailJobPostingResponse = jobPostingService.showDetailJobPosting(
            jobPostingId);
        return ResponseEntity.ok(detailJobPostingResponse);
    }
}
