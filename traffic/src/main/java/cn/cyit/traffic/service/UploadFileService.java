package cn.cyit.traffic.service;

import java.util.List;
import java.util.Map;

import cn.cyit.traffic.bean.Attachment;


public interface UploadFileService {

	int addAttachment(Attachment attachment);

	Map<String, Object> findAttachById(Attachment attachment);

	List<Attachment> getAttach_name(Attachment attachment);

	Map<String,Object> getDocConfig(Map<String,Object> map);
}
