package cn.cyit.traffic.serviceImpl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.cyit.traffic.bean.Attachment;
import cn.cyit.traffic.dao.FileMapper;
import cn.cyit.traffic.service.UploadFileService;

@Service
@Transactional
public class UploadFileServiceImpl implements UploadFileService{
	
	@Resource
	private FileMapper fileMapper;
	
	public int addAttachment(Attachment attachment) {
		return fileMapper.addAttachment(attachment);
	}

	public Map<String, Object> findAttachById(Attachment attachment) {
		return fileMapper.findAttachById(attachment);
	}

	public List<Attachment> getAttach_name(Attachment attachment) {
		return fileMapper.getAttach_name(attachment);
	}
	
	public Map<String,Object> getDocConfig(Map<String,Object> map){
		return fileMapper.getDocConfig(map);
	}


}
