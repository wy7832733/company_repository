package cn.cyit.traffic.service;

import java.util.List;
import java.util.Set;

import cn.cyit.traffic.bean.Permission;
import cn.cyit.traffic.bean.Role;
import cn.cyit.traffic.bean.User;
import cn.cyit.traffic.comm.PageHelper;

public interface UserService {
	
	public User queryUserByName(String userName);
	
    public Set<String> queryRolesByName(String userName);

	public void insertUser(User u);

	public PageHelper<User> getUserListData(User user);

	public void updateUser(User user);

	public User findByUsername(String username);

	public List<Role> getUserRoleList(User userInfo);

	public List<Permission> getUserPermissionList(Role role);

	public User getUserManageMap(int id);

	public void addUserRole(User user);

	public void deleteUserRole(User user);
	
	public List<User> getUserList(User user);
}
