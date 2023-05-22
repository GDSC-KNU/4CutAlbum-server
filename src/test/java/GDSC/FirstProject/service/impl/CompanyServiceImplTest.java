package GDSC.FirstProject.service.impl;

import GDSC.FirstProject.service.CompanyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
class CompanyServiceImplTest {

    @Autowired
    CompanyService service;

    @Test
    public void findCompanyListTest() throws Exception {
        //given

        //when
        Map<String, List<String>> result = service.findCompanyList();

        //then
        for (String s : result.keySet()) {
            System.out.println("키 : " + s);
            for (String s1 : result.get(s)) {
                System.out.println("밸류 : " + s1);
            }
        }
    }
}
