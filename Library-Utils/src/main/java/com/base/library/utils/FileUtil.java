package com.base.library.utils;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Locale;

/**
 * @author reber
 * on 2020/10/9 18:27
 */
public class FileUtil {

    public static final long GB = 1073741824; // 1024 * 1024 * 1024
    public static final long MB = 1048576;    // 1024 * 1024
    public static final long KB = 1024;
    public static final int DECIMAL_COUNT = 2;

    private FileUtil() {
        throw new Error(getClass().getSimpleName() + " do not need instantiate!");
    }

    /**
     * 判断文件是否为存在
     */
    public static boolean isFileExist(@NonNull String filePath) {
        if (StringUtil.isNullOrEmpty(filePath)) {
            return false;
        }
        return isFileExist(new File(filePath));
    }

    public static boolean isFileExist(File file) {
        return file != null && file.exists() && file.isFile();
    }

    /**
     * 判断文件夹是否存在
     */
    public static boolean isDirectoryExist(String directoryPath) {
        if (StringUtil.isNullOrEmpty(directoryPath)) {
            return false;
        }
        return isDirectoryExist(new File(directoryPath));
    }

    public static boolean isDirectoryExist(File file) {
        return file != null && file.exists() && file.isDirectory();
    }

    /**
     * 文件文件
     */
    public static boolean createFile(String filePath) {
        if (StringUtil.isNullOrEmpty(filePath)) {
            return false;
        }
        return createFile(new File(filePath));
    }

    public static boolean createFile(File file) {
        if (file != null && !isFileExist(file)) {
            if (createDirectory(file.getParentFile())) {
                try {
                    return file.createNewFile();
                } catch (IOException ignored) {
                }
            }
        }
        return false;
    }

    /**
     * 创建文件夹
     */
    public static boolean createDirectory(String filePath) {
        if (StringUtil.isNullOrEmpty(filePath)) {
            return false;
        }
        return createDirectory(new File(filePath));
    }

    public static boolean createDirectory(File file) {
        if (file != null && !isDirectoryExist(file)) {
            return file.mkdirs(); // mkdirs能创建多级目录
        }
        return true;
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
     * 文件的删除
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

    /**
     * 获取文件的大小
     */
    public static int getFileSize(String filePath) {
        return getFileSize(new File(filePath));
    }

    public static String getFileSizeFormat(String filePath) {
        return getFileSizeFormat(new File(filePath), DECIMAL_COUNT, Locale.CHINA);
    }

    public static String getFileSizeFormat(String filePath, Locale locale) {
        return getFileSizeFormat(new File(filePath), DECIMAL_COUNT, locale);
    }

    public static String getFileSizeFormat(File file) {
        return getFileSizeFormat(file, DECIMAL_COUNT, Locale.CHINA);
    }

    /**
     * 根据文件获取该文件的大小
     */
    public static int getFileSize(File file) {
        FileInputStream fileInputStream = null;
        int size = 0;
        try {
            fileInputStream = new FileInputStream(file);
            size = fileInputStream.available();
        } catch (IOException ignored) {
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException ignored) {
                }
            }
        }
        return size;
    }

    /**
     * 获取格式化的文件大小，如：10.00GB，10.00MB，10.00KB，10.00B
     *
     * @param decimalCount 保留小数的个数，默认为2
     * @see #DECIMAL_COUNT
     */
    public static String getFileSizeFormat(File file, int decimalCount, Locale locale) {
        int size = getFileSize(file);
        float argsValue;
        String format;
        if (size >= GB) {
            format = String.format(locale, "%%.%dfGB", decimalCount);
            argsValue = size * 1.0f / GB;
        } else if (size >= MB) {
            format = String.format(locale, "%%.%dfMB", decimalCount);
            argsValue = size * 1.0f / MB;
        } else if (size >= KB) {
            format = String.format(locale, "%%.%dfKB", decimalCount);
            argsValue = size * 1.0f / KB;
        } else {
            format = String.format(locale, "%%.%dfB", decimalCount);
            argsValue = size * 1.0f;
        }
        return String.format(locale, format, argsValue);
    }
}
