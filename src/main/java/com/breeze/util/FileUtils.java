package com.breeze.util;

import lombok.extern.slf4j.Slf4j;

import java.io.File;

/**
 * @author Created by wdnnccey
 * @date 2019-06-27 14:15
 * @Description: 文件操作
 */
@Slf4j
public class FileUtils {

    /**
     * 判断指定的文件或文件夹删除是否成功
     * @param filePath 文件或文件夹的路径
     * @return true or false 成功返回true，失败返回false
     */
    public static boolean deleteFolder(String filePath) {

        File file = new File(filePath);

        if (!file.exists()) {
            log.info("文件" + filePath + "不存在，删除失败！");
            return false;
        } else { //要删除的文件存在

            if (file.isFile()) {

                return deleteFile(filePath);

            } else {  //如果目标文件是目录
                return deleteDir(filePath);
            }
        }
    }

    /**
     * 判断指定的文件删除是否成功
     *
     * @param fileName 文件路径
     * @return true or false 成功返回true，失败返回false
     */
    public static boolean deleteFile(String fileName) {

        File file = new File(fileName);

        if (file.exists() && file.isFile()) {

            if (file.delete()) {
                log.info("文件" + fileName + "删除成功！");
                return true;
            } else {
                log.info("文件" + fileName + "删除失败！");
                return false;
            }
        } else {

            log.info("文件" + fileName + "不存在，删除失败！");
            return false;
        }
    }

    /**
     * 删除指定的目录以及目录下的所有子文件
     *
     * @param dirName is 目录路径
     * @return true or false 成功返回true，失败返回false
     */
    public static boolean deleteDir(String dirName) {

        if (dirName.endsWith(File.separator)) {
            dirName = dirName + File.separator;
        }
        File dirFile = new File(dirName);

        if (!dirFile.exists() || (!dirFile.isDirectory())) {
            log.info("目录删除失败" + dirName + "目录不存在！");
            return false;
        }

        File[] fileArrays = dirFile.listFiles();
        for (File f : fileArrays) {
            FileUtils.deleteFolder(f.getAbsolutePath());
        }
        if (dirFile.delete()) {
            log.info("目录" + dirName + "删除成功！");
        }
        return true;
    }
}
