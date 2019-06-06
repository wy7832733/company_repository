package cn.cyit.traffic.dao;

import java.util.List;
import java.util.Map;

import cn.cyit.traffic.bean.Attachment;

public interface FileMapper {
	
	int addAttachment(Attachment attachment);

	Map<String, Object> findAttachById(Attachment attachment);
	
	List<Attachment> getAttach_name(Attachment attachment);
	
	List<Attachment> getAttachList1(String ids);
	
	Map<String,Object> getDocConfig(Map<String,Object> map);
	
}
