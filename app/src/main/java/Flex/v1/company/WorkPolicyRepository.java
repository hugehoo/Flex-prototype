package Flex.v1.company;

import java.util.HashMap;
import java.util.Map;

public class WorkPolicyRepository {

    Map<String, WorkPolicy> workPolicyRepo = new HashMap<>();

    public void saveWorkPolicy(String name, WorkPolicy workPolicy) {
        workPolicyRepo.put(name, workPolicy);
    }

    public void deleteWorkPolicy(String name) {
        workPolicyRepo.remove(name);
    }

}
