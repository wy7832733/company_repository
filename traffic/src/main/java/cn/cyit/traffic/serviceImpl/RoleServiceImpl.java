package cn.cyit.traffic.serviceImpl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.cyit.traffic.bean.Permission;
import cn.cyit.traffic.bean.Role;
import cn.cyit.traffic.bean.RolePermission;
import cn.cyit.traffic.comm.PageHelper;
import cn.cyit.traffic.dao.RoleMapper;
import cn.cyit.traffic.service.RoleService;
@Service
@Transactional
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	private RoleMapper roleMapper;

	@Override
	public List<Role> getRoleList() {
		return roleMapper.getRoleList();
	}

	@Override
	public PageHelper<Role> getRoleListData(Role role) {
		PageHelper<Role> pageHelper = new PageHelper<Role>();
		Integer total = roleMapper.getRoleListDataCount(role);
		pageHelper.setTotal(total);
		List<Role> list = roleMapper.getRoleListData(role);
		pageHelper.setRows(list);
		return pageHelper;
	}

	@Override
	public Object getRoleManageMap(Role role) {
		return roleMapper.getRoleManageMap(role);
	}

	@Override
	public void inserRole(Role role) {
		roleMapper.inserRole(role);
		
	}

	@Override
	public void updateRole(Role role) {
		roleMapper.updateRole(role);
		
	}

	@Override
	public void deleteRole(Role role) {
		roleMapper.deleteRole(role);
		
	}

	@Override
	public List<Permission> getPermissionManageList() {
		return roleMapper.getPermissionManageList();
	}

	@Override
	public List<RolePermission> getPermissionByRole(Role role) {
		return roleMapper.getPermissionByRole(role);
	}

	@Override
	public void deletePermissionByRole(RolePermission rolePermission) {
		roleMapper.deletePermissionByRole(rolePermission);
	}

	@Override
	public void saveRolePermission(RolePermission rolePermission) {
		roleMapper.saveRolePermission(rolePermission);
	}

}
