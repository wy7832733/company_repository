package cn.cyit.traffic.service;

import java.util.Map;

import cn.cyit.traffic.bean.Attachment;


public interface FileService {
	
	void addAttachment(Attachment attachment);

	Map<String, Object> findAttachById(Attachment attachment);

}
