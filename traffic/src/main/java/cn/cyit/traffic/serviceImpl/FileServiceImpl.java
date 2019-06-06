package cn.cyit.traffic.serviceImpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.cyit.traffic.bean.Attachment;
import cn.cyit.traffic.dao.FileMapper;
import cn.cyit.traffic.service.FileService;

@Service
@Transactional
public class FileServiceImpl implements FileService{
	@Autowired
	private FileMapper fileMapper;

	public void addAttachment(Attachment attachment) {
		fileMapper.addAttachment(attachment);
		
	}

	public Map<String, Object> findAttachById(Attachment attachment) {
		return fileMapper.findAttachById(attachment);
		
	}
}
