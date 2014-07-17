package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import module.CustomGroup;
import module.CustomTag;
import module.MainTag;
import common.GetLog;
import common.Pagination;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import com.opensymphony.xwork2.ActionSupport;


import dao.TagOperateImpl;

public class TagOperateAction extends ActionSupport {
	//private TagOperateImpl tagoperate =new TagOperateImpl();
	private static TagOperateImpl tagoperate;
	private String tag_id="";
	private String profile="";
	private String tag_name="";
	private String create_time="";
	private String end_time="";
	private String mes;
	private String name;
	private String custom_type;
	private int a ;
	private String userId;
	private String userName;
	private String regionId;
    private String regionName;
    private int date;
    private String mon;
	private List<CustomGroup> listTag= new ArrayList<CustomGroup>();
	
	static ApplicationContext factory=null;
	private int pageNo;         //��ǰҳ


	private int pageCounts;  //��ҳ��
	private int totalRecords; //�ܼ�¼��
	private int prewPage;     //��һҳ
	private int nextPage;      //��һҳ
	
	public static void initTag(){
		//XmlBeanFactory factory = new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
		if (null==factory)
			factory = new ClassPathXmlApplicationContext("applicationContext.xml");
		tagoperate=(TagOperateImpl)factory.getBean("TagOperateDao");
	}
	
	
	public String execute() throws Exception{
		//this.addPerson();
		if (null==tagoperate)
		this.initTag();
		 HttpServletRequest request = ServletActionContext.getRequest();
		 String qgroupName="";
	     if(request.getParameter("mes")==null || ""==request.getParameter("mes")){
	    	mes="�ҵı�ǩ";
	     }else{
	    	 mes=URLDecoder.decode(request.getParameter("mes"), "utf-8");
	    	 
	     }
	        if(mes.equals("�ҵı�ǩ")){
	    		 mes="myTag";
	    	 }	
	    	 if(request.getParameter("ordername")==null || "".equals(request.getParameter("ordername"))){
		    	 name="tag_id";
		    	 
		    }else{
		    name=URLDecoder.decode(request.getParameter("ordername"), "utf-8");
		        
		    if(name.equals("��ǩ����")){
		    	name="count_subs";
		    }else if (name.equals("��ǩ����") || "".equals(name)){
		    	name="tag_name";
		    }else if (name.equals("��������")){
		    	name="create_time";
		    }}
	    	 if(request.getParameter("custom_type")==null || "".equals(request.getParameter("custom_type"))){
	    		 custom_type="�ҵĿͻ�Ⱥ";
	    	 }else{
	    	 custom_type=URLDecoder.decode(request.getParameter("custom_type"), "utf-8");
	    	 if(!"0".equals(custom_type)){
	    		 qgroupName=URLDecoder.decode(request.getParameter("qgroupName"), "utf-8");		
	    	 }
	    	 
	    	 if(custom_type.equals("ʡ��˾")){
	    	      custom_type="�ӱ�ʡ";	 
	    	 }
	    	 }
	    	 
	 		pageNo=0;
			int pagesize=10;			
			if (request.getParameter("pageNo")==null || "".equals(request.getParameter("pageNo")) || "0".equals(request.getParameter("pageNo")) ){
				pageNo=1;
			}else{
				pageNo=Integer.parseInt(request.getParameter("pageNo"));
			}
//	    	 String userId=request.getParameter("userId");
	      userId=(String) request.getSession().getAttribute("userId");
//		this.listTag=this.tagoperate.listTag(custom_type,name,userId);
//	      this.listTag=this.tagoperate.listTag(custom_type,name,userId,pageNo,pagesize);
	      this.listTag=this.tagoperate.listTag(custom_type,name,qgroupName,userId,pageNo,pagesize);
			pageCounts=tagoperate.getPageInfo().getTotalPages();		
			totalRecords=tagoperate.getPageInfo().getTotalRows();
			setPrewPage();
			setNextPage();
		date=tagoperate.getMonth();
		 mon=(String.valueOf(date)).substring(0,4)+"��"+(String.valueOf(date)).substring(4,6)+"��";
		a=listTag.size();
		if(custom_type.equals("0")){
			GetLog.getLog("�ͻ�Ⱥ", "��ѯ", "����Ⱥ", "--");
		}
		else{
			GetLog.getLog("�ͻ�Ⱥ", "��ѯ", custom_type, "--");	
		}
		//this.message=this.listTag.	
		return SUCCESS;
	}
	
	public void addTag() throws IOException {
		if (null==tagoperate)
		  this.initTag();
		  HttpServletResponse response = ServletActionContext.getResponse();
		  response.setCharacterEncoding("gbk");
		  response.setContentType("text/html;charset=gbk");
		  response.addHeader("Content-Type", "text/html;charset=gbk");	
		  HttpServletRequest request = ServletActionContext.getRequest();
		  Calendar date = Calendar.getInstance();
		  String time= new SimpleDateFormat("yyyyMMddHHmmss").format(date.getTime());
//		  String tag_id = request.getParameter("tag_id").toString();
		  userId=(String) request.getSession().getAttribute("userId");
		  userName=(String) request.getSession().getAttribute("userName");
		  regionName=(String) request.getSession().getAttribute("regionName");
          tag_id =time+userId;
          profile=request.getParameter("tag_introduce");
		  tag_name = request.getParameter("tag_name");
		  create_time=request.getParameter("create_time");
          end_time=request.getParameter("end_time");
          CustomGroup newTag = new CustomGroup();
		  newTag.setTag_id(tag_id);
		  newTag.setTag_name(tag_name);
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
		  newTag.setIs_share("0");
		  newTag.setTag_status("0");
		  newTag.setCustlist_path("");
		  newTag.setTag_tec_stamt("");
		  tagoperate.addTag(newTag);
		  GetLog.getLog("�����ͻ�Ⱥ", "���", "����"+tag_name+"�ͻ�Ⱥ", tag_name);
		  PrintWriter pWriter = response.getWriter();
		  
		  String aa ="�����ɹ�";
		  pWriter.print(aa);
		  pWriter.flush();
		  pWriter.close();
		  		
	}
	
public void delTag() throws IOException {
	if (null==tagoperate)
	  this.initTag();
	  HttpServletResponse response = ServletActionContext.getResponse();
	  response.setCharacterEncoding("gbk");
	  response.setContentType("text/html;charset=gbk");
	  response.addHeader("Content-Type", "text/html;charset=gbk");	
	  HttpServletRequest request = ServletActionContext.getRequest();
	  String id=request.getParameter("tag_id");
	  String ids[]=id.split(",");
	  for (int i=0;i<ids.length;i++){
		  tagoperate.delTag(ids[i]);
	  }
	  GetLog.getLog("ɾ���ͻ�Ⱥ", "ɾ��", "ɾ��"+tag_id+"�ͻ�Ⱥ", tag_id);
}
public void shareTag() throws IOException {
	if (null==tagoperate)
	  this.initTag();
	  HttpServletResponse response = ServletActionContext.getResponse();
	  response.setCharacterEncoding("gbk");
	  response.setContentType("text/html;charset=gbk");
	  response.addHeader("Content-Type", "text/html;charset=gbk");	
	  HttpServletRequest request = ServletActionContext.getRequest();
	  String id=request.getParameter("tag_id");
	   String [] ids = id.split(",");
	   for (int i=0;i<ids.length;i++){
		   tagoperate.shareTag(ids[i]);
	   }
}
public void renameTag() throws IOException{
	if (null==tagoperate)
	  this.initTag();
	  HttpServletResponse response = ServletActionContext.getResponse();
	  response.setCharacterEncoding("gbk");
	  response.setContentType("text/html;charset=gbk");
	  response.addHeader("Content-Type", "text/html;charset=gbk");	
	  HttpServletRequest request = ServletActionContext.getRequest();
	  String name=request.getParameter("name");
	  String id=request.getParameter("id");
	  tagoperate.renameTag(name, id);
	  GetLog.getLog("�������ͻ�Ⱥ", "������", "������"+name+"�ͻ�Ⱥ", name);
}


public TagOperateImpl getTagoperate() {
	return tagoperate;
}
public void setTagoperate(TagOperateImpl tagoperate) {
	this.tagoperate = tagoperate;
}
public String getTag_id() {
	return tag_id;
}
public void setTag_id(String tag_id) {
	this.tag_id = tag_id;
}
public String getProfile() {
	return profile;
}
public void setProfile(String profile) {
	this.profile = profile;
}
public String getTag_name() {
	return tag_name;
}
public void setTag_name(String tag_name) {
	this.tag_name = tag_name;
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
public String getMes() {
	return mes;
}
public void setMes(String mes) {
	this.mes = mes;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public int getA() {
	return a;
}
public void setA(int a) {
	this.a = a;
}
public List<CustomGroup> getListTag() {
	return listTag;
}
public void setListTag(List<CustomGroup> listTag) {
	this.listTag = listTag;
}
public String getCustom_type() {
	return custom_type;
}
public void setCustom_type(String custom_type) {
	this.custom_type = custom_type;
}
public String getUserId() {
	return userId;
}
public void setUserId(String userId) {
	this.userId = userId;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getRegionId() {
	return regionId;
}
public void setRegionId(String regionId) {
	this.regionId = regionId;
}
public String getRegionName() {
	return regionName;
}
public void setRegionName(String regionName) {
	this.regionName = regionName;
}

public int getDate() {
	return date;
}

public void setDate(int date) {
	this.date = date;
}

public String getMon() {
	return mon;
}

public void setMon(String mon) {
	this.mon = mon;
}
public int getNextPage() {
	return nextPage;
}


public void setNextPage() {
	this.nextPage = pageNo+1;
}
public int getPrewPage() {
	return prewPage;
}


public void setPrewPage() {
	this.prewPage = pageNo-1;
}
public int getPageNo() {
	return pageNo;
}


public void setPageNo(int pageNo) {
	this.pageNo = pageNo;
}


public int getPageCounts() {
	return pageCounts;
}


public void setPageCounts(int pageCounts) {
	this.pageCounts = pageCounts;
}


public int getTotalRecords() {
	return totalRecords;
}


public void setTotalRecords(int totalRecords) {
	this.totalRecords = totalRecords;
}

}
