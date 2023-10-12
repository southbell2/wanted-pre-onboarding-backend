package wanted.preonboarding.Job.service;


import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.mockito.junit.jupiter.MockitoExtension;
import wanted.preonboarding.Job.domain.Company;
import wanted.preonboarding.Job.domain.JobPosting;
import wanted.preonboarding.Job.dto.CreateJobPostingRequest;
import wanted.preonboarding.Job.dto.DetailJobPostingResponse;
import wanted.preonboarding.Job.dto.PagedJobPostingResponse;
import wanted.preonboarding.Job.dto.UpdateJobPostingRequest;
import wanted.preonboarding.Job.repository.company.CompanyRepository;
import wanted.preonboarding.Job.repository.jobposting.JobPostingRepository;

@ExtendWith(MockitoExtension.class)
class JobPostingServiceTest {

    @InjectMocks
    private JobPostingService jobPostingService;

    @Mock
    private CompanyRepository companyRepository;

    @Mock
    private JobPostingRepository jobPostingRepository;

    @Mock
    private JobPosting jobPosting;

    @Mock
    private Company company;

    @Test
    void 채용공고_등록() {
        // Given
        CreateJobPostingRequest postingRequest = new CreateJobPostingRequest();
        postingRequest.setCompanyId(1L);
        postingRequest.setJobDescription("backend junior");
        postingRequest.setJobPosition("backend");
        postingRequest.setCompensation(10000);
        postingRequest.setTechStack("java");

        when(companyRepository.findById(1L)).thenReturn(Optional.of(company));

        // When
        jobPostingService.createJobPosting(postingRequest);

        // Then
        verify(companyRepository, times(1)).findById(1L);
        verify(jobPostingRepository, times(1)).save(any(JobPosting.class));
    }

    @Test
    void 채용공고_수정() {
        // Given
        Long jobPostingId = 1L;
        UpdateJobPostingRequest updateRequest = new UpdateJobPostingRequest();

        when(jobPostingRepository.findById(jobPostingId))
            .thenReturn(Optional.of(jobPosting));

        // When
        jobPostingService.updateJobPosting(updateRequest, jobPostingId);

        // Then
        verify(jobPosting, times(1)).updateJobPosting(updateRequest);
    }

    @Test
    void 채용공고_삭제() {
        // Given
        Long jobPostingId = 1L;

        when(jobPostingRepository.findById(jobPostingId))
            .thenReturn(Optional.of(jobPosting));

        // When
        jobPostingService.deleteJobPosting(jobPostingId);

        // Then
        verify(jobPostingRepository, times(1)).delete(jobPosting);
    }

    @Test
    void 채용공고_목록_보기() {
        // Given
        int page = 2;
        PagedJobPostingResponse response = mock(PagedJobPostingResponse.class);

        // Assume
        when(jobPostingRepository.findPagedJobPostings(5, 5))
            .thenReturn(List.of(jobPosting));
        when(jobPosting.toPagedJobPostingResponse()).thenReturn(response);

        // When
        List<PagedJobPostingResponse> result = jobPostingService.showPagedJobPostings(page);

        // Then
        assertThat(result.size()).isEqualTo(1);
        assertThat(response).isEqualTo(result.get(0));
        verify(jobPostingRepository, times(1))
            .findPagedJobPostings(5 * (page - 1), 5);
    }

    @Test
    void 채용공고_검색_보기() {
        // Given
        String keyword = "Java";
        int page = 1;
        PagedJobPostingResponse pagedResponse = new PagedJobPostingResponse();
        when(jobPostingRepository.findSearchJobPostings(keyword, 0, 5))
            .thenReturn(List.of(jobPosting));
        when(jobPosting.toPagedJobPostingResponse()).thenReturn(pagedResponse);

        // When
        List<PagedJobPostingResponse> results = jobPostingService.showSearchJobPostings(keyword,
            page);

        // Then
        assertThat(results.size()).isEqualTo(1);
        assertThat(results.get(0)).isEqualTo(pagedResponse);
        verify(jobPostingRepository, times(1)).findSearchJobPostings(keyword, 0, 5);
        verify(jobPosting, times(1)).toPagedJobPostingResponse();
    }

    @Test
    void 채용공고_상세_보기() {
        // Given
        Long jobPostingId = 1L;
        Long companyId = 1L;
        List<Long> otherJobPostingIds = List.of(2L, 3L);
        DetailJobPostingResponse detailResponse = new DetailJobPostingResponse();

        when(jobPostingRepository.findByIdWithCompany(jobPostingId))
            .thenReturn(Optional.of(jobPosting));
        when(jobPosting.getCompany()).thenReturn(company);
        when(company.getId()).thenReturn(companyId);
        when(jobPostingRepository.findOtherPostingId(companyId, jobPostingId))
            .thenReturn(otherJobPostingIds);
        when(jobPosting.toDetailJobPostingResponse(otherJobPostingIds)).thenReturn(detailResponse);

        // When
        DetailJobPostingResponse result = jobPostingService.showDetailJobPosting(jobPostingId);

        // Then
        assertThat(result).isEqualTo(detailResponse);
        verify(jobPostingRepository, times(1)).findByIdWithCompany(jobPostingId);
        verify(jobPostingRepository, times(1)).findOtherPostingId(companyId, jobPostingId);
        verify(jobPosting, times(1)).toDetailJobPostingResponse(otherJobPostingIds);
    }
}