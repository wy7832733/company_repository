package cn.cyit.traffic.dao;

import java.util.List;

import cn.cyit.traffic.bean.Depart;

public interface DepartMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Depart record);

    int insertSelective(Depart record);

    Depart selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Depart record);

    int updateByPrimaryKey(Depart record);

	Integer getDepartListDataCount(Depart depart);

	List<Depart> getDepartListData(Depart depart);

	void insertDepart(Depart depart);

	Object getDepartManageMap(Depart depart);

	void updateDepart(Depart depart);

	void deleteDepart(Depart depart);

	List<Depart> getDepartManageList();
}