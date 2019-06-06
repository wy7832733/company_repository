package cn.cyit.traffic.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.cyit.traffic.bean.Permission;
import cn.cyit.traffic.bean.User;
import cn.cyit.traffic.dao.IndexMapper;
import cn.cyit.traffic.service.IndexService;

@Service
@Transactional
public class IndexServiceImpl implements IndexService{
	@Autowired
	private IndexMapper indexMapper;


	@Override
	public List<Permission> getPermissionList(User user) {
		return indexMapper.getPermissionList(user);
	}

	@Override
	public List<Permission> getPermissionChildList(Permission resource) {
		return indexMapper.getPermissionChildList(resource);
	}

}
