package Flex.v1.company.worktype;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class WorkTypeRepository {

    static Map<String, WorkType> workTypeRepo = new HashMap<>();

    static List<String> workingDay;
    static List<String> offDay;

    /**
     * 일하는 날을 지정합니다.
     */
    public static void setWorkingDay() {
        workingDay = new ArrayList<>();
        workingDay.add("월");
        workingDay.add("화");
        workingDay.add("수");
        workingDay.add("목");
        workingDay.add("금");
    }

    /**
     * 유급 휴일일을 지정합니다.
     */
    public void setOffDay() {
        offDay = new ArrayList<>();
        offDay.add("일");
    }

    static WorkType basicWorkType = WorkType
        .builder()
        .name("고정 출퇴근제")
        .workManagement("고정")
        .startHour("09:00:00")
        .endHour("18:00:00")
        .lunchBreak(1L)
        .workingDay(workingDay)
        .offDay(offDay)
        .build();


    public static WorkType getBasicWorkType() {
        workTypeRepo.put("basic", basicWorkType);
        return workTypeRepo.get("basic");
    }

    public static void main(String[] args) {
        WorkTypeRepository workTypeRepository = new WorkTypeRepository();

    }

}
