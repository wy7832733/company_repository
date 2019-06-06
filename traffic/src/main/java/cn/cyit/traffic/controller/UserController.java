package cn.cyit.traffic.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.cyit.traffic.bean.Depart;
import cn.cyit.traffic.bean.Role;
import cn.cyit.traffic.bean.User;
import cn.cyit.traffic.comm.PageHelper;
import cn.cyit.traffic.service.DepartService;
import cn.cyit.traffic.service.RoleService;
import cn.cyit.traffic.service.UserService;
import cn.cyit.traffic.util.ResultMsg;
import cn.cyit.traffic.util.oConvertUtils;

/**
 * 
* @ClassName: UserController
* @Description: 账号管理
* @author hean
* @date 2018年9月5日
*
 */
@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private DepartService departService;
	
	@Autowired 
	private RoleService roleService;
	
	/**
	 * 
	 * @Title：userManage
	 * @Descripton：  用户管理
	 * @data：2018年9月6日 
	 * @author：hean  
	 * @param：@return     
	 * @return：ModelAndView   
	 * @throws
	 */
	@RequestMapping(value = "/userManage")
	@RequiresPermissions("user:view")
	public ModelAndView userManage() {
		return new ModelAndView("user/userManage");
	}
	
	/**
	 * 
	 * @Title：getUserListData
	 * @Descripton：  获取用户信息
	 * @data：2018年9月6日 
	 * @author：hean  
	 * @param：@param user
	 * @param：@return     
	 * @return：PageHelper<User>   
	 * @throws
	 */
	@RequestMapping(value = "/getUserListData")
	@ResponseBody
	public PageHelper<User> getUserListData(User user) {
		return userService.getUserListData(user);
	}
	
	/**
	 * 
	 * @Title：userAdd
	 * @Descripton：  账号添加页面
	 * @data：2018年9月6日 
	 * @author：hean  
	 * @param：@param id
	 * @param：@param model
	 * @param：@return     
	 * @return：ModelAndView   
	 * @throws
	 */
	@RequestMapping(value = "/userAdd")
	@RequiresPermissions("user:add")
	public ModelAndView userAdd(@RequestParam(value = "id" , required = false) String id ,Model model) {
		if(oConvertUtils.isNotEmpty(id)) {
			model.addAttribute("id", id);
		}
		List<Depart> departList = departService.getDepartManageList();
		List<Role> roleLst = roleService.getRoleList();
		model.addAttribute("departList", departList);
		model.addAttribute("roleLst", roleLst);
		return new ModelAndView("user/userAdd");
	}
	
	
	/**
	 * 
	 * @Title：userAdd
	 * @Descripton：  账号修改页面
	 * @data：2018年9月6日 
	 * @author：hean  
	 * @param：@param id
	 * @param：@param model
	 * @param：@return     
	 * @return：ModelAndView   
	 * @throws
	 */
	@RequestMapping(value = "/userEdit")
	@RequiresPermissions("user:edit")
	public ModelAndView userEdit(@RequestParam(value = "id" , required = false) String id ,Model model) {
		if(oConvertUtils.isNotEmpty(id)) {
			model.addAttribute("id", id);
		}
		List<Depart> departList = departService.getDepartManageList();
		List<Role> roleLst = roleService.getRoleList();
		model.addAttribute("roleLst", roleLst);
		model.addAttribute("departList", departList);
		return new ModelAndView("user/userAdd");
	}
	
	 /**
	  * 
	  * @Title：getUserManageMap
	  * @Descripton：  获取账号信息
	  * @data：2018年9月6日 
	  * @author：hean  
	  * @param：@param id
	  * @param：@return     
	  * @return：Object   
	  * @throws
	  */
	 @RequestMapping(value = "/getUserManageMap")
	 @ResponseBody
	 public Object getUserManageMap(int id ) {
		 User user = userService.getUserManageMap(id); 
		 return user;
	 }
	
	
	/**
	 * 
	 * @Title：insertUser
	 * @Descripton：  账号添加
	 * @data：2018年9月6日 
	 * @author：hean  
	 * @param：@param user
	 * @param：@return     
	 * @return：Object   
	 * @throws
	 */
	@RequestMapping(value = "/insertUser")
	@ResponseBody
	public Object insertUser(User user) {
		try {
			user.setPassword("123456");
			userService.insertUser(user);
			userService.addUserRole(user);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultMsg(1, "添加失败！");
		}
		return new ResultMsg(0, "添加成功！");
	}
	
	/**
	 * 
	 * @Title：updateUser
	 * @Descripton：  账号删除操作
	 * @data：2018年9月6日 
	 * @author：hean  
	 * @param：@param user
	 * @param：@return     
	 * @return：Object   
	 * @throws
	 */
	 @RequestMapping(value = "/updateUser")
	 @ResponseBody
	 public Object updateUser(User user) {
		 
		try {
			userService.deleteUserRole(user);
			userService.updateUser(user);
			userService.addUserRole(user);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultMsg(1, "添加失败");
		}
		 
		 return new ResultMsg(0, "添加成功");
	 }
	 
	 
	 /**
	  * 
	  * @Title：deleteUsers
	  * @Descripton：  账号删除
	  * @data：2018年8月30日 
	  * @author：hean  
	  * @param：@param strJson
	  * @param：@return     
	  * @return：Object   
	  * @throws
	  */
	 @RequestMapping("/deleteUsers")
	 @ResponseBody
	 @RequiresPermissions("user:delete")
	 public Object deleteUsers(String strJson) {
		 try {
			 
			 String[] ids = strJson.split(",");
			 for(String idAll:ids) {
				 User user = new User();
				 user.setId(Integer.valueOf(idAll));
				 //userRepository.delete(user);
				 
			 }
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultMsg(1, "删除失败！"); 
		}
		 return new ResultMsg(0, "删除成功！"); 
	 }
	 
	 
	 @RequestMapping("/changePwd")
	 @ResponseBody
	 public Object changePwd(User user){
		 userService.updateUser(user);
		 return new ResultMsg(0);
	 }
	 
}
