package GDSC.FirstProject.service.impl;

import GDSC.FirstProject.entity.Company;
import GDSC.FirstProject.repository.CompanyRepository;
import GDSC.FirstProject.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository repository;

    @Override
    public Map<String, List<String>> findCompanyList() {
        List<Company> companies = repository.findAll();
        List<String> companyNames = new ArrayList<>();

        for (Company company : companies)
            companyNames.add(company.getValue());

        return Map.of("companyNames", companyNames);
    }
}
