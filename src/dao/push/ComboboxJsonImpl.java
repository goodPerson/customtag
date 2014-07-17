package dao.push;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import module.DownloadInfo;
import module.push.*;
public class ComboboxJsonImpl extends JdbcDaoSupport {
	
	public List<TargetCust> getcust(String userId,String type){
		String sql="";
		if("GROUP".equals(type)){
		 sql="select ID,FILE_NAME from MK_VGOP.TB_DOWNLOAD_INFO  where manager_id='"+userId+"' and file_type='group_list' and CUSTLIST_PATH!='none' ORDER BY ID ";
		}else if("TAG".equals(type)){
		 sql="select ID,FILE_NAME from MK_VGOP.TB_DOWNLOAD_INFO  where manager_id='"+userId+"' and file_type='tag_list' and CUSTLIST_PATH!='none' ORDER BY ID ";	
		}
		List<Map<String,Object>> list= getJdbcTemplate().queryForList(sql);
		   List<TargetCust> tagList= new ArrayList<TargetCust>();
		   for(Map<String,Object> row:list){
			    TargetCust cust= new TargetCust();
				cust.setCustid((String)row.get("ID"));
				cust.setCustname((String)row.get("FILE_NAME"));
				tagList.add(cust);
	}
		   return tagList;
}
	public List<UploadInfo> getupload(String userId){
		String sql="select * from MK_VGOP.TB_UPLOAD_CUST_INFO where creator='"+userId+"' order by UPLOAD_TIME desc";
		 List<Map<String,Object>> list= getJdbcTemplate().queryForList(sql);
		 List<UploadInfo> tagList= new ArrayList<UploadInfo>();
		 for(Map<String,Object> row:list){
			    UploadInfo cust= new UploadInfo();
				cust.setFilename((String)row.get("FILE_NAME"));
				cust.setUploadtime(row.get("UPLOAD_TIME").toString().substring(0,4)+"年"+row.get("UPLOAD_TIME").toString().substring(4,6)+"月"+row.get("UPLOAD_TIME").toString().substring(6,8)+"日");
				cust.setUploadfilename((String)row.get("UPLOAD_FILE_NAME"));
				tagList.add(cust);
	}
		 return tagList;
	}
	
	public List<DownloadInfo> getcustomlist(String userId,String type,Integer begin,Integer end ){
		String sql="select *  FROM (select FILE_NAME,ID,CREATE_TIME,FILE_TYPE,COUNT,REGION_NAME, ROW_NUMBER() OVER (ORDER BY create_time desc) AS RN from mk_vgop.tb_download_info where MANAGER_ID='"+userId+"' and FILE_TYPE='"+type+"' ) "+
            "WHERE RN<="+end+" AND RN>="+begin+"";			
		List<Map<String,Object>> list= getJdbcTemplate().queryForList(sql);
		 List<DownloadInfo> custList= new ArrayList<DownloadInfo>();
		 for(Map<String,Object> row:list){
			 DownloadInfo cust= new DownloadInfo();
				cust.setFile_name((String)row.get("FILE_NAME"));
				cust.setId((String)row.get("ID"));
				String time=row.get("CREATE_TIME").toString().substring(0,4)+"年"+row.get("create_time").toString().substring(4,6)+"月"+row.get("create_time").toString().substring(6,8)+"日";
				cust.setCreate_time(time);
				if("tag_list".equals((String)row.get("FILE_TYPE"))){
					cust.setFile_type("标签清单");
				}else if ("group_list".equals((String)row.get("file_type"))){
					cust.setFile_type("客户群清单");
				}
				cust.setCount((Integer)row.get("COUNT"));
				cust.setRegion_name((String)row.get("REGION_NAME"));
				custList.add(cust);
	}
		 return custList;
	}
	public List<DownloadInfo> getselectcustomlist(String userId,String custid,String custname){
		String sql=" select FILE_NAME,ID,CREATE_TIME,FILE_TYPE,COUNT,REGION_NAME, ROW_NUMBER() OVER (ORDER BY create_time desc) AS RN from mk_vgop.tb_download_info where MANAGER_ID='"+userId+"' and id='"+custid+"' and file_name='"+custname+"' ";			
		List<Map<String,Object>> list= getJdbcTemplate().queryForList(sql);
		 List<DownloadInfo> custList= new ArrayList<DownloadInfo>();
		 for(Map<String,Object> row:list){
			 DownloadInfo cust= new DownloadInfo();
				cust.setFile_name((String)row.get("FILE_NAME"));
				cust.setId((String)row.get("ID"));
				String time=row.get("CREATE_TIME").toString().substring(0,4)+"年"+row.get("create_time").toString().substring(4,6)+"月"+row.get("create_time").toString().substring(6,8)+"日";
				cust.setCreate_time(time);
				if("tag_list".equals((String)row.get("FILE_TYPE"))){
					cust.setFile_type("标签清单");
				}else if ("group_list".equals((String)row.get("file_type"))){
					cust.setFile_type("客户群清单");
				}
				cust.setCount((Integer)row.get("COUNT"));
				cust.setRegion_name((String)row.get("REGION_NAME"));
				custList.add(cust);
	}
		 return custList;
	}
	
	public Integer countlist(String userId,String type){
		String sql="select count(*) from mk_vgop.tb_download_info where MANAGER_ID='"+userId+"' and FILE_TYPE='"+type+"' ";
	    return (int)getJdbcTemplate().queryForInt(sql);  
	}
	
	public List<Object[]> finddetailtype(){
		String sql="select PLAN_DETAIL_TYPE,PLAN_DETAIL_TYPE_NAME from MK_VGOP.TB_DIM_PLAN_TYPE ORDER BY PLAN_DETAIL_TYPE ";
		List<Map<String,Object>> list= getJdbcTemplate().queryForList(sql);
		List<Object[]> typelist=new ArrayList<Object[]>();
		for (Map<String,Object>row:list) {
			Object[] objects={(String)row.get("PLAN_DETAIL_TYPE"),(String)row.get("PLAN_DETAIL_TYPE_NAME")};
			typelist.add(objects);
		}
		return typelist;
	}
	
	public List<Object[]> findregions(String regionId){
		String sql="";
	    if("1".equals(regionId)){
		sql="select distinct REGION_PARENT_ID,REGION_PARENT_NAME from MK_VGOP.TB_CUST_TAG_MARKT_STATIS_DIM_REGION order by REGION_PARENT_ID ";
	    }else{
	    sql="select distinct REGION_PARENT_ID,REGION_PARENT_NAME from MK_VGOP.TB_CUST_TAG_MARKT_STATIS_DIM_REGION where REGION_PARENT_ID like '%"+regionId+"%'order by REGION_PARENT_ID ";	
	    }
		List<Map<String,Object>> list= getJdbcTemplate().queryForList(sql);
		List<Object[]> citylist=new ArrayList<Object[]>();
		for (Map<String,Object>row:list) {
			Object[] objects={(String)row.get("REGION_PARENT_ID"),(String)row.get("REGION_PARENT_NAME")};
			citylist.add(objects);
		}
		return citylist;
	}
	
	public List<Object[]> findcountys(String cityid,String cutyid){
		String sql="";
	   // if(cityid.equals(cutyid)){
		sql="select distinct REGION_ID,REGION_NAME from MK_VGOP.TB_CUST_TAG_MARKT_STATIS_DIM_REGION where REGION_PARENT_ID='"+cityid+"' order by REGION_ID";
	   /* }else{
	    	sql="select distinct REGION_ID,REGION_NAME from MK_VGOP.TB_CUST_TAG_MARKT_STATIS_DIM_REGION where REGION_PARENT_ID='"+cityid+"' and REGION_ID='"+cutyid+"' order by REGION_ID";	
	    }*/
		List<Map<String,Object>> list= getJdbcTemplate().queryForList(sql);
		List<Object[]> coutylist=new ArrayList<Object[]>();
		for (Map<String,Object>row:list) {
			Object[] objects={(String)row.get("REGION_ID"),(String)row.get("REGION_NAME")};
			coutylist.add(objects);
		}
		return coutylist; 
	}
	
	public List<Object[]> findtypelist(String userregionId,String regionId ,String type,int begin ,int end ){
		String sql="";
		if(!"".equals(regionId) && regionId!=null){
			if("1".equals(userregionId)){
			sql="select * from ( select REGION_NAME,PLAN_TYPE,PLAN_CNT,MARKT_SUCC_RATE,ROW_NUMBER() OVER (ORDER BY REGION_ID,MARKT_SUCC_RATE) AS RN from MK_VGOP.TB_CUST_TAG_MARKT_STATIS_D where REGION_ID in ("+regionId+") and PLAN_TYPE like '%"+type+"%') WHERE RN<="+end+" AND RN>="+begin+"";
			}else{
				sql="select * from ( select REGION_NAME,PLAN_TYPE,PLAN_CNT,MARKT_SUCC_RATE,ROW_NUMBER() OVER (ORDER BY REGION_ID,MARKT_SUCC_RATE) AS RN from MK_VGOP.TB_CUST_TAG_MARKT_STATIS_D where REGION_PARENT_ID='"+userregionId+"' and REGION_ID in ("+regionId+") and PLAN_TYPE like '%"+type+"%') WHERE RN<="+end+" AND RN>="+begin+"";	
			}
		}else{
			sql="select * from ( select REGION_NAME,PLAN_TYPE,PLAN_CNT,MARKT_SUCC_RATE,ROW_NUMBER() OVER (ORDER BY REGION_ID,MARKT_SUCC_RATE) AS RN from MK_VGOP.TB_CUST_TAG_MARKT_STATIS_D where REGION_PARENT_ID='"+userregionId+"' and PLAN_TYPE like '%"+type+"%') WHERE RN<="+end+" AND RN>="+begin+"";
		}
		List<Map<String,Object>> list= getJdbcTemplate().queryForList(sql);
		List<Object[]> coutylist=new ArrayList<Object[]>();
		for (Map<String,Object>row:list) {
			Object[] objects={(String)row.get("REGION_NAME"),(String)row.get("PLAN_TYPE"),(Integer)row.get("PLAN_CNT"),(BigDecimal)row.get("MARKT_SUCC_RATE")};
			coutylist.add(objects);
		}
		return coutylist; 
		
	}
	
	public int gettypecount(String userregionid,String regionId,String type){
		String sql="";
		if(!"".equals(regionId) && regionId!=null){
			if("1".equals(userregionid)){
			sql="select count(*) from MK_VGOP.TB_CUST_TAG_MARKT_STATIS_D where  REGION_ID in ("+regionId+") and PLAN_TYPE like '%"+type+"%'";
			}else{
				sql="select count(*) from MK_VGOP.TB_CUST_TAG_MARKT_STATIS_D where REGION_PARENT_ID='"+userregionid+"' and REGION_ID in ("+regionId+") and PLAN_TYPE like '%"+type+"%'";	
			}
		}else{
			sql="select count(*) from MK_VGOP.TB_CUST_TAG_MARKT_STATIS_D where REGION_PARENT_ID='"+userregionid+"' and PLAN_TYPE like '%"+type+"%'";
		}
		 return (int)getJdbcTemplate().queryForInt(sql);
	}
	
	public List<Object[]> findtoplist(String type,int begin,int end){
		String sql="select * from (select PLAN_NAME,REGION_NAME,CREATOR_NAME,PLAN_DETAIL_TYPE,SUCCESS_RATE,RANK_ID,ROW_NUMBER() OVER (ORDER BY PLAN_DETAIL_TYPE,RANK_ID) AS RN from MK_VGOP.TB_CUST_TAG_MARKT_TYPE_SUCCESS_RATE where PLAN_DETAIL_TYPE like '%"+type+"%') WHERE RN<="+end+" AND RN>="+begin+"";
		List<Map<String,Object>> list= getJdbcTemplate().queryForList(sql);
		List<Object[]> coutylist=new ArrayList<Object[]>();
		for (Map<String,Object>row:list) {
			Object[] objects={(String)row.get("PLAN_NAME"),(String)row.get("REGION_NAME"),(String)row.get("CREATOR_NAME"),(String)row.get("PLAN_DETAIL_TYPE"),(BigDecimal)row.get("SUCCESS_RATE"),(Integer)row.get("RANK_ID")};
			coutylist.add(objects);
		}
		return coutylist; 
	}
	
	public int gettopcount(String type){
		String sql="select count(*) from MK_VGOP.TB_CUST_TAG_MARKT_TYPE_SUCCESS_RATE where PLAN_DETAIL_TYPE like '%"+type+"%'";
		return (int)getJdbcTemplate().queryForInt(sql);
	}
        
}
