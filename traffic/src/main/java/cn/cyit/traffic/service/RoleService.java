package cn.cyit.traffic.service;

import java.util.List;
import java.util.Set;

import cn.cyit.traffic.bean.Permission;
import cn.cyit.traffic.bean.Role;
import cn.cyit.traffic.bean.RolePermission;
import cn.cyit.traffic.comm.PageHelper;

public interface RoleService {

	List<Role> getRoleList();

	PageHelper<Role> getRoleListData(Role role);

	Object getRoleManageMap(Role role);

	void inserRole(Role role);

	void updateRole(Role role);

	void deleteRole(Role role);

	List<Permission> getPermissionManageList();

	List<RolePermission> getPermissionByRole(Role role);

	void deletePermissionByRole(RolePermission rolePermission);

	void saveRolePermission(RolePermission rolePermission);

}
