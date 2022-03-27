package Flex.v1.company.dayoff;

import org.junit.jupiter.api.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DayOffRepositoryTest {

    DayOffRepository dayOffRepository;

    static SimpleDateFormat offDate1;
    static CompanyDayOff offDays1;

    static SimpleDateFormat offDate2;
    static CompanyDayOff offDays2;

    @Test
    @DisplayName("새로운 휴일 추가")
    void SaveNewOffToOffList() {

        dayOffRepository = new DayOffRepository();

        offDate1 = new SimpleDateFormat("2022-05-09");
        offDays1 = new CompanyDayOff("대체공휴일", false, offDate1);

        dayOffRepository.saveNewOffDays(offDays1);
        assertEquals(dayOffRepository.offDaysList.get(0), offDays1);

    }

    @Test
    void GetOffDay() {

        offDate1 = new SimpleDateFormat("2022-05-09");
        offDays1 = new CompanyDayOff("대체공휴일", false, offDate1);

        offDate2 = new SimpleDateFormat("2022-05-19");
        offDays2 = new CompanyDayOff("대체공휴일2", false, offDate2);

        dayOffRepository = new DayOffRepository();

        dayOffRepository.saveNewOffDays(offDays1);
        dayOffRepository.saveNewOffDays(offDays2);

        assertEquals(dayOffRepository.getOffDays().size(), 2);

    }

}

