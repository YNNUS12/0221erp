package com.sxt.sys.quartz;

import java.io.File;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sxt.sys.utils.AppFileUtils;

/**
 * 回收_temp的垃圾文件
 * @author LJH
 *
 */
@Component
public class RecyleTempFileTask {
	
	@Scheduled(cron="0 0 0 1/1 * ? ")
	public void recyleTempFile() {
		File file=new File(AppFileUtils.FILE_SAVE_PATH);
		//列出所有文件
		File[] listFiles = file.listFiles();
		if(null!=listFiles&&listFiles.length>0) {
			for (File f : listFiles) {
				if(f.getName().contains("_temp")) {
					f.delete();//删除文件
				}
			}
		}
	}
	
}
