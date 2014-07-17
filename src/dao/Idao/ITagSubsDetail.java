package dao.Idao;

import java.util.List;
import java.util.Map;

import module.TagSubsDayDetail;
import module.TagSubsMonthDetail;

public interface ITagSubsDetail {
	/**
	 * ��ȡ���û�ʹ����ϸ��ͳ��
	 * @param tsmd
	 * @return
	 */
	public List<TagSubsMonthDetail> getListTsmd(String regionName,TagSubsMonthDetail tsmd,int pageNo,int pageSize);
	
	public List<TagSubsMonthDetail> getListTsmd(String regionName,TagSubsMonthDetail tsmd);
	/**
	 * ��ȡ���û�ʹ����ϸ��ͳ��
	 * @param tsdd
	 * @return
	 */
	public List<TagSubsDayDetail> getListTsdd(String regionName,String startTime, String endTime ,TagSubsDayDetail tsdd,int pageNo,int pageSize);
	
	public List<TagSubsDayDetail> getListTsdd(String regionName,String startTime, String endTime ,TagSubsDayDetail tsdd);
	
	/**
	 * ��ȡ���û�����
	 * @param regionName
	 * @return
	 */
	public List<Map<String, Object> >getUserName(String regionName);
	
	/**
	 * ��ȡ����������
	 * @param countyName
	 * @return
	 */
	public List<Map<String, Object>> getCountyName(String countyName);
	
	/**
	 * ��ȡ���û�����
	 * @param regionName
	 * @return
	 */
	public List<Map<String, Object> >getUserNameD(String regionName);
	
	/**
	 * ��ȡ����������
	 * @param countyName
	 * @return
	 */
	public List<Map<String, Object>> getCountyNameD(String countyName);

}
