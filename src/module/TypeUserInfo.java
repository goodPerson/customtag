package module;
/**
 * �ڰ׺�������Ϣ
 * @author ywz
 *@date 20130524
 */
public class TypeUserInfo {
	 private String   user_id;	 //      varchar(32)	                --�ϴ��˹���                       
	 private String load_time;	 //      varchar(16)                  --�ϴ�ʱ��
	 private String list_type;	 //      varchar(32)                  --�ϴ���������
	 private String tag_id;	 //	         varchar(32)	                --��ǩ��ʶ                       
	 private String tag_name;	 //	       varchar(64)                  --��ǩ����
	 private String list_num;	 //        varchar(16)   not null       --�ϴ���������
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getLoad_time() {
		return load_time;
	}
	public void setLoad_time(String load_time) {
		this.load_time = load_time;
	}
	public String getList_type() {
		return list_type;
	}
	public void setList_type(String list_type) {
		this.list_type = list_type;
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
	public String getList_num() {
		return list_num;
	}
	public void setList_num(String list_num) {
		this.list_num = list_num;
	}	 

}
