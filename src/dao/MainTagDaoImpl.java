package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import module.Attr;
import module.CustomTag;
import module.MainTag;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class MainTagDaoImpl extends JdbcDaoSupport{
	
	public List<MainTag> listTag_bak(int serv_type,String serv_name){
		
		String sql="select tag_id,tag_name,tag_type,tag_serv_type,tag_class,create_time,end_time,profile,count_subs,tag_statement,tag_creator,tag_region,tag_status,tag_url,is_used  from MK_VGOP.TB_CUSTOMTAG_INFO  ";		
		switch(serv_type)
		{
		case 1: //ҵ����
			//if(serv_name.equals("GPRS����")){
            // sql+=" where tag_type='GPRS�ײ�'  order by tag_class,is_share";}
			//else{sql+=" where tag_type='��ֵҵ����' and TAG_SERV_TYPE='"+serv_name+"'  order by tag_class,is_share";}
			sql+=" where tag_type='ҵ������' and TAG_SERV_TYPE='"+serv_name+"'  order by tag_class,is_share";
			break;
		case 6://ƫ����
			sql+="where TAG_TYPE='���Ѽ�ֵ����' order by TAG_SERV_TYPE,is_share";
			break;
		case 2://�¼���
			sql+="where TAG_TYPE='����ƫ��' order by TAG_SERV_TYPE,is_share";
			break;
		case 3://�ն���
			sql+="where TAG_TYPE='�ն�ƫ��'  order by TAG_SERV_TYPE,is_share";
			break;
		case 4://�����
			sql+="where TAG_TYPE='����������ƫ��'  order by TAG_SERV_TYPE,is_share";
			break;	
		case 5://��ֵ��
			sql+="where TAG_TYPE='����ƫ��'  order by TAG_SERV_TYPE,is_share";
			break;			
		case 0://����Ȧ��
			sql+="where TAG_TYPE='��������'  order by TAG_SERV_TYPE,is_share";
			break;	
		case 7://������
			sql+="where TAG_TYPE='Ӫ���ƫ��'  order by TAG_SERV_TYPE,is_share";
			break;	
		case 8://λ����
			sql+="where TAG_TYPE='��������'  order by TAG_SERV_TYPE,is_share";
			break;			
			
		}
			List<Map<String,Object>> list= this.getJdbcTemplate().queryForList(sql);
			List<MainTag> tagList= new ArrayList<MainTag>();
			for(Map<String,Object> row:list){
				MainTag tag= new MainTag();
				tag.setTag_id((String)row.get("tag_id"));
				tag.setTag_name((String)row.get("tag_name"));
				tag.setTag_type((String)row.get("tag_type"));
				tag.setTag_serv_type((String)row.get("tag_serv_type"));
				tag.setTag_class((String)row.get("tag_class"));
				tag.setCreate_time((String)row.get("create_time"));
				tag.setEnd_time((String)row.get("end_time"));
				tag.setProfile((String)row.get("profile"));
				tag.setCount_subs((Integer)row.get("count_subs")); 
				tag.setTag_creator((String)row.get("tag_creator"));
				tag.setTag_statement((String)row.get("tag_statement"));
				tag.setTag_region((String)row.get("tag_region"));
				tag.setIs_share((String)row.get("is_share"));
				tag.setTag_url((String)row.get("tag_url"));
				tag.setIs_used((String)row.get("is_used"));
				tagList.add(tag);
		}
			return tagList;
	}
	
public List<MainTag> listTag(int serv_type,String serv_name,String regionId){
		
		String sql="select a.tag_id,a.tag_name,a.tag_type,a.tag_serv_type,a.tag_class,a.create_time,a.end_time,"
                          +" a.profile,a.count_subs,a.tag_statement,a.tag_creator,a.tag_region,a.tag_status,a.tag_url,a.is_used,"
			              +" b.SUBS_CNT,b.REGION_NAME,b.PARENT_REGION_ID,b.REGION_ID"
                          +" from MK_VGOP.TB_CUSTOMTAG_INFO a, MK_VGOP.TB_CUST_TAG_SUBS_NUM_MON b where a.TAG_ID=b.TAG_ID ";		
//		and b.PARENT_REGION_ID='1' and a.TAG_ID='IS_TX_SILENT_USER';
		switch(serv_type)
		{
		case 1: //ҵ����
			//if(serv_name.equals("GPRS����")){
            // sql+=" where tag_type='GPRS�ײ�'  order by tag_class,is_share";}
			//else{sql+=" where tag_type='��ֵҵ����' and TAG_SERV_TYPE='"+serv_name+"'  order by tag_class,is_share";}
			sql+=" and a.tag_type='ҵ������' and a.TAG_SERV_TYPE='"+serv_name+"' and b.REGION_ID='"+ regionId +"'  order by a.tag_class,a.is_share";
			break;
		case 6://ƫ����
			sql+="and a.TAG_TYPE='���Ѽ�ֵ����' and b.REGION_ID='"+ regionId +"'  order by a.TAG_SERV_TYPE,a.is_share";
			break;
		case 2://�¼���
			sql+="and a.TAG_TYPE='����ƫ��' and b.REGION_ID='"+ regionId +"' order by a.TAG_SERV_TYPE,a.is_share";
			break;
		case 3://�ն���
			sql+="and a.TAG_TYPE='�ն�ƫ��' and b.REGION_ID='"+ regionId +"'  order by a.TAG_SERV_TYPE,a.is_share";
			break;
		case 4://�����
			sql+="and a.TAG_TYPE='����������ƫ��' and b.REGION_ID='"+ regionId +"'  order by a.TAG_SERV_TYPE,a.is_share";
			break;	
		case 5://��ֵ��
			sql+="and a.TAG_TYPE='����ƫ��' and b.REGION_ID='"+ regionId +"'  order by a.TAG_SERV_TYPE,a.is_share";
			break;			
		case 0://����Ȧ��
			sql+="and a.TAG_TYPE='��������' and b.REGION_ID='"+ regionId +"'  order by a.TAG_SERV_TYPE,a.is_share";
			break;	
		case 7://������
			sql+="and TAG_TYPE='Ӫ���ƫ��' and b.REGION_ID='"+ regionId +"'  order by a.TAG_SERV_TYPE,a.is_share";
			break;	
		case 8://λ����
			sql+="and a.TAG_TYPE='��������' and b.REGION_ID='"+ regionId +"'  order by a.TAG_SERV_TYPE,a.is_share";
			break;			
			
		}
			List<Map<String,Object>> list= this.getJdbcTemplate().queryForList(sql);
			List<MainTag> tagList= new ArrayList<MainTag>();
			for(Map<String,Object> row:list){
				MainTag tag= new MainTag();
				tag.setTag_id((String)row.get("tag_id"));
				tag.setTag_name((String)row.get("tag_name"));
				tag.setTag_type((String)row.get("tag_type"));
				tag.setTag_serv_type((String)row.get("tag_serv_type"));
				tag.setTag_class((String)row.get("tag_class"));
				tag.setCreate_time((String)row.get("create_time"));
				tag.setEnd_time((String)row.get("end_time"));
				tag.setProfile((String)row.get("profile"));
				tag.setCount_subs((Integer)row.get("count_subs")); 
				tag.setTag_creator((String)row.get("tag_creator"));
				tag.setTag_statement((String)row.get("tag_statement"));
				tag.setTag_region((String)row.get("tag_region"));
				tag.setIs_share((String)row.get("is_share"));
				tag.setTag_url((String)row.get("tag_url"));
				tag.setIs_used((String)row.get("is_used"));
				tag.setSubsCnt((Integer)row.get("SUBS_CNT"));
				tagList.add(tag);
		}
			return tagList;
	}

public List<MainTag> findListTag(int serv_type,String serv_name,String regionId,String tag_name){
	
	String sql="select a.tag_id,a.tag_name,a.tag_type,a.tag_serv_type,a.tag_class,a.create_time,a.end_time,"
                      +" a.profile,a.count_subs,a.tag_statement,a.tag_creator,a.tag_region,a.tag_status,a.tag_url,a.is_used,"
		              +" b.SUBS_CNT,b.REGION_NAME,b.PARENT_REGION_ID,b.REGION_ID"
                      +" from MK_VGOP.TB_CUSTOMTAG_INFO a, MK_VGOP.TB_CUST_TAG_SUBS_NUM_MON b where a.TAG_ID=b.TAG_ID ";		
//	and b.PARENT_REGION_ID='1' and a.TAG_ID='IS_TX_SILENT_USER';
	if ("".equals(tag_name)){
		switch(serv_type)
		{
		case 1: //ҵ����
			//if(serv_name.equals("GPRS����")){
	        // sql+=" where tag_type='GPRS�ײ�'  order by tag_class,is_share";}
			//else{sql+=" where tag_type='��ֵҵ����' and TAG_SERV_TYPE='"+serv_name+"'  order by tag_class,is_share";}
			sql+=" and a.tag_type='ҵ������' and a.TAG_SERV_TYPE='"+serv_name+"'  ";
			break;
		case 6://ƫ����
			sql+="and a.TAG_TYPE='���Ѽ�ֵ����'   ";
			break;
		case 2://�¼���
			sql+="and a.TAG_TYPE='����ƫ��'  ";
			break;
		case 3://�ն���
			sql+="and a.TAG_TYPE='�ն�ƫ��'   ";
			break;
		case 4://�����
			sql+="and a.TAG_TYPE='����������ƫ��'   ";
			break;	
		case 5://��ֵ��
			sql+="and a.TAG_TYPE='����ƫ��'   ";
			break;			
		case 0://����Ȧ��
			sql+="and a.TAG_TYPE='��������'   ";
			break;	
		case 7://������
			sql+="and TAG_TYPE='Ӫ���ƫ��'  ";
			break;	
		case 8://λ����
			sql+="and a.TAG_TYPE='��������' ";
			break;			
			
		}
	}
	
	   if (!"".equals(tag_name)){
		  sql+=" and a.TAG_NAME like '%"+ tag_name +"%'";
	   }
	   sql+=" and b.REGION_ID='"+ regionId +"' order by a.TAG_SERV_TYPE,a.tag_class,a.is_share";
		List<Map<String,Object>> list= this.getJdbcTemplate().queryForList(sql);
		List<MainTag> tagList= new ArrayList<MainTag>();
		for(Map<String,Object> row:list){
			MainTag tag= new MainTag();
			tag.setTag_id((String)row.get("tag_id"));
			tag.setTag_name((String)row.get("tag_name"));
			tag.setTag_type((String)row.get("tag_type"));
			tag.setTag_serv_type((String)row.get("tag_serv_type"));
			tag.setTag_class((String)row.get("tag_class"));
			tag.setCreate_time((String)row.get("create_time"));
			tag.setEnd_time((String)row.get("end_time"));
			tag.setProfile((String)row.get("profile"));
			tag.setCount_subs((Integer)row.get("count_subs")); 
			tag.setTag_creator((String)row.get("tag_creator"));
			tag.setTag_statement((String)row.get("tag_statement"));
			tag.setTag_region((String)row.get("tag_region"));
			tag.setIs_share((String)row.get("is_share"));
			tag.setTag_url((String)row.get("tag_url"));
			tag.setIs_used((String)row.get("is_used"));
			tag.setSubsCnt((Integer)row.get("SUBS_CNT"));
			tagList.add(tag);
	}
		return tagList;
}
	/*��ȡ�����������ص�����*/
	public List<MainTag> listRegionData(String regionId, String tagId){
		String sql="select 	b.SUBS_CNT,b.REGION_NAME,b.REGION_ID,b.TAG_ID,a.tag_name"
                          +" from MK_VGOP.TB_CUSTOMTAG_INFO a, MK_VGOP.TB_CUST_TAG_SUBS_NUM_MON b" 
                          +" where a.TAG_ID=b.TAG_ID and b.PARENT_REGION_ID='"+ regionId +"' and a.TAG_ID='"+ tagId +"' order by b.SUBS_CNT desc";
		List<Map<String,Object>> listTemp=this.getJdbcTemplate().queryForList(sql);
		List<MainTag> listRegionData=new ArrayList<MainTag>();
		for(Map<String,Object> obj: listTemp){
			MainTag mt=new MainTag();
			mt.setRegionName((String)obj.get("REGION_NAME"));
			mt.setSubsCnt((Integer)obj.get("SUBS_CNT"));
			mt.setRegionId((String)obj.get("REGION_ID"));
			mt.setTag_id((String)obj.get("TAG_ID"));
			mt.setTag_name((String)obj.get("tag_name"));
			listRegionData.add(mt);
		}
		
		return listRegionData;		
	}
	
	public int getMonth(){
		String sql="select DATE_VALUE from  MK_VGOP.TB_CUV_CUST_VIEW_SHOW_DATE where DATE_TYPE='��'";
		return (int)getJdbcTemplate().queryForInt(sql) ;
	}
		
	/*���ұ�ǩ����*/
	public List<CustomTag>	findTag(String tagName){
		String sql="select TAG_NAME from mk_vgop.tb_customtag_info  where TAG_NAME like  '%"+tagName+"%' with ur";
		List<Map<String, Object>> ltemp=this.getJdbcTemplate().queryForList(sql);
		List<CustomTag> listCust=new ArrayList<CustomTag>();
		for(Map<String,Object> obj : ltemp){
			 CustomTag  ctg=new CustomTag();
			 ctg.setTag_name((String)obj.get("TAG_NAME"));
			 listCust.add(ctg);
		}
		return listCust;
		
	}


}
