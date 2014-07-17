package action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import com.opensymphony.xwork2.ActionSupport;
import dao.ArrtClassDaoImpl;
 /**
  * ͳһ��ͼ��ǩ
  * @author ywz
  *
  */
public class ArrtClassAction extends ActionSupport {

   private String threeName;                                                                                        //�������������Ʋ���
   private String secName;                                                                                           //�ڶ����������Ʋ���
   //private	ArrtClassDaoImpl attrClassDao=new ArrtClassDaoImpl();                              //dao��
   private static	ArrtClassDaoImpl attrClassDao;                              //dao��
   private List  listThreeClassName;                                                                              //��������������List
   private List  listSecClassName;                                                                                 //�ڶ�����������List
   private List  baisAttr;                                                                                               //��������
   private List  voiceAttr;																								//��������
   private List  flowAttr;                    																			//��������
   private List  terminalAttr;																							//�ն�����
   private List  groupAttr;                                                                                           //��������
   private List  smsMmsAttr;																						//�̲�������
   private List  keyAttr;                                                                                                //�ص�����
   private List  busLike;                                                                                               //ҵ��ƫ��
   private List  contentLike;                                                                                         //����ƫ��
	
  
   
	public static void initTagUnifyView(){
		XmlBeanFactory factory=new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
		attrClassDao=(ArrtClassDaoImpl) factory.getBean("arrtClassDaoImpl"); 
	}
	/**
	 * ��ȡ����������
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String getListThreeName() throws UnsupportedEncodingException{
		if (null==attrClassDao)
			this.initTagUnifyView();
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");
		response.addHeader("Content-Type", "text/html;charset=gbk");
		request.setCharacterEncoding("gbk");
	    threeName=URLDecoder.decode(request.getParameter("threeName"), "utf-8");
		listThreeClassName=this.attrClassDao.getListByName(threeName);
		return SUCCESS;
	}
	/**
	 * ��ȡ��������
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String getListSecName() throws UnsupportedEncodingException{
		if (null==attrClassDao)
			this.initTagUnifyView();
		baisAttr=this.attrClassDao.getListSecName("��������");                                                                                              //��������
		voiceAttr=this.attrClassDao.getListSecName("��������");																								//��������
		flowAttr=this.attrClassDao.getListSecName("��������");                    																			//��������
		terminalAttr=this.attrClassDao.getListSecName("�ն�����");																							//�ն�����
		groupAttr=this.attrClassDao.getListSecName("��������");                                                                                           //��������
		smsMmsAttr=this.attrClassDao.getListSecName("����/��������");																						//�̲�������
		keyAttr=this.attrClassDao.getListSecName("�ص�ҵ��");                                                                                                //�ص�����
		busLike=this.attrClassDao.getListSecName("ҵ��ƫ��");                                                                                               //ҵ��ƫ��
		contentLike=this.attrClassDao.getListSecName("����ƫ��");                                                                                         //����ƫ��	
		return SUCCESS;
	}	
	


	public String getThreeName() {
		return threeName;
	}
	public void setThreeName(String threeName) {
		this.threeName = threeName;
	}	
	public ArrtClassDaoImpl getAttrClassDao() {
		return attrClassDao;
	}
	public void setAttrClassDao(ArrtClassDaoImpl attrClassDao) {
		this.attrClassDao = attrClassDao;
	}

	public List getListSecClassName() {
		return listSecClassName;
	}

	public void setListSecClassName(List listSecClassName) {
		this.listSecClassName = listSecClassName;
	}

	public List getListThreeClassName() {
		return listThreeClassName;
	}
	public void setListThreeClassName(List listThreeClassName) {
		this.listThreeClassName = listThreeClassName;
	}

	public String getSecName() {
		return secName;
	}
	public void setSecName(String secName) {
		this.secName = secName;
	}
	public List getBaisAttr() {
		return baisAttr;
	}
	public void setBaisAttr(List baisAttr) {
		this.baisAttr = baisAttr;
	}
	public List getVoiceAttr() {
		return voiceAttr;
	}
	public void setVoiceAttr(List voiceAttr) {
		this.voiceAttr = voiceAttr;
	}
	public List getFlowAttr() {
		return flowAttr;
	}
	public void setFlowAttr(List flowAttr) {
		this.flowAttr = flowAttr;
	}
	public List getTerminalAttr() {
		return terminalAttr;
	}
	public void setTerminalAttr(List terminalAttr) {
		this.terminalAttr = terminalAttr;
	}
	public List getGroupAttr() {
		return groupAttr;
	}
	public void setGroupAttr(List groupAttr) {
		this.groupAttr = groupAttr;
	}
	public List getSmsMmsAttr() {
		return smsMmsAttr;
	}
	public void setSmsMmsAttr(List smsMmsAttr) {
		this.smsMmsAttr = smsMmsAttr;
	}
	public List getKeyAttr() {
		return keyAttr;
	}
	public void setKeyAttr(List keyAttr) {
		this.keyAttr = keyAttr;
	}
	public List getBusLike() {
		return busLike;
	}
	public void setBusLike(List busLike) {
		this.busLike = busLike;
	}
	public List getContentLike() {
		return contentLike;
	}
	public void setContentLike(List contentLike) {
		this.contentLike = contentLike;
	}
	
}


