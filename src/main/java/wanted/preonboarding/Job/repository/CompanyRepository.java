package wanted.preonboarding.Job.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wanted.preonboarding.Job.domain.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

}
