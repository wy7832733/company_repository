package cn.cyit.traffic.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.cyit.traffic.bean.Permission;
import cn.cyit.traffic.bean.User;
import cn.cyit.traffic.config.SysCinfig;
import cn.cyit.traffic.service.IndexService;
import cn.cyit.traffic.service.UserService;


/**
 * 
* @ClassName: IndexController
* @Description: 系统主页面
* @author hean
* @date 2018年9月4日
*
 */
@Controller
@RequestMapping("/")
public class IndexController {
	
	
	private static final Logger logger = Logger.getLogger(IndexController.class);
	
	@Autowired
	private IndexService indexService;
	
	@Autowired
	private UserService userService;
	
	/**
	 * 
	 * @Title：login
	 * @Descripton：  账号登录页面
	 * @data：2018年9月4日 
	 * @author：hean  
	 * @param：@return     
	 * @return：ModelAndView   
	 * @throws
	 */
	@RequestMapping(value = "/login")
	public ModelAndView login(@RequestParam(value="message" ,required = false) String message) {
		ModelAndView mv = new ModelAndView("/login");
		if("overTime".equals(message)) message="页面过期，请重新登录！";
		mv.addObject("message", message);
		return mv;
	}
	
	/**
	 * 
	 * @Title：logout
	 * @Descripton：  用户退出
	 * @data：2018年9月8日 
	 * @author：hean  
	 * @param：@param model
	 * @param：@return     
	 * @return：ModelAndView   
	 * @throws
	 */
	 @RequestMapping(value="/logout")    
	    public ModelAndView logout(Model model,HttpSession session){   
	        //使用权限管理工具进行用户的退出，跳出登录，给出提示信息  
	        //SecurityUtils.getSubject().logout();   
		 	session.removeAttribute("user");
	        model.addAttribute("message", "您已安全退出");   
	        return new ModelAndView("redirect:/login");  
	    }   
	
	/**
	 * 
	 * @Title：doLogin
	 * @Descripton：  账号登录
	 * @data：2018年9月4日 
	 * @author：hean  
	 * @param：@param user
	 * @param：@param result
	 * @param：@param model
	 * @param：@param request
	 * @param：@param session
	 * @param：@return     
	 * @return：String   
	 * @throws
	 */
	@RequestMapping("/doLogin")
	public String doLogin(String account, String password,boolean rememberMe,Model model,HttpServletRequest request,HttpSession session) {
		
		/*System.out.println("rememberMe:"+rememberMe);
		//将form中的用户名密码传入Realm 的doGetAuthenticationInfo
		
         UsernamePasswordToken token  = new  UsernamePasswordToken(account,password);
         token.setRememberMe(rememberMe);
         Subject subject = SecurityUtils.getSubject();*/
	      /* try {
	        	 subject.login(token);
			} catch (UnknownAccountException ex) {// 用户名没有找到
				error = "您输入的用户名不存在！";
			} catch (IncorrectCredentialsException ex) {// 用户名密码不匹配
				error = "用户名密码不匹配 ！";
			}
			catch(ExcessiveAttemptsException e){
				error="密码错误次数已超五次，账号锁定1小时！";
			}
			catch (AuthenticationException ex) {// 其他的登录错误
				ex.printStackTrace();
				error = "其他的登录错误  ！";
			}
			// 验证是否成功登录的方法
			if (subject.isAuthenticated()) {
				String username = (String) SecurityUtils.getSubject().getPrincipal();
				System.out.println(username); 	
				return "redirect:/main";
			} else {
				model.addAttribute("message", error);
				subject.logout();
				return "redirect:/login";
			}*/
         User user=new User();
         user.setAccount(account);
         List<User> users=userService.getUserList(user);
         if(users.size()==0){
        	 model.addAttribute("message", "用户名不存在 ！");
        	 return "redirect:/login";
         }else if(!users.get(0).getPassword().equals(password)){
        	 model.addAttribute("message", "密码不匹配 ！");
        	 return "redirect:/login";
         }else{
        	 session.setAttribute("user", users.get(0));
        	 return "redirect:/main";
         }
		}

		 
	     
	
	/**
	 * 
	 * @Title：main
	 * @Descripton：  系统主页面
	 * @data：2018年9月4日 
	 * @author：hean  
	 * @param：@return     
	 * @return：ModelAndView   
	 * @throws
	 */
	@RequestMapping(value = "/main")
	public ModelAndView main(HttpSession session) {
		User user = (User) session.getAttribute("user");
		List<Permission> permissionList = indexService.getPermissionList(user);
		ModelAndView mv = new ModelAndView("main");
		mv.addObject("permissionList", permissionList);
		mv.addObject("user", user);
		return mv;
	}
	
	/**
	 * 
	 * @Title：getResourceChild
	 * @Descripton：  获取菜单下的子菜单
	 * @data：2018年9月6日 
	 * @author：hean  
	 * @param：@param resource
	 * @param：@return     
	 * @return：List<Resource>   
	 * @throws
	 */
	@RequestMapping(value = "/getPermissionChildList")
	@ResponseBody
	public List<Permission> getResourceChild(Permission permission) {		
		List<Permission> PermissionChildList = indexService.getPermissionChildList(permission);
		return PermissionChildList;
	}
	
	
	
}
