package com.wy.code.tools.model;

public class Column {
    
    private String columnName;
    private String columnType; //数据表的类型
    private String remark;
    private String propertyName; //属性名，首字小写
    private String propertyType; //属性的java type
    private String propertyCamelName; //首字大写的属性名
    private boolean isPrimaryKey;
    private boolean isNullable;//是否允许为空
    private Long length; //字段长度
    private Object defaultValue; //字段默认值
    private boolean identity=false;//是否自增长字段
    
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getColumnType() {
		return columnType;
	}
	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	public boolean isPrimaryKey() {
		return isPrimaryKey;
	}
	public void setPrimaryKey(boolean isPrimaryKey) {
		this.isPrimaryKey = isPrimaryKey;
	}
	public String getPropertyType() {
		return propertyType;
	}
	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}
	public String getPropertyCamelName() {
		return propertyCamelName;
	}
	public void setPropertyCamelName(String propertyCamelName) {
		this.propertyCamelName = propertyCamelName;
	}
	
	public boolean isNullable() {
		return isNullable;
	}
	public void setNullable(boolean isNullable) {
		this.isNullable = isNullable;
	}
	public Long getLength() {
		return length;
	}
	public void setLength(Long length) {
		this.length = length;
	}
	
	public Object getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(Object defaultValue) {
		this.defaultValue = defaultValue;
	}
	
	public boolean getIdentity() {
		return identity;
	}
	public void setIdentity(boolean identity) {
		this.identity = identity;
	}
	
	@Override
	public String toString() {
		return "Column [columnName=" + columnName + ", columnType=" + columnType + ", remark=" + remark
				+ ", propertyName=" + propertyName + ", propertyType=" + propertyType + ", propertyCamelName="
				+ propertyCamelName + ", isPrimaryKey=" + isPrimaryKey + ", isNullable=" + isNullable + ", length="
				+ length + ", defaultValue=" + defaultValue + ", identity=" + identity + "]";
	}
    
}
