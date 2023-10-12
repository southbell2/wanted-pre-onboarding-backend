package wanted.preonboarding.Job.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wanted.preonboarding.Job.domain.JobApplication;
import wanted.preonboarding.Job.repository.apply.ApplyRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ApplyService {

    private final ApplyRepository applyRepository;

    @Transactional
    public void apply(Long memberId, Long jobPostingId) {
        String id = makeJobApplicationId(memberId, jobPostingId);
        JobApplication jobApplication = JobApplication.builder()
            .id(id)
            .memberId(memberId)
            .jobPostingId(jobPostingId)
            .build();

        applyRepository.saveJobApplication(jobApplication);
    }

    private String makeJobApplicationId(Long memberId, Long jobPostingId) {
        return memberId.toString() + "x" + jobPostingId.toString();
    }
}
