package dao.Idao;

import java.util.List;

import module.DownReason;

public interface IDownReason {
	/**
	 * 增加记录
	 * @param dr
	 * @return
	 */
	public boolean add(DownReason dr);
	/**
	 * 下载原因列表
	 * 
	 */
	public List<DownReason> listDr(String tagId,int pageNo, int pageSize);
}
