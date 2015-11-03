/*
 * Copyright(c) www.baguix.com, All Rights Reserved!
 * Created by BaguixStudio on 2015/8/21.
 */
package com.baguix.utils.net;

import com.jcraft.jsch.*;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Properties;
import java.util.Vector;
/**
 * <b>SSH-FTP工具类</b><br>
 *
 * @author Scott(SG)
 */
public class SFtpTool {
    private static final Logger logger = Logger.getLogger(SFtpTool.class);
    private ChannelSftp sftp = null;

    /**
     * <b>连接服务器</b><br>
     * @param host 服务器ip地址或域名
     * @param port 端口号
     * @param username 用户名
     * @param password 密码
     */
    public void connect(String host, int port, String username, String password) {
        try {
            JSch jsch = new JSch();
            jsch.getSession(username, host, port);
            Session sshSession = jsch.getSession(username, host, port);
            sshSession.setPassword(password);
            Properties sshConfig = new Properties();
            sshConfig.put("StrictHostKeyChecking", "no");
            sshSession.setConfig(sshConfig);
            sshSession.connect();
            Channel channel = sshSession.openChannel("sftp");
            channel.connect();
            this.sftp = (ChannelSftp) channel;
            logger.info("Connected to " + host + ".");
        } catch (Exception e) {

        }
    }


    /**
     * <b>断开连接</b><br>
     */
    public void disconnect() {
        if(sftp != null){
            if(sftp.isConnected()){
                sftp.disconnect();
            }else if(sftp.isClosed()){
                logger.info("sftp is closed already");
            }
        }
    }

    /**
     * <b>上传</b><br>
     * @param directory 上传目录
     * @param uploadFile 上传文件
     */
    public void upload(String directory, String uploadFile) {
        try {
            try{
                this.sftp.cd(directory);
            }
            catch (Exception e) {
                createDir(directory);
                this.sftp.cd(directory);
            }

            File file = new File(uploadFile);
            this.sftp.put(new FileInputStream(file), file.getName());
            logger.info("Upload File: " + file.getName() );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * <b>批量上传</b><br>
     * @param directory 上传目录
     * @param fileList 上传文件列表
     */
    public void upload(String directory, List<String> fileList) {
        if (fileList != null) {
            for (String filepath : fileList) {
                File file = new File(filepath);
                if (file.isFile()) {
                    upload(directory, filepath);
                }
            }
        }
    }


    /**
     * <b>下载文件</b><br>
     * @param directory 下载目录
     * @param downloadFile 下载的文件
     * @param saveFile 存在本地的路径
     */
    public void download(String directory, String downloadFile, String saveFile) {
        try {
            this.sftp.cd(directory);
            File file = new File(saveFile);
            this.sftp.get(downloadFile, new FileOutputStream(file));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * <b>删除文件</b><br>
     * @param directory 要删除文件所在目录
     * @param deleteFile 要删除的文件
     */
    public void delete(String directory, String deleteFile) {
        try {
            this.sftp.cd(directory);
            this.sftp.rm(deleteFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * <b>批量删除文件</b><br>
     * @param directory 要删除文件所在目录
     * @param fileList 要删除的文件
     */
    public void delete(String directory, List<String> fileList) {
        for (String filepath : fileList) {
            File file = new File(filepath);
            if (file.isFile()) {
                delete(directory, filepath);
            }
        }
    }

    /**
     * <b>列出目录下的文件</b><br>
     * @param directory 要列出的目录
     * @return 多个文件
     * @throws SftpException
     */
    public Vector listFiles(String directory) throws SftpException {
        return this.sftp.ls(directory);
    }

    /**
     * 创建目录
     * @param directory 要列出的目录
     */
    private void createDir(String directory) {
        boolean bcreated = false;
        boolean bparent = false;
        File file = new File(directory);
        String ppath = file.getParent();
        try {
            this.sftp.cd(ppath);
            bparent = true;
        } catch (SftpException e1) {
            bparent = false;
        }
        try {
            if (bparent) {
                try {
                    this.sftp.cd(directory);
                    bcreated = true;
                } catch (Exception e) {
                    bcreated = false;
                }
                if (!bcreated) {
                    this.sftp.mkdir(directory);
                    bcreated = true;
                }
                return;
            } else {
                createDir(ppath);
                this.sftp.cd(ppath);
                this.sftp.mkdir(directory);
            }
        } catch (SftpException e) {
            logger.info("mkdir failed :" + directory);
            e.printStackTrace();
        }

        try {
            this.sftp.cd(directory);
        } catch (SftpException e) {
            e.printStackTrace();
            logger.info("can not cd into :" + directory);
        }

    }
}
