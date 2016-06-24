package com.conDB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.conDB.entry.TableForm;
import com.conDB.entry.Variable;
import com.conDB.until.ClassSerializable;
import com.conDB.until.GetInfoFromDB;
import com.conDB.until.StringUntilMy;

public class GenerateMain {
	private static GenerateClass generateClass;
	private static GetInfoFromDB getInfoFromDB;
	static String readPath="C:\\Users\\wb-limeng.g\\Desktop\\configure.txt";
	static String savePath;
	
	public static void generateJava(){
		ArrayList<String> getPrepty=(ArrayList<String>) ClassSerializable.readTxtFile(readPath);
		TableForm tableForm=new TableForm();
		for(String prety:getPrepty){
			if(prety.contains("dbAddress")){
				tableForm.setDbAddress(prety.replace("dbAddress=", "").replace(";", "").replace("\"", ""));
			}else if(prety.contains("dbLoginName")){
				tableForm.setDbLoginName(prety.replace("dbLoginName=", "").replace(";", "").replace("\"", ""));
			}else if(prety.contains("dbPassword")){
				tableForm.setDbPassword(prety.replace("dbPassword=", "").replace(";", "").replace("\"", ""));
			}else if(prety.contains("selectedDbNames")){
				tableForm.setSelectedDbNames(prety.replace("selectedDbNames=", "").replace(";", "").replace("\"", ""));
			}else if(prety.contains("entityPackage")){
				tableForm.setEntityPackage(prety.replace("entityPackage=", "").replace(";", "").replace("\"", ""));
			}else if(prety.contains("savePath")){
				savePath=prety.replace("savePath=", "").replace(";", "").replace("\"", "");
			}
		}

		generateClass=new GenerateClass();
		getInfoFromDB=new GetInfoFromDB();
		
		List<String> tables=getInfoFromDB.getTableNameList(tableForm);
		List<TableForm> needGenerateTables=new ArrayList<TableForm>();
		for (String tableName:tables) {
			TableForm table=new TableForm();
			table.setDbAddress(tableForm.getDbAddress());
			table.setDbLoginName(tableForm.getDbLoginName());
			table.setDbPassword(tableForm.getDbPassword());
			table.setEntityPackage(tableForm.getEntityPackage());
			table.setSelectedDbNames(tableForm.getSelectedDbNames());
			table.setSelectedTableNames(tableName);
			needGenerateTables.add(table);
		}
		
		//生成entry
		Map<String,String> classMap=new HashMap<String, String>();
		for(TableForm tf:needGenerateTables){
			generateClass.setGetInfoFromDB(getInfoFromDB);
			String getJave=generateClass.getJava(tf);
			//classMap.put(StringUntilMy.changeAaA(tf.getSelectedTableNames()), getJave);
		}
		
		//序列化entry
		for(String key:classMap.keySet()){
			ClassSerializable.saveFile(savePath+key+".java", classMap.get(key));
		}
		
		for (TableForm tf:needGenerateTables) {
			GenerateConfig dd=new GenerateConfig();
			dd.setTable(tf);
			dd.generate();
			ClassSerializable.saveFile(savePath+StringUntilMy.changeAaA(tf.getSelectedTableNames())+".java",dd.getSb().toString());
		}
		
	}
	
	public static void main(String[] args) {
		generateJava();
	}
	
}
