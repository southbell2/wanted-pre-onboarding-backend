package wanted.preonboarding.Job.repository;

import jakarta.persistence.EntityManager;
import java.util.List;
import lombok.RequiredArgsConstructor;
import wanted.preonboarding.Job.domain.JobPosting;

@RequiredArgsConstructor
public class JobPostingRepositoryImpl implements JobPostingRepositoryCustom {

    private final EntityManager em;

    @Override
    public List<JobPosting> findPagedJobPostings(int offset, int limit) {
        return em.createQuery(
                "SELECT jp FROM JobPosting jp "
                    + "JOIN FETCH jp.company c "
                    + "ORDER BY jp.id DESC", JobPosting.class)
            .setFirstResult(offset)
            .setMaxResults(limit)
            .getResultList();
    }
}
