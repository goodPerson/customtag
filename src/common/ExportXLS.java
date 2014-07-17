package common;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import module.CustTagAndGroupMonitorM;



public class ExportXLS<T> {
	protected   T t;
	/**��������������ϸ��Ϣ����Ҫ�Ĳ��� */
	protected String contentType;//��������
	protected FileInputStream downloadFile;//
	protected String fileName;//�ļ���
   
	/*
	public ExportXLS(T t){
		this.t=t;
	}
	*/
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public  void exportMonthPiece( List l,String coluns,String title ) throws Exception{
		String  exportType = "xls";
		// �����ͷ
//		String header ="���� ���� ���� Ա����� �������� �������� �������� �Ƽ���Ŀ�������� �Ƽ���ĿС������ �·�չ�� �·�չ���� �·�չн��  "
		String header = coluns;
		// ��ȡ����
//		List l = exportBasicInfoMList;
	//	List<TBMangerPieceSmallMonth> l=monthservice.findMagerPieceMonthNoPage(statisDate, countyName, name, 
	//			                                                               id, prncpChnlId, prncpChnlName);
		List<T> a=l;
		List<Object[]> datalist = new ArrayList<Object[]>();
		for (int i = 0; i < a.size(); i++) {
			T nt = (T) a.get(i);
			Gen<T> g=new Gen<T>(nt);
			
			
			Object[] obj =g.showTypeName() ;
			 
			datalist.add(obj);
		}
		FileInputStream result = PrintExcelUtil.getDownloadFile(exportType,
				title, title, header, datalist);
		contentType = "application/vnd.ms-excel;charset=utf-8";
		downloadFile = result;
		fileName = title+".xls";
		fileName = new String(fileName.getBytes(), "ISO8859-1");
		//return "monthDownLoad" ;		
	}
	/*
	public static void main(String[] args) throws Exception{
		System.out.println("dd");
		String header ="���� ���� ���� Ա����� �������� �������� �������� �Ƽ���Ŀ�������� �Ƽ���ĿС������ �·�չ�� �·�չ���� �·�չн��  ";
		String title="����";
		CustTagAndGroupMonitorM ctagm=new CustTagAndGroupMonitorM();
		ctagm.setAccessCnt(25);
		List<CustTagAndGroupMonitorM> lc=new ArrayList<CustTagAndGroupMonitorM>();
		int i=0;
		while(i<3){
			lc.add(ctagm);
			i++;
		}
		//ExportXLS<CustTagAndGroupMonitorM> e=new ExportXLS<CustTagAndGroupMonitorM>(ctagm);
		ExportXLS<CustTagAndGroupMonitorM> e=new ExportXLS();
		e.exportMonthPiece( lc, header, title);
		
	}
	*/
	public T getT() {
		return t;
	}
	public void setT(T t) {
		this.t = t;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public FileInputStream getDownloadFile() {
		return downloadFile;
	}
	public void setDownloadFile(FileInputStream downloadFile) {
		this.downloadFile = downloadFile;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
   
	
}
