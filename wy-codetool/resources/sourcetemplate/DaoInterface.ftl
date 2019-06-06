package ${basePackage}.${moduleName}.${daoPackage};

import java.util.List;

import ${basePackage}.${moduleName}.${entityPackage}.${entityCamelName};

/**
 * ${remark?default()}dao接口
 */
public interface ${entityCamelName}Mapper {
	
	/**
	 * 分页查询获取数据
	 * @param ${entityName}
	 */
	List<${entityCamelName}> get${entityCamelName}ListData(${entityCamelName} ${entityName});
	
	
	/**
	 * 分页查询获取数据数量
	 * @param ${entityName}
	 */
	Integer get${entityCamelName}ListDataCount(${entityCamelName} ${entityName});
	
	
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
