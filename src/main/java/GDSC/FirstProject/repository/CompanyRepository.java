package GDSC.FirstProject.repository;

import GDSC.FirstProject.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    boolean existsByvalue(String company_name);

    Optional<Company> findByvalue(String company_name);

}