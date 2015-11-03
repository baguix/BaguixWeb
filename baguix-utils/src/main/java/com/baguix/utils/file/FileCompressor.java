package com.baguix.utils.file;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Expand;
import org.apache.tools.ant.taskdefs.Zip;
import org.apache.tools.ant.types.FileSet;

import java.io.File;


/**
 * <b>文件/文件夹压缩器</b><br>
 *     说明：本工具类采用ant的压缩方案，详见<a href="http://ant.apache.org">Apache Ant</a>
 * @author Scott(SG)
 */
public class FileCompressor {
    // 单例模式
    private static FileCompressor instance;

    private static FileCompressor getInstance() {
        if (instance == null) {
            instance = new FileCompressor();
        }
        return instance;
    }

    // 隐藏构造函数
    private FileCompressor() {

    }

    /**
     * <b>压缩文件夹</b><br>
     * 注意：只压缩该目录下的所有文件和文件夹到zip文件中，不包括目录本身
     * @param zipfile 生成的zip文件路径
     * @param path    文件夹路径
     */
    public static void zipFolder(File zipfile, File path) {
        if (zipfile != null && path != null
                && path.exists() && path.isDirectory()) {
            Project proj = new Project();
            FileSet fileset = new FileSet();
            fileset.setProject(proj);
            fileset.setDir(path);

            Zip zip = new Zip();
            zip.setDestFile(zipfile);
            zip.setProject(proj);
            zip.addFileset(fileset);
            zip.setEncoding("UTF-8");
            zip.execute();
        }
    }

    /**
     * <b>压缩指定文件夹</b><br>
     * 注意：1. 包括目录本身一起压缩进zip包中<br>
     * 2. Windows下不能压缩整个盘，只能压缩盘中的内容<br>
     * 3.linux下不能压缩更个根目录，只能/中的内容。
     *
     * @param zipfile 生成的zip文件路径
     * @param path    文件夹路径
     */
    public static void zipWholeFolder(File zipfile, File path) {
        if (zipfile != null && path != null
                && path.exists() && path.isDirectory()) {
            Project proj = new Project();
            FileSet fileset = new FileSet();
            fileset.setProject(proj);
            File parent = path.getParentFile();
            if (parent != null && parent.exists() && parent.isDirectory()) {
                fileset.setDir(path.getParentFile());
                fileset.setIncludes(path.getName() + "/**/*");
            } else {
                fileset.setDir(path);
            }

            Zip zip = new Zip();
            zip.setDestFile(zipfile);
            zip.setProject(proj);
            zip.addFileset(fileset);
            zip.setEncoding("UTF-8");
            zip.execute();
        }
    }

    /**
     * <b>压缩文件夹</b><br>
     *
     * @param zipfile 生成的zip文件路径
     * @param path    文件夹路径
     * @see FileCompressor#zipFolder(File, File)
     */
    public static void zipFolder(String zipfile, String path) {
        if (zipfile != null && !"".equals(zipfile)
                && path != null && !"".equals(path)) {
            File zip = new File(zipfile);
            File src = new File(path);
            zipFolder(zip, src);
        }
    }

    /**
     * <b>压缩指定文件夹</b><br>
     *
     * @param zipfile 生成的zip文件路径
     * @param path    文件夹路径
     * @see FileCompressor#zipWholeFolder(File, File)
     */
    public static void zipWholeFolder(String zipfile, String path) {
        if (zipfile != null && !"".equals(zipfile)
                && path != null && !"".equals(path)) {
            File zip = new File(zipfile);
            File src = new File(path);
            zipWholeFolder(zip, src);
        }
    }

    /**
     * <b>压缩文件夹</b><br>
     *     includes，包含某种文件名通配符格式的文件均在压缩范围内。<br>
     *     例如：<br>
     *     有如下目录结构：<br>
     *         /aa/cc/test123.doc<br>
     *         /aa/testabc.doc<br>
     *         /aa/ok.doc<br>
     *     includes="**\test*.doc"<br>
     *     压缩后的zip包有：<br>
     *         /aa/cc/test123.doc<br>
     *         /aa/testabc.doc<br>
     *     若includes="test*.doc"<br>
     *     压缩后的zip包有：<br>
     *         /aa/testabc.doc<br>
     *     说明：**代表任意目录，而*是文件通配符。
     * @param zipfile 生成的zip文件路径
     * @param path    文件夹路径
     * @param includes 包含表达式，例如：**\*.doc
     */
    public static void zipFolderInclude(File zipfile, File path, String includes) {
        if (zipfile != null && path != null
                && path.exists() && path.isDirectory()) {
            Project proj = new Project();
            FileSet fileset = new FileSet();
            fileset.setProject(proj);
            fileset.setDir(path);
            fileset.setIncludes(includes);
            Zip zip = new Zip();
            zip.setDestFile(zipfile);
            zip.setProject(proj);
            zip.addFileset(fileset);
            zip.setEncoding("UTF-8");
            zip.execute();
        }
    }

    /**
     * <b>压缩文件夹</b><br>
     * @see FileCompressor#zipFolderInclude(File, File, String)
     * @param zipfile 生成的zip文件路径
     * @param path    文件夹路径
     * @param excludes 排除表达式，例如：**\*.doc
     */
    public static void zipFolderExclude(File zipfile, File path, String excludes) {
        if (zipfile != null && path != null
                && path.exists() && path.isDirectory()) {
            Project proj = new Project();
            FileSet fileset = new FileSet();
            fileset.setProject(proj);
            fileset.setDir(path);
            fileset.setExcludes(excludes);
            Zip zip = new Zip();
            zip.setDestFile(zipfile);
            zip.setProject(proj);
            zip.addFileset(fileset);
            zip.setEncoding("UTF-8");
            zip.execute();
        }
    }

    /**
     * <b>压缩一个或多个文件/文件夹</b><br>
     * 注意：如果File是一个目录，则连目录本身都一起压缩进zip根目录下
     *
     * @param zipfile 生成的zip文件
     * @param file    一个或多个文件/文件夹
     */
    public static void zipFile(File zipfile, File... file) {
        if (zipfile != null) {
            Project proj = new Project();
            Zip zip = new Zip();
            for (File f : file) {
                if (f != null && f.exists()) {
                    FileSet fileset = new FileSet();
                    fileset.setProject(proj);
                    if (f.isDirectory()) {
                        File parent = f.getParentFile();
                        if(parent!=null && parent.exists() && parent.isDirectory()) {
                            fileset.setDir(f.getParentFile());
                            fileset.setIncludes(f.getName() + "/**/*");
                        }
                        else{
                            fileset.setDir(f);
                        }
                    } else {
                        fileset.setFile(f);
                    }
                    zip.addFileset(fileset);
                }
            }
            zip.setDestFile(zipfile);
            zip.setProject(proj);
            zip.setEncoding("UTF-8");
            zip.execute();
        }
    }

    /**
     * <b>压缩一个或多个文件/文件夹</b><br>
     *
     * @param zipfile 生成的zip文件
     * @param file    一个或多个文件/文件夹
     */
    public static void zipFile(String zipfile, String... file) {
        if (zipfile != null && !"".equals(zipfile)) {
            File z = new File(zipfile);
            Project proj = new Project();
            Zip zip = new Zip();
            for (String fstr : file) {
                File f = new File(fstr);
                if (f != null && f.exists()) {
                    FileSet fileset = new FileSet();
                    fileset.setProject(proj);
                    if (f.isDirectory()) {
                        fileset.setDir(f);
                    } else {
                        fileset.setFile(f);
                    }
                    zip.addFileset(fileset);
                }
            }
            zip.setDestFile(z);
            zip.setProject(proj);
            zip.setEncoding("UTF-8");
            zip.execute();
        }
    }

    /**
     * <b>解压缩文件</b><br>
     *
     * @param zipfile 生成的zip文件
     * @param path    文件夹
     */
    public static void unZip(File zipfile, File path) {
        if (zipfile != null && path != null && zipfile.isFile()) {
            Project proj = new Project();
            Expand expand = new Expand();
            expand.setProject(proj);
            expand.setTaskType("unzip");
            expand.setTaskName("unzip");
            expand.setEncoding("UTF-8");
            expand.setSrc(zipfile);
            expand.setDest(path);
            expand.execute();
        }
    }

    /**
     * <b>解压缩文件</b><br>
     *
     * @param zipfile 生成的zip文件路径
     * @param path    文件夹路径
     */
    public static void unZip(String zipfile, String path) {
        if (zipfile != null && !"".equals(zipfile)
                && path != null && !"".equals(path)) {
            File zip = new File(zipfile);
            File src = new File(path);
            unZip(zip, src);
        }
    }
}
