package module;

 
import java.util.List;

import module.TbDimRegion;

import module.TbAdmUserUnit;

public class UserInfo implements java.io.Serializable{
	
	private static final long serialVersionUID = -2365924456109070617L;
	
	//�û���Ϣ 
	private TbAdmUserUnit user;
	/*
	//��ɫ��Ϣ
	private TbAdmRole role;
	//�û�����Ϣ
	private TbAdmUserGroup group;
	//�û���˾
	private TbAdmUserGroup company;
	//�û�ְλ
	private TbDimJob job;
	*/
	//�û���������
	private TbDimRegion region;
	//����Ȩ��
	private List special_role;
	//�����û�
	private List under;
	//�ϼ��û�
	private TbAdmUserUnit boss;
	
/*
	//�û���Ϣ 
	private TbAdmUserUnit user;
	//�û���������
	private TbDimRegion region;
  */
	public TbAdmUserUnit getUser() {
		return user;
	}
	public void setUser(TbAdmUserUnit user) {
		this.user = user;
	}
	public TbDimRegion getRegion() {
		return region;
	}
	public void setRegion(TbDimRegion region) {
		this.region = region;
	}
	
	 
}