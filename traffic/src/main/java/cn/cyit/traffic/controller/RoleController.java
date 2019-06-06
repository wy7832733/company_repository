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

import cn.cyit.traffic.bean.Permission;
import cn.cyit.traffic.bean.Role;
import cn.cyit.traffic.bean.RolePermission;
import cn.cyit.traffic.comm.PageHelper;
import cn.cyit.traffic.service.RoleService;
import cn.cyit.traffic.util.ResultMsg;
import cn.cyit.traffic.util.oConvertUtils;

/**
 * 
* @ClassName: RoleController
* @Description: 角色管理
* @author hean
* @date 2018年9月7日
*
 */
@Controller
@RequestMapping(value = "/role")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	/**
	 * 
	 * @Title：roleManage
	 * @Descripton：  橘色管理菜单
	 * @data：2018年9月7日 
	 * @author：hean  
	 * @param：@return     
	 * @return：ModelAndView   
	 * @throws
	 */
	@RequestMapping(value = "/roleManage")
	@RequiresPermissions("role:view")
	public ModelAndView roleManage() {
		return new ModelAndView("role/roleManage");
	}
	
	/**
	 * 
	 * @Title：getRoleListData
	 * @Descripton：  角色管理数据
	 * @data：2018年9月8日 
	 * @author：hean  
	 * @param：@param role
	 * @param：@return     
	 * @return：PageHelper<Role>   
	 * @throws
	 */
	@RequestMapping(value = "/getRoleListData")
	@ResponseBody
	public PageHelper<Role> getRoleListData(Role role) {
		return roleService.getRoleListData(role);
	}
	
	/**
	 * 
	 * @Title：roleAdd
	 * @Descripton：  角色添加页面
	 * @data：2018年9月8日 
	 * @author：hean  
	 * @param：@param id
	 * @param：@param model
	 * @param：@return     
	 * @return：ModelAndView   
	 * @throws
	 */
	@RequestMapping(value = "/roleAdd")
	@RequiresPermissions("role:add")	
	public ModelAndView roleAdd(@RequestParam(value = "id" ,required = false) String id,Model model) {
		if(oConvertUtils.isNotEmpty(id)) {
			model.addAttribute("id", id);
		}
		
		return new ModelAndView("role/roleAdd");
	}
	
	/**
	 * 
	 * @Title：roleEdit
	 * @Descripton：  角色修改页面
	 * @data：2018年9月8日 
	 * @author：hean  
	 * @param：@param id
	 * @param：@param model
	 * @param：@return     
	 * @return：ModelAndView   
	 * @throws
	 */
	@RequestMapping(value = "/roleEdit")
	@RequiresPermissions("role:edit")	
	public ModelAndView roleEdit(@RequestParam(value = "id" ,required = false) String id,Model model) {
		if(oConvertUtils.isNotEmpty(id)) {
			model.addAttribute("id", id);
		}
		
		return new ModelAndView("role/roleAdd");
	}
	
	/**
	 * 
	 * @Title：getRoleManageMap
	 * @Descripton：  获取角色信息
	 * @data：2018年9月8日 
	 * @author：hean  
	 * @param：@param role
	 * @param：@return     
	 * @return：Object   
	 * @throws
	 */
	@RequestMapping(value = "/getRoleManageMap")
	@ResponseBody
	public Object getRoleManageMap(Role role) {
		return roleService.getRoleManageMap(role);
	}
	
	
	/**
	 * 
	 * @Title：getPermissionManageList
	 * @Descripton：  获取菜单信息
	 * @data：2018年9月8日 
	 * @author：hean  
	 * @param：@param role
	 * @param：@return     
	 * @return：Object   
	 * @throws
	 */
	@RequestMapping(value = "/getPermissionManageList")
	@ResponseBody
	public List<Permission> getPermissionManageList() {
		return roleService.getPermissionManageList();
	}
	
	
	/**
	 * 
	 * @Title：inserRole
	 * @Descripton：  角色添加操作
	 * @data：2018年9月8日 
	 * @author：hean  
	 * @param：@param role
	 * @param：@return     
	 * @return：Object   
	 * @throws
	 */
	@RequestMapping(value = "/inserRole")
	@ResponseBody
	public Object inserRole(Role role) {
		
		try {
			
			roleService.inserRole(role);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultMsg(1, "添加失败！");
		}
		
		return new ResultMsg(0, "添加成功！");
	}
	
	
	/**
	 * 
	 * @Title：updateRole
	 * @Descripton：  角色修改操作
	 * @data：2018年9月8日 
	 * @author：hean  
	 * @param：@param role
	 * @param：@return     
	 * @return：Object   
	 * @throws
	 */
	@RequestMapping(value = "/updateRole")
	@ResponseBody
	public Object updateRole(Role role) {
		
		try {
			
			roleService.updateRole(role);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultMsg(1, "修改失败！");
		}
		
		return new ResultMsg(0, "修改成功！");
	}
	
	/**
	 * 
	 * @Title：deleteRole
	 * @Descripton：  删除角色
	 * @data：2018年9月8日 
	 * @author：hean  
	 * @param：@param role
	 * @param：@return     
	 * @return：Object   
	 * @throws
	 */
	@RequestMapping(value = "/deleteRole")
	@ResponseBody
	@RequiresPermissions("role:delete")
	public Object deleteRole(String strJson) {
		
		try {
			
			String[] ids = strJson.split(",");
			 for(String idAll:ids) {
				Role role = new Role();
				role.setId(Integer.valueOf(idAll));
				roleService.deleteRole(role);
			 }
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultMsg(1, "删除失败！");
		}
		
		return new ResultMsg(0, "删除成功！");
	}
	
	/**
	 * 
	 * @Title：addRolePermission
	 * @Descripton：  角色权限配置页面
	 * @data：2018年9月8日 
	 * @author：hean  
	 * @param：@return     
	 * @return：ModelAndView   
	 * @throws
	 */
	@RequestMapping(value = "/addRolePermission")
	public ModelAndView addRolePermission(int id,Model model) {
		model.addAttribute("id", id);
		return new ModelAndView("role/rolePermission");
	}
	
	
	/**
	 * 
	 * @Title：getPermissionByRole
	 * @Descripton：  获取角色对应权限
	 * @data：2018年9月8日 
	 * @author：hean  
	 * @param：@param role
	 * @param：@return     
	 * @return：List<RolePermission>   
	 * @throws
	 */
	@RequestMapping(value = "/getPermissionByRole")
	@ResponseBody
	public List<RolePermission>  getPermissionByRole(Role role) {
		List<RolePermission> list = roleService.getPermissionByRole(role);
		return list;
	}
	
	
	/**
	 * 
	 * @Title：saveRolePermission
	 * @Descripton：  角色权限添加操作
	 * @data：2018年9月8日 
	 * @author：hean  
	 * @param：@param rolePermission
	 * @param：@param Str
	 * @param：@return     
	 * @return：Object   
	 * @throws
	 */
	@RequestMapping(value = "/saveRolePermission")
	@ResponseBody
	public Object saveRolePermission(RolePermission rolePermission,String Str) {
		try {
			
			roleService.deletePermissionByRole(rolePermission);
			Str = Str.substring(0, Str.length()-1);
			String[] strs = Str.split(",");
			for(int i=0;i<strs.length;i++){
				rolePermission.setPermission_id(Integer.valueOf(strs[i]));
				roleService.saveRolePermission(rolePermission);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultMsg(1,"操作失败，请重试！");
		}
		
		return new ResultMsg(0,"操作成功");
	}
	
	/**
	 * 跟新此角色的可见部门  
	 * @param role
	 * @return
	 * @author wuyao
	 * @date 2019年4月8日下午1:10:31
	 */
	@RequestMapping("updateVisualDep")
	@ResponseBody
	public Object updateVisualDep(Role role){
		roleService.updateRole(role);
		return new ResultMsg(0);
	}
}
