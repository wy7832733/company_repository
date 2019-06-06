package cn.cyit.traffic.dao;

import java.util.List;

import cn.cyit.traffic.bean.Permission;
import cn.cyit.traffic.bean.Role;
import cn.cyit.traffic.bean.RolePermission;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

	List<Role> getRoleList();

	Integer getRoleListDataCount(Role role);

	List<Role> getRoleListData(Role role);

	Object getRoleManageMap(Role role);

	void inserRole(Role role);

	void updateRole(Role role);

	void deleteRole(Role role);

	List<Permission> getPermissionManageList();

	List<RolePermission> getPermissionByRole(Role role);

	void deletePermissionByRole(RolePermission rolePermission);

	void saveRolePermission(RolePermission rolePermission);
}