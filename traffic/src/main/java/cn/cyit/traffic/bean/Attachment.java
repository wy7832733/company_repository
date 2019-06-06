package cn.cyit.traffic.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *上传文件 
 * @author an
 *
 */

public class Attachment implements Serializable{
	private static final long serialVersionUID = 1L;
	private int attach_id;
	private String attach_name;
	private int user_id;
	private String attach_type;
	private String real_Path;
	private String attach_time;
	
	public String getAttach_time() {
		return attach_time;
	}
	public void setAttach_time(String attach_time) {
		this.attach_time = attach_time;
	}
	public int getAttach_id() {
		return attach_id;
	}
	public void setAttach_id(int attach_id) {
		this.attach_id = attach_id;
	}
	public String getAttach_name() {
		return attach_name;
	}
	public void setAttach_name(String attach_name) {
		this.attach_name = attach_name;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getAttach_type() {
		return attach_type;
	}
	public void setAttach_type(String attach_type) {
		this.attach_type = attach_type;
	}
	public String getReal_Path() {
		return real_Path;
	}
	public void setReal_Path(String real_Path) {
		this.real_Path = real_Path;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
