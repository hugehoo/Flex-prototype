package Flex.v1.company.leave;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 회사의 휴가 목록을 저장하고 관리하는 클래스입니다.
 */
public class LeaveRepository {

    /**
     * 회사 전체 휴가 목록을 저장하는 리스트
     */
    List<Leave> companyLeaveCollection = new ArrayList<>();

    Logger logger = LoggerFactory.getLogger(LeaveRepository.class);

    /**
     * 회사의 전체 휴가 목록을 반환합니다.
     * @return 전체 휴가 리스트
     */
    public List<Leave> getLeave() {
        logger.info("회사의 휴가 목록을 반환합니다.");
        logger.info("{}", companyLeaveCollection);

        return companyLeaveCollection;
    }

    /**
     * 새로 지정한 휴가를 저장합니다.
     * @param leave 저장할 휴가
     */
    public void saveLeave(Leave leave) {
        companyLeaveCollection.add(leave);
        logger.info("{}를 저장합니다. ", leave.getName());
    }

    /**
     * 법정 지정 휴가를 반환합니다.
     * @return 법정 지정 휴가
     */
    public List<Leave> getLegalOfficialLeave() {
        return null;
    }

}
