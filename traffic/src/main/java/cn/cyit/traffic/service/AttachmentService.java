package cn.cyit.traffic.service;
import java.util.Map;

//import com.yzmcxx.dao.BaseDao;


/**
 * @Description:  文件数据库操作 
 * @Author:lk
 */
public interface AttachmentService {
	
	
	/**
	public BaseDao getDao();
	 * @Description:  附件信息保存
	 * @param map
	 * @return int ID
	 */
	public int addAttachment(Map<String,Object> map);
	
	/**
	 * @Description:  查找附件
	 * @param id
	 * @return int ID
	 */
	public Map<String,Object> findAttachById(Map<String,Object> map);
	
	/**
	 * @Description:  读取数据库EXCEL表的配置文件
	 * @param map
	 * @return int ID
	 */
	public Map<String,Object> getDocConfig(Map<String,Object> map);
	

}
