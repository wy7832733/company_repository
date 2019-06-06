package ${basePackage}.${moduleName}.${servicePackage};

import java.util.Map;
import java.util.List;

import ${basePackage}.${moduleName}.comm.PageHelper;
import ${basePackage}.${moduleName}.${entityPackage}.${entityCamelName};

/**
 * ${remark?default()}接口
 */
public interface ${entityCamelName}Service {

	/**
	 * 分页查询
	 * @param ${entityName}
	 */
	PageHelper<${entityCamelName}> get${entityCamelName}ListData(${entityCamelName} ${entityName});
	
	/**
	 * 根据主键查询Map
	 * @param ${entityName}
	 */
	${entityCamelName} get${entityCamelName}ManageMap(${entityCamelName} ${entityName});
	
	
	/**
	 * 新增保存操作
	 * @param ${entityName}
	 */
	void insert${entityCamelName}(${entityCamelName} ${entityName});
	
	/**
	 * 修改操作
	 * @param ${entityName}
	 */
	void update${entityCamelName}(${entityCamelName} ${entityName});

	/**
	 * 删除操作
	 * @param ${entityName}
	 */
	void delete${entityCamelName}(${entityCamelName} ${entityName});
	
	/**
	 * 获取数据list
	 * @param ${entityName}
	 */
	List<${entityCamelName}> get${entityCamelName}ManageList(${entityCamelName} ${entityName});

}
