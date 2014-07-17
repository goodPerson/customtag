package action;


import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import module.Attr;
import module.BusClassTag;
import module.CustomTag;
import module.MainTag;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import com.opensymphony.xwork2.ActionSupport;

import dao.MainTagDaoImpl;
import dao.TagInfoDaoImpl;
import dao.Idao.IBusClassTag;

public class MainTanAction extends ActionSupport {
	
	//private MainTagDaoImpl tagDao =new MainTagDaoImpl(); //����Դ
	private static MainTagDaoImpl tagDao; //����Դ
	private IBusClassTag  busClassDao;                                                   //ҵ���������������
	private List<MainTag>mainTagList = new  ArrayList<MainTag>();//���ı�ǩLIST
	private List<BusClassTag> bcg=new ArrayList<BusClassTag>();
	private int serv_type=0;//0��ʾ
	private String serv_name="";//
	private String tag_name="";//��ǩ����
	private String serv_name_src="img/maintag/duanxin.png";         //����ͼ��
	private String url0="./img/maintag/serv2.png";                           //ҵ����
	private String url1="./img/maintag/like2.png";                            //ϲ����
	private String url2="./img/maintag/event2.png";                         //�¼���
	private String url3="./img/maintag/terminal2.png";                     //�ն���
	private String url4="./img/maintag/kehu2.png";                          //�����
	private String url5="./img/maintag/jiazhi2.png";                          //��ֵ��
	private String url6="./img/maintag/jiaowangq2.png";                  //����Ȧ��
	private String url7="./img/maintag/yuyinhui.png";                       //������
	private String url8="./img/maintag/taocanhui.png";                     //λ����	
	private int date;
	private String mon;
	private List<MainTag> listRegion;
	private  String tagName;
	
	static ApplicationContext cxt=null;
	//��ʼ������Դ
	public static void initTagDao(){
		//XmlBeanFactory factory = new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
		//tagDao=(MainTagDaoImpl)factory.getBean("MainTagDao");
		if (null==cxt)
			cxt = new ClassPathXmlApplicationContext("applicationContext.xml");
		tagDao=(MainTagDaoImpl)cxt.getBean("MainTagDao");
		
	}
	
	@Override
	public String execute() throws Exception{
		
		
		return "";
	}
	
	private void initBusDao(){
		XmlBeanFactory factory = new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
		busClassDao=(IBusClassTag) factory.getBean("busClassTagImpl");
	}
	
	//��ʼ�����ı�ǩ����
	public String  initMainTag() throws IOException{
		  if (null==tagDao)
			  this.initTagDao();
		  HttpServletRequest request = ServletActionContext.getRequest();	
		  request.setCharacterEncoding("GBK");
		  tag_name=URLDecoder.decode(request.getParameter("tag_name"), "utf-8");
		  
          this.serv_type=( Integer.parseInt(request.getParameter("serv_type").toString()));
          this.serv_name=( request.getParameter("serv_name").toString());   
          
          if(this.serv_name.equals("0")) { 
        	  this.serv_name="����";                
          }else{
        	  this.serv_name=URLDecoder.decode(request.getParameter("serv_name"), "utf-8");
        	 // this.serv_name_src=URLDecoder.decode(request.getParameter("serv_name_src"), "utf-8");
        	  if("����".equals(this.serv_name)){
        		  this.serv_name_src="img/maintag/duanxin.png";
        	  }else if ("����".equals(this.serv_name)){
        		  this.serv_name_src="img/maintag/caixin.png";
        	  }else if ("MM".equals(this.serv_name)){
        		  this.serv_name_src="img/maintag/MMbiaoqian.png";
        	  }else if ("�ֻ�����".equals(this.serv_name)){
        		  this.serv_name_src="img/maintag/daohang.png";
        	  }else if ("�ֻ�����".equals(this.serv_name)){
        		  this.serv_name_src="img/maintag/shoujidongman.png";
        	  }else if ("�ֻ���Ƶ".equals(this.serv_name)){
        		  this.serv_name_src="img/maintag/shoujishipin.png";
        	  }else if ("�ֻ�����".equals(this.serv_name)){
        		  this.serv_name_src="img/maintag/youxiang.png";
        	  }else if ("�ֻ���Ϸ".equals(this.serv_name)){
        		  this.serv_name_src="img/maintag/youxi.png";
        	  }else if ("�ֻ��Ķ�".equals(this.serv_name)){
        		  this.serv_name_src="img/maintag/yuedu.png";
        	  }else if ("�ֻ�֧��".equals(this.serv_name)){
        		  this.serv_name_src="img/maintag/zhifu.png";
        	  }else if ("��������".equals(this.serv_name)){
        		  this.serv_name_src="img/maintag/wuxianyinyue.png";
        	  }else if ("GPRS����".equals(this.serv_name)){
        		  this.serv_name_src="img/maintag/shoujishangwang.png";
        	  }
          }
          
    	  switch(this.serv_type){
	  		case 0:
	  	 	  this.url0="./img/maintag/serv1.png";
	  	 	  break;
	  		case 1:
	  	 	  this.url1="./img/maintag/like1.png";
	  	 	  break;
	  		case 2:
	  	 	  this.url2="./img/maintag/event1.png";
	  	 	  break;
	  		case 3:
	  	 	  this.url3="./img/maintag/terminal1.png";
	  	 	  break;
	  		case 4:
	  	 	  this.url4="./img/maintag/kehu1.png";
	  	 	  break;
	  		case 5:
	  	 	  this.url5="./img/maintag/jiazhi1.png";
	  	 	  break;
	  		case 6:
	  	 	  this.url6="./img/maintag/jiaowangq1.png";
	  	 	  break;   
	  		case 7:
		  	 	  this.url7="./img/maintag/yuyinlan.png";
		  	 	  break; 
	  		case 8:
		  	 	  this.url8="./img/maintag/taocanlan.png";
		  	 	  break; 
	  }
    	  initBusDao();
          HttpSession session=request.getSession();
          String regionId=session.getAttribute("regionId").toString();
//		  this.mainTagList=this.tagDao.listTag(serv_type, serv_name);       20130801 bak
          if (1==serv_type){
        	  bcg=busClassDao.listTags();
          }
    	 // this.mainTagList=this.tagDao.listTag(serv_type, serv_name,regionId);   
          this.mainTagList=this.tagDao.findListTag(serv_type, serv_name,regionId,tag_name);   
    	  
		  date=tagDao.getMonth();
	      mon=(String.valueOf(date)).substring(0,4)+"��"+(String.valueOf(date)).substring(4,6)+"��";
          return SUCCESS;
	}

	
	/*��ȡ�����������ر�ǩ����*/
	public String listRegionData() throws UnsupportedEncodingException{
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
	   request.setCharacterEncoding("utf-8");
	   String tagId=request.getParameter("tagId");
	   tagName=URLDecoder.decode(request.getParameter("tagName"), "utf-8");
	   String regionId=request.getParameter("regionId").trim();
	   if ("".equals(regionId)){
		   regionId=request.getSession().getAttribute("regionId").toString();
	   }
	   listRegion=tagDao.listRegionData(regionId, tagId);
	   
		return SUCCESS;
	}
	
	/*���ұ�ǩ*/
	public void findTag() throws IOException{
		this.initTagDao();
		  HttpServletResponse response = ServletActionContext.getResponse();
		  response.setCharacterEncoding("utf-8");
		  response.setContentType("text/html;charset=utf-8");
		  response.addHeader("Content-Type", "text/html;charset=utf-8");	
		HttpServletRequest request = ServletActionContext.getRequest();
		String tagName=URLDecoder.decode(request.getParameter("tag_name"), "utf-8");
		List<CustomTag> listCtag=tagDao.findTag(tagName);
		
		JSONArray  jarray=new JSONArray();
		for(CustomTag cust:  listCtag){
			JSONObject obj=new JSONObject();
			obj.put("tagName", cust.getTag_name());
			jarray.add(obj);			
		}
		
		JSONObject jsonObjectFinal1 = new JSONObject();
        jsonObjectFinal1.put("total", listCtag.size());
        jsonObjectFinal1.put("rows", jarray);
        PrintWriter printWriter = response.getWriter();
        printWriter.print(jsonObjectFinal1.toString());
        printWriter.flush();
        printWriter.close();
		
	}
	
	
	public String getUrl7() {
		return url7;
	}
	public void setUrl7(String url7) {
		this.url7 = url7;
	}
	public String getUrl8() {
		return url8;
	}
	public void setUrl8(String url8) {
		this.url8 = url8;
	}	
	public String getUrl0() {
		return url0;
	}
	public void setUrl0(String url0) {
		this.url0 = url0;
	}
	public String getUrl1() {
		return url1;
	}
	public void setUrl1(String url1) {
		this.url1 = url1;
	}
	public String getUrl2() {
		return url2;
	}
	public void setUrl2(String url2) {
		this.url2 = url2;
	}
	public String getUrl3() {
		return url3;
	}
	public void setUrl3(String url3) {
		this.url3 = url3;
	}
	public String getUrl4() {
		return url4;
	}
	public void setUrl4(String url4) {
		this.url4 = url4;
	}
	public String getUrl5() {
		return url5;
	}
	public void setUrl5(String url5) {
		this.url5 = url5;
	}
	public String getUrl6() {
		return url6;
	}
	public void setUrl6(String url6) {
		this.url6 = url6;
	}
	public String getServ_name_src() {
		return serv_name_src;
	}
	public void setServ_name_src(String serv_name_src) {
		this.serv_name_src = serv_name_src;
	}
	public String getServ_name() {
		return serv_name;
	}
	public void setServ_name(String serv_name) {
		this.serv_name = serv_name;
	}
	public int getServ_type() {
		return serv_type;
	}
	public void setServ_type(int serv_type) {
		this.serv_type = serv_type;
	}
	public List<MainTag> getMainTagList() {
		return mainTagList;
	}
	public void setMainTagList(List<MainTag> mainTagList) {
		this.mainTagList = mainTagList;
	}
	public MainTagDaoImpl getTagDao() {
		return tagDao;
	}
	public void setTagDao(MainTagDaoImpl tagDao) {
		this.tagDao = tagDao;
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
	public List<MainTag> getListRegion() {
		return listRegion;
	}
	public void setListRegion(List<MainTag> listRegion) {
		this.listRegion = listRegion;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public List<BusClassTag> getBcg() {
		return bcg;
	}
	public void setBcg(List<BusClassTag> bcg) {
		this.bcg = bcg;
	}
	public String getTag_name() {
		return tag_name;
	}
	public void setTag_name(String tag_name) {
		this.tag_name = tag_name;
	}    

}
