package wanted.preonboarding.Job.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

import org.mockito.junit.jupiter.MockitoExtension;
import wanted.preonboarding.Job.repository.apply.ApplyRepository;

@ExtendWith(MockitoExtension.class)
class ApplyServiceTest {

    @InjectMocks
    ApplyService applyService;

    @Mock
    ApplyRepository applyRepository;

    @Test
    void 채용공고_지원() {
        // Given
        Long memberId = 1L;
        Long jobPostingId = 2L;
        String expectedId = "1x2";

        // When
        applyService.apply(memberId, jobPostingId);

        // Then
        verify(applyRepository, times(1)).saveJobApplication(argThat(
            jobApplication ->
                jobApplication.getId().equals(expectedId) &&
                    jobApplication.getMemberId().equals(memberId) &&
                    jobApplication.getJobPostingId().equals(jobPostingId)
        ));
    }
}