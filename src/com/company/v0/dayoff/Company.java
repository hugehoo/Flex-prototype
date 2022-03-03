package com.company.v0.dayoff;

public class Company {

    private String[] dayOffPolicies;
    public static String currentDayOffPolicy;

    public static String checkCurrentDayOffPolicy() {
        return currentDayOffPolicy;
    }
}
