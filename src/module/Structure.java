package module;
/**
 * 
 * @author ywz
 *@date 20130729
 */
//MK_VGOP.TB_CUST_TAG_FORM_ATTR_SUBS_CNT    MK_VGOP.TB_CUST_TAG_DIM_FORM_ATTR
public class Structure {
	   private String attrId;//                                 --����ID
	   private String attrDimValue;//                               --�������Թ�������ֵ
	   private String regionId;//                                  --����id
	   private Integer subsCnt;//                                        --����
	   private String attrDesc;//                                  --�������� 
	   private String attrDim_desc;//                            --��������
//	   private String attrExtend1;//                                 --��չ1
//	   private String attrExtend2;//                                --��չ2
	   private String attrType;//                                      ��������
	public String getAttrId() {
		return attrId;
	}
	public void setAttrId(String attrId) {
		this.attrId = attrId;
	}
	public String getAttrDimValue() {
		return attrDimValue;
	}
	public void setAttrDimValue(String attrDimValue) {
		this.attrDimValue = attrDimValue;
	}
	public String getRegionId() {
		return regionId;
	}
	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}
	public Integer getSubsCnt() {
		return subsCnt;
	}
	public void setSubsCnt(Integer subsCnt) {
		this.subsCnt = subsCnt;
	}
	public String getAttrDesc() {
		return attrDesc;
	}
	public void setAttrDesc(String attrDesc) {
		this.attrDesc = attrDesc;
	}
	public String getAttrDim_desc() {
		return attrDim_desc;
	}
	public void setAttrDim_desc(String attrDim_desc) {
		this.attrDim_desc = attrDim_desc;
	}
	/*
	public String getAttrExtend1() {
		return attrExtend1;
	}
	public void setAttrExtend1(String attrExtend1) {
		this.attrExtend1 = attrExtend1;
	}
	public String getAttrExtend2() {
		return attrExtend2;
	}
	public void setAttrExtend2(String attrExtend2) {
		this.attrExtend2 = attrExtend2;
	}
	*/
	public String getAttrType() {
		return attrType;
	}
	public void setAttrType(String attrType) {
		this.attrType = attrType;
	}	   
	   
}
