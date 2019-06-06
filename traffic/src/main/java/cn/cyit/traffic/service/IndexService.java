package cn.cyit.traffic.service;

import java.util.List;

import cn.cyit.traffic.bean.Permission;
import cn.cyit.traffic.bean.User;

public interface IndexService {

	List<Permission> getPermissionList(User user);

	List<Permission> getPermissionChildList(Permission resource);
}
