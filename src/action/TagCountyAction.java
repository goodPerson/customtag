package action;
/**
 * ����ͳ��Action
 */
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import module.CustTagAndGroupMonitorD;
import module.CustTagCountyD;
import module.CustTagCountyM;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.opensymphony.xwork2.ActionSupport;

import common.CommonUtil;
import common.ExportXLS;
import common.PrintExcelUtil;

import dao.TagCountyImpl;

//public class TagCountyAction extends ActionSupport {
public class TagCountyAction extends ExportXLS{
	private HttpServletRequest request=ServletActionContext.getRequest();
	private HttpServletResponse response=ServletActionContext.getResponse();
	private TagCountyImpl tcounty;
	private ApplicationContext context;
	
	private HttpSession session=request.getSession();
	private String regionNameS=session.getAttribute("regionName").toString();
	

	
	/**��������������ϸ��Ϣ����Ҫ�Ĳ���
	String contentType;//��������
	FileInputStream downloadFile;//
	String fileName;//�ļ���
	*/
    int pageno=0;
	int pagesize=0;
	
	private void inittcounty(){
		 context=new ClassPathXmlApplicationContext("applicationContext.xml");
		 tcounty=(TagCountyImpl) context.getBean("tagcounty");
	}
	
	public String countyMethod(){
		return "contyMethod";
	}
	
     /**
      * ����������ͳ��
     * @throws IOException 
      */
	public void findCtcdList() throws IOException{
		inittcounty();
		response.setCharacterEncoding("utf-8");
	    response.addHeader("Content-Type", "text/html;charset=utf-8");
	    
	    String starDate=request.getParameter("starDate");     //��ʼ����
	    String endDate=request.getParameter("endDate");    //��������
	    String regionName=CommonUtil.requestFormat(request.getParameter("regionName")); //����
	    String countryName=CommonUtil.requestFormat(request.getParameter("countyName")); //����
	    pager();
	    
	    CustTagCountyD ctcd=new CustTagCountyD();
	    ctcd.setRegionName(regionName);
	    ctcd.setCountryName(countryName);
	    
	    List<CustTagCountyD> lctcd=tcounty.findCtcdList(regionNameS, starDate, endDate, ctcd, pageno, pagesize);
	    int totalRecord=tcounty.getPageInfo().getTotalRows();
	    
	    JSONArray jsonArray=new JSONArray();
	    for(CustTagCountyD ctcdt: lctcd){
	    	JSONObject jobj=new JSONObject();
	    	jobj.put("statisDate", ctcdt.getStatisDate());
	    	jobj.put("regionName", ctcdt.getRegionName());
	    	jobj.put("countryName", ctcdt.getCountryName());
	    	jobj.put("accessSubsCnt", ctcdt.getAccessSubsCnt());
	    	jobj.put("accessCnt", ctcdt.getAccessCnt());
	    	jobj.put("custlistDwlnSubsCnt", ctcdt.getCustlistDwlnSubsCnt());
	    	jobj.put("custlistDwlnCnt", ctcdt.getCustlistDwlnCnt());
	    	jobj.put("custgroupCreateSubsCnt", ctcdt.getCustgroupCreateSubsCnt());
	    	jobj.put("custgroupCreateCnt", ctcdt.getCustgroupCreateCnt());
	    	jobj.put("custtagCreateSubsCnt", ctcdt.getCustgroupCreateSubsCnt());
	    	jobj.put("custtagCreateCnt", ctcdt.getCusttagCreateCnt());
	    	jsonArray.add(jobj);
	    }
	   // jsonObj( totalRecord, jsonArray);
		   JSONObject  jsonFinal=new JSONObject();
		   jsonFinal.put("total", totalRecord);
		   jsonFinal.put("rows", jsonArray);
	    printWriter(jsonFinal.toString());
	    
	}
	/**
	 * ��ѯ������ͳ��
	 * @throws IOException
	 */
	public void findCtcmList() throws IOException{
		inittcounty();
		response.setCharacterEncoding("utf-8");
	    response.addHeader("Content-Type", "text/html;charset=utf-8");
	    String statisMonth=CommonUtil.requestFormat(request.getParameter("statisMonth"));     //��ʼ����
	    if ("".equals(statisMonth)){
	    	statisMonth="0";
	    }
	    String regionName=CommonUtil.requestFormat(request.getParameter("regionName")); //����
	    String countryName=CommonUtil.requestFormat(request.getParameter("countyName")); //����

		pager( );

	    CustTagCountyM ctcm=new CustTagCountyM();
	    ctcm.setStatisMonth(Integer.parseInt(statisMonth));
	    ctcm.setRegionName(regionName);
	    ctcm.setCountryName(countryName);
		 List<CustTagCountyM> lctcm=tcounty.findCtcmList(regionNameS, ctcm, pageno, pagesize);
		 int totalRecord=tcounty.getPageInfo().getTotalRows();
		    JSONArray jsonArray=new JSONArray();
		    for(CustTagCountyM ctcmt: lctcm){
		    	JSONObject jobj=new JSONObject();
		    	jobj.put("statisMonth", ctcmt.getStatisMonth());
		    	jobj.put("regionName", ctcmt.getRegionName());
		    	jobj.put("countryName", ctcmt.getCountryName());
		    	jobj.put("accessSubsCnt", ctcmt.getAccessSubsCnt());
		    	jobj.put("accessCnt", ctcmt.getAccessCnt());
		    	jobj.put("custlistDwlnSubsCnt", ctcmt.getCustlistDwlnSubsCnt());
		    	jobj.put("custlistDwlnCnt", ctcmt.getCustlistDwlnCnt());
		    	jobj.put("custgroupCreateSubsCnt", ctcmt.getCustgroupCreateSubsCnt());
		    	jobj.put("custgroupCreateCnt", ctcmt.getCustgroupCreateCnt());
		    	jobj.put("custtagCreateSubsCnt", ctcmt.getCustgroupCreateSubsCnt());
		    	jobj.put("custtagCreateCnt", ctcmt.getCusttagCreateCnt());
		    	jsonArray.add(jobj);
		    }
		    //jsonObj( totalRecord, jsonArray);
			   JSONObject  jsonFinal=new JSONObject();
			   jsonFinal.put("total", totalRecord);
			   jsonFinal.put("rows", jsonArray);
		    printWriter(jsonFinal.toString());
		 
		
	}
	
	/**
	 * ����������ͳ��
	 * @return
	 * @throws Exception
	*/
	public String exportMCount() throws Exception{
		inittcounty();
		response.setCharacterEncoding("utf-8");
	    response.addHeader("Content-Type", "text/html;charset=utf-8");
	    String title="";
	    String statisMonth=CommonUtil.requestFormat(request.getParameter("statisMonth"));     //��ʼ����
	    if ("".equals(statisMonth)){
	    	statisMonth="0";
	    }
	    String regionName=CommonUtil.requestFormat(request.getParameter("regionName")); //����
	    String countryName=CommonUtil.requestFormat(request.getParameter("countyName")); //����
	    if (!"".equals(regionName)){
	    	title+=regionName+"_";
	    }
	    if (!"".equals(countryName)){
	    	title+=countryName+"_";
	    }
	    title+="�ͻ���ǩ������ͳ��";
		CustTagCountyM ctcm=new CustTagCountyM();
	    ctcm.setStatisMonth(Integer.parseInt(statisMonth));
	    ctcm.setRegionName(regionName);
	    ctcm.setCountryName(countryName);
		 List<CustTagCountyM> lctcm=tcounty.findCtcmList(regionNameS, ctcm);
	    
		String  exportType = "xls";
		// �����ͷ
		String header = "�·� �������� �������� �������� ���ʴ��� �嵥�������� �嵥���ش��� ��Ⱥ�������� ��Ⱥ�������� ��ǩ������������ ��ǩ������������";
		// ��ȡ����
		List<Object[]> datalist = new ArrayList<Object[]>();
		for (int i = 0; i < lctcm.size(); i++) {
			CustTagCountyM t = (CustTagCountyM) lctcm.get(i);
			Object[]	obj={((CustTagCountyM) t).getStatisMonth(),((CustTagCountyM) t).getRegionName(),((CustTagCountyM) t).getCountryName(),((CustTagCountyM) t).getAccessSubsCnt(),((CustTagCountyM) t).getAccessCnt(),
	                 ((CustTagCountyM) t).getCustlistDwlnSubsCnt(),((CustTagCountyM) t).getCustlistDwlnCnt(),
					((CustTagCountyM) t).getCustgroupCreateSubsCnt(),((CustTagCountyM) t).getCustgroupCreateCnt(),
					((CustTagCountyM) t).getCusttagCreateSubsCnt(),((CustTagCountyM) t).getCusttagCreateCnt()};
			datalist.add(obj);
		}
		finputStream(title,exportType,  header,  datalist);
		return "mDownLoad" ;		
	}
	 
	/**
	 * ����������ͳ��
	 * @return
	 * @throws Exception
	 */
	public String exportDCount() throws Exception{
		inittcounty();
		response.setCharacterEncoding("utf-8");
	    response.addHeader("Content-Type", "text/html;charset=utf-8");
	    String title=""; //����
	    String starDate=request.getParameter("starDate");     //��ʼ����
	    String endDate=request.getParameter("endDate");    //��������
	    String regionName=CommonUtil.requestFormat(request.getParameter("regionName")); //����
	    String countryName=CommonUtil.requestFormat(request.getParameter("countyName")); //����
	    if (!"".equals(regionName)){
	    	title+=regionName+"_";
	    }
	    if (!"".equals(countryName)){
	    	title+=countryName+"_";
	    }
	    title+="�ͻ���ǩ������ͳ��";
	    
	    CustTagCountyD ctcd=new CustTagCountyD();
	    
	    ctcd.setRegionName(regionName);
	    ctcd.setCountryName(countryName);
		 List<CustTagCountyD> lctcm=tcounty.findCtcdList(regionNameS, starDate, endDate, ctcd);
	    
		String  exportType = "xls";
		// �����ͷ
		String header = "���� �������� �������� �������� ���ʴ��� �嵥�������� �嵥���ش��� ��Ⱥ�������� ��Ⱥ�������� ��ǩ������������ ��ǩ������������";
		// ��ȡ����
		List<Object[]> datalist = new ArrayList<Object[]>();
		for (int i = 0; i < lctcm.size(); i++) {
			CustTagCountyD t = (CustTagCountyD) lctcm.get(i);
			Object[]	obj={((CustTagCountyD) t).getStatisDate(),((CustTagCountyD) t).getRegionName(),((CustTagCountyD) t).getCountryName(),((CustTagCountyD) t).getAccessSubsCnt(),((CustTagCountyD) t).getAccessCnt(),
	                 ((CustTagCountyD) t).getCustlistDwlnSubsCnt(),((CustTagCountyD) t).getCustlistDwlnCnt(),
					((CustTagCountyD) t).getCustgroupCreateSubsCnt(),((CustTagCountyD) t).getCustgroupCreateCnt(),
					((CustTagCountyD) t).getCusttagCreateSubsCnt(),((CustTagCountyD) t).getCusttagCreateCnt()};
			datalist.add(obj);
		}
		finputStream(title, exportType,  header,  datalist);
		return "dDownLoad" ;		
	}
	
	private void finputStream(String title,String exportType, String header, List<Object[]> datalist) throws Exception{
		FileInputStream result = PrintExcelUtil.getDownloadFile(exportType,
				title, title, header, datalist);
		contentType = "application/vnd.ms-excel;charset=utf-8";
		downloadFile = result;
		fileName = title+".xls";
		fileName = new String(fileName.getBytes(), "ISO8859-1");
	}
	
	
	public String exportMCount_new() throws Exception{
		inittcounty();
		response.setCharacterEncoding("utf-8");
	    response.addHeader("Content-Type", "text/html;charset=utf-8");
	    String statisMonth=request.getParameter("statisMonth");     //��ʼ����
	    if ("".equals(statisMonth)){
	    	statisMonth="0";
	    }
	    String regionName=CommonUtil.requestFormat(request.getParameter("regionName")); //����
	    String countryName=CommonUtil.requestFormat(request.getParameter("countyName")); //����
	    
		CustTagCountyM ctcm=new CustTagCountyM();
	    ctcm.setStatisMonth(Integer.parseInt(statisMonth));
	    ctcm.setRegionName(regionName);
	    ctcm.setCountryName(countryName);
	    List<CustTagCountyM> lctcm=tcounty.findCtcmList(regionNameS, ctcm);
	    String header = "�·� �������� �������� �������� ���ʴ��� �嵥�������� �嵥���ش��� ��Ⱥ�������� ��Ⱥ�������� ��ǩ������������ ��ǩ������������";
	   
		ExportXLS<CustTagCountyM> e=new ExportXLS();
		e.exportMonthPiece(lctcm, header, "�ͻ���ǩ������ͳ��");
		
		return "mDownLoad" ;		
	}
	
	private void printWriter(String jsonArray) throws IOException{
		PrintWriter print=response.getWriter();
		print.write(jsonArray);
		print.flush();
		print.close();
	}
	
	private void pager(){
		if (request.getParameter("page")==null || ""==request.getParameter("page")){
			pageno=1;
		}else{
			pageno=Integer.parseInt(request.getParameter("page"));
		}
		if(request.getParameter("rows")==null || ""==request.getParameter("rows")){
			pagesize=10;
		}else{
			pagesize=Integer.parseInt(request.getParameter("rows"));
		}
	}
	
	private void jsonObj(int totalRecord,JSONArray jsonArray){
		   JSONObject  jsonFinal=new JSONObject();
		   jsonFinal.put("total", totalRecord);
		   jsonFinal.put("rows", jsonArray);
	}

}
