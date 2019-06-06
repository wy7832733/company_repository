package cn.cyit.traffic.dao;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import cn.cyit.traffic.bean.Permission;
import cn.cyit.traffic.bean.Role;
import cn.cyit.traffic.bean.User;

public interface UserMapper {
	
    int deleteByPrimaryKey(Integer id);

	int insert(User record);

	int insertSelective(User record);

	User selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(User record);

	int updateByPrimaryKey(User record);


    int insertUser(User record);

	User queryUserByName(String userName);

	Set<String> queryRolesByName(String userName);

	Integer findUsersByPageCount(User user);

	List<User> getUserListData(User user);

	User findByUsername(String username);

	List<Role> getUserRoleList(User userInfo);

	List<Permission> getUserPermissionList(Role role);

	User getUserManageMap(int id);

	void addUserRole(User user);

	void deleteUserRole(User user);

}