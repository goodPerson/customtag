package dao.Idao;

import java.util.List;

import module.DownReason;

public interface IDownReason {
	/**
	 * ���Ӽ�¼
	 * @param dr
	 * @return
	 */
	public boolean add(DownReason dr);
	/**
	 * ����ԭ���б�
	 * 
	 */
	public List<DownReason> listDr(String tagId,int pageNo, int pageSize);
}
