package Flex.v1.company;


/**
 * 회사 클래스, 하나의 회사를 나타내는 정보를 가진다.
 * 얘는 어떤 메서드를 가져야할까.
 */
public class Company {

    private final String name;
    private final String nameCEO;
    private final String registrationNumber;
    private final String address;

    /*
    etd : established date
     */
    private String companyAnniversary;
    private String companyPhoneNuber;

    public Company(String name, String nameCEO, String registrationNumber, String address,
        String companyAnniversary, String representationalPhoneNumber) {
        this.name = name;
        this.nameCEO = nameCEO;
        this.registrationNumber = registrationNumber;
        this.address = address;
        this.companyAnniversary = companyAnniversary;
        this.companyPhoneNuber = representationalPhoneNumber;
    }

    @Override
    public String toString() {
        return "Company{" +
            "name='" + name + '\'' +
            ", nameCEO='" + nameCEO + '\'' +
            ", registrationNumber='" + registrationNumber + '\'' +
            ", address='" + address + '\'' +
            ", companyAnniversary='" + companyAnniversary + '\'' +
            ", representationalPhoneNumber='" + companyPhoneNuber + '\'' +
            '}';
    }
}
