package GDSC.FirstProject.controller;

import GDSC.FirstProject.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService service;

    @GetMapping("/company")
    public Map<String, List<String>> findCompanyList() {
        return service.findCompanyList();
    }

}
