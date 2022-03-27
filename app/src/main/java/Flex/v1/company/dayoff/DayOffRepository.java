package Flex.v1.company.dayoff;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DayOffRepository {

    static List<CompanyDayOff> offDaysList = new ArrayList<>();
    private static Logger logger = LoggerFactory.getLogger(DayOffRepository.class);

    /**
     * 새로 지정한 휴일을 저장합니다.
     *
     * @param offDay : 생성한 휴일 인스턴스
     */
    public void saveNewOffDays(@NonNull CompanyDayOff offDay) {
        List<SimpleDateFormat> dateList =
                offDaysList.stream()
                        .map(CompanyDayOff::getOffDate)
                        .collect(Collectors.toList());

        if (!dateList.contains(offDay.getOffDate())) {
            offDaysList.add(offDay);
        } else {
            logger.warn("{} 는 이미 지정된 휴일입니다.", offDay.getOffDate());
        }
    }

    /**
     * 회사의 전체 휴일을 보여주는 메서드
     *
     * @return 전체 휴일이 저장된 리스트
     */
    static public List<CompanyDayOff> getOffDays() {
        // 규약 1. 모든 유저는 공통된 휴일 리스트를 리턴받는다.
        logger.info("회사의 지정 휴일을 보여줍니다.");
        for (CompanyDayOff offDay : offDaysList) {
            logger.info("{}", offDay.toString());
        }
        return offDaysList;
    }

}
