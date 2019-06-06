package ${basePackage}.${moduleName}.${servicePackage}${serviceImplPackage};

import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ${basePackage}.${moduleName}.comm.PageHelper;
import ${basePackage}.${moduleName}.${entityPackage}.${entityCamelName};
import ${basePackage}.${moduleName}.${servicePackage}.${entityCamelName}Service;
import ${basePackage}.${moduleName}.${daoPackage}.${entityCamelName}Mapper;

/**
 * ${remark?default()}实现类
 */
@Service
@Transactional
public class ${entityCamelName}ServiceImpl implements ${entityCamelName}Service {

	Logger logger = LoggerFactory.getLogger(${entityCamelName}ServiceImpl.class);

	@Autowired
	private ${entityCamelName}Mapper ${entityName}Mapper;
	
	@Override
	public PageHelper<${entityCamelName}> get${entityCamelName}ListData(${entityCamelName} ${entityName}) {
		PageHelper<${entityCamelName}> pageHelper = new PageHelper<${entityCamelName}>();
		// 统计总记录数
		Integer total = ${entityName}Mapper.get${entityCamelName}ListDataCount(${entityName});
		pageHelper.setTotal(total);
		// 查询当前页实体对象
		List<${entityCamelName}> list = ${entityName}Mapper.get${entityCamelName}ListData(${entityName});
		pageHelper.setRows(list);
		return pageHelper;
	}
	
	
	@Override
	public ${entityCamelName} get${entityCamelName}ManageMap(${entityCamelName} ${entityName}) {
		return ${entityName}Mapper.get${entityCamelName}ManageMap(${entityName});
	}
	
	
	@Override
	public void insert${entityCamelName}(${entityCamelName} ${entityName}) {
		${entityName}Mapper.insert${entityCamelName}(${entityName});
		
	}

	@Override
	public void update${entityCamelName}(${entityCamelName} ${entityName}) {
		${entityName}Mapper.update${entityCamelName}(${entityName});
	}

	@Override
	public void delete${entityCamelName}(${entityCamelName} ${entityName}) {
		${entityName}Mapper.delete${entityCamelName}(${entityName});
		
	}

	@Override
	public List<${entityCamelName}> get${entityCamelName}ManageList(${entityCamelName} ${entityName}) {
		return ${entityName}Mapper.get${entityCamelName}ManageList(${entityName});
	}

}
