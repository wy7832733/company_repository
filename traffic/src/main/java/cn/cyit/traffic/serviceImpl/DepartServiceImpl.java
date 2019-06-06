package cn.cyit.traffic.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.cyit.traffic.bean.Depart;
import cn.cyit.traffic.comm.PageHelper;
import cn.cyit.traffic.dao.DepartMapper;
import cn.cyit.traffic.service.DepartService;
@Service
@Transactional
public class DepartServiceImpl implements DepartService{
	@Autowired
	private DepartMapper departMapper;

	@Override
	public PageHelper<Depart> getDepartListData(Depart depart) {
		PageHelper<Depart> pageHelper = new PageHelper<Depart>();
		// 统计总记录数
		Integer total = departMapper.getDepartListDataCount(depart);
		pageHelper.setTotal(total);
		// 查询当前页实体对象
		List<Depart> list = departMapper.getDepartListData(depart);
		pageHelper.setRows(list);
		return pageHelper;
	}

	@Override
	public void insertDepart(Depart depart) {
		departMapper.insertDepart(depart);
	}

	@Override
	public Object getDepartManageMap(Depart depart) {
		return departMapper.getDepartManageMap(depart);
	}

	@Override
	public void updateDepart(Depart depart) {
		departMapper.updateDepart(depart);
		
	}

	@Override
	public void deleteDepart(Depart depart) {
		departMapper.deleteDepart(depart);
	}

	@Override
	public List<Depart> getDepartManageList() {
		return departMapper.getDepartManageList();
	}
}
