package com.base.library.ui.values;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author reber
 */
public class GenerateFileTool {

    public static String getBaseResPath() {
        return System.getProperty("user.dir") + "/Library-UI/src/main/res";
    }

    public static String getDefaultResValuePath() {
        return System.getProperty("user.dir") + "/Library-UI/src/main/res" + File.separator + "values";
    }

    public static String getDimensFileName() {
        return "dimens.xml";
    }

    public static String[] getLayoutArray() {
        return new String[]{
                "W_W", "W_M", "W_0",
                "M_W", "M_M", "M_0",
                "0_W", "0_M", "0_0"
        };
    }

    public static String getParamValue(String param) {
        switch (param) {
            case "0":
                return "0dp";
            case "W":
                return "wrap_content";
            case "M":
            default:
                return "match_parent";
        }
    }

    public static List<NameValueModel> getDefaultFontStyles() {
        List<NameValueModel> mFontStyles = new ArrayList<>();
        mFontStyles.add(new NameValueModel("RobotoBold", "roboto_bold"));
        mFontStyles.add(new NameValueModel("RobotoItalic", "roboto_italic"));
        mFontStyles.add(new NameValueModel("RobotoRegular", "roboto_regular"));
        return mFontStyles;
    }

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

    /**
     * values下的文件是否存在
     */
    public static boolean fileExist(String filePath) {
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
