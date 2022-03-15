package Flex.v1.company;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CompanyMain {

    public Company createCompany(String name,
        String nameCEO, String registrationNumber,
        String address, String companyAnniversary,
        String representationalPhoneNumber
    ) {

        return new Company(
            name,
            nameCEO,
            registrationNumber,
            address,
            companyAnniversary,
            representationalPhoneNumber);
    }

    private static final Logger logger = LoggerFactory.getLogger(CompanyMain.class);

    public void execute() {
        Company company = createCompany("OOP", "앨런 케이", "12345", "실리콘벨리", "2022-02-01",
            "82-10-3310-2203");
        logger.info("회사 정보 : {} ",company.toString());
    }
}
