package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import module.DownReason;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import common.Pagination;

import dao.Idao.IDownReason;

public class DownReasonImpl extends JdbcDaoSupport implements IDownReason {
	private Pagination pageInfo;
	public boolean add(DownReason dr) {
		// TODO Auto-generated method stub
		boolean upFlag=false;
		String sql="insert into MK_VGOP.TB_DOWNLOAD_LOG (TAG_ID,	TAG_NAME,	USER_ID,USER_NAME ,REGION_ID ,REGION_NAME,CREATE_TIME,DOWN_REASON )"
				      +" values ('"+dr.getTagId()+"','"+dr.getTagName()+"','"+dr.getUserId()+"','"+dr.getUserName()+"','"+dr.getRegionId()+"','"+dr.getRegionName()+"','"+dr.getCreateTime()+"','"+dr.getDownReason()+"')";
		if (this.getJdbcTemplate().update(sql)>0){
			upFlag=true;
		};
		return upFlag;
	}

	public List<DownReason> listDr(String tagId,int pageNo, int pageSize) {
		String sql="select TAG_ID,	TAG_NAME,	USER_ID,USER_NAME ,REGION_ID ,REGION_NAME,CREATE_TIME,DOWN_REASON,rownumber() over(ORDER BY create_time desc ) AS rn  from MK_VGOP.TB_DOWNLOAD_LOG"
				      +" where TAG_ID='"+tagId+"'";
		pageInfo=new Pagination(sql,pageNo,pageSize,getJdbcTemplate());
		List<Map<String,Object>> listTemp=pageInfo.getResultList();
		List<DownReason> listDr=new ArrayList<DownReason>();
		for(Map<String,Object> obj:listTemp){
			DownReason dr=new DownReason();
			dr.setTagId(tagId);
			dr.setTagName((String)obj.get("TAG_NAME"));
			dr.setUserId((String)obj.get("USER_ID"));
			dr.setUserName((String)obj.get("USER_NAME"));
			dr.setRegionId((String)obj.get("REGION_ID"));
			dr.setRegionName((String)obj.get("REGION_NAME"));
			dr.setCreateTime((String)obj.get("CREATE_TIME"));
			dr.setDownReason((String)obj.get("DOWN_REASON"));
			listDr.add(dr);
		}
		return listDr;
	}

	public Pagination getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(Pagination pageInfo) {
		this.pageInfo = pageInfo;
	}

}
