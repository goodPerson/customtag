package module;
/**
 * �ϴ�������¼
 * @author ywz
 *
 */
public class UploadLtLog {
	 private String tag_id;//	  ��ǩId      varchar(32)	       
	 private String tag_type;//   ��������     varchar(16)                 
	 private String upload_path;//   �ϴ�·��      varchar(32)             
	 private String state;//	  ״̬  0�Ѿ��ϴ�  1 �Ѿ����        varchar(32)
	 
	public String getTag_id() {
		return tag_id;
	}
	public void setTag_id(String tag_id) {
		this.tag_id = tag_id;
	}
	public String getTag_type() {
		return tag_type;
	}
	public void setTag_type(String tag_type) {
		this.tag_type = tag_type;
	}
	public String getUpload_path() {
		return upload_path;
	}
	public void setUpload_path(String upload_path) {
		this.upload_path = upload_path;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}	 
}
