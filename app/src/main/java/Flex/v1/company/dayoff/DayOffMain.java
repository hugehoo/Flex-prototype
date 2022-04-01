package Flex.v1.company.dayoff;

import java.text.SimpleDateFormat;

public class DayOffMain {

    public void execute() {
        DayOffRepository offDaysRepository = new DayOffRepository();

        SimpleDateFormat date = new SimpleDateFormat("2022-05-09");
        CompanyDayOff offDays = new CompanyDayOff("석가탄신일 대체 휴일", false, date);

        SimpleDateFormat date2 = new SimpleDateFormat("2022-12-24");
        CompanyDayOff offDays2 = new CompanyDayOff("크리스마스 이브 휴일", true, date2);

        offDaysRepository.saveNewOffDays(offDays);
        offDaysRepository.saveNewOffDays(offDays2);
        offDaysRepository.getOffDays();

    }

}
