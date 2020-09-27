package com.base.library.ui.values;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author reber
 */
public class GenerateFileTool {

    public final static String BASE_RES_PATH = System.getProperty("user.dir") + "/Library-UI/src/main/res";
    public final static String DEFAULT_RES_VALUE_PATH = BASE_RES_PATH + File.separator + "values";
    /**
     * 默认的dimens.xml文件路径
     */
    public final static String DEFAULT_DIMENS_PATH = DEFAULT_RES_VALUE_PATH + File.separator + "dimens.xml";

    public static StringBuilder getStartStringBuilder() {
        return new StringBuilder()
                .append("<?xml version=\"1.0\" encoding=\"utf-8\"?>")
                .append("\n")
                .append("<resources>");
    }

    public static StringBuilder getEndStringBuilder(StringBuilder stringBuilder) {
        stringBuilder
                .append("</resources>")
                .append("\n");
        return stringBuilder;
    }

    public static String getDimensAutoParentPath(int swValue) {
        //parent path: "../main/res/values-sw300dp"
        return "values-sw" + swValue + "dp";
    }


    /**
     * values下的文件是否存在
     */
    public static boolean isFileExist(String filePath) {
        return new File(filePath).exists();
    }

    /**
     * 二级目录、文件的创建和保存
     */
    public static void saveContentToFile(String fileParentPath, String childPath, String content) {
        if (createParentFile(fileParentPath)) {
            if (createChildFile(fileParentPath, childPath)) {
                writeToFile(fileParentPath, childPath, content);
            }
        }
    }

    /**
     * 创建目录
     */
    private static boolean createParentFile(String fileParentPath) {
        File parentFile = new File(fileParentPath);
        if (!parentFile.exists()) {
            return parentFile.mkdir() || parentFile.mkdir();
        }
        return true;
    }

    /**
     * 创建文件
     */
    private static boolean createChildFile(String parentPath, String childPath) {
        File childFile = new File(parentPath, childPath);
        if (!childFile.exists()) {
            try {
                return childFile.createNewFile() || childFile.createNewFile();
            } catch (IOException e) {
                return false;
            }
        }
        return true;
    }

    /**
     * 保存values下文件的内容
     */
    private static void writeToFile(String fileParentPath, String childPath, String content) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(new File(fileParentPath, childPath)));
            writer.write(content);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
