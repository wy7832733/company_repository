package cn.cyit.traffic.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import cn.cyit.traffic.bean.Attachment;
import cn.cyit.traffic.bean.User;
import cn.cyit.traffic.config.SysCinfig;
import cn.cyit.traffic.service.FileService;
import cn.cyit.traffic.util.ResultMsg;
import cn.cyit.traffic.util.UUIDUtil;
/**
 * 
* @ClassName: FileController
* @Description: 文件上传下载
* @author hean
* @date 2018年8月29日
*
 */
@RestController
@RequestMapping("/file")
public class FileController {
	
	
	@Autowired
	private FileService fileService;
	
	
	/**
	 * 
	 * @Title：upload
	 * @Descripton：  单个文件上传
	 * @data：2018年8月29日 
	 * @author：hean  
	 * @param：@param file
	 * @param：@param request
	 * @param：@param session
	 * @param：@return     
	 * @return：Object   
	 * @throws
	 */
	@RequestMapping("/upload")
	@ResponseBody
	public Object upload(@RequestParam("file") MultipartFile file, HttpServletRequest request,HttpSession session) {
		
			User user = (User) session.getAttribute("user");
			// 配置文件，文件存放地址
			String path = SysCinfig.attach_path;
			
			Calendar now = Calendar.getInstance();
			int year = now.get(Calendar.YEAR);
			int month = now.get(Calendar.MONTH) + 1;

			String filetype = file.getOriginalFilename().toLowerCase()
					.substring(file.getOriginalFilename().lastIndexOf('.') + 1);
			// 文件原名
			String name = file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf("."));
			String filename = UUIDUtil.getUUID().replace("-", "") +"." + filetype;
			//原文件保存路径
			String savePath = path + "/" + year + "/" + month + "/" + filename;
			try {
				
			File saveFile = new File(savePath); 
			
			 if (!saveFile.getParentFile().exists()) {
	                saveFile.getParentFile().mkdirs();
	            }else{
	            	saveFile.mkdirs();
	            }
			 file.transferTo(saveFile);
			 Attachment attachment = new Attachment();
			 attachment.setAttach_name(name);
			 attachment.setUser_id(user.getId());
			 attachment.setAttach_type(filetype);
			 attachment.setReal_Path(year + "/" + month + "/" + filename);
			 fileService.addAttachment(attachment);
			
			 Map<String, Object> attributes = new HashMap<String, Object>();
			 attributes.put("attach_id", attachment.getAttach_id());
			 attributes.put("attach_name", attachment.getAttach_name());
			 attributes.put("attach_type", attachment.getAttach_type());
			 return new ResultMsg(0, "上传成功！", attributes);
			 
			} catch (Exception e) {
				e.printStackTrace();
				return new ResultMsg(1, "系统错误,请稍候重试");
			}
			
	}
	
	/**
	 * 
	 * @Title：uploadFiles
	 * @Descripton：  多个文件上传
	 * @data：2018年8月29日 
	 * @author：hean  
	 * @param：@param request
	 * @param：@param session
	 * @param：@return
	 * @param：@throws IOException     
	 * @return：Object   
	 * @throws
	 */
	@RequestMapping("/uploadFiles")
	@ResponseBody
	public Object uploadFiles(HttpServletRequest request,HttpSession session) throws IOException {
		  List<MultipartFile> files = ((MultipartHttpServletRequest)request).getFiles("fileName");
		  if(files.isEmpty()){
			  return new ResultMsg(1, "上传失败，因为文件为空.");
	      }
		  
		// 配置文件，文件存放地址
		  String path = SysCinfig.attach_path;
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH) + 1;
		List<Attachment> attachList = new ArrayList<>();
		for(MultipartFile file:files){
			
			if(file.isEmpty()){
				
				 return new ResultMsg(1, "上传失败，因为文件为空.");
				 
            } else {
            	User user = (User) session.getAttribute("user");
            	String filetype = file.getOriginalFilename().toLowerCase()
    					.substring(file.getOriginalFilename().lastIndexOf('.') + 1);
    			// 文件原名
    			String name = file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf("."));
    			String filename = UUIDUtil.getUUID().replace("-", "") +"." + filetype;
    			//原文件保存路径
    			String savePath = path + "/" + year + "/" + month + "/" + filename;
    			try {
    				
    				File saveFile = new File(savePath); 
    				
    				 if (!saveFile.getParentFile().exists()) {
    		                saveFile.getParentFile().mkdirs();
    		            }
    				 Attachment attachment = new Attachment();
    				 attachment.setAttach_name(name);
    				 attachment.setUser_id(user.getId());
    				 attachment.setAttach_type(filetype);
    				 attachment.setReal_Path(year + "/" + month + "/" + filename);
    				 fileService.addAttachment(attachment);
    				
    				
    				 attachList.add(attachment);
    				 
    				} catch (Exception e) {
    					e.printStackTrace();
    					return new ResultMsg(1, "系统错误,请稍候重试");
    				}
            }
		}
		return attachList;
		
	}
	
	
	/**
	 * 
	 * @Title：download
	 * @Descripton：  文件下载
	 * @data：2018年8月29日 
	 * @author：hean  
	 * @param：@param request
	 * @param：@param response
	 * @param：@param attachment
	 * @param：@throws IOException     
	 * @return：void   
	 * @throws
	 */
	@RequestMapping(value = "/download")
	public void download(HttpServletRequest request, HttpServletResponse response,Attachment attachment) throws IOException {
		
		Map<String, Object> attach = fileService.findAttachById(attachment);
		
		if (attach == null)
			return;
		
		// 配置文件，文件存放地址
		String path = SysCinfig.attach_path;
		String realPath = attach.get("real_Path").toString();
		File f = new File(path + "/" + realPath);
		
		try {
			
			if (!f.exists()) {
				System.out.println("文件不存在！");
				return;
			}
			
			BufferedInputStream br = new BufferedInputStream(
					new FileInputStream(f));
			byte[] buf = new byte[1024];
			int len = 0;

			response.reset(); // 非常重要
			
			// 纯下载方式
				
			response.setContentType("application/x-msdownload");
			String userAgent = request.getHeader("User-Agent"); 
			String fileName = attach.get("attach_name").toString() + "." + attach.get("attach_type").toString();
			if (userAgent != null) {
				userAgent = userAgent.toLowerCase();
				byte[] bytes = (userAgent.contains("msie")||userAgent.contains("like gecko")) ? fileName.getBytes()  
				        : fileName.getBytes("UTF-8"); // fileName.getBytes("UTF-8")处理safari的乱码问题  
				
				//Windows 10 中 用户代理字符串已经修改了  不在是"msie"了 所以下面要判别msie和like gecko	
				if (userAgent.indexOf("msie") != -1 || userAgent.indexOf("like gecko") != -1){
					//这里虽然已经过滤出了window不同版本中的ie浏览器 但是经过实际测试，还是会有个别特例的出现 所以在这里把非ie但有进入该判断的主流浏览器再次过滤掉
					if(userAgent.indexOf("chrome") != -1 || userAgent.indexOf("safari") != -1 || userAgent.indexOf("firefox") != -1 || userAgent.indexOf("opera") != -1){
						fileName = new String(bytes, "ISO-8859-1");
					}else{
						fileName = URLEncoder.encode(fileName, "UTF8");
					}
				}else{
					fileName = new String(bytes, "ISO-8859-1");
				}// 各浏览器基本都支持ISO编码 
			}else {
				fileName = URLEncoder.encode(fileName, "UTF8");
			}
			response.setHeader("Content-Disposition",  
			        String.format("attachment; filename=\"%s\"", fileName)); 
			
			OutputStream out = response.getOutputStream();
			while ((len = br.read(buf)) > 0)
				out.write(buf, 0, len);
			br.close();
			out.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
