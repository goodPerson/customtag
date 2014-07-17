package dao.push;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
 
import module.PlanAccess;
import module.push.MarkPlanInfo;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class MarkPlanInfoImpl extends JdbcDaoSupport{
	
	public List<MarkPlanInfo> getMarkPlanInfoList(String creator_id,String regionId,String plan_name,int begin,int end) {
		String sql="";
		String region="";
		if (!"1".equals(regionId)) {
			region=" AND CREATOR_REGION_ID = '"+regionId.substring(0, 3)+"'";
		}else {
			region="";
		}
//		sql="SELECT PLAN_ID,PLAN_NAME,PLAN_REGION_NAME,START_TIME,END_TIME,PALN_STATE FROM ("
//		+" SELECT PLAN_ID,PLAN_NAME,PLAN_REGION_NAME,START_TIME,END_TIME,"
//		+" CASE PALN_STATE WHEN  '0' THEN '活动创建'"
//		+" WHEN '1' THEN '目标客户生成'"
//		+" WHEN '2' THEN '活动推送'"
//		+" WHEN '3' THEN '活动评估' END AS PALN_STATE,ROW_NUMBER() OVER(order by  PLAN_ID DESC) AS RN"
//		+" FROM MK_VGOP.TB_MARK_PLAN_BASIC_INFO"
//		+" WHERE "
//		+"CREATOR_ID='"+creator_id
//		+"' AND PLAN_NAME LIKE '%"+plan_name+"%' )"
//		+"  WHERE RN>="+begin+" AND RN<="+end+"";
		sql="SELECT PLAN_ID,PLAN_NAME,PLAN_REGION_NAME,START_TIME,END_TIME,CREATOR_ID,CREATOR_NAME,PALN_STATE,TARGET_CUST_TYPE,PUSH_TIME,PUSH_CHANL_ID,AGREE_COUNT,TOUCH_COUNT FROM ("
				+" SELECT a.PLAN_ID,a.PLAN_NAME,a.PLAN_REGION_ID,a.PLAN_REGION_NAME,a.START_TIME,a.END_TIME,a.CREATOR_ID,a.CREATOR_NAME,a.PUSH_CHANL_ID,CASE a.PALN_STATE"
				+" WHEN  '0' THEN '活动创建' "
				+"WHEN '1' THEN '目标客户生成' " 
				+"WHEN '2' THEN '活动推送' " 
				+"WHEN '3' THEN '活动评估' END AS PALN_STATE,CASE a.TARGET_CUST_TYPE "
				+" WHEN  'GROUP' THEN '客户群清单' "
				+"WHEN 'TAG' THEN '标签清单' " 
				+"WHEN 'UPLOAD' THEN '上传清单' END AS TARGET_CUST_TYPE,a.PUSH_TIME, b.AGREE_COUNT,b.TOUCH_COUNT," 
				+"ROW_NUMBER() OVER(ORDER BY CASE WHEN a.CREATOR_ID = '"+creator_id+"' THEN 1 ELSE 999 END ,a.PUSH_TIME DESC) AS RN"
				+" FROM MK_VGOP.TB_MARK_PLAN_BASIC_INFO a left join MK_VGOP.TB_PLAN_BACK_ACCESS_INFO b on a.plan_id=b.plan_id " 
				+"WHERE  a.PLAN_NAME LIKE '%"+plan_name+"%' AND a.PLAN_ID NOT LIKE '%core%'" 
				+region
				+")  WHERE RN>="+begin+" AND RN<="+end+"";
		List<Map<String, Object>> list=getJdbcTemplate().queryForList(sql);
		List<MarkPlanInfo> markPlanInfoList=new ArrayList<MarkPlanInfo>();
		
		for (Map<String, Object> row:list) {
			
			MarkPlanInfo markPlanInfo = new MarkPlanInfo();
			markPlanInfo.setPlan_id((String) row.get("plan_id"));
			markPlanInfo.setPlan_name((String) row.get("plan_name"));
			markPlanInfo.setPlan_region_name((String) row.get("plan_region_name"));
			markPlanInfo.setStart_time((String) row.get("start_time"));
			markPlanInfo.setEnd_time((String) row.get("end_time"));
			markPlanInfo.setCreator_id((String)row.get("creator_id"));
			markPlanInfo.setCreator_name((String)row.get("creator_name"));
			markPlanInfo.setPaln_state((String) row.get("paln_state"));
			markPlanInfo.setTarget_cust_type((String)row.get("TARGET_CUST_TYPE"));
			markPlanInfo.setPush_time((String)row.get("PUSH_TIME"));
			markPlanInfo.setPush_chanl_id((String)row.get("PUSH_CHANL_ID"));
			if("".equals(row.get("TOUCH_COUNT"))||null==row.get("TOUCH_COUNT")){
				markPlanInfo.setSuccesscent("--");
			}else{
				//markPlanInfo.setSuccesscent(((Integer)row.get("AGREE_COUNT")/(Integer)row.get("TOUCH_COUNT")))
				float a=(((Integer)row.get("AGREE_COUNT")).floatValue())/(Integer)(row.get("TOUCH_COUNT"));
				//System.out.print(a);
				DecimalFormat df = new DecimalFormat("###0.00");
				markPlanInfo.setSuccesscent(df.format(a*100));
			}
			markPlanInfoList.add(markPlanInfo);
		} 
		return markPlanInfoList;
	}
	
	public int getMarkPlanInfoCount(String creator_id ,String regionId,String plan_name) {
		int count=0;
		String region="";
		if (!"1".equals(regionId)) {
			region=" AND CREATOR_REGION_ID = '"+regionId.substring(0, 3)+"'";
		}else {
			region="";
		}
		String sql="SELECT COUNT(1) FROM "
				+" (SELECT PLAN_ID,PLAN_NAME,PLAN_REGION_ID,PLAN_REGION_NAME,START_TIME,END_TIME, CREATOR_NAME,CREATOR_ID,CASE PALN_STATE"
				+" WHEN  '0' THEN '活动创建' "
				+"WHEN '1' THEN '目标客户生成' " 
				+"WHEN '2' THEN '活动推送' " 
				+"WHEN '3' THEN '活动评估' END AS PALN_STATE," 
				+"ROW_NUMBER() OVER(ORDER BY CASE WHEN CREATOR_ID = '"+creator_id+"' THEN 1 ELSE 999 END ,PUSH_TIME DESC) AS RN"
				+" FROM MK_VGOP.TB_MARK_PLAN_BASIC_INFO " 
				+"WHERE  PLAN_NAME LIKE '%"+plan_name+"%' AND PLAN_ID NOT LIKE '%core%'"
				+region+" )";
		try {
			count=getJdbcTemplate().queryForInt(sql);
		} catch (Exception e) { 
			count=0;
		}
		return  count;
	}
	
	public List<MarkPlanInfo> getPlanNameList(String creator_id,String plan_name) {
		String sql="SELECT PLAN_ID, PLAN_NAME FROM MK_VGOP.TB_MARK_PLAN_BASIC_INFO WHERE  PLAN_NAME LIKE '%"+plan_name+"%' and PLAN_ID NOT LIKE '%core%' ORDER BY PUSH_TIME DESC";//CREATOR_ID='"+creator_id+"' AND
		List<Map<String, Object>> list=getJdbcTemplate().queryForList(sql);
		List<MarkPlanInfo> planNameList=new ArrayList<MarkPlanInfo>();
		for (Map<String, Object> row:list) {
			
			MarkPlanInfo markPlanInfo=new MarkPlanInfo();
			markPlanInfo.setPlan_id((String)row.get("plan_id"));
			markPlanInfo.setPlan_name((String)row.get("plan_name"));
			planNameList.add(markPlanInfo);
			
		}
		return planNameList;
	}
	
	public int delMarkt(String plan_id,String userId) {
		String sql="delete from MK_VGOP.TB_MARK_PLAN_BASIC_INFO where plan_id='"+plan_id+"' and CREATOR_ID='"+userId+"'";
		int i=0;
		try {
			i=getJdbcTemplate().update(sql);
		} catch (Exception e) {
			i=0;
		}				
		return i;
	}
	
	public List<PlanAccess>getplan (String planid){
		String sql="select * from MK_VGOP.TB_MARK_PLAN_ASSESS_INFO where plan_id='"+planid+"'";
		List<Map<String, Object>> list=getJdbcTemplate().queryForList(sql);
		List<PlanAccess> planNameList=new ArrayList<PlanAccess>();
		for (Map<String, Object> row:list) {
			PlanAccess plan=new PlanAccess();
			plan.setDate_stamp((String)row.get("DATE_STAMP"));
			plan.setPlan_id((String)row.get("PLAN_ID"));
			plan.setPlan_name((String)row.get("PLAN_NAME"));
			plan.setCommend_cnt((Integer)row.get("COMMEND_CNT"));
			plan.setSuccess_cnt((Integer)row.get("SUCCESS_CNT"));
			plan.setSuccess_rate((BigDecimal)row.get("SUCCESS_RATE"));
			planNameList.add(plan);
		}
		  return planNameList;
	}
	
	//活动接触人数
	public int totalcount(String planid,String date){
		String sql="select count(*) from DW.TB_PLAN_BACK_INFO_BOSS where PLAN_ID='"+planid+"'";
		return getJdbcTemplate().queryForInt(sql);
	}
	
	//拒绝用户
	public int rejectcount(String planid,String date){
		String sql="select count(*) from DW.TB_PLAN_BACK_INFO_BOSS where PLAN_ID='"+planid+"' and FEED_BACK_ID='0'";
		return getJdbcTemplate().queryForInt(sql);
	}
	
	//同意办理用户
	public int agreecount(String planid,String date){
		String sql="select count(*) from DW.TB_PLAN_BACK_INFO_BOSS where PLAN_ID='"+planid+"' and FEED_BACK_ID='1'";
		return getJdbcTemplate().queryForInt(sql);
	}
	
	//考虑办理用户
	public int thinkcount(String planid,String date){
		String sql="select count(*) from DW.TB_PLAN_BACK_INFO_BOSS where PLAN_ID='"+planid+"' and FEED_BACK_ID='2'";
		return getJdbcTemplate().queryForInt(sql);
	}
	
	//无法办理用户
	public int enalbecount(String planid,String date){
			String sql="select count(*) from DW.TB_PLAN_BACK_INFO_BOSS where PLAN_ID='"+planid+"' and FEED_BACK_ID='3'";
		return getJdbcTemplate().queryForInt(sql);
    }
	
	//已经办理用户
	public int alreadycount(String planid,String date){
			String sql="select count(*) from DW.TB_PLAN_BACK_INFO_BOSS where PLAN_ID='"+planid+"' and FEED_BACK_ID='5'";
		return getJdbcTemplate().queryForInt(sql);
	    }
	//其他用户
	public int elsecount(String planid,String date){
		String sql="select count(*) from DW.TB_PLAN_BACK_INFO_BOSS where PLAN_ID='"+planid+"' and FEED_BACK_ID='4'";
	return getJdbcTemplate().queryForInt(sql);
	    }
	//活动总人数
	public int total(String planid){
		String sql="select count from MK_VGOP.TB_PLAN_SUBS_COUNTS_INFO where PLAN_ID ='"+planid+"' and count !=0 ";
		int a;
		try {
			a = getJdbcTemplate().queryForInt(sql);
		} catch (DataAccessException e) {
			return 0;
		}
		return a;	
		}
	//活动相关人数信息查询
	public List<Object[]> getcount(String planid){
		String sql="select * from MK_VGOP.TB_PLAN_BACK_ACCESS_INFO where PLAN_ID ='"+planid+"'";
		List<Map<String,Object>> list = getJdbcTemplate().queryForList(sql);
		List<Object[]> newList = new ArrayList<Object[]>();
		for(Map<String, Object> rows: list){
			Object[] obj = new Object[]{
				rows.get("PUSH_COUNT"),
				rows.get("TOUCH_COUNT"),
				rows.get("REJECT_COUNT"),
				rows.get("AGREE_COUNT"),
				rows.get("THINK_COUNT"),
				rows.get("DISABLE_COUNT"),
				rows.get("ALREADY_COUNT"),
				rows.get("ELSE_COUNT")
			};
			newList.add(obj);
		}
		return newList;
	}
	
		
		
	
	

}
