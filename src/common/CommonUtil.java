package common;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class CommonUtil {
	/**
	 * �����ַ���ʽ��
	 * ȥ�ո�
	 * @param reqString
	 * @throws UnsupportedEncodingException 
	 */
	public static String requestFormat(String reqString) throws UnsupportedEncodingException{
		String str=reqString;
		str=URLDecoder.decode(str, "utf-8").trim();		
		return str;
	}

}
