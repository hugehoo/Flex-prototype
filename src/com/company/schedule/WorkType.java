package com.company.schedule;


import com.company.schedule.ScheduleMain.Company;
import java.util.ArrayList;
import java.util.List;

class WorkType implements WorkTypeRepository {

    private Company company;
    WorkType ordinaryWork;
    WorkType dayOff;

    public WorkType(Company company) {
        this.company = company;
    }

    public List<WorkType> basicWorkTypeList = new ArrayList<>() {{
        add(ordinaryWork);
        add(dayOff);
    }};

    @Override
    public List<WorkType> getWorkTypeList() {
        return basicWorkTypeList;
    }

    public WorkType getPersonalWorkType(User user) {
        return user.getBasicWorkType();
    }

}