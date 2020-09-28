package com.base.library.ui.values.style;

import com.base.library.ui.values.GenerateFileTool;
import com.base.library.ui.values.NameValueModel;

import java.io.File;
import java.util.List;

/**
 * @author reber
 */
public class GenerateFontStyle {

    private GenerateFontStyle() {
        throw new ExceptionInInitializerError(
                getClass().getSimpleName() + " can't created,this is a tool file.");
    }

    public static void main(String[] args) {
        generateFontStyles();
    }

    private static void generateFontStyles() {
        // 如果没有默认的values目录,不设置值
        if (!GenerateFileTool.isFileExist(GenerateFileTool.getDefaultResValuePath())) {
            System.out.println("--------------请先创建values目录--------------");
            return;
        }

        // 如果没有默认的color.xml,不设置值
        if (!GenerateFileTool.isFileExist(GenerateFileTool.getDefaultResValuePath() +
                File.separator + GenerateFileTool.getColorFileName())) {
            System.out.println("--------------请在color.xml中先生成需要的color--------------");
            return;
        }

        final StringBuilder sBuilder = GenerateFileTool.getStartStringBuilder();
        String startContent = sBuilder.toString();
        List<NameValueModel> fontList = GenerateFileTool.getDefaultFontStyles();
        for (NameValueModel model : fontList) {
            sBuilder.setLength(0);
            sBuilder.append(startContent).append("\n\r");
            insertFontItem(sBuilder, model);
            sBuilder.append("\n");
            String content = GenerateFileTool.getEndStringBuilder(sBuilder).toString();
            GenerateFileTool.saveContentToFile(GenerateFileTool.getDefaultResValuePath(), getDefaultFontFileName(model), content);
        }
    }

    private static String getDefaultFontFileName(NameValueModel fontNameModel) {
        return "font_" + fontNameModel.getValue() + "_styles.xml";
    }

    /**
     * <style name="RobotoBold">
     * <item name="android:fontFamily">@font/roboto_bold</item>
     * </style>
     */
    private static void insertFontItem(StringBuilder sBuilder, NameValueModel fontNameModel) {
        // <style name="RobotoBold.White.18">
        sBuilder
                .append("    <style name=\"")
                .append(fontNameModel.getName())
                .append("\">")
                .append("\n");

        // <item name="android:fontFamily">@font/roboto_bold</item>
        // <item name="android:textColor">@color/appTextWhite</item>
        sBuilder.append("        <item name=\"android:fontFamily\">@font/")
                .append(fontNameModel.getValue())
                .append("</item>")
                .append("\n");

        // </style>
        sBuilder.append("    </style>");

        List<NameValueModel> fontColorList = GenerateFileTool.getFontColorArray();
        for (NameValueModel model : fontColorList) {
            sBuilder.append("\n\r");
            insertFontColorItem(sBuilder, fontNameModel, model);
        }
    }

    /**
     * <style name="RobotoBold.White">
     * <item name="android:fontFamily">@font/roboto_bold</item>
     * <item name="android:textColor">@color/appTextWhite</item>
     * </style>
     */
    private static void insertFontColorItem(StringBuilder sBuilder, NameValueModel fontNameModel,
                                            NameValueModel fontColorModel) {
        // <style name="RobotoBold.White.18">
        sBuilder
                .append("    <style name=\"")
                .append(fontNameModel.getName())
                .append(".")
                .append(fontColorModel.getName())
                .append("\">")
                .append("\n");

        // <item name="android:fontFamily">@font/roboto_bold</item>
        // <item name="android:textColor">@color/appTextWhite</item>
        sBuilder
                .append("        <item name=\"android:textColor\">@color/")
                .append(fontColorModel.getValue())
                .append("</item>")
                .append("\n");

        // </style>
        sBuilder.append("    </style>");

        String[] fontsSizeArray = GenerateFileTool.getFontSizeArray();
        for (String size : fontsSizeArray) {
            sBuilder.append("\n\r");
            insertFontColorSizeItem(sBuilder, fontNameModel, fontColorModel, size);
        }
    }

    /**
     * <style name="RobotoBold.White.18">
     * <item name="android:fontFamily">@font/roboto_bold</item>
     * <item name="android:textColor">@color/appTextWhite</item>
     * <item name="android:textSize">@dimen/sp_18</item>
     * </style>
     */
    private static void insertFontColorSizeItem(StringBuilder sBuilder, NameValueModel fontNameModel,
                                                NameValueModel fontColorModel, String fontSize) {
        // <style name="RobotoBold.White.18">
        sBuilder
                .append("    <style name=\"")
                .append(fontNameModel.getName())
                .append(".")
                .append(fontColorModel.getName())
                .append(".")
                .append(fontSize)
                .append("\">")
                .append("\n");

        // <item name="android:fontFamily">@font/roboto_bold</item>
        // <item name="android:textColor">@color/appTextWhite</item>
        // <item name="android:textSize">@dimen/sp_18</item>
        sBuilder
                .append("        <item name=\"android:textColor\">@color/")
                .append(fontColorModel.getValue())
                .append("</item>")
                .append("\n")
                .append("        <item name=\"android:textSize\">@dimen/sp_")
                .append(fontSize)
                .append("</item>")
                .append("\n");

        // </style>
        sBuilder.append("    </style>");
    }
}
