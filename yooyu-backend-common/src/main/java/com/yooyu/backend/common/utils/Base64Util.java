package com.yooyu.backend.common.utils;

import java.io.FileOutputStream;
import java.io.OutputStream;

import org.springframework.util.Base64Utils;

import com.yooyu.backend.common.exception.AppException;

public class Base64Util {
	// 图片转化成base64字符串  
	public static String GetImageStr(String imgFile) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理  
		byte[] data = Base64Utils.decodeFromString(imgFile);
		
        return Base64Utils.encodeToString(data);
    }  
  
    // base64字符串转化成图片  
	public static boolean GenerateImage(String imgStr,String targetPath) { // 对字节数组字符串进行Base64解码并生成图片  
        if (imgStr == null) // 图像数据为空  
            return false;  
        try {  
            // Base64解码  
            byte[] b = Base64Utils.decodeFromString(imgStr);
            for (int i = 0; i < b.length; ++i) {  
                if (b[i] < 0) {// 调整异常数据  
                    b[i] += 256;  
                }  
            }  
            // 生成jpeg图片  
            OutputStream out = new FileOutputStream(targetPath);  // 新生成的图片  
            out.write(b);  
            out.flush();  
            out.close();  
            return true;  
        } catch (Exception e) {  
            return false;  
        }  
    }  
    
    // base64字符串转化成字节 
	public static byte[] GenerateBytes(String imgStr) { // 对字节数组字符串进行Base64解码并生成图片  
        if (imgStr == null) // 图像数据为空  
            return null;  
        
        try {  
            // Base64解码  
            return Base64Utils.decodeFromString(imgStr);
        } catch (Exception e) {  
            throw new AppException("image cast byte error");
        }  
    }
    
    // 字节转化成 base64字符串
	public static String GenerateBase64(byte[] bytes) { // 对字节数组字符串进行Base64解码并生成图片  
        if (bytes == null){
        	// 图像数据为空  
        	return null;
        }
            
        return Base64Utils.encodeToString(bytes);
    }  
}
