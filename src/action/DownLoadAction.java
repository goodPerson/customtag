package action;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import module.DownReason;
import module.DownloadInfo;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;

import com.opensymphony.xwork2.ActionSupport;
import common.GetLog;

import dao.DownLoadInfoImpl;
import dao.DownReasonImpl;
import dao.RegionDaoImpl;




public class DownLoadAction extends ActionSupport{
	//private DownLoadInfoImpl downloadinfo =new DownLoadInfoImpl();
	private static  DownLoadInfoImpl downloadinfo;
	private static RegionDaoImpl regionDao;
	private DownReasonImpl downReasons;
	private String group_name;
	private Integer count;
	private String sql;
    private String group_id;
    private String column;
    private String column_desc;
    private String type;
    private String userId;
    private String userName;
    private String regionName;
    private String regionId;
    private String flag; //�������ͣ�0��ʾ��ǩ���أ�1��ʾ�ͻ�Ⱥ����
    private List<DownloadInfo> listDown= new ArrayList<DownloadInfo>();
    private DownloadInfo downInfo;
    private DownReason downReasonm;
    private List<DownReason> downReasonmList;
    private String tagId;

	private String downReason;
    private String createTime;
    private String fileName;
    private String downPath;
    
    static ApplicationContext factory=null;
	//��ҳ���ܲ���  start
	
	private int pageNo;  //��ǰҳ
	private int pageCounts;  //��ҳ��
	private int totalRecords; //�ܼ�¼��
	private int fontPage;
	private int nextPage;
	  HttpServletResponse response = ServletActionContext.getResponse();
	  HttpServletRequest request = ServletActionContext.getRequest();
	//��ҳ���ܲ���  end
	public static void initTag(){
		XmlBeanFactory factory = new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
		downloadinfo=(DownLoadInfoImpl)factory.getBean("DownLoad");
	}
	
	public  void initDownReason(){
		XmlBeanFactory factory = new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
		downReasons=(DownReasonImpl) factory.getBean("downReason");
	}
	
	
	
	public void addDownload() throws IOException{
	   	if (null==downloadinfo)
		   this.initTag();

		  response.setCharacterEncoding("gbk");
		  response.setContentType("text/html;charset=gbk");
		  response.addHeader("Content-Type", "text/html;charset=gbk");	
		  
		  Calendar date = Calendar.getInstance();
		  String time= new SimpleDateFormat("yyyyMMddhhmmss").format(date.getTime());
		  group_name=request.getParameter("tag_name");
		  count=Integer.parseInt(request.getParameter("count"));
		 // sql=URLDecoder.decode(request.getParameter("sql"), "utf-8");
		//	sql=sql.replace("'", "'||Chr(39)||'");
		  String sql1=URLDecoder.decode(request.getParameter("sql"), "utf-8");
		  sql=sql1.substring(0,sql1.length());
		  group_id=request.getParameter("id");
		  String  column1=request.getParameter("col");
		  column=column1.substring(0,column1.length()-1);
		  column_desc=request.getParameter("col_desc");
		  type=request.getParameter("type");
		  userId=(String) request.getSession().getAttribute("userId");
		  userName=(String) request.getSession().getAttribute("userName");
		 // regionName=(String) request.getSession().getAttribute("regionName");
		  regionName=request.getParameter("region_name");
		  flag=request.getParameter("flag");
		  if(type.equals("group_list")){
		  regionId=(String) request.getSession().getAttribute("regionId");
		  }else{
			  regionId=request.getParameter("regionId");
		  }
		  DownloadInfo down = new DownloadInfo();
		  int count_id=downloadinfo.getList(userId, group_id);
		  if(count_id==0){
		  down.setFile_name(group_name+"�û��嵥");
		  }else{
	       down.setFile_name(group_name+"�û��嵥"+"("+count_id+")");	  
		  }
		  down.setId(group_id);
		  down.setCreate_time(time);
		  down.setCount(count);
		  down.setFile_type(type);
		  down.setRegion_id(regionId);
		  down.setRegion_name(regionName);
		  down.setStatus("1");
		  down.setColumn(column);
		  down.setColumn_desc(column_desc);
		  down.setSql(sql);
		  down.setManager_id(userId);
		  down.setManager(userName);
		  down.setCustlist_path("none");
		  if(flag.equals("0")){
		  GetLog.getLog("�ͻ���ǩ","����",group_id,userName+"���ؿͻ���ǩ"+group_id); }
		  // �ͻ���ǩ�����ء���ǩID����������
		  if(flag.equals("1")){
		  GetLog.getLog("�ͻ�Ⱥ","����",group_id,userName+"���ؿͻ�Ⱥ"+group_id); }
		  downloadinfo.addDownload(down);
	}
	
	
	public String getListDownInfo() throws Exception{
		if (null==downloadinfo)
		 this.initTag();
		  response.setCharacterEncoding("gbk");
		  response.setContentType("text/html;charset=gbk");
		  response.addHeader("Content-Type", "text/html;charset=gbk");	
		  
	//	 userId=(String) request.getSession().getAttribute("userId");
		 userId=request.getParameter("userId");
		 pageNo=0;
		int pagesize=15;			
		if (request.getParameter("pageNo")==null || ""==request.getParameter("pageNo")){
			pageNo=1;
		}else{
			pageNo=Integer.parseInt(request.getParameter("pageNo"));
		}
		
		listDown=downloadinfo.queryDown(userId,pageNo,pagesize);
		pageCounts=downloadinfo.getPageInfo().getTotalPages();		
		totalRecords=downloadinfo.getPageInfo().getTotalRows();
		 return SUCCESS;
		
	}
	
	public String requestDownReason(){
		this.initTag();
		  response.setCharacterEncoding("gbk");
		  response.setContentType("text/html;charset=gbk");
		  response.addHeader("Content-Type", "text/html;charset=gbk");	
		 
		
		String userId=request.getParameter("userId");		
		downPath=request.getParameter("downPath");
	    createTime=request.getParameter("createTime");
		downInfo=downloadinfo.getDownInfo(createTime, userId);
		 fileName=downInfo.getFile_name();
		return SUCCESS;
	}
	
	public String addReason(){	
		initDownReason();
	  response.setCharacterEncoding("gbk");
	  response.setContentType("text/html;charset=gbk");
	  response.addHeader("Content-Type", "text/html;charset=gbk");	
	  
	  Calendar c=Calendar.getInstance();
	  Date date=c.getTime();
	  SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
	  String reasonForm=request.getParameter("reasonForm");
//	  String createTimeForm=request.getParameter("createTimeForm");
	  String createTimeForm=sdf.format(date);
	  String downPath=request.getParameter("downPath");
	  String tagId=request.getParameter("tagId");
	  String tagName=request.getParameter("tagName");
	  HttpSession session=request.getSession();
	  String userId=session.getAttribute("userId").toString();
	  String userName=session.getAttribute("userName").toString();
	  String regionId=session.getAttribute("regionId").toString();
	  String regionName=session.getAttribute("regionName").toString();
	  DownReason dr=new DownReason();
	  dr.setTagId(tagId);
	  dr.setTagName(tagName);
	  dr.setUserId(userId);
	  dr.setUserName(userName);
	  dr.setRegionId(regionId);
	  dr.setRegionName(regionName);
	  dr.setCreateTime(createTimeForm);
	  dr.setDownReason(reasonForm);
	  
	  //boolean  flag=downloadinfo.updateReason(createTimeForm, reasonForm);	 
	   
	  boolean  flag=downReasons.add(dr);
	  GetLog.getLog("���ع���", "���", tagName, userName+"���"+tagName);
	  if (flag){
		  return SUCCESS;
	  }else{
		  return "false";
	  }	  
	}

	public String listReason(){
		initDownReason();
		HttpServletRequest request=ServletActionContext.getRequest();
		tagId=request.getParameter("tagId");
		int pagesize=15;			
		if (request.getParameter("pageNo")==null || ""==request.getParameter("pageNo")){
			pageNo=1;
		}else{
			pageNo=Integer.parseInt(request.getParameter("pageNo"));
		}
		downReasonmList=downReasons.listDr(tagId, pageNo, pagesize);
		pageCounts=downReasons.getPageInfo().getTotalPages();		
		totalRecords=downReasons.getPageInfo().getTotalRows();
		setFontPage();
		setNextPage();
		return SUCCESS;
	}
	
	public String delShow(){
		this.initTag();
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");
		response.setContentType("text/html;charset=gbk");
		response.addHeader("Content-Type", "text/html;charset=gbk");
		HttpServletRequest request=ServletActionContext.getRequest();
		String createTime=request.getParameter("createTime");
		boolean flag=downloadinfo.delShow(createTime);
		if (flag){
			return SUCCESS;
		}else{
			return "false";
		}
		
	}
	
	//���ذ����ļ�
	public void   downHelpFile() throws FileNotFoundException{
		   // ���ر����ļ�
        String fileName = "onlineHelp.txt".toString(); // �ļ���Ĭ�ϱ�����
        // ��������
        InputStream inStream = new FileInputStream("D://customHelper//onlineHelp.txt");// �ļ��Ĵ��·��
        // ��������ĸ�ʽ
        response.reset();
        response.setContentType("bin");
        response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        // ѭ��ȡ�����е�����
        byte[] b = new byte[100];
        int len;
        try {
            while ((len = inStream.read(b)) > 0)
                response.getOutputStream().write(b, 0, len);
            inStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public List<DownloadInfo> getListDown() {
		return listDown;
	}
	public void setListDown(List<DownloadInfo> listDown) {
		this.listDown = listDown;
	}

	public String getDownReason() {
		return downReason;
	}

	public void setDownReason(String downReason) {
		this.downReason = downReason;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
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

	public String getDownPath() {
		return downPath;
	}

	public void setDownPath(String downPath) {
		this.downPath = downPath;
	}
	public DownloadInfo getDownInfo() {
		return downInfo;
	}
	public void setDownInfo(DownloadInfo downInfo) {
		this.downInfo = downInfo;
	}

	public DownReason getDownReasonm() {
		return downReasonm;
	}

	public void setDownReasonm(DownReason downReasonm) {
		this.downReasonm = downReasonm;
	}	
	
	public List<DownReason> getDownReasonmList() {
		return downReasonmList;
	}

	public void setDownReasonmList(List<DownReason> downReasonmList) {
		this.downReasonmList = downReasonmList;
	}

	public int getFontPage() {
		return fontPage;
	}

	public void setFontPage() {
		this.fontPage = pageNo-1;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage() {
		this.nextPage = pageNo+1;
	}

	public String getTagId() {
		return tagId;
	}

	public void setTagId(String tagId) {
		this.tagId = tagId;
	}
	
}
