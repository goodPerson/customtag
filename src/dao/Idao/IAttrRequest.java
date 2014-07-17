package dao.Idao;

import java.util.List;

import module.AttrRequest;

public interface IAttrRequest {
    /**
     * �����������
     * @param attr
     * @return
     */
	public boolean AddAttrRequest(AttrRequest  attr);
	
	/**
	 * �ҵ������б�
	 * @param attr
	 * @return
	 */
	public List<AttrRequest> listAttrR(AttrRequest  attr);
	
	/**
	 * �����������б�
	 * @param attr
	 * @return
	 */
	public List<AttrRequest> listAttrRWait(AttrRequest  attr);
	
	/**
	 * �Ѿ����������б�
	 * @param attr
	 * @return
	 */
	public List<AttrRequest> listAttrRalready(AttrRequest  attr);
	
	/**
	 * ����������������
	 * @param attr
	 * @return
	 */
	public AttrRequest findAttrReq(String reqId);
	/**
	 * ������������
	 * @param attr
	 * @return
	 */
	public boolean updateAttr(AttrRequest  attr);
}
