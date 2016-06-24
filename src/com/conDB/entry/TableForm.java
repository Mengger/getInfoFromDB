package com.conDB.entry;

import java.util.ArrayList;
import java.util.List;

public class TableForm {
	/**
	 * 数据库地址
	 */
	private String dbAddress;
	/**
	 * 登录名
	 */
	private String dbLoginName;
	/**
	 * 密码
	 */
	private String dbPassword;
	/**
	 * 数据库名字
	 */
	private String selectedDbNames;
	/**
	 * 表名
	 */
	private String SelectedTableNames;
	/**
	 * 包名
	 */
	private String entityPackage;
	
	/**
	 * dao层package
	 */
	private String daoPackage;
	
	/**
	 * 数据库 列
	 */
	private List<Variable> columnList=new ArrayList<Variable>();
	
	
	public String getDaoPackage() {
		return daoPackage;
	}

	public void setDaoPackage(String daoPackage) {
		this.daoPackage = daoPackage;
	}

	public void addColumn(Variable column){
		columnList.add(column);
	}
	
	public List<Variable> getColumnList() {
		return columnList;
	}
	public void setColumnList(List<Variable> columnList) {
		this.columnList = columnList;
	}
	public String getSelectedTableNames() {
		return SelectedTableNames;
	}
	public void setSelectedTableNames(String selectedTableNames) {
		SelectedTableNames = selectedTableNames;
	}
	public String getDbAddress() {
		return dbAddress;
	}
	public void setDbAddress(String dbAddress) {
		this.dbAddress = dbAddress;
	}
	public String getDbLoginName() {
		return dbLoginName;
	}
	public void setDbLoginName(String dbLoginName) {
		this.dbLoginName = dbLoginName;
	}
	public String getDbPassword() {
		return dbPassword;
	}
	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}
	public String getSelectedDbNames() {
		return selectedDbNames;
	}
	public void setSelectedDbNames(String selectedDbNames) {
		this.selectedDbNames = selectedDbNames;
	}
	public String getEntityPackage() {
		return entityPackage;
	}
	public void setEntityPackage(String entityPackage) {
		this.entityPackage = entityPackage;
	}
}
