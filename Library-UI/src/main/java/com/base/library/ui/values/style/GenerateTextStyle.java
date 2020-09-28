package com.base.library.ui.values.style;

import com.base.library.ui.values.GenerateFileTool;
import com.base.library.ui.values.NameValueModel;

import java.io.File;
import java.util.List;

/**
 * @author reber
 */
public class GenerateTextStyle {

    private GenerateTextStyle() {
        throw new ExceptionInInitializerError(
                getClass().getSimpleName() + " can't created,this is a tool file.");
    }

    public static void main(String[] args) {
        generateTextStyle();
    }

    private static void generateTextStyle() {

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

        String titleName = "TextStyle";
        sBuilder.append("\n\r").append("    <style name=\"").append(titleName).append("\" />");

        List<NameValueModel> colorList = GenerateFileTool.getFontColorArray();
        for (NameValueModel model : colorList) {
            sBuilder.append("\n\r");
            insertColorItem(sBuilder, titleName, model);
        }

        sBuilder.append("\n");
        String content = GenerateFileTool.getEndStringBuilder(sBuilder).toString();
        GenerateFileTool.saveContentToFile(GenerateFileTool.getDefaultResValuePath(),
                GenerateFileTool.getTextStyleFileName(), content);
    }

    private static void insertColorItem(StringBuilder sBuilder, String titleName, NameValueModel colorModel) {
        // <style name="TextStyle.White">
        sBuilder
                .append("    <style name=\"")
                .append(titleName)
                .append(".")
                .append(colorModel.getName())
                .append("\">")
                .append("\n");

        // <item name="android:fontFamily">@font/roboto_bold</item>
        // <item name="android:textColor">@color/appTextWhite</item>
        sBuilder
                .append("        <item name=\"android:textColor\">@color/")
                .append(colorModel.getValue())
                .append("</item>")
                .append("\n");

        // </style>
        sBuilder.append("    </style>");

        String[] fontsSizeArray = GenerateFileTool.getFontSizeArray();
        for (String size : fontsSizeArray) {
            sBuilder.append("\n\r");
            insertColorSizeItem(sBuilder, titleName, colorModel, size);
        }
    }

    /**
     * <style name="TextStyle.White.18">
     * <item name="android:textColor">@color/appTextWhite</item>
     * <item name="android:textSize">@dimen/sp_18</item>
     * </style>
     */
    private static void insertColorSizeItem(StringBuilder sBuilder, String titleName,
                                            NameValueModel colorModel, String fontSize) {
        // <style name="TextStyle.White.18">
        sBuilder
                .append("    <style name=\"")
                .append(titleName)
                .append(".")
                .append(colorModel.getName())
                .append(".")
                .append(fontSize)
                .append("\">")
                .append("\n");

        // <item name="android:textSize">@dimen/sp_18</item>
        sBuilder
                .append("        <item name=\"android:textSize\">@dimen/sp_")
                .append(fontSize)
                .append("</item>")
                .append("\n");

        // </style>
        sBuilder.append("    </style>");
    }
}
