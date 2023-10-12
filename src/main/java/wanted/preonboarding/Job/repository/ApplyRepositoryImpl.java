package wanted.preonboarding.Job.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import wanted.preonboarding.Job.domain.JobApplication;

@RequiredArgsConstructor
public class ApplyRepositoryImpl implements ApplyRepositoryCustom{

    private final EntityManager em;

    @Override
    public void saveJobApplication(JobApplication jobApplication) {
        em.persist(jobApplication);
    }
}
