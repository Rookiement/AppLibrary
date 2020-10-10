package com.base.library.utils;

import androidx.annotation.NonNull;

import java.io.File;

/**
 * @author reber
 * on 2020/10/9 18:27
 */
public class FileUtil {

    /**
     * 判断文件是否为存在
     */
    public static boolean isFileExist(@NonNull String filePath) {
        if (StringUtil.isNullOrEmpty(filePath)) {
            return false;
        }
        File file = new File(filePath);
        return file.exists() && file.isFile();
    }

    /**
     * 判断文件夹是否存在
     */
    public static boolean isDirectoryExist(String directoryPath) {
        if (StringUtil.isNullOrEmpty(directoryPath)) {
            return false;
        }
        File file = new File(directoryPath);
        return file.exists() && file.isDirectory();
    }

    /**
     * 根据路径删除文件夹或文件
     */
    public static boolean deleteFileOrDirectory(String path) {
        if (StringUtil.isNullOrEmpty(path)) {
            return true;
        }
        return deleteFileOrDirectory(new File(path));
    }

    /**
     * 根据文件对象删除文件夹或文件
     */
    public static boolean deleteFileOrDirectory(File file) {
        if (file == null || !file.exists()) {
            return true;
        }
        // 删除文件
        if (file.isFile()) {
            return deleteFile(file);
        }
        // 如果不是文件又不是文件夹，返回失败
        if (!file.isDirectory()) {
            return false;
        }
        // 删除文件夹
        return deleteDirectory(file);
    }

    /**
     * 文件夹的删除
     */
    private static boolean deleteFile(File file) {
        return file.delete();
    }

    /**
     * 文件夹的删除
     */
    private static boolean deleteDirectory(File directoryFile) {
        File[] fileArray = directoryFile.listFiles();
        boolean deleted = true; // 判断是否删除完成
        if (!ArrayOrListUtil.isNullOrEmpty(fileArray)) {
            for (File file : fileArray) {
                if (file.isFile()) {
                    boolean deleteFile = deleteFile(file);
                    if (!deleteFile) {
                        deleted = false;
                    }
                } else if (file.isDirectory()) {
                    boolean deleteDirectory = deleteDirectory(file);
                    if (!deleteDirectory) {
                        deleted = false;
                    }
                }
            }
        }
        return deleted && directoryFile.delete();
    }
}
