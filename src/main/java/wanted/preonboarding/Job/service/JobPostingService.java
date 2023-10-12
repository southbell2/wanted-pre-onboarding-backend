package wanted.preonboarding.Job.service;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wanted.preonboarding.Job.domain.Company;
import wanted.preonboarding.Job.domain.JobPosting;
import wanted.preonboarding.Job.exception.NoCompanyException;
import wanted.preonboarding.Job.exception.NoJobPostingException;
import wanted.preonboarding.Job.repository.company.CompanyRepository;
import wanted.preonboarding.Job.repository.jobposting.JobPostingRepository;
import wanted.preonboarding.Job.dto.CreateJobPostingRequest;
import wanted.preonboarding.Job.dto.DetailJobPostingResponse;
import wanted.preonboarding.Job.dto.PagedJobPostingResponse;
import wanted.preonboarding.Job.dto.UpdateJobPostingRequest;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class JobPostingService {

    private final JobPostingRepository jobPostingRepository;
    private final CompanyRepository companyRepository;

    @Transactional
    public void createJobPosting(CreateJobPostingRequest postingRequest) {
        Company company = companyRepository.findById(postingRequest.getCompanyId())
            .orElseThrow(() -> new NoCompanyException("회사가 존재하지 않습니다."));

        JobPosting jobPosting = JobPosting.builder()
            .jobDescription(postingRequest.getJobDescription())
            .jobPosition(postingRequest.getJobPosition())
            .compensation(postingRequest.getCompensation())
            .techStack(postingRequest.getTechStack())
            .company(company)
            .build();

        jobPostingRepository.save(jobPosting);
    }

    @Transactional
    public void updateJobPosting(UpdateJobPostingRequest updateJobPostingRequest,
        Long jobPostingId) {
        JobPosting jobPosting = jobPostingRepository.findById(jobPostingId)
            .orElseThrow(() -> new NoJobPostingException("채용공고가 존재하지 않습니다."));

        jobPosting.updateJobPosting(updateJobPostingRequest);
    }

    @Transactional
    public void deleteJobPosting(Long jobPostingId) {
        JobPosting jobPosting = jobPostingRepository.findById(jobPostingId)
            .orElseThrow(() -> new NoJobPostingException("채용공고가 존재하지 않습니다."));

        jobPostingRepository.delete(jobPosting);
    }

    public List<PagedJobPostingResponse> showPagedJobPostings(int page) {
        List<JobPosting> jobPostings = jobPostingRepository.findPagedJobPostings(5 * (page - 1), 5);
        return jobPostings.stream()
            .map(JobPosting::toPagedJobPostingResponse)
            .toList();
    }

    public List<PagedJobPostingResponse> showSearchJobPostings(String keyword, int page) {
        List<JobPosting> jobPostings = jobPostingRepository.findSearchJobPostings(keyword,
            5 * (page - 1), 5);
        return jobPostings.stream()
            .map(JobPosting::toPagedJobPostingResponse)
            .toList();
    }

    public DetailJobPostingResponse showDetailJobPosting(Long jobPostingId) {
        JobPosting jobPosting = jobPostingRepository.findByIdWithCompany(jobPostingId)
            .orElseThrow(() -> new NoJobPostingException("채용공고가 존재하지 않습니다."));

        List<Long> otherPostingId = jobPostingRepository.findOtherPostingId(
            jobPosting.getCompany().getId(), jobPostingId);

        return jobPosting.toDetailJobPostingResponse(otherPostingId);
    }
}
