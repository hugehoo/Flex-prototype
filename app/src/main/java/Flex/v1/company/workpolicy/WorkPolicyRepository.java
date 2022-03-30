package Flex.v1.company.workpolicy;

import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;

public class WorkPolicyRepository {

    Map<Long, WorkPolicy> workPolicyRepo = new HashMap<>();

    public WorkPolicy getWorkPolicy(@NonNull Long id) {
        return workPolicyRepo.get(id);
    }

    public void saveWorkPolicy(@NonNull Long id, @NonNull WorkPolicy workPolicy) {
        workPolicyRepo.put(id, workPolicy);
    }

    public void deleteWorkPolicy(@NonNull Long id) {
        workPolicyRepo.remove(id);
    }

}
