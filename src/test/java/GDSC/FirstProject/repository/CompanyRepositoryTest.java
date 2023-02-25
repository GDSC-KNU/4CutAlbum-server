package GDSC.FirstProject.repository;

import GDSC.FirstProject.entity.Company;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CompanyRepositoryTest {

    @Autowired
    CompanyRepository companyRepository;

    @Test
    public void findExistsByCompanyNameTest() throws Exception{
        //given
        String companyName = "인생네컷";

        //when
        boolean existsByCompanyName = companyRepository.existsByvalue(companyName);

        //then
        assertThat(existsByCompanyName).isEqualTo(true);
    }

    @Test
    public void findByvalueTest() throws Exception{
        //given
        String value = "인생네컷";

        //when
        Company findCompany = companyRepository.findByvalue(value).orElseThrow(()-> new IllegalArgumentException("회사가 존재하지 않습니다."));

        //then
        assertThat(findCompany.getValue()).isEqualTo(value);
    }
}