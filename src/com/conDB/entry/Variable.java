package com.conDB.entry;

/**
 * 表字段的详细情况
 * @author wb-limeng.g
 *
 */
public class Variable {
	/**
	 * 列名称
	 */
	private String columnName;
	/**
	 * 数据类型
	 */
	private String type;
	/**
	 * 注释
	 */
	private String comment;
	/**
	 * 主建
	 */
	private String columnKey;
	/**
	 * 属性名
	 */
	private String propertyNames;
	public String getPropertyNames() {
		return propertyNames;
	}
	public void setPropertyNames(String propertyNames) {
		this.propertyNames = propertyNames;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getColumnKey() {
		return columnKey;
	}
	public void setColumnKey(String columnKey) {
		this.columnKey = columnKey;
	}
	
	
	
}
