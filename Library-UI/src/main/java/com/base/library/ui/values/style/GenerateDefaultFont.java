package com.base.library.ui.values.style;


import com.base.library.ui.values.GenerateFileTool;
import com.base.library.ui.values.NameValueModel;

import java.util.List;

/**
 * @author reber
 */
public class GenerateDefaultFont {

    private GenerateDefaultFont() {
        throw new ExceptionInInitializerError(
                getClass().getSimpleName() + " can't created,this is a tool file.");
    }

    public static void main(String[] args) {
        generateDefaultFontStyles();
    }

    private static void generateDefaultFontStyles() {
        List<NameValueModel> mFontStyles = GenerateFileTool.getDefaultFontStyles();

        // 如果没有默认的Dimens.xml,不设置值
        if (!GenerateFileTool.fileExist(GenerateFileTool.getDefaultResValuePath())) {
            return;
        }

        final StringBuilder sBuilder = GenerateFileTool.getStartStringBuilder();
        for (NameValueModel model : mFontStyles) {
            sBuilder.append("\n\r");
            insertFontStyleItem(sBuilder, model.getName(), model.getValue());
        }
        sBuilder.append("\n");
        String content = GenerateFileTool.getEndStringBuilder(sBuilder).toString();
        GenerateFileTool.saveContentToFile(GenerateFileTool.getDefaultResValuePath(), getDefaultFontFileName(), content);
    }

    /**
     * <color name="appWhite">#FFFFFF</color>
     */
    private static void insertFontStyleItem(StringBuilder stringBuilder, String fontName, String fontValue) {
        // <style name="RobotoBold">
        stringBuilder
                .append("    <style name=\"")
                .append(fontName)
                .append("\">")
                .append("\n");

        // <item name="android:fontFamily">@font/roboto_bold</item>
        stringBuilder
                .append("        <item name=\"android:fontFamily\">@font/")
                .append(fontValue).append("</item>").append("\n");

        // </style>
        stringBuilder
                .append("    </style>");
    }

    private static String getDefaultFontFileName() {
        return "font_default_styles.xml";
    }
}
