package dao.Idao;

import java.util.List;

import module.CustTagCountyD;
import module.CustTagCountyM;

public interface ITagCounty {
	/**
	 * չʾ������ͳ��
	 * @param ctcd
	 * @return
	 */
	public List<CustTagCountyD> findCtcdList(String regionName,String startTime, String endTime ,CustTagCountyD ctcd,int pageNo,int pageSize);
	
	public List<CustTagCountyD> findCtcdList(String regionName,String startTime, String endTime ,CustTagCountyD ctcd);
	/**
	 * չʾ������ͳ��
	 * @param ctcm
	 * @return
	 */
	public List<CustTagCountyM> findCtcmList(String regionName,CustTagCountyM ctcm,int pageNo,int pageSize);
	
	public List<CustTagCountyM> findCtcmList(String regionName,CustTagCountyM ctcm);

}
