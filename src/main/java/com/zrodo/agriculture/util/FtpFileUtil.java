package com.zrodo.agriculture.util;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;


@Component
public class FtpFileUtil {
    private static String FTP_ADDRESS;
    private static int FTP_PORT;
    private static String FTP_USERNAME;
    private static String FTP_PASSWORD;
    private static String FTP_BASEPATH;
    private static String FTP_PATH;


    //ftp服务器ip地址
    @Value("${ftp.address}")
    public void setFtpAddress(String ftpAddress) {
        FTP_ADDRESS = ftpAddress;
    }

    //端口号
    @Value("${ftp.port}")
    public void setFtpPort(int ftpPort) {
        FTP_PORT = ftpPort;
    }

    //用户名
    @Value("${ftp.username}")
    public void setFtpUsername(String ftpUsername) {
        FTP_USERNAME = ftpUsername;
    }

    //密码
    @Value("${ftp.password}")
    public void setFtpPassword(String ftpPassword) {
        FTP_PASSWORD = ftpPassword;
    }

    //图片路径
    @Value("${ftp.basePath}")
    public void setFtpBasepath(String ftpBasepath) {
        FTP_BASEPATH = ftpBasepath;
    }

    //图片路径
    @Value("${ftp.path}")
    public void setFtpPath(String ftpPath) {
        FTP_PATH = ftpPath;
    }

    public static boolean uploadFile(String originFileName, InputStream input, Integer type) throws IOException {
        boolean success = false;
        FTPClient ftp = new FTPClient();
        ftp.setControlEncoding("GBK");
        try {
            int reply;
            ftp.connect(FTP_ADDRESS, FTP_PORT);// 连接FTP服务器
            ftp.login(FTP_USERNAME, FTP_PASSWORD);// 登录
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return false;
            }
            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
//            ftp.makeDirectory(FTP_BASEPATH);
            String[] array = FTP_PATH.split(",");
            ftp.changeWorkingDirectory(FTP_BASEPATH + array[type - 1] + '/');
            ftp.storeFile(originFileName, input);
            input.close();
            ftp.logout();
            success = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                    System.out.println(ioe);
                }
            }
        }
        return success;
    }
}
