package com.company.v0.WorkPolicy;

public class CompanyInfo {

    private String companyName;
    private String email;
    private String joinedDay;

    private String role;
    private String organization;
    private String pid;

    public void infoSetting() {
        User flexManager = user.flexManager();
        // 회사 정보는 관리자가 입력한다.
        CompanyInfo info = flexManager.inputCompanyInfo(); // 회사 정보는 회사 클래스에 저장되야한다.
        info.save();
    }
}
