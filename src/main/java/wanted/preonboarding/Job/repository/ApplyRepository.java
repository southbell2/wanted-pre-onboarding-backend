package wanted.preonboarding.Job.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wanted.preonboarding.Job.domain.JobApplication;

public interface ApplyRepository extends JpaRepository<JobApplication, String>,
    ApplyRepositoryCustom {

}
