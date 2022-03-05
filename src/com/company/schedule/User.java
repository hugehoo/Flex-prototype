package flex.schedule;


import com.company.schedule.ScheduleMain.Company;

class User {

    Company company;
    WorkType workType;

    public User(Company company) {
        this.company = company;
    }

    public WorkType getBasicWorkType() {
        return workType.basicWorkTypeList.get(0);
    }
}