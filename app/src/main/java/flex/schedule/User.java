package flex.schedule;


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