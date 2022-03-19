package Flex.v1.company.worktype;


// JUnit4 를 사용할 때 import 경로
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
//
// Junit5 를 사용할 때 경로
//import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test; // Junit4 의 import/ JUnit5의 vintage 제외를 하지 않더라도 import 할 수 없게 된다.
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.Assertions.*;

// 1) 4, 5 모두 있을 땐 @Before 잘 동작
// 2) 5만 있을 땐 @Before 못찾음, 5만 있더라도 vintage 가 자동으로 설치되진 않는다 (왜?)
// 3) 의문 : 왜 JUnit5 dependency 를 추가해도, External Libraries 에는 Vintage 만 안보이지(platform, jupiter 모두 존재)
public class WorkTypeTest {

    static List<String> workingDay;
    static List<String> offDay;
    static WorkType workType;

    @Before
    public void BeforeTest() {
        System.out.println("you running?");
        workingDay = new ArrayList<>();
        workingDay.add("월");
        workingDay.add("화");
        workingDay.add("수");
        workingDay.add("목");
        workingDay.add("금");

        offDay = new ArrayList<>();
        offDay.add("일");

        workType = new WorkType(
            "고정 출퇴근제",
            "고정",
            "09:00:00",
            "18:00:00",
            1L,
            workingDay,
            offDay
        );
    }


    //    @Display() : JUnit4 에서 사용불가.
    @Test
    public void testJunit4Methods() throws ParseException {

        // 1. Junit4 의 assertThat 은 deprecated 돼있다.
//        assertThat(40, workType.countWeeklyWorkingHour());

        System.out.println(workType);
        assertEquals(40, workType.countWeeklyWorkingHour());
        assertTrue(workType.checkStatutoryWorkingHour(40));
        assertFalse(workType.checkStatutoryWorkingHour(45));
//        assertAll(() -> assertFalse(true, "Exception!!!"));
    }

//    @Test
//    @DisplayName("JUnit5 메소드 테스트 : JUnit Vintages 를 제외하기 전")
//    public void testJUnit5Methods() {
//        assertAll("AssertAll",
//            () -> assertEquals(40, workType.countWeeklyWorkingHour()),
//            () -> assertTrue(workType.checkStatutoryWorkingHour(40))
//        );
//    }

    //    @Test
//    @DisplayName("JUnit Vintages 제외")
//    public void testJUnitVintagesExclude() {
//        // @Before 어노테이션 정상 동작 -> Junit4 를 안지워서 그랬음 -> 지우니까 동작안한다.
//        // todo : Junit4 호환해주는 Vintage 를 다시 깔고 시험해봐야지
//    }
}

// 트러블 : JUnit5 만 있을 때는(jUnit4 없음) vintage 가 있더라도, @Before 어노테이션이 붙은 메서드가 실행이 안됨.
// 가설: 위 상태에서 JUnit4 를 추가해본다. 그럼 @Before 이 붙은 메서드가 실행되지 않을까?


