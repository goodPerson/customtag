package module;
/**
 * ����������ͳ��Ŀ���
 * @author ywz
 *
 */
// Ŀ��� MK_VGOP.TB_CUST_TAG_COUNTY_M
public class CustTagCountyM {
	 private Integer statisMonth                 ;//            integer                  --�·�               
	 private String regionName                   ;//           varchar(10)              --��������           
	 private String countryName                  ;//           varchar(70)              --��������           
	 private Integer accessSubsCnt               ;//           integer                   --����������         
	 private Integer accessCnt                   ;//    integer                  --���ʴ�����         
	 private Integer custlistDwlnSubsCnt         ;//      integer                    --�ͻ��嵥���������� 
	 private Integer custlistDwlnCnt             ;//  integer                   --�ͻ��嵥���ش����� 
	 private Integer custgroupCreateSubsCnt     ;//   integer                   --�ͻ���Ⱥ���������� 
	 private Integer custgroupCreateCnt          ;//  integer                   --�ͻ���Ⱥ���������� 
	 private Integer custtagCreateSubsCnt       ;//   integer                   --��ǩ�������������� 
	 private Integer custtagCreateCnt            ;//   integer                   --��ǩ��������������  
	public Integer getStatisMonth() {
		return statisMonth;
	}
	public void setStatisMonth(Integer statisMonth) {
		this.statisMonth = statisMonth;
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
	public Integer getAccessSubsCnt() {
		return accessSubsCnt;
	}
	public void setAccessSubsCnt(Integer accessSubsCnt) {
		this.accessSubsCnt = accessSubsCnt;
	}
	public Integer getAccessCnt() {
		return accessCnt;
	}
	public void setAccessCnt(Integer accessCnt) {
		this.accessCnt = accessCnt;
	}
	public Integer getCustlistDwlnSubsCnt() {
		return custlistDwlnSubsCnt;
	}
	public void setCustlistDwlnSubsCnt(Integer custlistDwlnSubsCnt) {
		this.custlistDwlnSubsCnt = custlistDwlnSubsCnt;
	}
	public Integer getCustlistDwlnCnt() {
		return custlistDwlnCnt;
	}
	public void setCustlistDwlnCnt(Integer custlistDwlnCnt) {
		this.custlistDwlnCnt = custlistDwlnCnt;
	}
	public Integer getCustgroupCreateSubsCnt() {
		return custgroupCreateSubsCnt;
	}
	public void setCustgroupCreateSubsCnt(Integer custgroupCreateSubsCnt) {
		this.custgroupCreateSubsCnt = custgroupCreateSubsCnt;
	}
	public Integer getCustgroupCreateCnt() {
		return custgroupCreateCnt;
	}
	public void setCustgroupCreateCnt(Integer custgroupCreateCnt) {
		this.custgroupCreateCnt = custgroupCreateCnt;
	}
	public Integer getCusttagCreateSubsCnt() {
		return custtagCreateSubsCnt;
	}
	public void setCusttagCreateSubsCnt(Integer custtagCreateSubsCnt) {
		this.custtagCreateSubsCnt = custtagCreateSubsCnt;
	}
	public Integer getCusttagCreateCnt() {
		return custtagCreateCnt;
	}
	public void setCusttagCreateCnt(Integer custtagCreateCnt) {
		this.custtagCreateCnt = custtagCreateCnt;
	}
	 
}
