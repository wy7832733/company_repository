package cn.cyit.traffic.serviceImpl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.cyit.traffic.bean.Permission;
import cn.cyit.traffic.bean.Role;
import cn.cyit.traffic.bean.User;
import cn.cyit.traffic.comm.PageHelper;
import cn.cyit.traffic.dao.UserMapper;
import cn.cyit.traffic.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	@Autowired
	private UserMapper userMapper;

	@Override
	public User queryUserByName(String userName) {
		 return userMapper.queryUserByName(userName);
	}

	@Override
	public Set<String> queryRolesByName(String userName) {
		return userMapper.queryRolesByName(userName);
	}

	@Override
	public void insertUser(User u) {
		userMapper.insertUser(u);
	}

	@Override
	public PageHelper<User> getUserListData(User user) {
		PageHelper<User> pageHelper = new PageHelper<User>();
		// 统计总记录数
		Integer total = userMapper.findUsersByPageCount(user);
		pageHelper.setTotal(total);
		// 查询当前页实体对象
		List<User> list = userMapper.getUserListData(user);
		pageHelper.setRows(list);
		return pageHelper;
	}

	@Override
	public void updateUser(User user) {
		userMapper.updateByPrimaryKeySelective(user);
		
	}

	@Override
	public User findByUsername(String username) {
		return userMapper.findByUsername(username);
	}

	@Override
	public List<Role> getUserRoleList(User userInfo) {
		return userMapper.getUserRoleList(userInfo);
	}

	@Override
	public List<Permission> getUserPermissionList(Role role) {
		return userMapper.getUserPermissionList(role);
	}

	@Override
	public User getUserManageMap(int id) {
		return userMapper.getUserManageMap(id);
	}

	@Override
	public void addUserRole(User user) {
		userMapper.addUserRole(user);
		
	}

	@Override
	public void deleteUserRole(User user) {
		userMapper.deleteUserRole(user);
		
	}
	
	public List<User> getUserList(User user) {
		return userMapper.getUserListData(user);
	}

}
