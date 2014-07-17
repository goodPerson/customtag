package dao.push;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import module.push.MarkPlanInfo;
import module.push.PlanModel;
import module.push.TargetCust;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class CorePlanImpl extends JdbcDaoSupport {
	
	public List<PlanModel> getModels(){
		String sql="select distinct YW_TYPE1_NAME ,YW_TYPE1_ID from MK_VGOP.TB_ASSESS_DIM_MODEL order by YW_TYPE1_ID";
		List<Map<String,Object>> list= getJdbcTemplate().queryForList(sql);
		List<PlanModel> getModel = new ArrayList<PlanModel>();
		 for(Map<String,Object> row:list){
			 PlanModel model= new PlanModel();
				model.setModelId((String)row.get("YW_TYPE1_ID"));
				model.setModelName((String)row.get("YW_TYPE1_NAME"));
				getModel.add(model);
	           }
		 return getModel;
		
	}
	
	public List<PlanModel> getModelchild(String modelId){
		String sql="select distinct YW_TYPE2_NAME, YW_TYPE2_ID from MK_VGOP.TB_ASSESS_DIM_MODEL where YW_TYPE1_ID='"+modelId+"' order by YW_TYPE2_ID";
		List<Map<String,Object>> list= getJdbcTemplate().queryForList(sql);
		List<PlanModel> getModel = new ArrayList<PlanModel>();
		 for(Map<String,Object> row:list){
			 PlanModel model= new PlanModel();
				model.setModelId((String)row.get("YW_TYPE2_ID"));
				model.setModelName((String)row.get("YW_TYPE2_NAME"));
				getModel.add(model);
	           }
		 return getModel;
	}
	
	public List<MarkPlanInfo> getMarkPlanInfoList(String creator_id,String regionId,String type,String plan_name,int begin,int end) {
		String sql="";
		String region="";
		if (!"1".equals(regionId)) {
			region=" AND CREATOR_REGION_ID = '"+regionId.substring(0, 3)+"'";
		}else {
			region="";
		}
		sql="SELECT PLAN_ID,PLAN_NAME,PLAN_REGION_NAME,START_TIME,END_TIME,CREATOR_ID,CREATOR_NAME,PALN_STATE,TARGET_CUST_TYPE,PUSH_TIME,PUSH_CHANL_ID FROM ("
				+" SELECT PLAN_ID,PLAN_NAME,PLAN_REGION_ID,PLAN_REGION_NAME,START_TIME,END_TIME,CREATOR_ID, CREATOR_NAME,PUSH_CHANL_ID,CASE PALN_STATE"
				+" WHEN  '0' THEN '活动创建' "
				+"WHEN '1' THEN '目标客户生成' " 
				+"WHEN '2' THEN '活动推送' " 
				+"WHEN '3' THEN '活动评估' END AS PALN_STATE,CASE TARGET_CUST_TYPE "
				+" WHEN  'GROUP' THEN '客户群清单' "
				+"WHEN 'TAG' THEN '标签清单' " 
				+"WHEN 'UPLOAD' THEN '上传清单' END AS TARGET_CUST_TYPE,PUSH_TIME, " 
				+"ROW_NUMBER() OVER(ORDER BY CASE WHEN CREATOR_ID = '"+creator_id+"' THEN 1 ELSE 999 END ,PUSH_TIME DESC) AS RN"
				+" FROM MK_VGOP.TB_MARK_PLAN_BASIC_INFO " 
				+"WHERE  PLAN_NAME LIKE '%"+plan_name+"%' AND PLAN_ID LIKE '%"+type+"%'" 
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
			markPlanInfoList.add(markPlanInfo);
		} 
		return markPlanInfoList;
	}
	
	public int getMarkPlanInfoCount(String creator_id ,String regionId,String type,String plan_name) {
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
				+"WHERE  PLAN_NAME LIKE '%"+plan_name+"%' AND PLAN_ID LIKE '%"+type+"%'"
				+region+" )";
		try {
			count=getJdbcTemplate().queryForInt(sql);
		} catch (Exception e) { 
			count=0;
		}
		return  count;
	}
	
	public List<MarkPlanInfo> getPlanNameList(String creator_id,String plan_name) {
		String sql="SELECT PLAN_ID, PLAN_NAME FROM MK_VGOP.TB_MARK_PLAN_BASIC_INFO WHERE  PLAN_NAME LIKE '%"+plan_name+"%' AND PLAN_ID LIKE '%core%' ORDER BY PUSH_TIME DESC";//CREATOR_ID='"+creator_id+"' AND
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
	
	public List<Object[]> getnewplan(String planid,String region){
		String sql="";
		List<Object[]> newList = new ArrayList<Object[]>();
		if(planid.substring(4, 9).equals("ccmd1")){
			sql ="select m.*,r.region_name from MK_VGOP.TB_ASSESS_SIL_AND_LOW_USE m left join MK_VGOP.TB_ASSESS_DIM_REGION r on M.REGION_ID=R.REGION_ID where r.region_name='"+region+"' order by m.STATIS_MONTH desc fetch first 1 rows only ";
			List<Map<String,Object>> list = getJdbcTemplate().queryForList(sql);
			for(Map<String, Object> rows: list){
				Object[] obj = new Object[]{
					rows.get("STATIS_MONTH"),
					rows.get("REGION_ID"),
					rows.get("MONTH_SIL_USER_RATE"),
					rows.get("LOWUSE_USER_RATE"),
					rows.get("ACTIVE_RATE"),
					rows.get("REGION_NAME")
				};
				newList.add(obj);
			}
		}else if(planid.substring(4, 9).equals("ggjl1")){
			sql ="select m.*,r.region_name from MK_VGOP.TB_ASSESS_HIGH_VALUE_CUST_LOST_WARN m left join MK_VGOP.TB_ASSESS_DIM_REGION r on M.REGION_ID=R.REGION_ID where r.region_name='"+region+"' order by m.STATIS_MONTH desc fetch first 1 rows only ";
			List<Map<String,Object>> list = getJdbcTemplate().queryForList(sql);
			for(Map<String, Object> rows: list){
				Object[] obj = new Object[]{
					rows.get("STATIS_MONTH"),
					rows.get("REGION_ID"),
					rows.get("HIGH_VAL_CUST_OFFNET_RATE"),
					rows.get("HIGH_VAL_ADD_SUBS_NUM"),
					rows.get("REGION_NAME")
				};
				newList.add(obj);
			}
		}else if(planid.substring(4, 9).equals("lllp0")){
			sql ="select m.*,r.region_name from MK_VGOP.TB_ASSESS_FLUX_PREFER_ANALYZE m left join MK_VGOP.TB_ASSESS_DIM_REGION r on M.REGION_ID=R.REGION_ID where r.region_name='"+region+"' order by m.STATIS_MONTH desc fetch first 1 rows only ";
			List<Map<String,Object>> list = getJdbcTemplate().queryForList(sql);
			for(Map<String, Object> rows: list){
				Object[] obj = new Object[]{
					rows.get("STATIS_MONTH"),
					rows.get("REGION_ID"),
					rows.get("RECOMMEND_SUCC_RATE"),
					rows.get("JP_SERV_FW_AMOUNT"),
					rows.get("REGION_NAME")
				};
				newList.add(obj);
			}
		}else  if(planid.substring(4, 9).equals("ysjxz")){
			sql ="select m.*,r.region_name from MK_VGOP.TB_ASSESS_NEW_MARKT_OPPORTUNITY_CHOOSE m left join MK_VGOP.TB_ASSESS_DIM_REGION r on M.REGION_ID=R.REGION_ID where r.region_name='"+region+"' order by m.STATIS_MONTH desc fetch first 1 rows only ";
			List<Map<String,Object>> list = getJdbcTemplate().queryForList(sql);
			for(Map<String, Object> rows: list){
				Object[] obj = new Object[]{
					rows.get("STATIS_MONTH"),
					rows.get("REGION_ID"),
					rows.get("CONTACT_SUM_CNT"),
					rows.get("RESPOND_CNT"),
					rows.get("RESPOND_RATE"),
					rows.get("BUSI_TYPE"),
					rows.get("USE_OPPORTUNITY"),   
					rows.get("LOCATE"),
					rows.get("REGION_NAME")
				};
				newList.add(obj);
			}
		}else  if(planid.substring(4, 9).equals("yqdpg")){
			sql ="select m.*,r.region_name from MK_VGOP.TB_ASSESS_NEW_MARKT_CHNL_SPREAD m left join MK_VGOP.TB_ASSESS_DIM_REGION r on M.REGION_ID=R.REGION_ID where r.region_name='"+region+"' order by m.STATIS_MONTH desc fetch first 1 rows only ";
			List<Map<String,Object>> list = getJdbcTemplate().queryForList(sql);
			for(Map<String, Object> rows: list){
				Object[] obj = new Object[]{
					rows.get("STATIS_MONTH"),
					rows.get("REGION_ID"),
					rows.get("CONTACT_SUM_CNT"),
					rows.get("RESPOND_CNT"),
					rows.get("CHANGE_RATE"),
					rows.get("CHNL"),
					rows.get("REGION_NAME")
				};
				newList.add(obj);
			}
		}else  if(planid.substring(4, 9).equals("z2ghj")){
			sql ="select m.*,r.region_name from MK_VGOP.TB_ASSESS_2G_HIGH_FLUX_CHG_TMNL m left join MK_VGOP.TB_ASSESS_DIM_REGION r on M.REGION_ID=R.REGION_ID where r.region_name='"+region+"' order by m.STATIS_MONTH desc fetch first 1 rows only ";
			List<Map<String,Object>> list = getJdbcTemplate().queryForList(sql);
			for(Map<String, Object> rows: list){
				Object[] obj = new Object[]{
					rows.get("STATIS_MONTH"),
					rows.get("REGION_ID"),
					rows.get("CHG_TMNL_SUC_RATE"),
					rows.get("AVG_FLUX_PROMOTE_RATE"),
					rows.get("REGION_NAME")
				};
				newList.add(obj);
			}
		}else  if(planid.substring(4, 9).equals("zywhy")){
			sql ="select m.*,r.region_name from MK_VGOP.TB_ASSESS_DATA_SERV_CONTRACT_PLAN m left join MK_VGOP.TB_ASSESS_DIM_REGION r on M.REGION_ID=R.REGION_ID where r.region_name='"+region+"' order by m.STATIS_MONTH desc fetch first 1 rows only ";
			List<Map<String,Object>> list = getJdbcTemplate().queryForList(sql);
			for(Map<String, Object> rows: list){
				Object[] obj = new Object[]{
					rows.get("STATIS_MONTH"),
					rows.get("REGION_ID"),
					rows.get("CHG_TMNL_SUC_RATE"),
					rows.get("AVG_FLUX_PROMOTE_RATE"),
					rows.get("REGION_NAME")
				};
				newList.add(obj);
			}
		}else  if(planid.substring(4, 9).equals("zllrh")){
			sql ="select m.*,r.region_name from MK_VGOP.TB_ASSESS_INT_TMNL_FLUX_MIX m left join MK_VGOP.TB_ASSESS_DIM_REGION r on M.REGION_ID=R.REGION_ID  where r.region_name='"+region+"' order by m.STATIS_MONTH desc fetch first 1 rows only ";
			List<Map<String,Object>> list = getJdbcTemplate().queryForList(sql);
			for(Map<String, Object> rows: list){
				Object[] obj = new Object[]{
					rows.get("STATIS_MONTH"),
					rows.get("REGION_ID"),
					rows.get("MOBILE_FLUX_PROMOTE_RATE"),
					rows.get("MOBILE_OFFER_MARKET_SUCC_RATE"),
					rows.get("CONTRACT_TMNL_OFFER_MIX_RATE"),
					rows.get("CONTRACT_TMNL_FLUX_PRO_RATE"),
					rows.get("CONTMNL_OFFER_MARKT_SUCC_RATE"),
					rows.get("REGION_NAME")
				};
				newList.add(obj);
			}
		}else  if(planid.substring(4, 9).equals("zhjxf")){
			sql ="select m.*,r.region_name from MK_VGOP.TB_ASSESS_CHG_TMNL_EXCIT_PERIOD_CONTENT m left join MK_VGOP.TB_ASSESS_DIM_REGION r on M.REGION_ID=R.REGION_ID where r.region_name='"+region+"' order by m.STATIS_MONTH desc fetch first 1 rows only ";
			List<Map<String,Object>> list = getJdbcTemplate().queryForList(sql);
			for(Map<String, Object> rows: list){
				Object[] obj = new Object[]{
					rows.get("STATIS_MONTH"),
					rows.get("REGION_ID"),
					rows.get("CHG_TMNL_EXCIT_USER_PUSH_RATE"),
					rows.get("CHG_TMNL_AVG_FLUX_PRO_RATE"),
					rows.get("REGION_NAME")
				};
				newList.add(obj);
			}
		}else  if(planid.substring(4, 9).equals("zllts")){
			sql ="select m.*,r.region_name from MK_VGOP.TB_ASSESS_INT_TMNL_FLUX_PROMOTE m left join MK_VGOP.TB_ASSESS_DIM_REGION r on M.REGION_ID=R.REGION_ID where r.region_name='"+region+"' order by m.STATIS_MONTH desc fetch first 1 rows only ";
			List<Map<String,Object>> list = getJdbcTemplate().queryForList(sql);
			for(Map<String, Object> rows: list){
				Object[] obj = new Object[]{
					rows.get("STATIS_MONTH"),
					rows.get("REGION_ID"),
					rows.get("AVG_FLUX_PROMOTE_RATE"),
					rows.get("OFFER_MARKT_SUCC_RATE"),
					rows.get("REGION_NAME")
				};
				newList.add(obj);
			}
		}else  if(planid.substring(4, 9).equals("ztwqy")){
			sql ="select m.*,r.region_name from MK_VGOP.TB_ASSESS_TD_TMNL_T_NET_FLUX_MOVE m left join MK_VGOP.TB_ASSESS_DIM_REGION r on M.REGION_ID=R.REGION_ID where r.region_name='"+region+"' order by m.STATIS_MONTH desc fetch first 1 rows only ";
			List<Map<String,Object>> list = getJdbcTemplate().queryForList(sql);
			for(Map<String, Object> rows: list){
				Object[] obj = new Object[]{
					rows.get("STATIS_MONTH"),
					rows.get("REGION_ID"),
					rows.get("TD_FLUX_PRO_RATE"),
					rows.get("TD_BRANCH_EFFECT"),
					rows.get("REGION_NAME")
				};
				newList.add(obj);
			}
		}
		return newList;
	}
	
	public List<Object[]> getbenifit(String plan_id){
         String sql="select * from MK_VGOP.TB_ASSESS_MARKT_CASE_BENIFET WHERE PLAN_ID='"+plan_id+"'";
         List<Map<String,Object>> list = getJdbcTemplate().queryForList(sql);
         List<Object[]> benifitList = new ArrayList<Object[]>();
           for(Map<String, Object> rows: list){
				Object[] obj = new Object[]{
					rows.get("STATIS_MONTH"),
					rows.get("PRESENT_FEE_SUBS_CNT"),
					rows.get("MARKTING_COST_INPUT"),
					rows.get("MARKTING_DEVELOP_SUBS_CNT"),
					rows.get("MARKTING_ACTIVE_INCOME"),
					rows.get("MARKTING_ACTIVE_INOUT_RATE"),
					rows.get("AVG_DEVELOP_COST_INPUT"),
					rows.get("OFF_NET_SUBS_CNT"),
					rows.get("OFF_NET_RATE")
				};
				benifitList.add(obj);
	}
           return benifitList;
	}

}
