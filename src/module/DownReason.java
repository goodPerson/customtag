package module;

public class DownReason {
	private String createTime ;// '下载时间',
	private String downReason ;// '下载原因'
	private String regionId 	;// '操作用户地域ID',
	private String regionName ;// '操作用户地域名称',
	private String tagId;// 			下载标签或群Id	',
	private String tagName 		;// '下载标签或群名称	',
	private String userId 		;// '用户Id',
	private String userName 	;// '用户名称',
	
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
