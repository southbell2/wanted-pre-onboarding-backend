package wanted.preonboarding.Job.repository.jobposting;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import wanted.preonboarding.Job.domain.JobPosting;

public interface JobPostingRepository extends JpaRepository<JobPosting, Long>,
    JobPostingRepositoryCustom {

    @Query("SELECT jp FROM JobPosting jp JOIN FETCH jp.company WHERE jp.id = :jobPostingId")
    Optional<JobPosting> findByIdWithCompany(@Param("jobPostingId") Long jobPostingId);

    @Query("SELECT jp.id FROM JobPosting jp JOIN jp.company c WHERE c.id = :companyId AND jp.id != :jobPostingId")
    List<Long> findOtherPostingId(@Param("companyId") Long companyId,
        @Param("jobPostingId") Long jobPostingId);
}
