package aop;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import dao.ArrtClassDaoImpl;
import dao.OperateLogDaoImpl;

/** ��־���� */
public class LogAspectJ {
	// ȡ����־��¼��Logger
  // 	public Logger logger = Logger.getLogger(LogAspectJ.class);
	  private  OperateLogDaoImpl operateLogDao;
      public  void initOperate(){
    	  XmlBeanFactory factory = new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
  		 operateLogDao=(OperateLogDaoImpl) factory.getBean("operateLogDaoImpl");
      }   
	//�˷���������ǰ��֪ͨ
	public void myBeforeAdvice(JoinPoint joinPoint) {
		  this.initOperate();		
		  HttpServletRequest  request=ServletActionContext.getRequest();
		  HttpSession session=request.getSession();
		  SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");   	  
	   	  String clazzName=joinPoint.getTarget().getClass().getName();  //����
	   	  String  userId=(String) session.getAttribute("userId");
	   	  String  userName=(String) session.getAttribute("userName");
	   	  String  regionId=(String) session.getAttribute("regionId");
	   	  String  regionName=(String) session.getAttribute("regionName");
	   	  String methodName=joinPoint.getSignature().getName(); //������
	   	  StringBuffer sb=new StringBuffer();
	   	  Object[] obj=joinPoint.getArgs();
	   	  for(Object o:obj){
	   		  sb.append(o+",");
	   	  }
	   	  String param=sb.toString();  //�������    	  
	   	  String createTime=sdf.format(new Date());    	  
//   	      operateLogDao.addOperateLog(userId, userName, regionId, regionName, methodName, "", createTime,"");
   	      
		 // operateLogDao.addOperateLog("111", "111", "111", "111", "111", "111", "111");
   	     // logger.info("��¼��־");
	}
	
	public OperateLogDaoImpl getOperateLogDao() {
		return operateLogDao;
	}
	public void setOperateLogDao(OperateLogDaoImpl operateLogDao) {
		this.operateLogDao = operateLogDao;
	}
	
}
