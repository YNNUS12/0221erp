package com.sxt.sys.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sxt.sys.utils.AppFileUtils;
import com.sxt.sys.utils.RandomUtils;

@Controller
@RequestMapping("file")	
public class FileController {
	
	

	/**
	 * 文件下载的方法
	 */
	@RequestMapping("downloadFile")
	public ResponseEntity<Object> downloadFile(String path){
		String fileName="aa"+path.substring(path.lastIndexOf("."), path.length());
		//构造文件对象
		File file=new File(AppFileUtils.FILE_SAVE_PATH+path);
		if(file.exists()) {
			ResponseEntity<Object> entity = AppFileUtils.downloadFile(file, fileName);
			return entity;
		}else {
			return null;
		}
	}
	
	/**
	 * 文件上传
	 */
	@RequestMapping("uploadFile")
	@ResponseBody
	public Map<String,Object> uploadFile(MultipartFile mf){
		 Map<String,Object> map=new HashMap<>();
		//得到文件保存的路径FILE_SAVE_PATH
		//得到文件老名字
		String oldName=mf.getOriginalFilename();
		//得到新名字
		String newName=RandomUtils.createFileNameUseUUID(oldName);
		//得到文件夹名
		String dirName = RandomUtils.createDirNameUseDate();
		//构造文件夹对象
		File dirFile=new File(AppFileUtils.FILE_SAVE_PATH,dirName);
		if(!dirFile.exists()) {
			dirFile.mkdirs();//创建文件夹
		}
		//构造文件
		File file=new File(dirFile, newName+"_temp");
		//保存
		try {
			mf.transferTo(file);
			map.put("path", dirName+"/"+newName+"_temp");
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			map.put("ccc", "error");
		}
		return map;
	}
	
}
