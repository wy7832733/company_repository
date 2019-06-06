package ${basePackage}.${moduleName}.${actionPackage};
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import ${basePackage}.${moduleName}.bean.User;
import ${basePackage}.${moduleName}.comm.PageHelper;
import ${basePackage}.${moduleName}.${entityPackage}.${entityCamelName};
import ${basePackage}.${moduleName}.${servicePackage}.${entityCamelName}Service;
import ${basePackage}.${moduleName}.util.ResultMsg;
import ${basePackage}.${moduleName}.util.oConvertUtils;
import ${basePackage}.${moduleName}.util.UUIDUtil;



/**
 * 
* @ClassName: ${entityCamelName}Controller
* @Description: ${remark?default()}管理
* @author wuyao
* @date 
*
 */
@Controller
@RequestMapping("/${entityName}")
public class ${entityCamelName}Controller {
	
	@Autowired
	private ${entityCamelName}Service ${entityName}Service;
	
	
	/**
	 * 
	 * @Title:${entityName}Manage
	 * @Descripton:  ${entityCamelName}管理列表
	 * @author: wuyao 
	 * @return:ModelAndView   
	 * @throws
	 */
	@RequestMapping(value = "/${entityName}Manage")
	@RequiresPermissions("${entityName}:view")
	public ModelAndView ${entityName}Manage() {
		return new ModelAndView("/${entityName}/list${entityCamelName}");
	}
	

	
	/**
	 * 
	 * @Title:get${entityCamelName}ListData
	 * @Descripton:  获取${entityCamelName}数据
	 * @author: wuyao 
	 * @param: ${entityName}
	 * @return:PageHelper<${entityCamelName}>   
	 * @throws
	 */
	@RequestMapping(value = "/get${entityCamelName}ListData")
	@ResponseBody
	public PageHelper<${entityCamelName}> get${entityCamelName}ListData(${entityCamelName}  ${entityName}){
		return ${entityName}Service.get${entityCamelName}ListData(${entityName});
	}
	
	
	
	/**
	 * 
	 * @Title:${entityName}Add
	 * @Descripton: 添加页面
	 * @author: wuyao 
	 * @param: id
	 * @param:model
	 * @return:ModelAndView   
	 * @throws
	 */
	@RequestMapping(value = "/${entityName}Add")
	@RequiresPermissions("${entityName}:add")
	public ModelAndView ${entityName}Add (@RequestParam(value = "id", required = false) String id, Model model) {
		if(oConvertUtils.isNotEmpty(id)) {
			model.addAttribute("id", id);
		}
		return new ModelAndView("/${entityName}/add${entityCamelName}");
	}
	
	
	
	/**
	 * 
	 * @Title:${entityName}Edit
	 * @Descripton:  修改页面
	 * @author: wuyao 
	 * @param: id
	 * @param: model
	 * @return:ModelAndView   
	 * @throws
	 */
	@RequestMapping(value = "/${entityName}Edit")
	@RequiresPermissions("${entityName}:edit")
	public ModelAndView ${entityName}Edit(@RequestParam(value = "id", required = false) String id, Model model) {
		if(oConvertUtils.isNotEmpty(id)) {
			model.addAttribute("id", id);
		}
		return new ModelAndView("${entityName}/add${entityCamelName}");
	}
	
	
	/**
	 * 
	 * @Title:insert${entityCamelName}
	 * @Descripton:  添加操作
	 * @author: wuyao 
	 * @param:${entityName}
	 * @return:Object   
	 * @throws
	 */
	@RequestMapping(value = "/insert${entityCamelName}")
	@ResponseBody
	public Object insert${entityCamelName}(${entityCamelName} ${entityName}) {
		try {
		
			//${entityName}.setId(UUIDUtil.getUUID());
			${entityName}.setDeleteFlag(0);
			${entityName}Service.insert${entityCamelName}(${entityName});
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultMsg(1, "添加失败！");
		}
		
		return new ResultMsg(0, "添加成功！");
	}
	
	
	/**
	 * 
	 * @Title:get${entityCamelName}ManageMap
	 * @Descripton:  获取数据map
	 * @author: wuyao 
	 * @param: ${entityName}
	 * @return:Object   
	 * @throws
	 */
	@RequestMapping(value = "/get${entityCamelName}ManageMap")
	@ResponseBody
	public Object get${entityCamelName}ManageMap(${entityCamelName} ${entityName}) {
		return ${entityName}Service.get${entityCamelName}ManageMap(${entityName});
	}
		
	
	/**
	 * 
	 * @Title:update${entityCamelName}
	 * @Descripton:修改操作
	 * @author: wuyao 
	 * @param: ${entityName}
	 * @return:Object   
	 * @throws
	 */
	@RequestMapping(value = "/update${entityCamelName}")
	@ResponseBody
	public Object update${entityCamelName}(${entityCamelName} ${entityName}) {
		
		try {
			${entityName}.setDeleteFlag(0);
			${entityName}Service.update${entityCamelName}(${entityName});
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultMsg(1, "修改失败！");
		}
		
		return new ResultMsg(0, "修改成功！");
	}
	
	
	/**
	 * 
	 * @Title:delete${entityCamelName}
	 * @Descripton:  删除操作
	 * @author: wuyao 
	 * @param:strJson
	 * @return:Object   
	 * @throws
	 */
	@RequestMapping(value = "/delete${entityCamelName}")
	@ResponseBody
	@RequiresPermissions("${entityName}:delete")
	public Object delete${entityCamelName}(String strJson) {
		try {
			 
			 String[] ids = strJson.split(",");
			 for(String idAll:ids) {
				${entityCamelName} ${entityName} = new ${entityCamelName}();
				${entityName}.setId(Integer.valueOf(idAll));
				${entityName}Service.delete${entityCamelName}(${entityName});
			 }
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultMsg(1, "删除失败！"); 
		}
		 return new ResultMsg(0, "删除成功！"); 
	}
	
	
	
	/**
	 * 
	 * @Title:${entityName}Detail
	 * @Descripton:详情页面
	 * @author: wuyao 
	 * @param: id
	 * @param: model
	 * @return:ModelAndView   
	 * @throws
	 */
	@RequestMapping(value = "/${entityName}Detail")
	@RequiresPermissions("${entityName}:detail")
	public ModelAndView ${entityName}Detail(@RequestParam(value = "id", required = false) String id, Model model) {
		if(oConvertUtils.isNotEmpty(id)) {
			model.addAttribute("id", id);
		}
		return new ModelAndView("${entityName}/detail${entityCamelName}");
	}
	

}
