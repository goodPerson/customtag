package test;

import net.sf.json.JSONObject;

public class JsonToString {
	
	public static void main (String[] args){
		String test="{name:'lyk',pwd:'12345'}";
		JSONObject AA =JSONObject.fromObject(test);
		String name=AA.getString("pwd");
		System.out.println(name);
	}

}
