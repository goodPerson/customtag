package module;
/**
 * ��������Ϣ��
 * @author ywz
 *@date 20130605
 */
public class ApproverInfo {
	  private String user_id;//	       varchar(32)	not null          --���ֹ���                       
	  private String user_name;//	     varchar(64)                    --����������
	  private String user_role;//      varchar(32)                    --�����˽�ɫ
	  private String user_area;//     varchar(50)                    --��������
	  
	  
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_role() {
		return user_role;
	}
	public void setUser_role(String user_role) {
		this.user_role = user_role;
	}
	public String getUser_area() {
		return user_area;
	}
	public void setUser_area(String user_area) {
		this.user_area = user_area;
	}

     
}
