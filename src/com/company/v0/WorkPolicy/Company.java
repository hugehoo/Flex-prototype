package com.company.v0.WorkPolicy;

public class Company {

    private String companyName;
    private String email;
    private String joinedDay;

    private String role;
    private String organization;
    private String cid;

    public void setCompanyInfo(String infos) {
        User manager = User.isManagerAuthorized(id);
        // 회사 정보는 관리자가 입력한다.
        Company info = inputCompanyInfo(infos); // 회사 정보는 회사 클래스에 저장되야한다.

        info.save();
    }

    private void save() {
        System.out.println("save company infos");
    }

    private Company inputCompanyInfo(String infos) {
        System.out.println(" input Company Information ");
    }
}
