package wanted.preonboarding.Job.repository.jobposting;

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

    @Override
    public List<JobPosting> findSearchJobPostings(String keyword, int offset, int limit) {
        return em.createQuery(
                "SELECT jp FROM JobPosting jp "
                    + "JOIN FETCH jp.company c "
                    + "WHERE jp.jobPosition like concat('%', :keyword, '%') or jp.techStack like concat('%', :keyword, '%') "
                    + "or jp.jobDescription like concat('%', :keyword, '%') or c.name like concat('%', :keyword, '%') "
                    + "or c.country like concat('%', :keyword, '%') or c.region like concat('%', :keyword, '%') "
                    + "ORDER BY jp.id DESC", JobPosting.class)
            .setFirstResult(offset)
            .setMaxResults(limit)
            .setParameter("keyword", keyword)
            .getResultList();
    }
}
