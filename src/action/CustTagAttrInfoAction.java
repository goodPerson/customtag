package action;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javassist.bytecode.Descriptor.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import module.AttrTable;
import module.CustomGroup;
import module.ExportUserInfo;
import module.Region;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import common.GetLog;
import common.PrintExcelUtil;

import dao.AttrTableDaoImpl;
import dao.CustTagAttrInfoDaoImpl;
import dao.CustomgroupInfoDaoImpl;

public class CustTagAttrInfoAction {
	private static CustTagAttrInfoDaoImpl custTagAttrInfoDao;
	private static  AttrTableDaoImpl attrTableDao;
	private static CustomgroupInfoDaoImpl custGroupDao;
	private int tagUserCounts;
	private String downPath;
	private CustomGroup customGroup;
	 /*���ص�������Ҫ�Ĳ���*/
	String contentType;//��������
	FileInputStream downloadFile;//
	String fileName;//�ļ���
	String userId;
	String userName;
	String regionName;
	String profile;
	String tag_name;
	String create_time;
	String end_time;
	String tag_id;
	   static ApplicationContext factory=null;
	public static void initCustTagAttr(){
		//XmlBeanFactory factory=new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
		if (null==factory)
			factory = new ClassPathXmlApplicationContext("applicationContext.xml");
		custTagAttrInfoDao=(CustTagAttrInfoDaoImpl) factory.getBean("custTagAttrInfoDaoImpl");
		
	}
	public static void initCustGroupInfo(){
		//XmlBeanFactory factory =new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
		if (null==factory)
			factory = new ClassPathXmlApplicationContext("applicationContext.xml");
		custGroupDao=(CustomgroupInfoDaoImpl) factory.getBean("customgroupInfoDaoImpl");
	}
	
	public static void initAttrTable(){
		//XmlBeanFactory factory=new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
		if (null==factory)
			factory = new ClassPathXmlApplicationContext("applicationContext.xml");
		attrTableDao=(AttrTableDaoImpl) factory.getBean("attrTableDaoImpl");
		
	}
	/**
	 * �޸ı�ǩ
	 * @return
	 * @throws IOException 
	 */
	/*
	//public String updateGroupTag() throws IOException{
	public void updateGroupTag() throws IOException{
		if (null==custTagAttrInfoDao)
		this.initCustTagAttr();
		HttpServletResponse response=ServletActionContext.getResponse();
		HttpServletRequest   request   =ServletActionContext.getRequest();
		response.setCharacterEncoding("gbk");		
		response.addHeader("Content-Type", "text/html;charset=gbk");
		request.setCharacterEncoding("gbk");
		userId=(String) request.getSession().getAttribute("userId");
		userName=(String) request.getSession().getAttribute("userName");
		regionName=(String) request.getSession().getAttribute("regionName");
        profile=URLDecoder.decode(request.getParameter("tag_introduce"), "utf-8");
		tag_name = URLDecoder.decode(request.getParameter("tag_name"), "utf-8");
		create_time=request.getParameter("create_time");
        end_time=request.getParameter("end_time");
        CustomGroup newTag = new CustomGroup();
        String share=request.getParameter("share");
         tag_id=request.getParameter("tag_id");
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
		  newTag.setTag_statement("--");
		  newTag.setTag_class("--");
		  newTag.setCount_subs(0);
		  if("0".equals(share)){
		  newTag.setIs_share("1");
		  }else{
		  newTag.setIs_share("0");  
		  }
		  newTag.setTag_status("0");
		  newTag.setCustlist_path("");
		  newTag.setTag_tec_stamt("");
		  String type=request.getParameter("type");
		  
		
		
		String flag="";
		String saveFlag="";                                                  //�����־
		tag_id=request.getParameter("tag_id");
		String tag_attrs=URLDecoder.decode(request.getParameter("tag_attrs"), "utf-8");
		String stamt=URLDecoder.decode(request.getParameter("stamt"), "utf-8");		
		String persons=URLDecoder.decode(request.getParameter("persons"), "utf-8");		
		String sql=URLDecoder.decode(request.getParameter("sql"), "utf-8");
				sql=sql.replace("'", "'||Chr(39)||'");		
	    if("0".equals(type)){
		    if (custTagAttrInfoDao.updateGroupTag(tag_id, tag_attrs, stamt,persons,sql) && custTagAttrInfoDao.addTag(newTag)){
		    	     saveFlag="1";
					flag="success";
		    }else{
		    	saveFlag="0";
		    	flag="success";
		    }
		    PrintWriter out=response.getWriter();
		    out.print(saveFlag);
		    out.flush();
		    out.close();
		    
		    GetLog.getLog("�ͻ�Ⱥ", "����", tag_id, userName+"�����ͻ�Ⱥ"+tag_id);
			//return flag;
		}else{
			if (custTagAttrInfoDao.updateGroupTag(tag_id, tag_attrs, stamt,persons,sql)){
				saveFlag="1";
			    PrintWriter out=response.getWriter();
			    out.print(saveFlag);
			    out.flush();
			    out.close();
				flag="success";
			}else{
				flag="false";
			}
			//return flag;
		}
	}
*/
	/**
	 * �޸ı�ǩ
	 * @return
	 * @throws IOException 
	 */
	//public String updateGroupTag() throws IOException{
	public void updateGroupTag() throws IOException{
		if (null==custTagAttrInfoDao)
		this.initCustTagAttr();
		HttpServletResponse response=ServletActionContext.getResponse();
		HttpServletRequest   request   =ServletActionContext.getRequest();
		response.setCharacterEncoding("gbk");		
		response.addHeader("Content-Type", "text/html;charset=gbk");
		request.setCharacterEncoding("gbk");
		userId=(String) request.getSession().getAttribute("userId");
		userName=(String) request.getSession().getAttribute("userName");
		regionName=(String) request.getSession().getAttribute("regionName");
        profile=URLDecoder.decode(request.getParameter("tag_introduce"), "utf-8");
		tag_name = URLDecoder.decode(request.getParameter("tag_name"), "utf-8");
		create_time=request.getParameter("create_time");
        end_time=request.getParameter("end_time");
        String stamt=URLDecoder.decode(request.getParameter("stamt"), "utf-8");		
        String persons=URLDecoder.decode(request.getParameter("persons"), "utf-8").replace("��", "");		
		String sql=URLDecoder.decode(request.getParameter("sql"), "utf-8");
		sql=sql.replace("'", "'||Chr(39)||'");		
		String tag_attrs=URLDecoder.decode(request.getParameter("tag_attrs"), "utf-8");
        CustomGroup newTag = new CustomGroup();
        String share=request.getParameter("share");
         tag_id=request.getParameter("tag_id");
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
		  String type=request.getParameter("type");
		  
		
		
		String flag="";
		String saveFlag="";                                                  //�����־
		
		
		

	    if("0".equals(type)){
		    if (custTagAttrInfoDao.updateGroupTag(tag_id, tag_attrs, stamt,persons,sql) && custTagAttrInfoDao.addTag(newTag)){		    	
		    	flag="success";
		    	saveFlag="1";
		    	/*
				if (custTagAttrInfoDao.updateGroupTag(tag_id, tag_attrs, stamt,persons,sql))
					flag="success";
				else
					flag="false";
					*/
		    }else{
		    	saveFlag="0";
		    	flag="success";
		    }
		    PrintWriter out=response.getWriter();
		    out.print(saveFlag);
		    out.flush();
		    out.close();
		    
		    GetLog.getLog("�ͻ�Ⱥ", "����", tag_id, userName+"�����ͻ�Ⱥ"+tag_id);
			//return flag;
		}else{
			if (custTagAttrInfoDao.updateGroupTag(tag_id, tag_attrs, stamt,persons,sql)){
				GetLog.getLog("�ͻ�Ⱥ", "�޸�", tag_id, userName+"�޸Ŀͻ�Ⱥ"+tag_id);
				saveFlag="1";
			    PrintWriter out=response.getWriter();
			    out.print(saveFlag);
			    out.flush();
			    out.close();
				flag="success";
			}else{
				flag="false";
			}
			//return flag;
		}
	}
	
	
	/*������ǩ*/
	public String exportTag(){
		if (null==custTagAttrInfoDao)
		this.initCustTagAttr();
		
		return "success";
	}
	/*ɸѡ��ǩ*/
	public String filterTagExport() throws Exception{
		if (null==custTagAttrInfoDao)
		this.initCustTagAttr();
		if (null==custGroupDao)
		this.initCustGroupInfo();
		HttpServletResponse response=ServletActionContext.getResponse();
		HttpServletRequest   request   =ServletActionContext.getRequest();
		response.setCharacterEncoding("gbk");
		response.addHeader("Content-Type", "text/html;charset=gbk");
		request.setCharacterEncoding("gbk");
		
		String tag_id=request.getParameter("tag_id");
		String downPath=custGroupDao.getDownPath(tag_id);
		String dowPath="";
		PrintWriter print=response.getWriter();
		print.print(downPath);
		print.close();
		print.flush();			
	return "exportUser" ;		
	
	}
	/*��ȡɸѡ��ǩ�û���*/
	public void filterTagCounts() throws IOException{
		//this.initCustTagAttr();
		if (null==custTagAttrInfoDao)
		this.initCustTagAttr();

		HttpServletResponse response=ServletActionContext.getResponse();
		HttpServletRequest   request   =ServletActionContext.getRequest();
		response.setCharacterEncoding("gbk");
		response.setHeader("Content-Type", "text/html;charset=gbk");
		request.setCharacterEncoding("gbk");
		String attrsql=URLDecoder.decode(request.getParameter("sql"),"utf-8");
		int date=custTagAttrInfoDao.getData();
		String data_time=date+"";
		tagUserCounts=custTagAttrInfoDao.getUserTagConts(attrsql,data_time.substring(0,6));
		int data=tagUserCounts;
		PrintWriter print=response.getWriter();
		print.print(data);
		print.close();
		print.flush();
	}
	
	public void getRepeatGroup() throws IOException{
		this.initCustTagAttr();
		HttpServletResponse response=ServletActionContext.getResponse();
		HttpServletRequest   request   =ServletActionContext.getRequest();
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-Type", "text/html;charset=utf-8");
		String groupName=request.getParameter("group_Name");
		int groupCount=custTagAttrInfoDao.getRepeatGroup(groupName);		
		PrintWriter print=response.getWriter();
		print.write(String.valueOf(groupCount));
		print.flush();
		print.close();
		
	}
	
	/**
	 * ��ȡ��ֵ���Լ���
	 * @return
	 * @throws IOException 
	 */
 	public void getAttrTable() throws IOException{
		//this.initAttrTable();
		if (null==attrTableDao)
			this.initAttrTable();
		HttpServletRequest   request   =ServletActionContext.getRequest();
		String table_name=request.getParameter("table_name");
		List<AttrTable> listAttr=new ArrayList<AttrTable>();			
		listAttr=attrTableDao.getAttrTableValue(table_name);
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");
		response.setContentType("text/html;charset=gbk");
		response.addHeader("Content-Type", "text/html;charset=gbk");		
		JSONArray listJsonAttrTable=new JSONArray();
		JSONObject object=new JSONObject();

		for(int i=listAttr.size();i>=0;i--){			 
			if (i!=listAttr.size()){
			AttrTable attr=listAttr.get(i);
			object.put("id", attr.getAttr_table_id());
			object.put("text", attr.getAttr_table_text());
			}else{
				object.put("id", "");
				object.put("text", "-��ѡ��-");
			}
			listJsonAttrTable.add(object);
		}		
		PrintWriter print=response.getWriter();
		print.print(listJsonAttrTable.toString());
		print.flush();
		print.close();
	}

	
	public CustTagAttrInfoDaoImpl getCustTagAttrInfoDao() {
		return custTagAttrInfoDao;
	}
	public void setCustTagAttrInfoDao(CustTagAttrInfoDaoImpl custTagAttrInfoDao) {
		this.custTagAttrInfoDao = custTagAttrInfoDao;
	}
	public int getTagUserCounts() {
		return tagUserCounts;
	}
	public void setTagUserCounts(int tagUserCounts) {
	}
	public FileInputStream getDownloadFile() {
		return downloadFile;
	}
	public void setDownloadFile(FileInputStream downloadFile) {
		this.downloadFile = downloadFile;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public CustomgroupInfoDaoImpl getCustGroupDao() {
		return custGroupDao;
	}
	public void setCustGroupDao(CustomgroupInfoDaoImpl custGroupDao) {
		this.custGroupDao = custGroupDao;
	}
	public String getDownPath() {
		return downPath;
	}
	public void setDownPath(String downPath) {
		this.downPath = downPath;
	}
	public CustomGroup getCustomGroup() {
		return customGroup;
	}
	public void setCustomGroup(CustomGroup customGroup) {
		this.customGroup = customGroup;
	}
	
}
