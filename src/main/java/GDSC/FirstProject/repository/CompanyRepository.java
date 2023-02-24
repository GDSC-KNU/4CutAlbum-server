package GDSC.FirstProject.repository;

import GDSC.FirstProject.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    boolean existsByvalue(String company_name);
}