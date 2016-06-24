package com.conDB.until;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ClassSerializable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * @Description 将字符串写入文件==生成文件
     * @author zhangyd
     * @date 2015年12月8日 下午3:29:49
     * @param filePath
     * @param content
     * @return
     */
    public static boolean saveFile(String filePath, String content) {
        return saveFile(filePath, content, "UTF-8");
    }

    /**
     * @Description 将字符串写入文件==生成文件
     * @author zhangyd
     * @date 2015年12月9日 下午1:32:30
     * @param path
     * @param content
     * @param encodeingType
     * @return
     */
    public static boolean saveFile(String path, String content, String encodeingType) {
        boolean flag = false;
        DataOutputStream dos = null;
        try {
            File file = new File(path);
            File fileParent=new File(file.getParent());
            if(!fileParent.exists()){
            	fileParent.mkdirs();
            	} 
            if (content != null && content.length() >= 0) {
                byte abyte[] = content.getBytes(encodeingType);
                dos = new DataOutputStream(new FileOutputStream(file));
                dos.write(abyte, 0, abyte.length);
                dos.flush();
                flag = true;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (dos != null) {
                close(dos);
            }
        }
        return flag;
    }
    
    public static List<String> readTxtFile(String filePath){
    	List<String> rtn=null;
        try {
        		rtn=new ArrayList<String>();
                String encoding="UTF-8";
                File file=new File(filePath);
                if(file.isFile() && file.exists()){ //判断文件是否存在
                    InputStreamReader read = new InputStreamReader(
                    new FileInputStream(file),encoding);//考虑到编码格式
                    BufferedReader bufferedReader = new BufferedReader(read);
                    String lineTxt = null;
                    while((lineTxt = bufferedReader.readLine()) != null){
                    	rtn.add(lineTxt);
                    }
                    read.close();
        }else{
            System.out.println("找不到指定的文件");
        }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        return rtn;
    }

	private static void close(DataOutputStream dos) {
		// TODO Auto-generated method stub
		try {
			dos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
