package com.sxt.sys.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.sxt.sys.constast.SysConstast;

/**
 * 文件的工具类
 * 
 * @author LJH
 *
 */
public class AppFileUtils {

	public static String FILE_SAVE_PATH = "E:/upload/";

	static {
		InputStream stream = AppFileUtils.class.getClassLoader().getResourceAsStream("file.properties");
		System.out.println(stream + "-----------------------------------");
		Properties properties = new Properties();
		try {
			properties.load(stream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		FILE_SAVE_PATH = properties.getProperty("fileSavePath");

	}

	/**
	 * 下载文件
	 * 
	 * @param file
	 *            文件对象
	 * @param oldName
	 *            文件老名字
	 * @return
	 */
	public static ResponseEntity<Object> downloadFile(File file, String oldName) {
		// 把文件转字节数组
		byte[] bytes = null;
		try {
			bytes = FileUtils.readFileToByteArray(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 创建封装响应头信息的对象
		HttpHeaders header = new HttpHeaders();
		// 封装响应内容类型(APPLICATION_OCTET_STREAM 响应的内容不限定)
		header.setContentType(MediaType.APPLICATION_OCTET_STREAM);

		// 处理下载文件名中文的问题
		try {
			oldName = URLEncoder.encode(oldName, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		// 设置下载的文件的名称
		header.setContentDispositionFormData("attachment", oldName);
		// 创建ResponseEntity对象
		ResponseEntity<Object> entity = new ResponseEntity<Object>(bytes, header, HttpStatus.CREATED);
		return entity;
	}

	/**
	 * 根据文件名修改文件去掉_temp   
	 * 
	 */
	public static String updateFileName(String fileName) {
		if (fileName.contains("_temp")) {
			File file = new File(FILE_SAVE_PATH, fileName);
			file.renameTo(new File(FILE_SAVE_PATH, fileName.replace("_temp", "")));
			return fileName.replace("_temp", "");
		} else {
			return fileName;
		}
	}

	/**
	 * 根据文件名删除文件
	 * 
	 * @param carimg
	 */
	public static void deleteFileByName(String img) {
		File file = new File(FILE_SAVE_PATH + img);
		if (!img.equals(SysConstast.GOODS_DEFAULT_IMAGE)) {
			// 判断文件是否存在
			if (file.exists()) {
				file.delete();
			}
		}
	}
	
	/**
	 * 导出文件时的公用方法
	 * 
	 * @param is
	 *            文件流
	 * @param fileName
	 *            文件名注意后缀
	 * @return
	 */
	public static ResponseEntity<Object> downloadFile(ByteArrayOutputStream bos, String fileName) {
		// 把文件转字节数组
		byte[] bytes=bos.toByteArray();
		// 创建封装响应头信息的对象
		HttpHeaders header = new HttpHeaders();
		// 封装响应内容类型(APPLICATION_OCTET_STREAM 响应的内容不限定)
		header.setContentType(MediaType.APPLICATION_OCTET_STREAM);

		// 处理下载文件名中文的问题
		try {
			fileName = URLEncoder.encode(fileName, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		// 设置下载的文件的名称
		header.setContentDispositionFormData("attachment", fileName);
		// 创建ResponseEntity对象
		ResponseEntity<Object> entity = new ResponseEntity<Object>(bytes, header, HttpStatus.CREATED);
		return entity;
	}

}
