package Flex.v1.company.dayoff;

import Flex.v1.company.user.User;
import org.junit.jupiter.api.*;

import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DayOffRepositoryTest {

    DayOffRepository dayOffRepository = new DayOffRepository();

    static SimpleDateFormat offDate1;
    static CompanyDayOff offDays1;

    @Test
    @Order(1)
    @DisplayName("새로운 휴일을 추가하면 offDaysList 에 저장된다.")
    void Save_New_Created_DayOff() {

        offDate1 = new SimpleDateFormat("2022-05-08");
        offDays1 = new CompanyDayOff("대체공휴일", false, offDate1);

        dayOffRepository.saveNewOffDays(offDays1);
        assertEquals(DayOffRepository.offDaysList.get(0), offDays1);

    }

    @Test
    void Should_NullPointException_When_NewDayOff_Null() {
        assertThrows(NullPointerException.class, () -> dayOffRepository.saveNewOffDays(null));
    }

    @Test
    @DisplayName("User 클래스가 가지는 dayOff List 와 DayOffRepository 의 OffDays 가 같아야한다")
    void Should_Users_Have_Same_DayOffLists() {
        assertEquals(User.dayOffList, DayOffRepository.getOffDays());
    }

    @Test
    @DisplayName("동일한 일자로 휴일을 등록할 수 없다.")
    void When_DayOffDate_Duplicate_Not_Registered() {

        SimpleDateFormat offDate2 = new SimpleDateFormat("2022-05-09");
        CompanyDayOff offDays2 = new CompanyDayOff("대체공휴일2", false, offDate2);
        dayOffRepository.saveNewOffDays(offDays2);

        List<CompanyDayOff> beforeDuplicate = DayOffRepository.getOffDays();

        SimpleDateFormat offDate3 = new SimpleDateFormat("2022-05-09");
        CompanyDayOff offDays3 = new CompanyDayOff("대체공휴일3", false, offDate3);
        dayOffRepository.saveNewOffDays(offDays3);

        List<CompanyDayOff> afterDuplicate = DayOffRepository.getOffDays();
        assertEquals(beforeDuplicate, afterDuplicate);
    }

}

