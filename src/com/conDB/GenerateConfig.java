package com.conDB;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.common.lang.StringUtil;
import com.conDB.entry.TableForm;
import com.conDB.entry.Variable;
import com.conDB.until.StringUntilMy;

/**
 * 创建dao
 * @author wb-limeng.g
 *
 */
public class GenerateConfig {

	private TableForm table;

	private StringBuffer sb=new StringBuffer();
	
	private List<Variable> keyVar=new ArrayList<Variable>();
	private List<Variable> normalVar=new ArrayList<Variable>();
	public StringBuffer getSb() {
		return sb;
	}

	public void setSb(StringBuffer sb) {
		this.sb = sb;
	}

	public TableForm getTable() {
		return table;
	}

	public void setTable(TableForm table) {
		this.table = table;
	}
	
	public void generate(){
		generateHead();
		generateQueryListMethod();//查询符合条件的list
		generateUdateIntoMethod();//更新entry
		generateInsertEnteyMetnod();//动态插入
		generateEnd();
	}
	
	public void generateHead(){
		sb.append("package ").append(table.getDaoPackage()).append(";\r\n\r\n");
		sb.append("import java.util.List;").append("\r\n\r\n");
		sb.append("import org.apache.ibatis.annotations.Result;").append("\r\n\r\n");
		sb.append("import org.apache.ibatis.annotations.Results;").append("\r\n\r\n");
		sb.append("import org.apache.ibatis.annotations.Select;").append("\r\n\r\n");
		sb.append("import org.springframework.stereotype.Repository;").append("\r\n\r\n");
		sb.append("import ").append(table.getEntityPackage()).append(".").append(StringUntilMy.changeAaA(table.getSelectedTableNames())).append("\r\n\r\n");
		sb.append("\r\n\r\n");
		sb.append("@Repository(\"").append(table.getSelectedTableNames()).append("_dao\")");
		sb.append("\r\n\r\n");
		sb.append("public interface ").append(StringUntilMy.changeAaA(table.getSelectedTableNames())).append("Dao").append(" {\r\n\r\n");
	}
	
	public void generateQueryListMethod(){
		sb.append("\t").append("@Select(\"<script>select * from ").append(table.getSelectedTableNames()).append(" where 1=1 \"").append("\r\n\r\n");
		
		StringBuffer bfNormal=new StringBuffer();
		StringBuffer bfKey=new StringBuffer();
		StringBuffer bfResult=new StringBuffer();
		
		for(Variable v:table.getColumnList()){
			bfResult.append("\t").append("\t").append("@Result(property=\\\"").append(StringUntilMy.changeabA(v.getColumnName())).append("\\\",column=\\\"").append(v.getColumnName()).append("\\\")").append("\r\n\r\n");
			if(StringUtil.isBlank(v.getColumnKey())){
				bfNormal.append("\t").append("\t").append("+\"<if test=\\\"").append(StringUntilMy.changeabA(v.getColumnName())).append(" !=null \\\">and ");
				bfNormal.append(v.getColumnName()).append(" = #{").append(StringUntilMy.changeabA(v.getColumnName())).append("} </if>\"").append("\r\n\r\n");
				normalVar.add(v);
			}else{
				keyVar.add(v);
				bfKey.append("\t").append("\t").append("+\"<if test=\\\"").append(StringUntilMy.changeabA(v.getColumnName())).append(" !=null \\\">and ");
				bfKey.append(v.getColumnName()).append(" = #{").append(StringUntilMy.changeabA(v.getColumnName())).append("} </if>\"").append("\r\n\r\n");
			}
		}
		sb.append(bfKey).append(bfNormal).append("\t").append("</script>\")").append("\r\n\r\n");
		
		
		sb.append("\t").append("@Results({").append("\r\n\r\n");
		sb.append(bfResult).append("\t").append("})").append("\r\n\r\n");
		
		
		sb.append("\t").append("List<").append(StringUntilMy.changeAaA(table.getSelectedTableNames())).append("> query");
		sb.append(StringUntilMy.changeAaA(table.getSelectedTableNames())).append("List(").append(StringUntilMy.changeAaA(table.getSelectedTableNames()));
		sb.append(" ").append(StringUntilMy.changeabA(table.getSelectedTableNames())).append(");");
		sb.append("\r\n\r\n").append("\r\n\r\n");
	}
	
	public void generateUdateIntoMethod(){
		sb.append("\r\n\r\n");
		sb.append("\t").append("@Update(\"<script>update ").append(table.getSelectedTableNames()).append(" \"");
		sb.append("\r\n\r\n");
		sb.append("\t\t").append("+\"<set>\"");
		sb.append("\r\n\r\n");
		for(Variable v:normalVar){
			sb.append("\t\t\t");
			sb.append("+ \\\"<if test=\\\"").append(StringUntilMy.changeabA(v.getColumnName())).append(" !=null \\\"> ")
				.append(v.getColumnName()).append(" = #{")
				.append(StringUntilMy.changeabA(v.getColumnName())).append("} ,</if>\"");
			sb.append("\r\n\r\n");
		}
		sb.append("\t\t");
		sb.append("+\"</set>\"");
		sb.append("\r\n\r\n");
		sb.append("\t\t");
		sb.append("+\"where 1=1 ");
		sb.append("\"");
		sb.append("\r\n\r\n");
		for(Variable v:keyVar){
			sb.append("\t\t");
			sb.append("+ \"<if test=\\\"").append(StringUntilMy.changeabA(v.getColumnName()))
				.append(" !=null \\\"> and ").append(v.getColumnName()).append(" = #{")
				.append(StringUntilMy.changeabA(v.getColumnName())).append("} </if>\"");
			sb.append("\r\n\r\n");
		}
		sb.append("\t");
		sb.append("+ \"</script>\")");
		sb.append("\r\n\r\n");
		sb.append("\t");
		sb.append("int update").append(StringUntilMy.changeAaA(table.getSelectedTableNames()))
			.append("(").append(StringUntilMy.changeAaA(table.getSelectedTableNames())).append(" ")
			.append(StringUntilMy.changeabA(table.getSelectedTableNames())).append(");");
		sb.append("\r\n\r\n");
	}

	public void generateInsertEnteyMetnod(){
		
	}
	public void generateEnd(){
		sb.append("}");
	}
}
