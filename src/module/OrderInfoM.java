package module;
/**
 * ������
 * @author ywz
 *@date 20130716
 */
public class OrderInfoM {
	private String  sameMonth;// ����	varchar(64),
	private Integer  sameMonthCount;// ����ͳ������	integer,
	private String  orderType; // ͳ������   ���� , ��ǩ,�����û��������û�	varchar(50),
	private String  orderName;// ͳ������	varchar(50),
	private Integer  beforeMonthCount;// ����ͳ�� 	integer
	
	public String getSameMonth() {
		return sameMonth;
	}
	public void setSameMonth(String sameMonth) {
		this.sameMonth = sameMonth;
	}
	public Integer getSameMonthCount() {
		return sameMonthCount;
	}
	public void setSameMonthCount(Integer sameMonthCount) {
		this.sameMonthCount = sameMonthCount;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public String getOrderName() {
		return orderName;
	}
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	public Integer getBeforeMonthCount() {
		return beforeMonthCount;
	}
	public void setBeforeMonthCount(Integer beforeMonthCount) {
		this.beforeMonthCount = beforeMonthCount;
	}
	
}
