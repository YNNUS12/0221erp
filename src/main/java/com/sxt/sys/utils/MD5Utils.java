package com.sxt.sys.utils;

import java.util.UUID;

import org.apache.shiro.crypto.hash.Md5Hash;

public class MD5Utils {
	
	
	
	/**
	 * 生成密码
	 */
	public static String md5(String source,Object salt,Integer hashIterations) {
		Md5Hash md5Hash=new Md5Hash(source, salt, hashIterations);
		return md5Hash.toString();
	}
	/**
	 * 生成盐
	 * 
	 */
	public static String createSalt() {
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}

}
