package cn.cyit.traffic.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import cn.cyit.traffic.bean.Attachment;
import cn.cyit.traffic.bean.User;
import cn.cyit.traffic.config.SysCinfig;
import cn.cyit.traffic.service.DepartService;
import cn.cyit.traffic.service.UploadFileService;
import cn.cyit.traffic.util.ImageUtils;
import cn.cyit.traffic.util.ResultMsg;
import cn.cyit.traffic.util.UUIDUtil;
import cn.cyit.traffic.util.oConvertUtils;
import excel.ParseExcel;





@Controller
@RequestMapping(value = "/uploadFileController")
public class UploadFileController {
	
	public static String FILEDIR = "";
	
	@Autowired
	private UploadFileService uploadFileService;
	
	@Autowired
	private DepartService departService;
	
	
	/**
	 * 上传附件
	 * @author an
	 */
	@RequestMapping(value = "/saveFile")
	@ResponseBody
	public Object UploadFile(
			@RequestParam("file") CommonsMultipartFile file,
			HttpServletRequest req, HttpServletResponse response,
			HttpSession session) {
		
		User user = (User) session.getAttribute("user");
		int id = user.getId();
		int depart_id = user.getP_id();
		
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
				int attachId = 0;
				//原文件保存路径
				String savePath = path + "/" + year + "/" + month + "/" + filename;
				try {
					
					File ff = new File(savePath);
					if (!ff.exists()) {
						ff.mkdirs();
					}
					file.transferTo(ff);
					
					Date date=new Date();
					DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String time=format.format(date);
					
					// 插入附件表
					Attachment attachment = new Attachment();
					attachment.setAttach_name(name);
					attachment.setUser_id(id);
					attachment.setAttach_type(filetype);
					attachment.setReal_Path(year + "/" + month + "/" + filename);
					attachment.setAttach_time(time);
					uploadFileService.addAttachment(attachment);
					
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
	
	
	
	
	
	public List<Map<String,Object>> batchAdd(int id,int depart_id,List<Attachment> list,String filetype,String name,CommonsMultipartFile file) throws IllegalStateException, IOException{
		List<Map<String,Object>> result=new ArrayList<>();
		// 配置文件，文件存放地址
		String path = SysCinfig.attach_path;
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH) + 1;		
		for (Attachment attachment : list) {

			String filename = UUIDUtil.getUUID().replace("-", "") +"." + filetype;
			int attachId = 0;	
			String savePath = path + "/" + year + "/" + month + "/" + filename;
			File ff = new File(savePath);
			if (!ff.exists()) {
				ff.mkdirs();
			}
			file.transferTo(ff);
			
			Date date=new Date();
			DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time=format.format(date);
			
			// 插入附件表
			//Attachment attachment = new Attachment();
			attachment.setAttach_name(name);
			attachment.setUser_id(id);
			attachment.setAttach_type(filetype);
			attachment.setReal_Path(year + "/" + month + "/" + filename);
			attachment.setAttach_time(time);
			uploadFileService.addAttachment(attachment);
			
			Map<String, Object> attributes = new HashMap<String, Object>();
			attributes.put("attach_id", attachment.getAttach_id());
			result.add(attributes);
		}
		return result;
	}
	
	/**
	 * 下载附件
	 * @author mjc
	 */
	@RequestMapping(value = "/downloadFile")
	@ResponseBody
	public void downloadFile(HttpServletRequest request,Attachment attachment, HttpServletResponse response,
			boolean isOnLine) {
		
		Map<String, Object> attach = uploadFileService.findAttachById(attachment);
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
			
			if (isOnLine) {  // 在线打开方式
				
				URL u = new URL("file:///" + FILEDIR + "/" + realPath);
				response.setContentType(u.openConnection().getContentType());
				response.setHeader("Content-Disposition", "inline; filename="
						+ f.getName());
				// 文件名应该编码成UTF-8
				
			} else { // 纯下载方式
				
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
			}
			
			OutputStream out = response.getOutputStream();
			while ((len = br.read(buf)) > 0)
				out.write(buf, 0, len);
			br.close();
			out.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Description:	kindeditor专用上传图片
	 * @Author:an
	 * @param attachId 附件ID
	 * @param response
	 * @param isOnLine 是否在线打开
	 */
	@RequestMapping(value = "/kindUpLoadImg")
	@ResponseBody
	public void kindUpLoadImg(@RequestParam("imgFile") CommonsMultipartFile file,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		try {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String path = SysCinfig.attach_path;
		// 图片压缩效果中存放路径
		String comMPath =SysCinfig.img_m_path;
		// 图片压缩效果小存放路径
		String comSPath = SysCinfig.img_s_path;
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH) + 1;
		String filetype = file.getOriginalFilename().toLowerCase()
				.substring(file.getOriginalFilename().lastIndexOf('.') + 1);
		// 文件原名
		String name = file.getOriginalFilename().substring(0,
				file.getOriginalFilename().lastIndexOf("."));
		String filename = UUIDUtil.getUUID().replace("-", "") + "."+filetype;
		int id = 0;
		//原文件保存路径
		String savePath = path + "/" + year + "/" + month + "/" + filename;
		String[] fileTypes = new String[] { "gif", "jpg", "jpeg", "png", "bmp" };
			if (file == null) {
				out.println(getError("请选择文件。"));
				return ;
			}
			if (!Arrays.<String> asList(fileTypes).contains(filetype)) {
				out.println(getError("上传文件扩展名[" + filetype + "]是不允许的扩展名。"));
				return ;
			}
			File ff = new File(savePath);
			if (!ff.exists()) {
				ff.mkdirs();
			}
			file.transferTo(ff);
			// 插入附件表
			Attachment attachment = new Attachment();
			attachment.setAttach_name(name);
			attachment.setUser_id(id);
			attachment.setAttach_type(filetype);
			attachment.setReal_Path(year + "/" + month + "/" + filename);
			
			//图片压缩
			if (filetype.equalsIgnoreCase("jpg")
					|| filetype.equalsIgnoreCase("png")
					|| filetype.equalsIgnoreCase("jpeg")) {
				// 图片附件压缩中
				String saveComMPath=comMPath + "/" + year + "/" + month;
				File comMFile = new File(saveComMPath);
				if(!comMFile.exists()){
					comMFile.mkdirs();
				}
				//压缩
				ImageUtils.maxLengthResize(savePath, saveComMPath+ "/" + filename, 800, 0.8f);
				
				// 图片附件压缩小
				String saveComSPath=comSPath + "/" + year + "/" + month;
				File comSFile = new File(saveComSPath);
				if(!comSFile.exists()){
					comSFile.mkdirs();
				}
				//压缩
				ImageUtils.resize(savePath, saveComSPath+ "/" + filename, 200,200, 0.8f);
			}
			uploadFileService.addAttachment(attachment);
			int attact_id = attachment.getAttach_id();
			System.out.println(attact_id);
			 JSONObject obj =new JSONObject();
			              obj.put("error", 0);
			              obj.put("url","../uploadFileController/downloadImg?attachId="+attact_id);
			              out.println(obj.toString());
			/* 注销取压缩后图片的方法
			              JSONObject obj =new JSONObject();
			              obj.put("error", 0);
			              obj.put("url","../uploadFileController/downloadImg?type=m&attachId="+id);
			              out.println(obj.toString());   */          
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param message
	 * @return
	 */
	private String getError(String message) {
		JSONObject obj = new JSONObject();
		obj.put("error", 1);
		obj.put("message", message);
		return obj.toString();
	}

	
	/**
	 * @Description:下载图片
	 * @Author:lk
	 * @param attachId 附件ID
	 * @param response
	 * @param isOnLine 是否在线打开
	 * @param 下载图片类型
	 */
	@RequestMapping(value = "/downloadImg")
	@ResponseBody
	public void downloadImg(int attachId, HttpServletResponse response,
			boolean isOnLine, String type) {
		type =oConvertUtils.getString(type);
		Attachment attachment = new Attachment();
		attachment.setAttach_id(attachId);
		Map<String, Object> attach = uploadFileService.findAttachById(attachment);
		if (attach == null)
			return;
		// 配置文件，文件存放地址
		String path = SysCinfig.attach_path;
		String realPath="";
		if(type.equals("m")){
			 realPath = attach.get("real_Path").toString();
		}else if(type.equals("s")){
			 realPath = attach.get("real_Path").toString();
		}else{
			path = SysCinfig.attach_path;
		 realPath = attach.get("real_Path").toString();
		}
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
			if (isOnLine) { // 在线打开方式
				URL u = new URL("file:///" + FILEDIR + "/" + realPath);
				response.setContentType(u.openConnection().getContentType());
				response.setHeader("Content-Disposition", "inline; filename="
						+ f.getName());
				// 文件名应该编码成UTF-8
			} else { // 纯下载方式
				response.setContentType("application/x-msdownload");
				response.setHeader("Content-Disposition",
						"attachment; filename=" + f.getName());
			}
			OutputStream out = response.getOutputStream();
			while ((len = br.read(buf)) > 0)
				out.write(buf, 0, len);
			br.close();
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 判断文件是否重复
	 * @author an
	 */
	@RequestMapping(value = "/getAttach_name")
	@ResponseBody
	public Object getAttach_name(Attachment attachment) {
			
			List<Attachment> list = uploadFileService.getAttach_name(attachment);
			if(list.size() <= 0) {
				
				return 0;
				
			} else {
				
				return 1;
			}
		
		}
	

	/**
	 * @Description:上传excel并读取EXCEL数据插入到对应表
	 * @return ResultMsg
	 * @Author:yty
	 */
	@RequestMapping(value = "/saveExcel")
	@ResponseBody
	public Object saveExcel(@RequestParam("file") CommonsMultipartFile file,
			HttpServletRequest request, HttpServletResponse response,Integer type,String classInfo,Integer trainId,
			HttpSession session) {
		User user = (User) session.getAttribute("user");
		int id = user.getId();
		String pro_id = request.getParameter("pro_id");
		String filetype = file.getOriginalFilename().toLowerCase()
				.substring(file.getOriginalFilename().lastIndexOf('.') + 1);
		// 文件原名
		String name = file.getOriginalFilename().substring(0,
				file.getOriginalFilename().lastIndexOf("."));
		String filename = UUIDUtil.getUUID().replace("-", "") +"."+ filetype;
		int attachId = 0;
		//读取数据库EXCEL表的配置文件
		Map<String, Object> configMap = new HashMap<String, Object>();
		Map<String,Object> config=uploadFileService.getDocConfig(configMap);
		ResultMsg result=null;
		List<Map<String,Object>> list=null;
		if(filetype.matches(".*(xlsx|xls)$")){
			try {
				ParseExcel parseExel= new ParseExcel();
				parseExel.inportFiles(file.getInputStream(),(String)config.get("xmlsetting"));
				if(parseExel.startParse()){
					//此方法将从excel读出的数据的，将对应的汉字转为外键 ，并加上相应的用户id等字段
					//parseExel.setAttachmentService(uploadFileService);
					//parseExel.datasToDB(lsh);
					/*file.transferTo(new File(uploadPath+"/"+lsh+filetype));
					docService.addExcelHistory(lsh, parseExel.getDatacount(), fileid, (String)session.getAttribute("s_userid"));
					result=new ResultMsg(1,"文件导入成功，共导入"+parseExel.getDatacount()+"条数据");*/
				}else{
					result=new ResultMsg(0,parseExel.getErrorString());
				}
			} catch (Exception e) {
				e.printStackTrace();
				//result=new ResultMsg(0,e.getMessage());
				return new ResultMsg(1, "系统错误,请稍候重试");
			}
		}
		
		//ResourceBundle bundle = ResourceBundle.getBundle("sysConfig");
		// 配置文件，文件存放地址
		//String path = bundle.getString(config.get("TMPPROC").toString());
		String path = SysCinfig.attach_path+"/"+config.get("tmpproc").toString();
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH) + 1;

		//原文件保存路径
		String savePath = path + "/" + year + "/" + month + "/" + filename;
		try {
			File ff = new File(savePath);
			if (!ff.exists()) {
				ff.mkdirs();
			}
			file.transferTo(ff);
			// 插入附件表
			Map<String, Object> map = new HashMap<String, Object>();
			// 插入附件表
			DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Attachment attachment = new Attachment();
			attachment.setAttach_name(name);
			attachment.setUser_id(id);
			attachment.setAttach_type(filetype);
			attachment.setReal_Path(year + "/" + month + "/" + filename);
			attachment.setAttach_time(format.format(new Date()));
			uploadFileService.addAttachment(attachment);			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultMsg(1, "系统错误,请稍候重试");
		}
		Map<String, Object> attributes = new HashMap<String, Object>();
		attributes.put("attachId", attachId);
		attributes.put("realPath", year + "/" + month + "/" + filename);
		if(type==14 || type==8 || type==12 || type==15) attributes.put("list", list);
		return new ResultMsg(0, "导入成功！", attributes);
	}
}
