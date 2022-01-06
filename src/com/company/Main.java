package com.company;

public class Main {

    public class Employee {
        public int personalId;
        public String name;
        public DepartmentName department; // 부서
        public JobPosition jobPosition; // 직무
        public String attendance; // 근태
        public String contractType; // 계약 형태 (ex, 정규직, 계약직, 인턴 등)
        public int vacationCount; // 잔여 휴가일
    }

    public interface Vacation {
        public void dayOff();
        public void morningOff();
        public void afternoonOff();
    }


    public class useVacation implements Vacation {
        Employee employee = new Employee();

        @Override
        public void dayOff() {
            employee.vacationCount -= 1;
        }

        @Override
        public void morningOff() {
            employee.vacationCount -= 0.5;
        }

        @Override
        public void afternoonOff() {
            employee.vacationCount -= 0.5;
        }
    }

    public enum DepartmentName {
        WEB,
        MOBILE,
        MARKETING,
        PLANNING,
        HR,
        PR,
    }

    public enum JobPosition {
        FRONTEND,
        BACKEND,
        IOS,
        AOS,
        AI_DEVELOPER,
        PERFORMANCE_MARKETER,
    }


    public enum ContractType {
        FULL_TIME,
        CONTRACT_POSITION,
        INTERN
    }

    public void changeDepartment(DepartmentName department) {
        Employee employee = new Employee();
        employee.department = department;
    }

    public void changePosition(JobPosition position) {
        Employee employee = new Employee();
        employee.jobPosition = position;
    }

    public static void main(String[] args) {
        // write your code here
    }
}
