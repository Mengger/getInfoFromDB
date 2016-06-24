package com.conDB;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.common.lang.StringUtil;
import com.conDB.entry.TableForm;
import com.conDB.entry.Variable;
import com.conDB.until.GetInfoFromDB;
import com.conDB.until.StringUntilMy;

/**
 * class的生成
 * @author wb-limeng.g
 *
 */
public class GenerateClass {

		private GetInfoFromDB GetInfoFromDB;

	    // 数据库数据类型、java数据类型映射map
	    private static Map<String, String> dataTypeMapping = new HashMap<String, String>();

	    // 初始化数据库类型与java类型转换
	    static {
	        dataTypeMapping.put("int", "int");
	        dataTypeMapping.put("tinyint", "int");
	        dataTypeMapping.put("smallint", "int");
	        dataTypeMapping.put("mediumint", "int");
	        dataTypeMapping.put("integer", "int");
	        dataTypeMapping.put("bigint", "long");
	        dataTypeMapping.put("bit", "boolean");
	        dataTypeMapping.put("real", "float");
	        dataTypeMapping.put("double", "double");
	        dataTypeMapping.put("float", "float");
	        dataTypeMapping.put("float", "float");
	        dataTypeMapping.put("decimal", "BigDecimal");
	        dataTypeMapping.put("numeric", "BigDecimal");
	        dataTypeMapping.put("char", "String");
	        dataTypeMapping.put("varchar", "String");
	        dataTypeMapping.put("date", "java.util.Date");
	        dataTypeMapping.put("time", "java.util.Time");
	        dataTypeMapping.put("year", "java.util.Date");
	        dataTypeMapping.put("timestamp", "java.util.Timestamp");
	        dataTypeMapping.put("datetime", "java.util.Date");
	        dataTypeMapping.put("tinyblob", "byte[]");
	        dataTypeMapping.put("blob", "byte[]");
	        dataTypeMapping.put("mediumblob", "byte[]");
	        dataTypeMapping.put("longblob", "byte[]");
	        dataTypeMapping.put("tinytext", "String");
	        dataTypeMapping.put("text", "String");
	        dataTypeMapping.put("mediumtext", "String");
	        dataTypeMapping.put("longtext", "String");
	        dataTypeMapping.put("binary", "byte[]");
	    }
	
	    public GetInfoFromDB getGetInfoFromDB() {
			return GetInfoFromDB;
		}
	
		public void setGetInfoFromDB(GetInfoFromDB getInfoFromDB) {
			GetInfoFromDB = getInfoFromDB;
		}

	    /**
	     * @Description 生成java实体
	     * @author zhangyd
	     * @date 2015年12月9日 上午10:11:17
	     * @param tableForm
	     * @return
	     */
	    
	    public String getJava(TableForm tableForm) {
	        List<Variable> tableInfo = GetInfoFromDB.getTableInfo(tableForm);
	        tableForm.setColumnList(tableInfo);
	        String sqlString = createJava(tableInfo, tableForm);
	        return sqlString;
	    }

	    /**
	     * @Description 创建java实体字符串
	     * @author zhangyd
	     * @date 2015年12月9日 上午10:11:22
	     * @param tableInfo
	     * @param tableForm
	     * @return
	     */
	    
	    public String createJava(List<Variable> tableInfo, TableForm tableForm) {
	        StringBuilder sb = new StringBuilder();
	        // 包名
	        String packageString = createPackage(tableForm.getEntityPackage());
	        sb.append(packageString);
	        // class
	        String classString = createClass(tableForm.getSelectedTableNames());
	        sb.append(classString);
	        // 变量
	        String variablesString = createVariables(tableInfo);
	        sb.append(variablesString);
	        // SetAndGet
	        String SetAndGetString = createSetAndGet(tableInfo);
	        sb.append(SetAndGetString);
	        // 尾部
	        String tailString = createTail();
	        sb.append(tailString);
	        return sb.toString();
	    }

	    /**
	     * @Description 创建包信息
	     * @author zhangyd
	     * @date 2015年12月9日 上午10:11:28
	     * @param packagePath
	     * @return
	     */
	    
	    public String createPackage(String packagePath) {
	        StringBuilder sb = new StringBuilder();
	        sb.append("package " + packagePath + ";\r\n\r\n");
	        return sb.toString();
	    }

	    /**
	     * @Description 创建导入信息
	     * @author zhangyd
	     * @date 2015年12月9日 上午10:11:33
	     * @return
	     */
	    
	    public String createImport() {
	        StringBuilder sb = new StringBuilder();
	        sb.append("\r\n");
	        return sb.toString();
	    }

	    /**
	     * @Description 创建class名
	     * @author zhangyd
	     * @date 2015年12月9日 上午10:11:39
	     * @param tableName
	     * @return
	     */
	    
	    public String createClass(String tableName) {
	        StringBuilder sb = new StringBuilder();
	        sb.append("public class " + StringUntilMy.changeAaA(tableName) + " {\r\n");
	        return sb.toString();
	    }

	    /**
	     * @Description 创建变量
	     * @author zhangyd
	     * @date 2015年12月9日 上午10:11:55
	     * @param tableInfo
	     * @return
	     */
	    
	    public String createVariables(List<Variable> tableInfo) {
	    	
	        StringBuilder sb = new StringBuilder();
	        sb.append("\r\n");
	        for (Variable variable : tableInfo) {
	            if (StringUtil.isNotEmpty(variable.getComment())) {
	                sb.append("	/**" + "\r\n"+ "	*" + variable.getComment() + "\r\n"+ "	*/" + "\r\n");
	            }
	            sb.append("	private " + dataTypeMapping.get(variable.getType()) + " " + getPrety(variable.getColumnName())
	                    + ";\r\n\r\n");
	        }
	        sb.append("\r\n");
	        return sb.toString();
	    }
	    
	    public static String getPrety(String columnName){
	        String[] temps = columnName.split("_");
	        columnName = temps[0];
	        for (int i = 1; i < temps.length; i++) {
	        	columnName += StringUtil.capitalize(temps[i]);
	        }
	        return columnName;
    	}

	    /**
	     * @Description 创建set、get方法
	     * @author zhangyd
	     * @date 2015年12月9日 上午10:12:02
	     * @param tableInfo
	     * @return
	     */
	    
	    public String createSetAndGet(List<Variable> tableInfo) {
	        StringBuilder sb = new StringBuilder();
	        for (Variable variable : tableInfo) {
	            sb.append("	public void set" + StringUtil.capitalize(variable.getPropertyNames()) + "("
	                    + dataTypeMapping.get(variable.getType()) + " " + getPrety(variable.getColumnName()) + "){\r\n");
	            sb.append("		this." + getPrety(variable.getColumnName()) + " = " + getPrety(variable.getColumnName()) + ";\r\n");
	            sb.append("	}\r\n\r\n");

	            sb.append("	public " + dataTypeMapping.get(variable.getType()) + " get"
	                    + StringUtil.capitalize(getPrety(variable.getColumnName())) + "(){\r\n");
	            sb.append("		return this." + getPrety(variable.getColumnName()) + ";\r\n");
	            sb.append("	}\r\n\r\n");
	        }
	        return sb.toString();
	    }

	    /**
	     * @Description 创建class尾部
	     * @author zhangyd
	     * @date 2015年12月9日 上午10:12:09
	     * @return
	     */
	    
	    public String createTail() {
	        return "}";
	    }
}
