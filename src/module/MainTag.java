package module;

public class MainTag {
	
	
	private String tag_id;             //��ǩ��ʶ
	private String tag_name;           //��ǩ����
	private String tag_type;           //��ǩ����
	private String tag_serv_type;      //��ǩҵ������
	private String tag_class;          //��ǩ������ 
	private String create_time;        //��ǩ����ʱ��
	private String end_time;           //��ǩ����ʱ��
	private String profile;            //��ǩ���  
	private int  count_subs;           //��ǩͳ������
	private String tag_statement;      //��ǩ�ھ� 
	private String tag_creator;        //��ǩ������
	private String tag_region;         //��ǩ����
	private String is_share;           //�Ƿ��� 
	private String tag_status;         //��ǩ״̬
	private String tag_url;              //��ǩ���ص�ַ
	private String is_used;            //�Ƿ����
	/**
	 * 20130801 ywz
	 * @return
	 */
	private Integer subsCnt;     //��ǩͳ������
	private String regionName;//��������
	private String parentRegionId;//������Id
	private String regionId; //����Id
	
	
	public String getTag_url() {
		return tag_url;
	}
	public void setTag_url(String tag_url) {
		this.tag_url = tag_url;
	}
	public String getTag_id() {
		return tag_id;
	}
	public void setTag_id(String tag_id) {
		this.tag_id = tag_id;
	}
	public String getTag_name() {
		return tag_name;
	}
	public void setTag_name(String tag_name) {
		this.tag_name = tag_name;
	}
	public String getTag_type() {
		return tag_type;
	}
	public void setTag_type(String tag_type) {
		this.tag_type = tag_type;
	}
	public String getTag_serv_type() {
		return tag_serv_type;
	}
	public void setTag_serv_type(String tag_serv_type) {
		this.tag_serv_type = tag_serv_type;
	}
	public String getTag_class() {
		return tag_class;
	}
	public void setTag_class(String tag_class) {
		this.tag_class = tag_class;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public int getCount_subs() {
		return count_subs;
	}
	public void setCount_subs(int count_subs) {
		this.count_subs = count_subs;
	}
	public String getTag_statement() {
		return tag_statement;
	}
	public void setTag_statement(String tag_statement) {
		this.tag_statement = tag_statement;
	}
	public String getTag_creator() {
		return tag_creator;
	}
	public void setTag_creator(String tag_creator) {
		this.tag_creator = tag_creator;
	}
	public String getTag_region() {
		return tag_region;
	}
	public void setTag_region(String tag_region) {
		this.tag_region = tag_region;
	}
	public String getIs_share() {
		return is_share;
	}
	public void setIs_share(String is_share) {
		this.is_share = is_share;
	}
	public String getTag_status() {
		return tag_status;
	}
	public void setTag_status(String tag_status) {
		this.tag_status = tag_status;
	}
	public String getIs_used() {
		return is_used;
	}
	public void setIs_used(String is_used) {
		this.is_used = is_used;
	}
	public Integer getSubsCnt() {
		return subsCnt;
	}
	public void setSubsCnt(Integer subsCnt) {
		this.subsCnt = subsCnt;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public String getParentRegionId() {
		return parentRegionId;
	}
	public void setParentRegionId(String parentRegionId) {
		this.parentRegionId = parentRegionId;
	}
	public String getRegionId() {
		return regionId;
	}
	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}
		
}
