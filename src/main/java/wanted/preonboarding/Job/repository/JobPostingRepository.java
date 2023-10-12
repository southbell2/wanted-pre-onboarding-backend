package wanted.preonboarding.Job.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wanted.preonboarding.Job.domain.JobPosting;

public interface JobPostingRepository extends JpaRepository<JobPosting, Long>, JobPostingRepositoryCustom {

}
