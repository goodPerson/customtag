package action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import dao.TagInfoDaoImpl;
import dao.TypeUserInfoDaoImpl;
import dao.UploadLtLogDaoImpl;

import module.TypeUserInfo;

public class TypeUserInfoAction {
	   private static TypeUserInfoDaoImpl typeUserInfoDao;
	   private static  TagInfoDaoImpl customTagDao;
	   private static UploadLtLogDaoImpl upLoadLtLogDao;
	   private boolean flag=false;
	//�ļ����������ʼ
		private String path;//������ļ���·��
		private String fileName = "";
		private String dirPath = "D:/customTag/";
		private File file;
		String imsg="";
		private final static int BUFFER_SIZE = 1024 * 1024;
		//�ļ������������		
		
	    static ApplicationContext factory=null;
		
		public void initTypeUserInfo(){
			XmlBeanFactory factory=new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
			typeUserInfoDao=(TypeUserInfoDaoImpl) factory.getBean("typeUserInfoDaoImpl");
		}
		public void initUploadLt(){
			XmlBeanFactory factory=new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
			upLoadLtLogDao=(UploadLtLogDaoImpl) factory.getBean("uploadLtLogDaoImpl");
		}
		/*
		 * ��������
		 */
		private static void copy(File src, File dst) {
			try {
				InputStream in = null;
				OutputStream out = null;
				try {
					in = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE);

					out = new BufferedOutputStream(new FileOutputStream(dst), BUFFER_SIZE);
					byte[] buffer = new byte[BUFFER_SIZE];
					while (true) {
						int temp = in.read(buffer);
						if (temp == -1)
							break;
						out.write(buffer, 0, temp);
					}
				} finally {
					if (null != in) {
						in.close();
					}
					if (null != out) {
						out.close();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		/**���빤��
		 * @throws IOException */
		@SuppressWarnings("deprecation")
		public String importUser() throws IOException {
			if (null==typeUserInfoDao)
			this.initTypeUserInfo();
			if (null==upLoadLtLogDao)
			this.initUploadLt();
			int countSucess=0;
			int countFail=0;
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();		
			response.setCharacterEncoding("gbk");
			request.setCharacterEncoding("gbk");
			response.addHeader("Content-Type", "text/html;charset=gbk");
//			path=URLDecoder.decode(request.getParameter("path"),"utf-8");		
	        //�ļ�����
//			if (fileName == null || fileName.trim().length()==0) {
			fileName = URLDecoder.decode(fileName, "gbk");
			//fileName = URLDecoder.decode(request.getParameter("fileName"), "gbk");
		    int lastPosi=fileName.lastIndexOf(".");
			String fileNameNew=fileName.substring(lastPosi);
//			}
			//���ֹ���
           String user_id=request.getParameter("user_id");
           SimpleDateFormat dateformat1=new SimpleDateFormat("yyyyMMddHHmmss");
           String load_time=dateformat1.format(new Date());

           //�ϴ�ʱ��
//           String load_time=sdf.format(new Date());
           String type=request.getParameter("list_type");
           //��������
           String list_type=URLDecoder.decode(type, "utf-8");
           //��ǩId
           String tag_id=request.getParameter("tag_id");
           //String tag_id=URLDecoder.decode(r_tag_id, "gbk");
           //��ǩ����
           String tag_name=request.getParameter("tag_name");
           
			File uploadDir = new File(dirPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			String uploadFileName = new Date().getTime() +""+ fileNameNew;
			
			File uploadFile = new File(dirPath, uploadFileName);
			if (!uploadFile.exists()) {
				try {
					uploadFile.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		
		if (file != null) {
			copy(file, uploadFile);// �ϴ�excel�ļ�
			}
		  //�ϴ��ɹ����޸ı�ǩ
		   //customTagDao.upTag(tag_id, "4", list_type, uploadFileName);
		if (upLoadLtLogDao.addUploadLt(tag_id, list_type, uploadFileName, "0")){
			flag=true;
			return "importUser";
		}
		   return "importUserFalse";
		}
		public String getFileName() {
			return fileName;
		}
		public void setFileFileName(String fileName) {
			this.fileName = fileName;
		}
		public File getFile() {
			return file;
		}
		public void setFile(File file) {
			this.file = file;
		}
		public TypeUserInfoDaoImpl getTypeUserInfoDao() {
			return typeUserInfoDao;
		}
		public void setTypeUserInfoDao(TypeUserInfoDaoImpl typeUserInfoDao) {
			this.typeUserInfoDao = typeUserInfoDao;
		}
		public TagInfoDaoImpl getCustomTagDao() {
			return customTagDao;
		}
		public void setCustomTagDao(TagInfoDaoImpl customTagDao) {
			this.customTagDao = customTagDao;
		}
		public UploadLtLogDaoImpl getUpLoadLtLogDao() {
			return upLoadLtLogDao;
		}
		public void setUpLoadLtLogDao(UploadLtLogDaoImpl upLoadLtLogDao) {
			this.upLoadLtLogDao = upLoadLtLogDao;
		}
		
}
