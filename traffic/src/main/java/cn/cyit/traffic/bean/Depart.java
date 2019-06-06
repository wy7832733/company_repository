package cn.cyit.traffic.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.cyit.traffic.comm.Page;

public class Depart extends Page implements Serializable{
    private Integer id;

    private String depart_name;

    private String intro;

    private Integer delete_flay;
    
    private Integer pid;
    
    

    public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepart_name() {
        return depart_name;
    }

    public void setDepart_name(String depart_name) {
        this.depart_name = depart_name == null ? null : depart_name.trim();
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro == null ? null : intro.trim();
    }

    public Integer getDelete_flay() {
        return delete_flay;
    }

    public void setDelete_flay(Integer delete_flay) {
        this.delete_flay = delete_flay;
    }
}