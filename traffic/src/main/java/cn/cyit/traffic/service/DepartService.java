package cn.cyit.traffic.service;

import java.util.List;

import cn.cyit.traffic.bean.Depart;
import cn.cyit.traffic.comm.PageHelper;

public interface DepartService {

	PageHelper<Depart> getDepartListData(Depart depart);

	void insertDepart(Depart depart);

	Object getDepartManageMap(Depart depart);

	void updateDepart(Depart depart);

	void deleteDepart(Depart depart);

	List<Depart> getDepartManageList();

}
