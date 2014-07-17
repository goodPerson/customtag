package action.push;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import module.push.MarkPlanInfo;
import module.push.PlanModel;

import com.opensymphony.xwork2.ActionSupport;

import dao.push.CorePlanImpl;
import dao.push.CreatePlanImpl;



public class CorePlanAction extends ActionSupport{
	
	private List<PlanModel> getFModels;
	private List<PlanModel> getSModels;
	private CorePlanImpl coreplanImpl;
	private CreatePlanImpl  create;
	private int markcount;
	private int lastcount;
	private String maxcount;
	private List<MarkPlanInfo> markPlanInfoList=new ArrayList<MarkPlanInfo>();
	private List<MarkPlanInfo> planNameList=new ArrayList<MarkPlanInfo>();
	String userId;
	private int count;
	public void initHallsImpl() {
		XmlBeanFactory factory=new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
		coreplanImpl=(CorePlanImpl)factory.getBean("coreplanImpl");
	}
	
	public  void initCreate(){
		XmlBeanFactory factory = new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
		create=(CreatePlanImpl)factory.getBean("createImpl");
	}	
	
	public String excute() throws IOException {
		this.initCreate();
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest   request = ServletActionContext.getRequest();
		response.setCharacterEncoding("gbk");
		response.setContentType("text/html;charset=gbk");
		response.addHeader("Content-Type", "text/html;charset=gbk");
        String userId=(String) request.getSession().getAttribute("userId");	
        Calendar date = Calendar.getInstance();
		String time= new SimpleDateFormat("yyyyMMdd").format(date.getTime());
		maxcount=create.maxcount();
		markcount=create.markcount(userId, time);
		lastcount=Integer.parseInt(maxcount)-markcount;
		return SUCCESS;
		
	}
	
	
	public void getmodelsList() throws IOException {
		this.initHallsImpl();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");
		response.addHeader("Content-Type", "text/html;charset=gbk");
		getFModels=coreplanImpl.getModels();
		  JSONArray listcity=new JSONArray();
		for (int i=0;i<getFModels.size();i++){
			getSModels=coreplanImpl.getModelchild(getFModels.get(i).getModelId());
			JSONArray list=new JSONArray();
			JSONObject modelobject=new JSONObject();
			  for (int j=0;j<getSModels.size();j++){
				  PlanModel model=getSModels.get(j);
				  modelobject.put("id", model.getModelId());
				  modelobject.put("text", model.getModelName());
				  list.add(modelobject);
			  }
			
			  JSONObject pro=new JSONObject();
				pro.put("id", getFModels.get(i).getModelId());
				pro.put("text",getFModels.get(i).getModelName());
				pro.put("children", list);
				pro.put("state", "closed");
				listcity.add(pro);
		}
		PrintWriter print=response.getWriter();
		print.print(listcity.toString());
		print.flush();
		print.close();
		
	}
	
	public void getCoreMarkPlanInfoList() throws IOException {
		this.initHallsImpl();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");
		response.addHeader("Content-Type", "text/html;charset=gbk");
		int pageno;
		int pagesize;
		String type=request.getParameter("type");
		if (request.getParameter("page")==null || "".equals(request.getParameter("page"))){
			pageno=1;
		}else{
			pageno=Integer.parseInt(request.getParameter("page"));
		}
		if(request.getParameter("rows")==null || "".equals(request.getParameter("rows"))){
			pagesize=10;
		}else{
			pagesize=Integer.parseInt(request.getParameter("rows"));
		}

		String planName="";
		if(request.getParameter("planName")==null || "".equals(request.getParameter("planName"))){
			planName="";
		}else{
			planName=URLDecoder.decode(request.getParameter("planName"),"utf-8");
		}
		
		String regionId=(String)request.getSession().getAttribute("regionId");

        String userId=(String) request.getSession().getAttribute("userId");
		int begin=(pageno-1)*pagesize+1;
		int end=pageno*pagesize;
		markPlanInfoList=coreplanImpl.getMarkPlanInfoList(userId,regionId,type,planName, begin, end);
		count=coreplanImpl.getMarkPlanInfoCount(userId,regionId,type,planName); 
		JSONArray jsonArray = new JSONArray();
		for (int i = 0; i < markPlanInfoList.size(); i++) {
			MarkPlanInfo markPlanInfo=markPlanInfoList.get(i);
			JSONObject object=new JSONObject();
			object.put("plan_id", markPlanInfo.getPlan_id());
			object.put("plan_name", markPlanInfo.getPlan_name());
			object.put("plan_region_name", markPlanInfo.getPlan_region_name());
			object.put("start_time", markPlanInfo.getStart_time());
			object.put("end_time", markPlanInfo.getEnd_time());
			object.put("creator_id", markPlanInfo.getCreator_id());
			object.put("creator_name", markPlanInfo.getCreator_name());
			if("z".equals(markPlanInfo.getPlan_id().substring(4, 5))){
				object.put("plan_model", "终端运营应用模型");
			}else if ("y".equals(markPlanInfo.getPlan_id().substring(4, 5))){
				object.put("plan_model", "新业务营销");
			}else if ("l".equals(markPlanInfo.getPlan_id().substring(4, 5))){
				object.put("plan_model", "流量偏好分析模型");
			}else if ("g".equals(markPlanInfo.getPlan_id().substring(4, 5))){
				object.put("plan_model", "高价值客户流失预警");
			}else if ("c".equals(markPlanInfo.getPlan_id().substring(4, 5))){
				object.put("plan_model", "沉默与低使用量");
			}
			object.put("paln_state", markPlanInfo.getPaln_state());
			object.put("cust_type", markPlanInfo.getTarget_cust_type());
			object.put("push_time", markPlanInfo.getPush_time().substring(0,8));
			if("bsacHal".equals(markPlanInfo.getPush_chanl_id())){
				object.put("push_chanl_id","营业厅");
			}else if ("bsacKF".equals(markPlanInfo.getPush_chanl_id())){
				object.put("push_chanl_id","客服人工台");
			}else if ("bsacSms".equals(markPlanInfo.getPush_chanl_id())){
				object.put("push_chanl_id","短信");
			}
			//markPlanInfo.getPush_time().substring(0, 4)+"年"+markPlanInfo.getPush_time().substring(4,6)+"月"+markPlanInfo.getPush_time().substring(6,8)+"日"
			object.put("operate","<a href='javascript:PlanAccess();' style='text-decoration: none;'>效果</a>");
			object.put("model_operate","<a href='javascript:PlanModelAccess();' style='text-decoration: none;'>效益</a>");
			jsonArray.add(object);
		}

		JSONObject jsonObjectFinal1 = new JSONObject();
		jsonObjectFinal1.put("total", count);
		jsonObjectFinal1.put("rows", jsonArray);
		PrintWriter print=response.getWriter();
		print.print(jsonObjectFinal1);
		print.flush();
		print.close();
	}
	
	public void getPlanNameList() throws IOException {
		this.initHallsImpl();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");
		response.addHeader("Content-Type", "text/html;charset=gbk");
		String planName="";
		if(request.getParameter("planName")==null || "".equals(request.getParameter("planName"))){
			planName="";
		}else{
			planName=URLDecoder.decode(request.getParameter("planName"),"utf-8");
		}
		String userId=(String) request.getSession().getAttribute("userId");
		planNameList=coreplanImpl.getPlanNameList(userId, planName);
		JSONArray table = new JSONArray();
		if (planNameList!=null) {
			for (int i = 0; i < planNameList.size(); i++) {
				JSONObject tr= new JSONObject(); 
				tr.put("plan_id", planNameList.get(i).getPlan_id());
				tr.put("plan_name", planNameList.get(i).getPlan_name());
				table.add(tr);
			}
		}

		//JSONObject jsonObjectFinal1 = new JSONObject();
		//jsonObjectFinal1.put("total", planNameList.size());
		//jsonObjectFinal1.put("rows", table);

		PrintWriter printWriter = response.getWriter();
		printWriter.print(table.toString());
		printWriter.flush();
		printWriter.close();


	}

	public void getnewplanaccess() throws IOException{
		this.initHallsImpl();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");
		response.addHeader("Content-Type", "text/html;charset=gbk");
		String plan_id=request.getParameter("plan_id");
		String region=URLDecoder.decode(request.getParameter("region"), "UTF-8");
		//List<Object[]> newAccessList=markPlanInfoImpl.getplan(planid);
		List<Object[]> newAccessList = coreplanImpl.getnewplan(plan_id,region);
		JSONArray jsonArray = new JSONArray();
		if(plan_id.substring(4,9).equals("ggjl1")||plan_id.substring(4,9).equals("lllp0")||
			plan_id.substring(4,9).equals("z2ghj")||plan_id.substring(4,9).equals("zywhy")||
			plan_id.substring(4,9).equals("zhjxf")||plan_id.substring(4,9).equals("zllts")||
			plan_id.substring(4,9).equals("ztwqy")){
			for(int i=0;i<newAccessList.size();i++){
				JSONObject object=new JSONObject();
				object.put("A", newAccessList.get(i)[0]);
				object.put("B", newAccessList.get(i)[4]);
				object.put("C", newAccessList.get(i)[2]);
				object.put("D", newAccessList.get(i)[3]);
				jsonArray.add(object);
			}
		}else if(plan_id.substring(4,9).equals("ccmd1")){
			for(int i=0;i<newAccessList.size();i++){
				JSONObject object=new JSONObject();
				object.put("A", newAccessList.get(i)[0]);
				object.put("B", newAccessList.get(i)[5]);
				object.put("C", newAccessList.get(i)[2]);
				object.put("D", newAccessList.get(i)[3]);
				object.put("E", newAccessList.get(i)[4]);
				jsonArray.add(object);
			}
		}else if(plan_id.substring(4,9).equals("yqdpg")){
			for(int i=0;i<newAccessList.size();i++){
				JSONObject object=new JSONObject();
				object.put("A", newAccessList.get(i)[0]);
				object.put("B", newAccessList.get(i)[6]);
				object.put("C", newAccessList.get(i)[2]);
				object.put("D", newAccessList.get(i)[3]);
				object.put("E", newAccessList.get(i)[4]);
				object.put("F", newAccessList.get(i)[5]);
				jsonArray.add(object);
			}
		} else if(plan_id.substring(4,9).equals("zllrh")){
			for(int i=0;i<newAccessList.size();i++){
				JSONObject object=new JSONObject();
				object.put("A", newAccessList.get(i)[0]);
				object.put("B", newAccessList.get(i)[7]);
				object.put("C", newAccessList.get(i)[2]);
				object.put("D", newAccessList.get(i)[3]);
				object.put("E", newAccessList.get(i)[4]);
				object.put("F", newAccessList.get(i)[5]);
				object.put("G", newAccessList.get(i)[6]);
				jsonArray.add(object);
			}
		} else if(plan_id.substring(4,9).equals("ysjxz")){
			for(int i=0;i<newAccessList.size();i++){
				JSONObject object=new JSONObject();
				object.put("A", newAccessList.get(i)[0]);
				object.put("B", newAccessList.get(i)[8]);
				object.put("C", newAccessList.get(i)[2]);
				object.put("D", newAccessList.get(i)[3]);
				object.put("E", newAccessList.get(i)[4]);
				object.put("F", newAccessList.get(i)[5]);
				object.put("G", newAccessList.get(i)[6]);
				object.put("H", newAccessList.get(i)[7]);
				jsonArray.add(object);
			}
		}
		JSONObject jsonObjectFinal1 = new JSONObject();
		jsonObjectFinal1.put("total", 1);
		jsonObjectFinal1.put("rows", jsonArray);
		PrintWriter print=response.getWriter();
		print.print(jsonObjectFinal1);
		print.flush();
		print.close();
	}
	
	  public void getbenefitjson() throws IOException{
			this.initHallsImpl();
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response=ServletActionContext.getResponse();
			response.setCharacterEncoding("gbk");
			response.addHeader("Content-Type", "text/html;charset=gbk");
			String plan_id=request.getParameter("plan_id");
			List<Object[]> newAccessList = coreplanImpl.getbenifit(plan_id);
			JSONArray jsonArray = new JSONArray();
			if(newAccessList.size()!=0){
			for(int i=0;i<newAccessList.size();i++){
				JSONObject object=new JSONObject();
				object.put("A", newAccessList.get(i)[0]);
				object.put("B", newAccessList.get(i)[1]);
				object.put("C", newAccessList.get(i)[2]);
				object.put("D", newAccessList.get(i)[3]);
				object.put("E", newAccessList.get(i)[4]);
				object.put("F", newAccessList.get(i)[5]);
				object.put("G", newAccessList.get(i)[6]);
				object.put("H", newAccessList.get(i)[7]);
				object.put("I", newAccessList.get(i)[8]);
				jsonArray.add(object);
			}
			}
			PrintWriter print=response.getWriter();
			print.print(jsonArray);
			print.flush();
			print.close();
	  }
	public List<PlanModel> getGetFModels() {
		return getFModels;
	}

	public void setGetFModels(List<PlanModel> getFModels) {
		this.getFModels = getFModels;
	}

	public List<PlanModel> getGetSModels() {
		return getSModels;
	}

	public void setGetSModels(List<PlanModel> getSModels) {
		this.getSModels = getSModels;
	}

	public int getMarkcount() {
		return markcount;
	}

	public void setMarkcount(int markcount) {
		this.markcount = markcount;
	}

	public int getLastcount() {
		return lastcount;
	}

	public void setLastcount(int lastcount) {
		this.lastcount = lastcount;
	}

	public String getMaxcount() {
		return maxcount;
	}

	public void setMaxcount(String maxcount) {
		this.maxcount = maxcount;
	}
	
	
	
	

}
