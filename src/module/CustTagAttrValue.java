package module;
/**
 * �ͻ���ǩ����ֵ
 * @author ywz
 * @date 20130528
 */
public class CustTagAttrValue {
	  private String tag_form_attr;//            varchar(64)                    --��������
	  private String form_attr_value_type;//     varchar(4)                     --��������ֵ���ͣ�1��ֵ��2�����͡�3�Ƿ���ֵ��4������ 
	  private String form_attr_value_order;//    varchar(4)                     --��������ֵ��������
	  private String form_attr_value_betn1;//    varchar(10)                    --�����������䷶Χ(>=,<=,=)
	  private String form_attr_value1;//         decimal(18,4)                  --��������ֵ
	  private String form_attr_value_betn2;//    varchar(10 )                   --�����������䷶Χ(>=,<=,=)
	  private String form_attr_value2;//         decimal(18,4)                  --��������ֵ  
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
	public String getForm_attr_value_order() {
		return form_attr_value_order;
	}
	public void setForm_attr_value_order(String form_attr_value_order) {
		this.form_attr_value_order = form_attr_value_order;
	}
	public String getForm_attr_value_betn1() {
		return form_attr_value_betn1;
	}
	public void setForm_attr_value_betn1(String form_attr_value_betn1) {
		this.form_attr_value_betn1 = form_attr_value_betn1;
	}
	public String getForm_attr_value1() {
		return form_attr_value1;
	}
	public void setForm_attr_value1(String form_attr_value1) {
		this.form_attr_value1 = form_attr_value1;
	}
	public String getForm_attr_value_betn2() {
		return form_attr_value_betn2;
	}
	public void setForm_attr_value_betn2(String form_attr_value_betn2) {
		this.form_attr_value_betn2 = form_attr_value_betn2;
	}
	public String getForm_attr_value2() {
		return form_attr_value2;
	}
	public void setForm_attr_value2(String form_attr_value2) {
		this.form_attr_value2 = form_attr_value2;
	}	  
}
