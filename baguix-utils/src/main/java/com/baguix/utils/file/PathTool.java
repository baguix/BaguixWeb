package com.baguix.utils.file;

import org.apache.commons.lang3.SystemUtils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * <b>路径工具</b>
 *
 * @author Scott(SG)
 */
public class PathTool {
	// 单例
	private static PathTool instance;
	private static PathTool getInstance(){
		if(instance == null){
			instance = new PathTool();
		}
		return instance;
	}

	// 网站根目录
	private static String webroot;
	// 网站WEB-INF目录
	private static String webinf;
	// classes目录
	private static String classes;
	// JAVA_HOME目录
	private static String jrehome;

	/**
	 * <b>根据类获取该类的物理路径</b><br>
	 *     例如：getPath(PathTool.class)=D:/workspace/com/baguix/utils/file
	 * @param clazz 类
	 * @return 类所在的物理位置
	 */
	@SuppressWarnings("rawtypes")
	public static String getPath(Class clazz) {
		String path = clazz.getResource("").getPath();
		try {
			String url = URLDecoder.decode(path,"UTF-8");
			File f = new File(url);
			return f.getAbsolutePath();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * * <b>根据对象获取该对象的物理路径</b><br>
	 *     例如：getPath(pathtool)=D:/workspace/com/baguix/utils/file
	 * @param object 对象
	 * @return 对象所在的物理位置
	 */
	public static String getPath(Object object) {
		String path = object.getClass().getResource("").getPath();
		try {
			String url = URLDecoder.decode(path,"UTF-8");
			File f = new File(url);
			return f.getAbsolutePath();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * <b>如果是webapp项目，返回webapp根目录/的物理路径</b><br>
	 *     例如：baguixweb是webapp目录，getWebRootPath()=D:/tomcat/webapp/baguixweb
	 * @return webapp根目录
	 */
	public static String getWebRootPath() {
		File webinfodir = new File(getWebInfPath());
		if(webinfodir.isDirectory()){
			return webinfodir.getParent();
		}
		return webroot;
	}

	/**
	 * <b>如果是webapp项目，返回/WEB-INF目录的物理路径</b><br>
	 *     例如：getWebInfPath()=D:/tomcat/webapp/baguixweb/WEB-INF
	 * @return webapp根目录
	 */
	public static String getWebInfPath() {
		File clazzdir = new File(getClassesPath());
		if(clazzdir.isDirectory()){
			return clazzdir.getParent();
		}
		return webinf;
	}

	/**
	 * <b>如果是webapp项目，返回/WEB-INF/classes目录的物理路径</b><br>
	 *     例如：getClassesPath()=D:/tomcat/webapp/baguixweb/WEB-INF
	 * @return webapp根目录
	 */
	public static String getClassesPath() {
		try {
			String path = PathTool.class.getClassLoader().getResource("").toURI().getPath();
			classes = new File(path).getAbsolutePath();
		} catch (Exception e) {
			String path = PathTool.class.getClassLoader().getResource("").getPath();
			classes = new File(path).getAbsolutePath();
		}
		return classes;
	}

	/**
	 * <b>获得JRE_HOME目录</b><br>
	 *     例如：getJreHomePath()=C:\Program Files\Java\jdk1.7.0_76\jre
	 * @return webapp根目录
	 */
	public static String getJreHomePath() {
		jrehome = SystemUtils.getJavaHome().getAbsolutePath();
		return jrehome;
	}
}
