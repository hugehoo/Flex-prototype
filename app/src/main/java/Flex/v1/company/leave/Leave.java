package Flex.v1.company.leave;

import lombok.Getter;
import lombok.Setter;

/**
 * 휴가를 생성하는 클래스입니다.
 */
@Getter
@Setter
public class Leave {

    /**
     * 휴가 이름
     */
    private String name;

    /**
     * 휴가 사용 단위 (일 / 반차 / 시간용 / 연속사용)
     */
    private String leaveUnit;

    /**
     * 휴가 지급일
     */
    private int leaveDay;

    /**
     * 휴일 포함
     */
    private boolean isIncludeHoliday;

    /**
     * 급여 지급
     */
    private String paidLeave;

    /**
     * 적용 성별
     */
    private String sexualAdaption;

    /*
    조직장 승인 설정
     */
    private Boolean approvalAdministrator;

    /**
     * 조직장 알림 설정
     */
    private boolean notifyAdministrator;

    public Leave(String name, String leaveUnit, int leaveDay, boolean isIncludeHoliday,
        String paidLeave, String sexualAdaption, Boolean approvalAdministrator,
        boolean notifyAdministrator) {
        this.name = name;
        this.leaveUnit = leaveUnit;
        this.leaveDay = leaveDay;
        this.isIncludeHoliday = isIncludeHoliday;
        this.paidLeave = paidLeave;
        this.sexualAdaption = sexualAdaption;
        this.approvalAdministrator = approvalAdministrator;
        this.notifyAdministrator = notifyAdministrator;
    }

//    public Leave(Builder builder) {
//        this.name = name;
//        this.leaveUnit = leaveUnit;
//        this.leaveDay = leaveDay;
//        this.isIncludeHoliday = isIncludeHoliday;
//        this.paidLeave = paidLeave;
//        this.sexualAdaption = sexualAdaption;
//        this.approvalAdministrator = approvalAdministrator;
//        this.notifyAdministrator = notifyAdministrator;
//    }

    /**
     * Build Class 생성 시도
     */
    public static class Builder{

        private final String name;
        private String leaveUnit;
        private int leaveDay;
        private boolean isIncludeHoliday;
        private String paidLeave;
        private String sexualAdaption;
        private Boolean approvalAdministrator;
        private boolean notifyAdministrator;

        /**
         * Builder 클래스 생성자
         * @param name (필수 인자) 이름
         */
        public Builder(String name) {
            this.name = name;
        }

        public Builder leaveUnit(String leaveUnit) {
            this.leaveUnit = leaveUnit;
            return this;
        }

        public Builder leaveDay(int leaveDay) {
            this.leaveDay = leaveDay;
            return this;
        }

        public Builder isIncludeHoliday(boolean isIncludeHoliday) {
            this.isIncludeHoliday = isIncludeHoliday;
            return this;
        }

        public Builder paidLeave(String paidLeave) {
            this.paidLeave = paidLeave;
            return this;
        }

        public Builder sexualAdaption(String sexualAdaption) {
            this.sexualAdaption = sexualAdaption;
            return this;
        }

        public Builder approvalAdministrator(boolean approvalAdministrator) {
            this.approvalAdministrator = approvalAdministrator;
            return this;
        }

        public Builder notifyAdministrator(boolean notifyAdministrator) {
            this.notifyAdministrator = notifyAdministrator;
            return this;
        }

//        public Leave build() {
//            return new Leave(this);
//        }

    }



}
