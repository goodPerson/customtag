package module;
/**
 * MK_VGOP.TB_CUST_TAG_SUBS_USING_DETAIL_M IS '�ͻ���ǩϵͳ�û�ʹ����ϸ�±�'
 * @author ywz
 * 20131017
 */
public class TagSubsMonthDetail {
	private Integer statisMonth;  //       int                         'ͳ���·�'         
	private String   userId;             // varchar(50)                '���ֹ���'         
	private String   userName;        //    varchar(50)                '�û�����'         
	private String   regionName;      //    varchar(50)                '��������'         
	private String   countryName;    //     varchar(70)                '��������'         
	private  Integer accessCnt;          // int                        '���ʴ���'         
	private  Integer downloadCnt;      //   int                        '�ͻ��嵥���ش���' 
	private  Integer groupCreateCnt;   //  int                         '�ͻ���Ⱥ��������' 
	private  Integer tagCreateCnt;      //   int                       '��ǩ������������' 

	public Integer getStatisMonth() {
		return statisMonth;
	}
	public void setStatisMonth(Integer statisMonth) {
		this.statisMonth = statisMonth;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public Integer getAccessCnt() {
		return accessCnt;
	}
	public void setAccessCnt(Integer accessCnt) {
		this.accessCnt = accessCnt;
	}
	public Integer getDownloadCnt() {
		return downloadCnt;
	}
	public void setDownloadCnt(Integer downloadCnt) {
		this.downloadCnt = downloadCnt;
	}
	public Integer getGroupCreateCnt() {
		return groupCreateCnt;
	}
	public void setGroupCreateCnt(Integer groupCreateCnt) {
		this.groupCreateCnt = groupCreateCnt;
	}
	public Integer getTagCreateCnt() {
		return tagCreateCnt;
	}
	public void setTagCreateCnt(Integer tagCreateCnt) {
		this.tagCreateCnt = tagCreateCnt;
	}
	 
}
