/**
 * 
 */
package com.wy.code.tools.dbservice;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.wy.code.tools.Config;
import com.wy.code.tools.model.Module;
import com.wy.code.tools.model.Table;
import com.wy.code.tools.model.TableConf;

/**
 * 获取数据表和数据字段信息接口
 * @author hean
 *
 */
public interface ITableService {
	
	void setConfig(Config config);
	
	/**
     * 连接数据库获取所有表信息 
     */  
	List<TableConf> getAllTables(String pattern);
	
	 /**
     * 获取指定表信息并封装成Table对象 
     * @param tbConf 
     * @param module
     * @param con 
     */
	Table getTable(TableConf tbConf,Module module, Connection con) throws SQLException;
	
	/**
     * 获取数据表的所有字段
     * @param table
     * @param conn
     * @throws SQLException
     */
	void getTableColumns(Table table,Connection conn) throws SQLException;
	
	/**
	 * 获取表主键
	 * @param tableName
	 * @return
	 * @throws SQLException
	 */
	String getTablePrimaryKey(String tableName, Connection con) throws SQLException;
	/**
	 * 取表的联合主键
	 * @param tableName
	 * @param con
	 * @return
	 * @throws SQLException
	 */
	List<String> getTablePrimaryKeys(String tableName, Connection con) throws SQLException;
	
	/**
	 * 获取字段类型
	 * @param tableName
	 * @param column 指定列名
	 * @return
	 * @throws SQLException
	 */
	String getColumnType(Table table,String column) throws SQLException;
	
	/**
	 * 表注释
	 * @param tableName
	 * @return
	 * @throws SQLException
	 */
	String getTableRemark(String tableName, Connection con) throws SQLException;
}
