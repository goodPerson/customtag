package common;


import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class Pagination extends JdbcDaoSupport {
	public static final int NUMBERS_PER_PAGE = 10;

  //һҳ��ʾ�ļ�¼��
	private int numPerPage;
	//��¼����
	private int totalRows;
	//��ҳ��
	private int totalPages;
	//��ǰҳ��
	private int currentPage;
	//��ʼ����
	private int startIndex;
	//��������
	private int lastIndex;
	//��������List
	private List resultList;
	//JdbcTemplate jTemplate
	private JdbcTemplate jTemplate;

     /**
	����* ÿҳ��ʾ10����¼�Ĺ��캯��,ʹ�øú��������ȸ�Pagination����currentPage��jTemplate��ֵ
	����* @param sql oracle���
	����*/

	public Pagination(String sql){

		if( jTemplate==null){
			throw new IllegalArgumentException("com.deity.ranking.util.Pagination.jTemplate is null,please initial it first. ");

		}else if(sql.equals("")){

			throw new IllegalArgumentException("com.deity.ranking.util.Pagination.sql is empty,please initial it first. ");
		}

		new Pagination(sql,currentPage,NUMBERS_PER_PAGE,jTemplate);
	}

	/**��ҳ���캯��
	����* @param sql ���ݴ����sql���õ�һЩ������ҳ��Ϣ
	����* @param currentPage ��ǰҳ
	����* @param numPerPage ÿҳ��¼��
	����* @param jTemplate JdbcTemplateʵ��
	����*/

	public Pagination(String sql,int currentPage,int numPerPage,JdbcTemplate jTemplate){

	if(jTemplate == null){

	throw new IllegalArgumentException("com.deity.ranking.util.Pagination.jTemplate is null,please initial it first. ");

	}else if(sql == null || sql.equals("")){

	throw new IllegalArgumentException("com.deity.ranking.util.Pagination.sql is empty,please initial it first. ");

	}

	//����ÿҳ��ʾ��¼��
	setNumPerPage(numPerPage);
	//����Ҫ��ʾ��ҳ��
	setCurrentPage(currentPage);
	//�����ܼ�¼��
	StringBuffer totalSQL = new StringBuffer(" SELECT count(*) FROM ( ");
	totalSQL.append(sql);
	totalSQL.append(" ) totalTable ");
	//��JdbcTemplate��ֵ
	setJdbcTemplate(jTemplate);
	//�ܼ�¼��
	setTotalRows(getJdbcTemplate().queryForInt(totalSQL.toString()));
	//������ҳ��
	setTotalPages();
	//������ʼ����
	setStartIndex();
	//�����������
	setLastIndex();
	//System.out.println("lastIndex="+lastIndex);//////////////////
	//����oracle���ݿ�ķ�ҳ���
	
	/*SELECT * FROM (
			select tag_id,tag_name,tag_applyer,create_time,tag_examiner,tag_treater,tag_state ,rownumber() over(ORDER BY tag_state desc) AS rn 
			from mk_vgop.tb_tag_approve where (tag_applyer_id ='008' or tag_examiner_id='008') and tag_state in('0','1')
			) as a1 where rn BETWEEN 10 AND 15
			*/
	StringBuffer paginationSQL = new StringBuffer(" SELECT * FROM ( ");
	//paginationSQL.append(" SELECT temp.*  FROM ( ");
	paginationSQL.append(sql);
	//paginationSQL.append(" ) as a1 where a1.rn BETWEEN "+startIndex+" AND "+lastIndex+"");
	paginationSQL.append(" ) as a1 where a1.rn > "+startIndex+" AND  a1.rn <="+lastIndex+"");
	//paginationSQL.append(" fetch first "+ numPerPage +" rows only");

	//paginationSQL.append(" ) WHERE	����num > " + startIndex);
	//װ������
	setResultList(getJdbcTemplate().queryForList(paginationSQL.toString()));
	}
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getNumPerPage() {
	return numPerPage;
	}

	public void setNumPerPage(int numPerPage) {
	this.numPerPage = numPerPage;
	}

	public List getResultList() {
	return resultList;
	}

	public void setResultList(List resultList) {
	this.resultList = resultList;
	}

	public int getTotalPages() {
	return totalPages;
	}

	//������ҳ��

	public void setTotalPages() {

		if(totalRows % numPerPage == 0){	
			this.totalPages = totalRows / numPerPage;	
		}else{	
			this.totalPages	= (totalRows / numPerPage)+1;
		}
	}

	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex() {
		this.startIndex = (currentPage - 1) * numPerPage;
	}

	public int getLastIndex() {
	return lastIndex;
	}

	public JdbcTemplate getJTemplate() {
	return jTemplate;
	}

	public void setJTemplate(JdbcTemplate template) {
	jTemplate = template;
	}

	//�������ʱ�������

	public void setLastIndex(){
		if( totalRows < numPerPage){
			this.lastIndex = totalRows;
		}else if((totalRows % numPerPage == 0) || (totalRows % numPerPage != 0 && currentPage < totalPages)){
			this.lastIndex = currentPage * numPerPage;
		}else if(totalRows % numPerPage != 0 && currentPage == totalPages){//���һҳ
			this.lastIndex = totalRows;
		}
	}
//���ҵ�ҵ���߼������У�

/**
	����* find season ranking list from DC
	����* @param areaId ѡ������id
	����* @param rankDate ����
	����* @param category ���
	����* @param characterName ��ɫ��
	����* @return List

	����*/

public List findSeasonRankingList(Long areaId, int rankYear,int rankMonth,
			Long categoryId,String characterName) {
	//SQL���
	StringBuffer sql = new StringBuffer(" SELECT C.USERID userid,D.POSNAME posname,C.GAMEID gameid,C.AMOUNT amount,C.RANK rank FROM ");
	//��
	sql.append(" (SELECT B.USERID USERID,");
	sql.append(" B.POSID POSID,");	
	sql.append(" A.DISTRICT_CODE DISTRICTCODE,");
	sql.append(" A.GAMEID GAMEID,");
	sql.append(" AMOUNT AMOUNT,");
	sql.append(" RANK RANK ");
	sql.append(" FROM TB_FS_RANK A ");
	sql.append(" LEFT JOIN TB_CHARACTER_INFO B ");
	sql.append(" ON A.DISTRICT_CODE = B.DISTRICT_CODE ");
	sql.append(" AND A.GAMEID = B.GAMEID ");
	//��������
	if(areaId != null && areaId.intValue() != 0){
	sql.append(" and A.DISTRICT_CODE = " + areaId.intValue());
	}
//	if( rankYear > 1Array70 && rankMonth > 0){
	if( rankYear > 1 && rankMonth > 0){
	//hql.append(" and sas.id.dt >= to_date(��" + rankYear + "-" + rankMonth + "-01 00:00:00��," + "YYYY-MM-DD HH24:MI:SS");
	//hql.append(" and sas.id.dt <= to_date(��" + rankYear + "-" + rankMonth + "-" + TimeTool.findMaxDateInMonth(rankYear,rankMonth) + " 23:5Array:5Array��," + "YYYY-MM-DD HH24:MI:SS");
	sql.append(" and A.DT = fn_time_convert(to_date(��" + rankYear + "-" + rankMonth + "��," + "��YYYY-MM��)) ");
	}
	if(categoryId != null && categoryId.intValue() != 0){
	sql.append(" and A.CID = " + categoryId.intValue());
	}
	if(characterName != null && !characterName.trim().equals("")){
	sql.append(" and A.GAMEID = ��" + characterName.trim()+"�� ");
	}
	sql.append(" ORDER BY RANK ASC) C ");
	sql.append(" LEFT JOIN TB_FS_POSITION D ");
	sql.append(" ON C.POSID = D.POSID ");
	sql.append(" ORDER BY C.RANK ");
	//System.out.println("hql="+sql.toString());////////////////
	//ʹ���Լ��ķ�ҳ������ƽ����
	Pagination pageInfo = new Pagination(sql.toString(),1,10,getJdbcTemplate());
	return pageInfo.getResultList();
}}
