package wanted.preonboarding.Job.repository.company;

import org.springframework.data.jpa.repository.JpaRepository;
import wanted.preonboarding.Job.domain.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

}
