package module;

public class DownReason {
	private String createTime ;// '����ʱ��',
	private String downReason ;// '����ԭ��'
	private String regionId 	;// '�����û�����ID',
	private String regionName ;// '�����û���������',
	private String tagId;// 			���ر�ǩ��ȺId	',
	private String tagName 		;// '���ر�ǩ��Ⱥ����	',
	private String userId 		;// '�û�Id',
	private String userName 	;// '�û�����',
	
	public String getCreateTime() {
		return createTime;
	}
	public String getDownReason() {
		return downReason;
	}
	public String getRegionId() {
		return regionId;
	}
	public String getRegionName() {
		return regionName;
	}
	public String getTagId() {
		return tagId;
	}
	public String getTagName() {
		return tagName;
	}
	public String getUserId() {
		return userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public void setDownReason(String downReason) {
		this.downReason = downReason;
	}
	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public void setTagId(String tagId) {
		this.tagId = tagId;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}
