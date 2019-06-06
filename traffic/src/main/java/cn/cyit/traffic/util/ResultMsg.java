package cn.cyit.traffic.util;

import java.util.Map;

public class ResultMsg {
	private int errorCode=0;//0成功，1错误
	private String message="";
	private Map<String, Object> attributes=null;// 其他参数

	public ResultMsg(int code){
		errorCode=code;
	}
	public ResultMsg(int code,String msg){
		errorCode=code;
		message=msg;
	}
	public ResultMsg(int code,String msg,Map<String, Object> map){
		errorCode=code;
		message=msg;
		attributes=map;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public String getMessage() {
		return message;
	}
	public Map<String, Object> getAttributes() {
		return attributes;
	}
}
