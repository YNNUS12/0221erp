package com.sxt.sys.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class RandomUtils {

	
	private static SimpleDateFormat format1=new SimpleDateFormat("yyyyMMddHHmmssSSS");
	private static SimpleDateFormat format2=new SimpleDateFormat("yyyyMMdd");
	private static SimpleDateFormat format3=new SimpleDateFormat("yyyyMMdd_HHmmss_SSS");
	private static Random random=new Random();
	
	/**
	 * 根据日期+时间+随机数生成一个字符串    201905281111113004564
	 * @param oldName  文件老名字
	 * @return
	 */
	public static String createFileNameUseTime(String oldName) {
		//得到文件后缀
		String end=oldName.substring(oldName.lastIndexOf("."), oldName.length());
		//生成时间字符串
		String time=format1.format(new Date());
		//生成四位随机数
		Integer num=random.nextInt(9000)+1000;//0-8999
		return time+"_"+num+end;
	}
	
	/**
	 * 根据UUID生成一个字符串    ASDDFAEREAWSFDSGASDFASDFASDFASD.JPG
	 * @param oldName  文件老名字
	 * @return
	 */
	public static String createFileNameUseUUID(String oldName) {
		//得到文件后缀
		String end=oldName.substring(oldName.lastIndexOf("."), oldName.length());
		String uuid=UUID.randomUUID().toString().replace("-", "").toUpperCase();
	
		return uuid+end;
	}

	/**
	 * 返回当前的日期子符串
	 * @return  yyyyMMdd
	 */
	public static String createDirNameUseDate() {
		return format2.format(new Date());
	}

	/**
	 * 根据前缀生成字符串
	 * @param idPrefixCz
	 * @return
	 */
	public static String createRandomStringWithPrefix(String prefix) {
		return prefix+"_"+format3.format(new Date())+(random.nextInt(90000)+10000);
	}

}
