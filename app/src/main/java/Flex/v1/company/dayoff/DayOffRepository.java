package Flex.v1.company.dayoff;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DayOffRepository {

    List<CompanyDayOff> offDaysList = new ArrayList<>();
    private static Logger logger = LoggerFactory.getLogger(DayOffRepository.class);

    /**
     * 새로 지정한 휴일을 저장합니다.
     * @param offDay : 생성한 휴일 인스턴스
     */
    public void saveNewOffDays(CompanyDayOff offDay) {
        offDaysList.add(offDay);
        logger.info("{}이 지정 됐습니다.", offDay.getName());
    }

    /**
     * 회사의 전체 휴일을 보여주는 메서드
     * @return 전체 휴일이 저장된 리스트
     */
    public List<CompanyDayOff> getOffDays() {
        logger.info("회사의 지정 휴일을 보여줍니다.");
        for (CompanyDayOff offDay : offDaysList) {
            logger.info("{}", offDay.toString());
        }
        return offDaysList;
    }

}
