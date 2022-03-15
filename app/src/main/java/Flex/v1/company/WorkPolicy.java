package Flex.v1.company;


import lombok.Data;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
근무 정책 생성 및 관리하는 클래스
(근무 정책 : 외근, 원격 근무, 출장 etc)
생성된 근무 정책은 근무 정책 테이블에 저장해야한다.
 */
@Getter
public class WorkPolicy {

    WorkPolicyRepository workPolicyRepository = new WorkPolicyRepository();

    /*
    근무정책 이름
     */
    private String name;

    /*
    근무정책 설명
     */
    private String workPolicyInfo;

    /*
     등록시 일정 공유
     */
    private String notification;

    /*
    유무급 여부
     */
    private boolean paid;

    /*
    야간 근무 허용
     */
    private boolean isNightWork;

    /*
    휴일 근무 허용
     */
    private boolean isWeekendWork;

    /*
    해당 근무 정책 사용시 조직장 승인이 필요한지 결정 (Y/N)
     */
    public boolean approvalAdministrator = true;

    /*
    해당 근무 정책 사용시 조직장에게 알림을 보낼지 결정 (!approvalAdministrator)
     */
    private boolean notifyAdministrator = !approvalAdministrator;

    private static final Logger logger = LoggerFactory.getLogger(WorkPolicy.class);

    public WorkPolicy(String name, String workPolicyInfo, String notification,
        boolean paid,
        boolean isNightWork,
        boolean isWeekendWork,
        boolean approvalAdministrator,
        boolean notifyAdministrator) {

        this.name = name;
        this.workPolicyInfo = workPolicyInfo;
        this.notification = notification;
        this.paid = paid;
        this.isNightWork = isNightWork;
        this.isWeekendWork = isWeekendWork;
        this.approvalAdministrator = approvalAdministrator;
        this.notifyAdministrator = notifyAdministrator;
    }

    public void notifyAdministrator() {
        if (approvalAdministrator) {
            logger.info("조직장에게 승인을 받아야 합니다.");
        } else {
            logger.info("결재를 진행합니다.");
        }
    }

    public void saveWorkPolicy(String name, WorkPolicy workPolicy) {
        workPolicyRepository.saveWorkPolicy(name, workPolicy);
        logger.info("새 근무 정책이 저장 됐습니다.");

    }

    public void deleteWorkPolicy(String name) {
        workPolicyRepository.deleteWorkPolicy(name);
        logger.info("새 근무 정책이 저장 됐습니다.");
    }

}
