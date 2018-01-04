package com.origin.gener;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * 项目路径管理
 * @author Siber.xu
 *
 */
public class PathUtils {

	private static PathUtils instance = null;
	
	public static PathUtils getInstance() {
		if(instance == null){
			instance = new PathUtils();
		}
		return instance;
	}
	
	/**
	 * 获取项目根目录
	 * @return
	 */
	public String getWebRootPath(){
		String path = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
		int index = path.indexOf("classes");
		path = path.substring(0, index);
		if (path.startsWith("zip")) {// 当class文件在war中时，此时返回zip:D:/...这样的路径
			path = path.substring(4);
		} else if (path.startsWith("file")) {// 当class文件在class文件中时，此时返回file:/D:/...这样的路径
			path = path.substring(6);
		} else if (path.startsWith("jar")) {// 当class文件在jar文件里面时，此时返回jar:file:/D:/...这样的路径
			path = path.substring(10);
		}
		try {
			path = URLDecoder.decode(path, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return null;
		}
		return path;
	}
	/**
	 * 获取项目WEBINF目录
	 * @return
	 */
	public String getWEBINFPath(){
		
		return this.getWebRootPath()+ "WEB-INF" + File.separator;
	}
	
	/**
	 * 获取模板的目录
	 * @return
	 */
	public String getTempPath(){
		return this.getWebRootPath()+ "classes" + File.separator;
	}
	/**
	 * 获取项目CLASS目录
	 * @return
	 */
	public String getClassPath(){
//		String oss = System.getProperty("os.name") ;
//		String classPath;
//		if(oss.indexOf("Windows")==-1){//针对Linux JBOSS环境
//			String userHome = "";
//			userHome = System.getenv("JBOSS_HOME");
//			classPath = userHome +File.separator+".settings"+File.separator;
//		}else{
//			classPath  = this.getWEBINFPath() + "classes" + File.separator;
//		}
//		return classPath;
		return this.getWEBINFPath() + "classes" + File.separator;
	}
}
