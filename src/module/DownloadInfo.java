package module;

public class DownloadInfo {
	private String file_name;                 //�嵥����
	private String id;                        //Ⱥid
	private String create_time;               //����ʱ��
	private String file_type;                 //�ļ�����
	private Integer count;                    //Ⱥ����
	private String region_id;                //����ID
	private String region_name;              //��������
	private String status;                   //�ļ�״̬
	private String column;                   //�嵥�ֶ�
	private String column_desc;              //�ֶ�����
	private String sql;                      //ѡ��SQL
	private String manager_id;               //�����û�Id
	private String manager;                  //�����û�
	private String custlist_path;            //�ļ�·��
	private String downLoadReason;  //����ԭ��
	private Integer downLoagFlag;  //���ر�־
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFile_type() {
		return file_type;
	}
	public void setFile_type(String file_type) {
		this.file_type = file_type;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getRegion_id() {
		return region_id;
	}
	public void setRegion_id(String region_id) {
		this.region_id = region_id;
	}
	public String getRegion_name() {
		return region_name;
	}
	public void setRegion_name(String region_name) {
		this.region_name = region_name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getColumn() {
		return column;
	}
	public void setColumn(String column) {
		this.column = column;
	}
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getColumn_desc() {
		return column_desc;
	}
	public void setColumn_desc(String column_desc) {
		this.column_desc = column_desc;
	}
	public String getManager_id() {
		return manager_id;
	}
	public void setManager_id(String manager_id) {
		this.manager_id = manager_id;
	}
	public String getCustlist_path() {
		return custlist_path;
	}
	public void setCustlist_path(String custlist_path) {
		this.custlist_path = custlist_path;
	}
	public String getDownLoadReason() {
		return downLoadReason;
	}
	public void setDownLoadReason(String downLoadReason) {
		this.downLoadReason = downLoadReason;
	}
	public Integer getDownLoagFlag() {
		return downLoagFlag;
	}
	public void setDownLoagFlag(Integer downLoagFlag) {
		this.downLoagFlag = downLoagFlag;
	}	
	
}
