package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import module.CustomGroup;
import module.GroupCondition;
import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import com.opensymphony.xwork2.ActionSupport;
import common.GetLog;

import dao.ArrtClassDaoImpl;
import dao.CustTagAttrInfoDaoImpl;
import dao.GroupConditionImpl;
import dao.RegionDaoImpl;
import dao.Idao.IGroupCondition;

public class AdvanceTagInfoAction extends ActionSupport {
   private ArrtClassDaoImpl attrClassDao;                                //��ǩ���Dao
   private  RegionDaoImpl regionDao;                                  //�ͻ�Ⱥɸѡ��������Dao
   private  CustTagAttrInfoDaoImpl custTagAttrInfoDao;    //�ͻ�Ⱥ����Dao
   private  CustTagAttrInfoDaoImpl custTagAttrDao;   //�ͻ�Ⱥɸѡ����Dao
   private List  baisAttr;                                                                                                //��������
   private List  voiceAttr;																							   //��������
   private List  flowAttr;                    																			   //��������
   private List  terminalAttr;																						   //�ն�����
   private List  groupAttr;                                                                                             //��������
   private List  smsMmsAttr;																						   //�̲�������
   private List  servAttr;                                                                                                //ҵ������
   private List  regionAttr;                                                                                            //��������
   private List  qudaoAttr;                                                                                            //��������
   private List  tagAttr;                                                                                                 //��ǩ����	
   private String groupType;                                                                                       // Ⱥ����  0 ����Ⱥ  1 �ҵ�Ⱥ
   private Integer regionLevl;                                                                                       //��������
   private String groupId;                                                                                             //ȺId
   private String  months;                                                                                           //�����¸���
   private String days;                                                                                                //�����ո���
   private  String title_name;                                                                                     //Ⱥ����
   private JSONArray jsonarray;                                                                                 //��ʽ���ַ���
   private GroupConditionImpl   igcon;
   private String saveFlag;
	String userId;
	String userName;
	String regionName;
	String profile;
	String tag_name;
	String create_time;
	String end_time;
	String tag_id;
	
	String tagIntroduce;
	String createTagDate;
	String endTagDate;
	String cityName;     
	String countyName;   
	String userState;    
	String count;

	
	public void initAttrClass(){
		XmlBeanFactory factory=new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
		attrClassDao=(ArrtClassDaoImpl) factory.getBean("arrtClassDaoImpl");
	}
	
	public  void initRegion(){
    	XmlBeanFactory factory=new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
		regionDao=(RegionDaoImpl) factory.getBean("regionDaoImpl");
    }
	
	public void initCustTagAttr(){
		XmlBeanFactory factory =new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
		custTagAttrInfoDao=(CustTagAttrInfoDaoImpl) factory.getBean("custTagAttrInfoDaoImpl");
	}
    
	public void initGroupCondi(){
		XmlBeanFactory factory=new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
		igcon=(GroupConditionImpl) factory.getBean("groupConditionImpl");
	}

	/**
	 * ��ʼ����������
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String initBigClassName() throws UnsupportedEncodingException{
		  HttpServletResponse response = ServletActionContext.getResponse();
		  response.setCharacterEncoding("gbk");
		  response.setContentType("text/html;charset=gbk");
		  response.addHeader("Content-Type", "text/html;charset=gbk");	
		  HttpServletRequest request = ServletActionContext.getRequest();
		initAttrClass();
		baisAttr=this.attrClassDao.getListSecName("��������");                                                                                                //��������
		voiceAttr=this.attrClassDao.getListSecName("��������");																								//��������
		flowAttr=this.attrClassDao.getListSecName("��������");                    																			//��������
		terminalAttr=this.attrClassDao.getListSecName("�ն�����");																							//�ն�����
		groupAttr=this.attrClassDao.getListSecName("��������");                                                                                             //��������
		smsMmsAttr=this.attrClassDao.getListSecName("��/��������");																				        //�̲�������
		servAttr=this.attrClassDao.getListSecName("ҵ������");                                                                                                 //ҵ������
		regionAttr=this.attrClassDao.getListSecName("��������");                                                                                              //��������
		qudaoAttr=this.attrClassDao.getListSecName("��������");                                                                                              //��������
		tagAttr=this.attrClassDao.getListSecName("��ǩ");                                                                                                   //��ǩ����    
		 groupType=request.getParameter("type");
		userId=request.getParameter("userId");                                                                               //�û�Id
        String reg_id=(String) request.getSession().getAttribute("regionId");
        title_name=URLDecoder.decode(request.getParameter("title_name"), "utf-8");
        if(title_name.equals("������ͻ�Ⱥ����")){
        	title_name="";
        }
         count  =URLDecoder.decode(request.getParameter("count"), "utf-8") .replace("��", "");//  Ⱥ��ʶ
         tagIntroduce  =URLDecoder.decode(request.getParameter("tag_introduce"), "utf-8");//  Ⱥ���
         createTagDate=URLDecoder.decode(request.getParameter("create_tag_date"), "utf-8"); //  ��Ч��ʼʱ��
         endTagDate    =URLDecoder.decode(request.getParameter("end_tag_date"), "utf-8"); //    ��Ч����ʱ��
         cityName           =URLDecoder.decode(request.getParameter("cityName"), "utf-8");  //  �������
         countyName     =URLDecoder.decode(request.getParameter("countyName"), "utf-8");  //��������
         userState           =URLDecoder.decode(request.getParameter("userState"), "utf-8");  //���������û�״̬
        //String groupId=request.getParameter("id");
        initRegion();
        regionLevl=regionDao.getlvl(reg_id);
        groupId  =request.getParameter("id");
        initCustTagAttr();
        Integer monthss=custTagAttrInfoDao.getMonth();
        months=(String.valueOf(monthss)).substring(0,4)+"��"+(String.valueOf(monthss)).substring(4,6)+"��";
        
        Integer  dayss=custTagAttrInfoDao.getData();
        days=(String.valueOf(dayss)).substring(0,4)+"��"+(String.valueOf(dayss)).substring(4,6)+"��"+(String.valueOf(dayss)).substring(6,8)+"��";
       // date=tagoperate.getMonth();
		 //mon=(String.valueOf(date)).substring(0,4)+"��"+(String.valueOf(date)).substring(4,6)+"��";
        initGroupCondi();
        List<GroupCondition> lgcon;
        lgcon=igcon.getGroupConditionList(groupId);
        jsonFormat(lgcon);
        
		return SUCCESS;
	}
	
	
	
	
	/**
	 * �޸ĸ߼���ǩ
	 * @return
	 * @throws IOException 
	 */
	/*
	public String updateAdvanceGroupTag() throws IOException{		
		this.initCustTagAttr();
		HttpServletResponse response=ServletActionContext.getResponse();
		HttpServletRequest   request   =ServletActionContext.getRequest();
		response.setCharacterEncoding("gbk");		
		response.addHeader("Content-Type", "text/html;charset=gbk");
		request.setCharacterEncoding("gbk");
		userId=(String) request.getSession().getAttribute("userId");                                            //�û�Id
		userName=(String) request.getSession().getAttribute("userName");                               //�û�����
		regionName=(String) request.getSession().getAttribute("regionName");                       //��������
        profile=URLDecoder.decode(request.getParameter("tag_introduce"), "utf-8");              //�ͻ�Ⱥ���   
		tag_name = URLDecoder.decode(request.getParameter("tag_name"), "utf-8");             //Ⱥ����
		create_time=request.getParameter("create_time");                                                           //��Ч�ڿ�ʼʱ��
        end_time=request.getParameter("end_time");                                                                  //��Ч�ڽ���ʱ��
        CustomGroup newTag = new CustomGroup();
        String share=request.getParameter("share");                                                                     //0 Ϊ�����ǩ
         tag_id=request.getParameter("tag_id");                                                                           // ȺId
 		String tag_attrs=URLDecoder.decode(request.getParameter("tag_attrs"), "utf-8");
 		tag_attrs=tag_attrs.replace("'", "'||Chr(39)||'");		
 		String stamt=URLDecoder.decode(request.getParameter("stamt"), "utf-8");		             //��������
 		stamt=stamt.replace("'", "'||Chr(39)||'");		
 		String persons=URLDecoder.decode(request.getParameter("persons"), "utf-8");		
 		String sql=URLDecoder.decode(request.getParameter("sql"), "utf-8");
 		 sql=sql.replace("'", "'||Chr(39)||'");		
 		//sql=sql.replace("'", "");		
		  newTag.setTag_id(tag_id);
		  if("0".equals(share)){
	      newTag.setTag_name(tag_name+"���ҵĹ���");	  
		  }else{
	      newTag.setTag_name(tag_name);
		  }
		  newTag.setCreate_time(create_time);
		  newTag.setEnd_time(end_time);
		  newTag.setTag_type("myTag");
		  newTag.setTag_serv_type("--");
		  newTag.setProfile(profile);
		  newTag.setTag_creator(userName);
		  newTag.setTag_creator_id(userId);
		  newTag.setTag_region(regionName);
		  newTag.setTag_statement(stamt);
		  newTag.setTag_class("--");
		  newTag.setCount_subs(Integer.parseInt(persons));
		  if("0".equals(share)){
		  newTag.setIs_share("1");
		  }else{
		  newTag.setIs_share("0");  
		  }
		  newTag.setTag_status("0");
		  newTag.setCustlist_path("");
		  newTag.setTag_tec_stamt(sql);
		  //newTag.setStamtAdvance(sql);
		  newTag.setTypeFlag(1);
		   groupType=request.getParameter("type");  
		
		String flag="";
		

				
	    if("0".equals(groupType)){
			if (custTagAttrInfoDao.addAdvanceTag(newTag)) {
				custTagAttrInfoDao
						.updateAdvanceTag(tag_id, tag_name, tag_attrs);
				saveFlag = "1";
			} else {
				saveFlag = "0";
			}
	       
			GetLog.getLog("�ͻ�Ⱥ", "����", tag_id, userName + "�����ͻ�Ⱥ" + tag_id);
			PrintWriter print = response.getWriter();
			print.write(saveFlag);
			print.flush();
			print.close();
	        return "success";
		}else{
			if (custTagAttrInfoDao.upAdvanceTag(newTag)) {
				custTagAttrInfoDao
						.updateAdvanceTag(tag_id, tag_name, tag_attrs);
				saveFlag = "1";
			} else {
				saveFlag = "0";
			}
			
			 GetLog.getLog("�ͻ�Ⱥ", "�޸�", tag_id, userName+"�����ͻ�Ⱥ"+tag_id);
	        PrintWriter  print=response.getWriter();
	        print.write(saveFlag);
	        print.flush();
	        print.close();
			 return "success";
		}
	}	
	*/
	
	/**
	 * �޸ĸ߼���ǩ
	 * @return
	 * @throws IOException 
	 */
	public String updateAdvanceGroupTag() throws IOException{		
		this.initCustTagAttr();
		HttpServletResponse response=ServletActionContext.getResponse();
		HttpServletRequest   request   =ServletActionContext.getRequest();
		response.setCharacterEncoding("gbk");		
		response.addHeader("Content-Type", "text/html;charset=gbk");
		request.setCharacterEncoding("gbk");
		userId=(String) request.getSession().getAttribute("userId");                                            //�û�Id
		userName=(String) request.getSession().getAttribute("userName");                               //�û�����
		regionName=(String) request.getSession().getAttribute("regionName");                       //��������
        profile=URLDecoder.decode(request.getParameter("tag_introduce"), "utf-8");              //�ͻ�Ⱥ���   
		tag_name = URLDecoder.decode(request.getParameter("tag_name"), "utf-8");             //Ⱥ����
		create_time=request.getParameter("create_time");                                                           //��Ч�ڿ�ʼʱ��
        end_time=request.getParameter("end_time");                                                                  //��Ч�ڽ���ʱ��
        CustomGroup newTag = new CustomGroup();
        String share=request.getParameter("share");                                                                     //0 Ϊ�����ǩ
         tag_id=request.getParameter("tag_id");                                                                           // ȺId
 		String tag_attrs=URLDecoder.decode(request.getParameter("tag_attrs"), "utf-8");
 		tag_attrs=tag_attrs.replace("'", "'||Chr(39)||'");		
 		String stamt=URLDecoder.decode(request.getParameter("stamt"), "utf-8");		             //��������
 		stamt=stamt.replace("'", "'||Chr(39)||'");		
 		String persons=URLDecoder.decode(request.getParameter("persons"), "utf-8");		
 		String sql=URLDecoder.decode(request.getParameter("sql"), "utf-8");
 		 sql=sql.replace("'", "'||Chr(39)||'");		
 		//sql=sql.replace("'", "");		
		  newTag.setTag_id(tag_id);
		  if("0".equals(share)){
	      newTag.setTag_name(tag_name+"���ҵĹ���");	  
		  }else{
	      newTag.setTag_name(tag_name);
		  }
		  newTag.setCreate_time(create_time);
		  newTag.setEnd_time(end_time);
		  newTag.setTag_type("myTag");
		  newTag.setTag_serv_type("--");
		  newTag.setProfile(profile);
		  newTag.setTag_creator(userName);
		  newTag.setTag_creator_id(userId);
		  newTag.setTag_region(regionName);
		  newTag.setTag_statement(stamt);
		  newTag.setTag_class("--");
		  newTag.setCount_subs(Integer.parseInt(persons));
		  if("0".equals(share)){
		  newTag.setIs_share("1");
		  }else{
		  newTag.setIs_share("0");  
		  }
		  newTag.setTag_status("0");
		  newTag.setCustlist_path("");
		  newTag.setTag_tec_stamt(sql);
		  //newTag.setStamtAdvance(sql);
		  newTag.setTypeFlag(1);
		   groupType=request.getParameter("type");  
		
		String flag="";
		

				
	    if("0".equals(groupType)){
			if (custTagAttrInfoDao
					.updateAdvanceTag(tag_id, tag_name, tag_attrs)) {
				if (custTagAttrInfoDao.addAdvanceTag(newTag)){
					saveFlag = "1";
				}else{
					saveFlag = "0";
				}
				
				
			} else {
				saveFlag = "0";
			}
			GetLog.getLog("�ͻ�Ⱥ", "����", tag_id, userName + "�����ͻ�Ⱥ" + tag_id);
			PrintWriter print = response.getWriter();
			print.write(saveFlag);
			print.flush();
			print.close();
	        return "success";
		}else{
			if (custTagAttrInfoDao.upAdvanceTag(newTag)) {
				custTagAttrInfoDao
						.updateAdvanceTag(tag_id, tag_name, tag_attrs);
				saveFlag = "1";
			} else {
				saveFlag = "0";
			}
			
			 GetLog.getLog("�ͻ�Ⱥ", "�޸�", tag_id, userName+"�����ͻ�Ⱥ"+tag_id);
	        PrintWriter  print=response.getWriter();
	        print.write(saveFlag);
	        print.flush();
	        print.close();
			 return "success";
		}
	}	
	
	private void jsonFormat(List<GroupCondition>  listgcon){
		jsonarray=JSONArray.fromObject(listgcon);
	}
	
	public ArrtClassDaoImpl getAttrClassDao() {
		return attrClassDao;
	}
	public void setAttrClassDao(ArrtClassDaoImpl attrClassDao) {
		this.attrClassDao = attrClassDao;
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
	public List getServAttr() {
		return servAttr;
	}
	public void setServAttr(List servAttr) {
		this.servAttr = servAttr;
	}
	public List getRegionAttr() {
		return regionAttr;
	}
	public void setRegionAttr(List regionAttr) {
		this.regionAttr = regionAttr;
	}
	public List getQudaoAttr() {
		return qudaoAttr;
	}
	public void setQudaoAttr(List qudaoAttr) {
		this.qudaoAttr = qudaoAttr;
	}
	public List getTagAttr() {
		return tagAttr;
	}
	public void setTagAttr(List tagAttr) {
		this.tagAttr = tagAttr;
	}
	public String getGroupType() {
		return groupType;
	}
	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}
	public Integer getRegionLevl() {
		return regionLevl;
	}
	public void setRegionLevl(Integer regionLevl) {
		this.regionLevl = regionLevl;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getTitle_name() {
		return title_name;
	}

	public void setTitle_name(String title_name) {
		this.title_name = title_name;
	}

	public JSONArray getJsonarray() {
		return jsonarray;
	}

	public void setJsonarray(JSONArray jsonarray) {
		this.jsonarray = jsonarray;
	}

	public String getMonths() {
		return months;
	}

	public void setMonths(String months) {
		this.months = months;
	}

	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTagIntroduce() {
		return tagIntroduce;
	}

	public void setTagIntroduce(String tagIntroduce) {
		this.tagIntroduce = tagIntroduce;
	}

	public String getCreateTagDate() {
		return createTagDate;
	}

	public void setCreateTagDate(String createTagDate) {
		this.createTagDate = createTagDate;
	}

	public String getEndTagDate() {
		return endTagDate;
	}

	public void setEndTagDate(String endTagDate) {
		this.endTagDate = endTagDate;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCountyName() {
		return countyName;
	}

	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}

	public String getUserState() {
		return userState;
	}

	public void setUserState(String userState) {
		this.userState = userState;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}
	


	
}
