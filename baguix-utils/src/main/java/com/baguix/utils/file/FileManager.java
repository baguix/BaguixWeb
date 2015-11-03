package com.baguix.utils.file;

import com.baguix.utils.data.ValueTool;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.zip.CRC32;

/**
 * <b>单线程的文件管理类</b><br>
 * 未考虑多线程读写和文件锁等机制。<br>
 * 若本类不够用，可使用org.apache.commons.io.FileUtils。
 *
 * @author Scott(SG)
 */
public class FileManager {
	// 单例模式
	private static FileManager instance;
	private static FileManager getInstance(){
		if(instance == null){
			instance = new FileManager();
		}
		return instance;
	}
	// 隐藏构造函数
	private FileManager(){

	}
	/**
	 * <b>获取文件的扩展名</b><br>
	 *
	 * @param file 文件
	 * @return 文件的扩展名
	 */
	public static String getFileExtension(File file) {
		if (file.exists()) {
			String fileName = file.getName();
			if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
				return fileName.substring(fileName.lastIndexOf(".") + 1);
			}
		}
		return "";
	}

	/**
	 * <b>获取操作系统的目录分割线</b><br>
	 *     例如：Windows(\)，Linux(/)
	 * @return String
	 */
	public static String getFileSeparator(){
		Properties props = System.getProperties();
		return props.getProperty("file.separator");
	}

	/**
	 * <b>建立文件</b><br>
	 * @param path 目录
	 */
	public static void newFolder(File path) {
		if(path!=null) {
			try {

				String dir = path.getAbsolutePath().toString();
				newFolder(dir);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * <b>建立文件</b><br>
	 * @param path 目录字符串
	 */
	public static void newFolder(String path) {
		try {
			if(path!=null && !"".equals(path)) {
				File myFilePath = new File(path);
				if (!myFilePath.exists()) {
					myFilePath.mkdirs();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * <b>建立文本文件</b><br>
	 * @param file 目录和名字字符串
	 * @param content 文件的内容
	 */
	public static void newTextFile(String file, String content) {
		try {
			if(file!=null && !"".equals(file)) {
				File filepath = new File(file);
				if (!filepath.exists()) {
					newFolder(filepath.getParent());
					filepath.createNewFile();
				}
				writeStrToFile(file, content, "");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * <b>建立文本文件</b><br>
	 * @param file 目录和名字字符串
	 * @param content 文件的内容
	 * @param charset 文件的编码
	 */
	public static void newTextFile(String file, String content, String charset) {
		try {
			if(file!=null && !"".equals(file)) {
				File filepath = new File(file);
				if (!filepath.exists()) {
					newFolder(filepath.getParent());
					if(!filepath.createNewFile()){
						throw new IOException("无法新建"+filepath);
					}
				}
				writeStrToFile(file, content, charset);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * <b>将字符串写入文件</b><br>
	 * @param file 文件的物理路径，如：c:\\windows\\t.txt
	 * @param content 要写入的内容
	 * @param charset 文件的编码，若该值为null或""，则为操作系统默认编码格式文件
	 */
	public static void writeStrToFile(String file, String content, String charset) {
		if(file!=null && !"".equals(file) ) {
			File f = new File(file);
			if (f.exists()) {
				content = ValueTool.getString(content);
				try {
					FileOutputStream fos = new FileOutputStream(f);
					if (charset != null && !"".equals(charset)) {
						OutputStreamWriter outfile = new OutputStreamWriter(fos, charset);
						outfile.write(content);
						outfile.close();
					}
					else{
						charset = System.getProperty("file.encoding");
						OutputStreamWriter outfile = new OutputStreamWriter(fos, charset);
						outfile.write(content);
						outfile.close();
					}

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * <b>从文件中读取字符串</b><br>
	 * @param file 文件的物理路径，如：c:\\windows\\t.txt
	 * @return String 文件中的内容
	 */
	public static String readStrFromFile(String file, String encoding){
		StringBuilder content = new StringBuilder();
		if(file != null){
			try{
				File f = new File(file);
				if(f.exists()) {
					InputStreamReader isr = new InputStreamReader(new FileInputStream(file), encoding);
					BufferedReader br = new BufferedReader(isr);
					String s;
					while ((s = br.readLine()) != null) {
						content.append(s).append("\n");
					}
					br.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		// 删除最后的换行符(\n)
		if (content.length()>0) {
			content.delete(content.length() - 1, content.length());
		}
		return content.toString();
	}

	/**
	 * <b>删除文件</b><br>
	 * @param file 文件的物理路径，如：c:\\windows\\t.txt
	 */
	public static void delFile(String file) {
		if(file!=null && !"".equals(file)) {
			try {
				File f = new File(file);
				if(f.exists()){
					if(!f.delete()) {
						String message = "无法删除 " + f.getName() + "。";
						throw new IOException(message);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * <b>递归法删除目录下的所有文件夹</b><br>
	 *     注意 该目录仍保留
	 * @param path 文件夹路径，如：c:\\windows
	 */
	public static void cleanFolder(String path) {
		if (path != null && !"".equals(path)) {
			try {
				File file = new File(path);
				if (file.exists()) {
					if (file.isDirectory()) {
						String[] tempList = file.list();
						File temp = null;
						for (int i = 0; i < tempList.length; i++) {
							if (path.endsWith(getFileSeparator())) {
								temp = new File(path + tempList[i]);
							} else {
								temp = new File(path + getFileSeparator() + tempList[i]);
							}
							if (temp.isFile()) {
								if (!temp.delete()) {
									String message = "无法删除 " + temp.getName() + "。";
									throw new IOException(message);
								}
							}
							if (temp.isDirectory()) {
								cleanFolder(path + getFileSeparator() + tempList[i]);
								delFolder(path + getFileSeparator() + tempList[i]);
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * <b>删除文件夹</b><br>
	 * @param path 文件夹路径，如：c:\\windows
	 */
	public static void delFolder(String path) {
		if(path!=null && !"".equals(path)) {
			File file = new File(path);
			if(file.exists()) {
				try {
					cleanFolder(path);
					if (!file.delete()) {
						String message = "无法删除 " + file.getName() + "。";
						throw new IOException(message);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * <b>获得某个目录内的所有文件</b><br>
	 * @param path 文件夹路径，如：c:\\windows
	 * @return  文件列表 List&lt;file&gt;
	 */
	public static List<File> listFileFromFolder(String path) {
		List<File> list = new ArrayList<File>();
		if(path != null && !"".equals(path)){
			File dir = new File(path);
			File[] files = dir.listFiles();
			if (files!=null){
				for(File f : files){
					if (f.isFile()) {
						list.add(f);
					}
				}
			}
		}
		return list;
	}

	/**
	 * <b>获得某个目录内的所有文件名</b><br>
	 * @param path 文件夹路径，如：c:\\windows
	 * @return  文件列表，List&lt;file&gt;
	 */
	public static List<String> listFileNameFromFolder(String path) {
		List<String> list = new ArrayList<String>();
		if(path!=null && !"".equals(path)) {
			List<File> files = listFileFromFolder(path);
			for (File file : files) {
				list.add(file.getName());
			}
		}
		return list;
	}

	/**
	 * <b>获得某个目录内的所有目录名、文件名。</b><br>
	 * 例如，C盘下有名为“新建文件夹”的文件夹和名为“测试内容.txt”的文件。<br>
	 *      则返回的字符串列表如下：<br>
	 *          新建文件夹\<br>
	 *          测试内容.txt<br>
	 * 注意<br> 1.文件夹名后有反斜杠(\)标志<br>2.不列出“新建文件夹”中的子目录或文件<br>
	 * @param path 文件夹路径，如：c:\\windows
	 * @return  文件名列表，List&lt;String&gt;
	 */
	public static List<String> listFolderAndFileFromFolder(File path) {
		List<String> list  = new ArrayList<String>();
		if(path!=null) {
			File[] array = path.listFiles();
			for(File f : array){
				if (f.isDirectory()) {
					list.add(f.getName() + getFileSeparator());
				}else if (f.isFile()) {
					list.add(f.getName());
				}
			}
		}
		return list;
	}

	/**
	 * <b>递归获得某个目录内的所有目录名、文件名，并且包括子目录下的子目录和文件。</b><br>
	 * 例如，C盘下有名为“新建文件夹”的文件夹和名为“测试内容.txt”的文件，新建文件夹下有名为“新文件.txt的文件”。<br>
	 *     则返回的字符串列表如下：<br>
	 *          新建文件夹\<br>
	 *          新建文件夹\新文件.txt<br>
	 *          测试内容.txt<br>
	 * 注意<br> 1.文件夹名后有反斜杠(\)标志<br>2.会列出“新建文件夹”中的子目录或文件<br>
	 * @param path 文件夹路径，如：c:\\windows*
	 * @param base 初始路径，由于递归需要，必填
	 * @param list 文件名列表，由于递归需要，必填
	 * @return  文件名列表，List&lt;String&gt;
	 */
	public static List<String> listAllFolderAndFileFromFolder(File path, String base, List<String> list) {
		if(path!=null || base!=null) {
			if(!"".equals(base) && new File(base).exists()) {
				File[] array = path.listFiles();
				if(array!=null) {
					int pos = base.length() + 1;
					for (File f : array) {
						if (f.isDirectory()) {
							StringBuffer sbpath = new StringBuffer();
							String abspath = f.getAbsolutePath();
							sbpath.append(abspath.substring(pos, abspath.length())).append(getFileSeparator());
							list.add(sbpath.toString());
							listAllFolderAndFileFromFolder(f.getAbsoluteFile(), base, list);
						} else if (f.isFile()) {
							StringBuffer sbpath = new StringBuffer();
							String abspath = f.getAbsolutePath();
							sbpath.append(abspath.substring(pos, abspath.length()));
							list.add(sbpath.toString());
						}
					}
				}
			}
		}
		return list;
	}

	/**
	 * <b>复制文件</b><br>
	 * @param oldpath 旧文件位置
	 * @param newpath 新文件位置
	 * @param isoverwrite 是否覆盖，1覆盖，其他不覆盖
	 */
	public static void copyFile(String oldpath, String newpath, int isoverwrite) {
		if( (oldpath!=null && !"".equals(oldpath)) || (newpath!=null && !"".equals(newpath)) ) {
			try {
				int bytesum = 0;
				int byteread = 0;
				File oldfile = new File(oldpath);
				if (oldfile.exists()) {
					File newfile = new File(newpath);
					File newfilefolder = newfile.getParentFile();
					if(!newfilefolder.exists()){
						newFolder(newfilefolder);
					}
					if( (newfile.exists() && isoverwrite==1) || !newfile.exists()) {
						InputStream inStream = new FileInputStream(oldpath);
						FileOutputStream fs = new FileOutputStream(newpath);
						byte[] buffer = new byte[1024];
						while ((byteread = inStream.read(buffer)) != -1) {
							bytesum += byteread;
							fs.write(buffer, 0, byteread);
						}
						inStream.close();
						fs.close();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * <b>复制文件夹</b><br>
	 * @param oldpath 旧文件位置
	 * @param newpath 新文件位置
	 */
	public static void copyFolder(String oldpath, String newpath) {
		try {
			(new File(newpath)).mkdirs();
			File a = new File(oldpath);
			String[] file = a.list();
			File temp = null;
			for (int i = 0; i < file.length; i++) {
				if (oldpath.endsWith(File.separator)) {
					temp = new File(oldpath + file[i]);
				} else {
					temp = new File(oldpath + File.separator + file[i]);
				}
				if (temp.isFile()) {
					FileInputStream input = new FileInputStream(temp);
					FileOutputStream output = new FileOutputStream(newpath
							+ getFileSeparator() + (temp.getName()).toString());
					byte[] buffer = new byte[1024 * 5];
					int len;
					while ((len = input.read(buffer)) != -1) {
						output.write(buffer, 0, len);
					}
					output.flush();
					output.close();
					input.close();
				}
				if (temp.isDirectory()) {
					copyFolder(oldpath + getFileSeparator() + file[i], newpath + getFileSeparator() + file[i]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * <b>移动文件</b><br>
	 *     采用先copy后del的方法，避免IO缓冲错误
	 * @param oldpath 旧文件位置
	 * @param newpath 新文件位置
	 */
	public static void moveFile(String oldpath, String newpath) {
		copyFile(oldpath, newpath, 1);
		delFile(oldpath);
	}
	/**
	 * <b>移动文件夹</b><br>
	 *     采用先copy后del的方法，避免IO缓冲错误
	 * @param oldpath 旧文件位置
	 * @param newpath 新文件位置
	 */
	public static void moveFolder(String oldpath, String newpath) {
		copyFolder(oldpath, newpath);
		delFolder(oldpath);
	}

	/**
	 * <b>获取文件的MD5校验码</b><br>
	 *     注意：<br>
	 *     1.因为按字节读取，所以仅适用于较小的文件<br>
	 *     2.在1MB大小以上的文件，使用md5速度要快，1MB以下的小文件，SHA-1速度要快些
	 * @param file 文件（File类型）
	 * @return String MD5校验码
	 */
	public static String getFileMd5(File file){
		String value = "";
		if(file != null && file.isFile()) {
			MessageDigest digest = null;
			FileInputStream in = null;
			byte buffer[] = new byte[8192];
			int len;
			try {
				digest = MessageDigest.getInstance("MD5");
				in = new FileInputStream(file);
				while((len = in.read(buffer)) != -1){
					digest.update(buffer,0,len);
				}
				BigInteger bigInt = new BigInteger(1, digest.digest());
				return bigInt.toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				try{
					in.close();
				}
				catch (Exception e){
					e.printStackTrace();
				}
			}
		}
		return value;
	}

	/**
	 * <b>获取文件的SHA-1校验码</b><br>
	 *     注意：<br>
	 *     1.因为按字节读取，所以仅适用于较小的文件<br>
	 *     2.在1MB大小以上的文件，使用md5速度要快，1MB以下的小文件，SHA-1速度要快些
	 * @param file 文件（File类型）
	 * @return String SHA-1校验码
	 */
	public static String getFileSha1(File file){
		String value = "";
		if(file != null && file.isFile()) {
			MessageDigest digest = null;
			FileInputStream in = null;
			byte buffer[] = new byte[8192];
			int len;
			try {
				digest = MessageDigest.getInstance("SHA-1");
				in = new FileInputStream(file);
				while((len = in.read(buffer)) != -1){
					digest.update(buffer,0,len);
				}
				BigInteger bigInt = new BigInteger(1, digest.digest());
				return bigInt.toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				try{
					in.close();
				}
				catch (Exception e){
					e.printStackTrace();
				}
			}
		}
		return value;
	}
}
