package cn.cyit.traffic.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.cyit.traffic.bean.Depart;
import cn.cyit.traffic.comm.PageHelper;
import cn.cyit.traffic.service.DepartService;
import cn.cyit.traffic.util.ResultMsg;
import cn.cyit.traffic.util.oConvertUtils;
/**
 * 
* @ClassName: DepartController
* @Description: 部门管理
* @author hean
* @date 2018年9月7日
*
 */
@Controller
@RequestMapping(value = "/depart")
public class DepartController {
	
	@Autowired
	private DepartService departService;
	/**
	 * 
	 * @Title：departManage
	 * @Descripton：  部门管理列表
	 * @data：2018年9月7日 
	 * @author：hean  
	 * @param：@return     
	 * @return：ModelAndView   
	 * @throws
	 */
	@RequestMapping(value = "/departManage")
	@RequiresPermissions("depart:view")
	public ModelAndView departManage() {
		return new ModelAndView("depart/departManage");
	}
	
	/**
	 * 
	 * @Title：getDepartListData
	 * @Descripton：  获取部门数据
	 * @data：2018年9月7日 
	 * @author：hean  
	 * @param：@param depart
	 * @param：@return     
	 * @return：PageHelper<Depart>   
	 * @throws
	 */
	@RequestMapping(value = "/getDepartListData")
	@ResponseBody
	public PageHelper<Depart> getDepartListData(Depart depart) {
		return departService.getDepartListData(depart);
	}
	
	
	/**
	 * 
	 * @Title：departAdd
	 * @Descripton：  部门添加页面
	 * @data：2018年9月7日 
	 * @author：hean  
	 * @param：@param id
	 * @param：@param model
	 * @param：@return     
	 * @return：ModelAndView   
	 * @throws
	 */
	@RequestMapping(value = "/departAdd")
	@RequiresPermissions("depart:add")
	public ModelAndView departAdd(@RequestParam(value = "id", required = false) String id, Model model) {
		if(oConvertUtils.isNotEmpty(id)) {
			model.addAttribute("id", id);
		}
		return new ModelAndView("depart/departAdd");
	}
	
	
	/**
	 * 
	 * @Title：departAdd
	 * @Descripton：  部门添加页面
	 * @data：2018年9月7日 
	 * @author：hean  
	 * @param：@param id
	 * @param：@param model
	 * @param：@return     
	 * @return：ModelAndView   
	 * @throws
	 */
	@RequestMapping(value = "/departEdit")
	@RequiresPermissions("depart:edit")
	public ModelAndView departEdit(@RequestParam(value = "id", required = false) String id, Model model) {
		if(oConvertUtils.isNotEmpty(id)) {
			model.addAttribute("id", id);
		}
		return new ModelAndView("depart/departAdd");
	}
	
	
	/**
	 * 
	 * @Title：insertDepart
	 * @Descripton：  部门添加操作
	 * @data：2018年9月7日 
	 * @author：hean  
	 * @param：@param depart
	 * @param：@return     
	 * @return：Object   
	 * @throws
	 */
	@RequestMapping(value = "/insertDepart")
	@ResponseBody
	public Object insertDepart(Depart depart) {
		try {
			
			departService.insertDepart(depart);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultMsg(1, "添加失败！");
		}
		
		return new ResultMsg(0, "添加成功！");
	}
	
	/**
	 * 
	 * @Title：getDepartManageMap
	 * @Descripton：  获取部门map
	 * @data：2018年9月7日 
	 * @author：hean  
	 * @param：@param depart
	 * @param：@return     
	 * @return：Object   
	 * @throws
	 */
	@RequestMapping(value = "/getDepartManageMap")
	@ResponseBody
	public Object getDepartManageMap(Depart depart) {
		return departService.getDepartManageMap(depart);
	}
	
	
	
	/**
	 * 
	 * @Title：updateDepart
	 * @Descripton：  部门修改操作
	 * @data：2018年9月7日 
	 * @author：hean  
	 * @param：@param depart
	 * @param：@return     
	 * @return：Object   
	 * @throws
	 */
	@RequestMapping(value = "/updateDepart")
	@ResponseBody
	public Object updateDepart(Depart depart) {
		
		try {
			
			departService.updateDepart(depart);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultMsg(1, "修改失败！");
		}
		
		return new ResultMsg(0, "修改成功！");
	}
	
	
	/**
	 * 
	 * @Title：deleteDepart
	 * @Descripton：  部门删除操作
	 * @data：2018年9月7日 
	 * @author：hean  
	 * @param：@param strJson
	 * @param：@return     
	 * @return：Object   
	 * @throws
	 */
	@RequestMapping(value = "/deleteDepart")
	@ResponseBody
	@RequiresPermissions("depart:delete")
	public Object deleteDepart(String strJson) {
		try {
			 
			 String[] ids = strJson.split(",");
			 for(String idAll:ids) {
				Depart depart = new Depart();
				depart.setId(Integer.valueOf(idAll));
				depart.setDelete_flay(1);
				departService.deleteDepart(depart);
				//删除这个单位对应的所有的应持证信息
			 }
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultMsg(1, "删除失败！"); 
		}
		 return new ResultMsg(0, "删除成功！"); 
	}
	
	/**
	 *   
	 * @return
	 * @author wuyao
	 * @date 2019年3月29日下午12:52:30
	 */
	@RequestMapping("/getDepartTreeData")
	@ResponseBody
	public Object getDepartTreeData(){
		List<Depart> departs=departService.getDepartManageList();
		return departs;
	}
	
	/**
	 * @return
	 * @author wuyao
	 * @date 2019年3月29日下午12:52:30
	 */
	@RequestMapping("/getOrgTreeData")
	@ResponseBody
	public Object getOrgTreeData(){
		List<Depart> departs=departService.getDepartManageList();		
		
		List<Map<String,Object>> datas=new ArrayList<Map<String,Object>>();
		for (Depart depart : departs) {
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("id", depart.getId());
			map.put("pid", depart.getPid());
			map.put("name", depart.getDepart_name());
			map.put("type", "depart");
			datas.add(map);
		}
		return datas;
	}	
	
}
