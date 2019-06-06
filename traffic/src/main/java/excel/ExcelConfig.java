package excel;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 解析xml工具类
 * @author PCCW-80352891
 *
 */
@SuppressWarnings("rawtypes")
public class ExcelConfig {

	 /**entity map对象，key:name ,value:entity的属性map集**/
	private Map entityMap ;
	
	/**column map 对象，key:entityName_colName , value:column的属性map集 **/
	private Map columnMap;
	
	/**rule map 对象，key:entityName_colName_ruleName, value: rule 的map集：找到一行rule**/
	private Map ruleMap ;
	
	/**rules map 对象, key:entityName_colName, value: rules 的map集:找到该column下所有的rule**/
	private Map  columnRulesMap ;
	
	/**entity--column map: key:entityName, value: column list:根据实体类名得到所有的列**/
	private Map columnListMap ;
	
	private Map tables;
	
   /**column list**/
	private List columnList ;
    /**开始解析xml文件**/
	public ExcelConfig(File xmlFilePath){
		FileInputStream in = null;
		try {
			if(xmlFilePath == null){
				 throw new FileNotFoundException();
			}
			SAXReader reader = new SAXReader();		           
           	in = new FileInputStream(xmlFilePath);	           	
			Document doc = reader.read(in);
			Element root = doc.getRootElement();	
			Iterator itEntity = root.elements("sheet").iterator();
			while(itEntity.hasNext()){
				Element entity = (Element) itEntity.next();
				parseEntity(entity);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public ExcelConfig(String xmlcontent) throws Exception{
			if(xmlcontent.indexOf("<excel>") < 0){
				 throw new Exception("excel config not xml");
			}
			SAXReader reader = new SAXReader();		           
			Document doc = reader.read(new ByteArrayInputStream(xmlcontent.getBytes("UTF-8")));
			Element root = doc.getRootElement();	
			Iterator itEntity = root.elements("sheet").iterator();
			while(itEntity.hasNext()){
				Element entity = (Element) itEntity.next();
				parseEntity(entity);
			}
	}
	
	 /**开始解析entity**/
	@SuppressWarnings("unchecked")
	public void parseEntity(Element entity){
		if(entity != null){
			
			/**对数据进行初始化设置**/
			columnListMap = new HashMap();
			columnMap = new HashMap();
			entityMap = new HashMap();
			ruleMap = new HashMap();
			columnRulesMap = new HashMap();
			columnList = new ArrayList();
			tables=new HashMap();
			Map colsMap=new LinkedHashMap();
			setEntityMap(entity);			
			String entityName = entity.attributeValue("name");
			String entityCode = entity.attributeValue("code");
			Iterator itColumn = entity.elements("column").iterator();
			while(itColumn.hasNext()){
				Element column = (Element) itColumn.next();
				setColumnMap(entityName,column);
				colsMap.put(column.attributeValue("code"), column.attributeValue("name"));
			}
			tables.put(entityCode, colsMap);
			columnListMap.put(entityName, columnList);
		}
	}
	 
	
	
	/**将entity放入entityMap中**/
	@SuppressWarnings("unchecked")
	public void setEntityMap(Element entity){		
		Map ent = new HashMap();
		String name = entity.attributeValue("name");
		String code = entity.attributeValue("code");
		String headstart = entity.attributeValue("head");
		String datastart = entity.attributeValue("data");
		String drlsname = entity.attributeValue("drlsname");
		ent.put("head", headstart==null?0:Integer.parseInt(headstart));
		ent.put("data", headstart==null?1:Integer.parseInt(datastart));
		ent.put("drlsname", drlsname);
		ent.put("name", name);
		ent.put("code", code);
		entityMap.put(name, ent);
	}
	
	/**将column放入columnMap中**/
	@SuppressWarnings("unchecked")
	public void setColumnMap(String entityName,Element column){
		if(column != null){		
			Map col = new HashMap();
			String name = column.attributeValue("name");
			String code = column.attributeValue("code");
			String type = column.attributeValue("type");
			col.put("name", name);
			col.put("code", code);
			col.put("type", type);
			String columnMapKey = entityName+"_"+name;    //eg:  用户表_用户名
			columnMap.put(columnMapKey, col);		
			columnList.add(col);
			Iterator ruleIt = column.elements("rules").iterator();  //获得rules
			while(ruleIt.hasNext()){
				Element rules = (Element)ruleIt.next(); 
    			Iterator rule  = rules.elements("rule").iterator();   //获得 rule
    			while(rule.hasNext()){
    				Element ruleValid = (Element) rule.next();     //获得每一行rule
    				setRuleMap(entityName,name,ruleValid);    				
    			}
			}
		}
	}
		
    /**将 rule 验证规则放入ruleMap中**/
	@SuppressWarnings("unchecked")
	public void setRuleMap(String entityName,String columnName,Element ruleValid){
		if(ruleValid != null){			
			String ruleName = ruleValid.attributeValue("name");
			String ruleMsg = ruleValid.attributeValue("message");
			String ruleValue = ruleValid.attributeValue("value");
			Map ruleValidMap = new HashMap();
			ruleValidMap.put("name", ruleName);
			ruleValidMap.put("message", ruleMsg);
			ruleValidMap.put("value", ruleValue);
			String ruleStrKey = entityName+"_"+columnName+"_"+ruleName;
			String colStrKey = entityName+"_"+columnName;
			if(this.getColumnRulesMap().containsKey(colStrKey)){
    			List valids = (List) this.getColumnRulesMap().get(colStrKey);
    			valids.add(ruleValidMap);
    		}else{
    			List valids = new ArrayList();
    			valids.add(ruleValidMap);
    			this.columnRulesMap.put(colStrKey, valids);  //将每个column下的所有rules存入该map中
    		}
			ruleMap.put(ruleStrKey, ruleValidMap); //将每个column下的一条rule存入该map中
		}
	}
	
	/**主方法**/
	public static void main(String[] args) {
		File file = new File("src/user.xml");
       new ExcelConfig(file);		
	}

	/**所有的get set 方法**/
	
	public Map getEntityMap() {
		return entityMap;
	}


	public Map getColumnMap() {
		return columnMap;
	}


	public Map getRuleMap() {
		return ruleMap;
	}


	public Map getColumnRulesMap() {
		return columnRulesMap;
	}

	public Map getColumnListMap() {
		return columnListMap;
	}

	public Map getTables() {
		return tables;
	}
	
	


}
