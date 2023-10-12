package wanted.preonboarding.Job.repository;

import java.util.List;
import wanted.preonboarding.Job.domain.JobPosting;

public interface JobPostingRepositoryCustom {

    List<JobPosting> findPagedJobPostings(int offset, int limit);
}
