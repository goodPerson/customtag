package module;

import java.math.BigDecimal;

/**
 * �ͻ���ǩ������Ϣ
 * @author ywz
 *@date 20130528
 */
public class CustTagAttrInfo {
	  private String tag_id;//	                  varchar(32)	not null           --��ǩ��ʶ                       
	  private String tag_name;//	                varchar(64)                    --��ǩ����
	  private String tag_form_attr;//            varchar(64)                    --��ǩ��������
	  private String form_attr_value_type;//     varchar(4)                     --��ǩ��������ֵ���ͣ�1��ֵ��2�����͡�3�Ƿ���ֵ��4������ 
	  private String form_attr_value_betn1;//    varchar(10)                    --��ǩ�����������䷶Χ(>=,<=,=)
	  private BigDecimal   form_attr_value1;//         decimal(18,4)                  --��ǩ��������ֵ
	  private String form_attr_value_betn2;//    varchar(10 )                   --��ǩ�����������䷶Χ(>=,<=,=)
	  private BigDecimal  form_attr_value2;//         decimal(18,4)                  --��ǩ��������ֵ 
	  private String attr_name; //��ǩ�����ֶ�
	public String getTag_id() {
		return tag_id;
	}
	public void setTag_id(String tag_id) {
		this.tag_id = tag_id;
	}
	public String getTag_name() {
		return tag_name;
	}
	public void setTag_name(String tag_name) {
		this.tag_name = tag_name;
	}
	public String getTag_form_attr() {
		return tag_form_attr;
	}
	public void setTag_form_attr(String tag_form_attr) {
		this.tag_form_attr = tag_form_attr;
	}
	public String getForm_attr_value_type() {
		return form_attr_value_type;
	}
	public void setForm_attr_value_type(String form_attr_value_type) {
		this.form_attr_value_type = form_attr_value_type;
	}
	public String getForm_attr_value_betn1() {
		return form_attr_value_betn1;
	}
	public void setForm_attr_value_betn1(String form_attr_value_betn1) {
		this.form_attr_value_betn1 = form_attr_value_betn1;
	}

	public String getForm_attr_value_betn2() {
		return form_attr_value_betn2;
	}
	public void setForm_attr_value_betn2(String form_attr_value_betn2) {
		this.form_attr_value_betn2 = form_attr_value_betn2;
	}

	public String getAttr_name() {
		return attr_name;
	}
	public void setAttr_name(String attr_name) {
		this.attr_name = attr_name;
	}
	public BigDecimal  getForm_attr_value1() {
		return form_attr_value1;
	}
	public void setForm_attr_value1(BigDecimal  form_attr_value1) {
		this.form_attr_value1 = form_attr_value1;
	}
	public BigDecimal  getForm_attr_value2() {
		return form_attr_value2;
	}
	public void setForm_attr_value2(BigDecimal  form_attr_value2) {
		this.form_attr_value2 = form_attr_value2;
	}   	
}
