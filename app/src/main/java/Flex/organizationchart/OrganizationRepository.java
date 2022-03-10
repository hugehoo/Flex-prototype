package Flex.organizationchart;

/**
 * 조직을 관리하는 저장소 인터페이스
 */
public interface OrganizationRepository {

    /**
     * 새로 생성되거나, 수정된 조직의 정보를 저장소에 저장한다.
     * @param organization : 저장할 조직 객체
     */
    void saveOrganization(Organization organization);

    /**
     * 인자로 받는 조직을 저장소에서 삭제한다.
     * @param organization : 삭제할 조직 객체
     */
    void deleteOrganization(Organization organization);
}