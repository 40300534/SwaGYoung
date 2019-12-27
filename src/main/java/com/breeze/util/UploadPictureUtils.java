package com.breeze.util;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

/**
 * @author 莫奇
 */
@Component
public class UploadPictureUtils {

    public static String generateUUIDProductImageFilePath(String fileName) {
        String filePath = "";
        if (fileName != null) {
            // 获取文件后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            // 文件上传地址前缀
            Date dt = new Date();
            String year = String.format("%tY", dt);
            String mon = String.format("%tm", dt);
            String day = String.format("%td", dt);
            String hour = String.format("%tH", dt);
            //文件日期目录
            String datePath = "a/"+ year + "/" + mon + "/" + day + "/"+ hour + "/";
            //最新的排序号码+后缀.jpg
            //图片名称UUID
            String uuidFileName = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();

            //最新的排序号码
            filePath = datePath + uuidFileName + suffixName;
        }
        return filePath;
    }

    public static String generateUUIDUserAvaterFilePath(String fileName, Long userId) {
        String filePath = "";
        if (fileName != null) {
            // 获取文件后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            // 文件上传地址前缀
            Date dt = new Date();
            String year = String.format("%tY", dt);
            String mon = String.format("%tm", dt);
            //文件日期目录
            String datePath = "b/"+ year + "/" + mon + "/" ;
            //最新的排序号码+后缀.jpg
            //图片名称UUID
            String uuidFileName = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();

            //最新的排序号码
            filePath = datePath  + userId + "_"  + uuidFileName + suffixName;
        }
        return filePath;
    }

    public static String generateUUIDUserImageFilePath(String fileName, Long userId) {
        String filePath = "";
        if (fileName != null) {
            // 获取文件后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            // 文件上传地址前缀
            Date dt = new Date();
            String year = String.format("%tY", dt);
            String mon = String.format("%tm", dt);
            String day = String.format("%td", dt);
            //文件日期目录
            String datePath ="c/" + year + "/" + mon + "/" + day + "/";
            //最新的排序号码+后缀.jpg
            //图片名称UUID
            String uuidFileName = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();

            //最新的排序号码
            filePath = datePath + userId + "/" + uuidFileName + suffixName;
        }
        return filePath;
    }



    public static void uploadPicture(String url, MultipartFile file, String uploadImage) {
        String newUrl = url.substring(0, url.lastIndexOf("/"));
        // 磁盘保存
        BufferedOutputStream out = null;
        try {
            File folder = new File(uploadImage + newUrl);
            if (!folder.exists()) {
                folder.mkdirs();
                folder.createNewFile();
            }



            out = new BufferedOutputStream(new FileOutputStream(uploadImage + url));
            out.write(file.getBytes());
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
