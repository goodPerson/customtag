package dao. Idao;

import java.util.List;

import module.GroupCondition;

public interface IGroupCondition {
	    /**
	     * ��ȡָ�����Թ���List
	     * @param groupId
	     * @return
	     */
        public List<GroupCondition> getGroupConditionList(String groupId);
}
