package com.company.v0;

public class Main {
    public String name;

    public Main(String name) {
        this.name = name;
    }

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


    public class UseVacation implements Vacation {

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

    // 어느 객체가 가지고 있는것이 좋을지 생각해볼 것
    public void changeDepartment(DepartmentName department) {
        Employee employee = new Employee();
        employee.department = department;
    }

    public void changePosition(JobPosition position) {
        Employee employee = new Employee();
        employee.jobPosition = position;
    }

    public static void main(String[] args) {

//        String a = new String("a");
//        String b = new String("a");
//        System.out.println(a == b);
//        System.out.println(a.equals(b));

        Main str1 = new Main("객체");
        Main str2 = new Main("객체");
        System.out.println(str1);
        System.out.println(str2);
        System.out.println(str1.equals(str2)); // >>> false 인 이유는 String 클래스의 equals 와 Object 클래스의 equals 는 다르기 때문.
    }
}
