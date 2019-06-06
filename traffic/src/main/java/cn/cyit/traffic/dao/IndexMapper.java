package cn.cyit.traffic.dao;

import java.util.List;

import cn.cyit.traffic.bean.Permission;
import cn.cyit.traffic.bean.User;

public interface IndexMapper {

	List<Permission> getPermissionList(User user);

	List<Permission> getPermissionChildList(Permission resource);
}
