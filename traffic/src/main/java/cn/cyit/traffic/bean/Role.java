package cn.cyit.traffic.bean;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

public class Role {
    private Integer id;

    private String role;

    private String description;

    private String available;
    
    private int delete_flag;
    
    
    /**
     * 可见部门
     */
    private String visualDep;
    
    
    
    public String getVisualDep() {
		return visualDep;
	}

	public void setVisualDep(String visualDep) {
		this.visualDep = visualDep;
	}

	@ManyToMany
    private List<Permission> permissions;
    
    @ManyToMany
    private List<User> users;// 一个角色对应多个用户
    
    
    
    public int getDelete_flag() {
		return delete_flag;
	}

	public void setDelete_flag(int delete_flag) {
		this.delete_flag = delete_flag;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available == null ? null : available.trim();
    }
}