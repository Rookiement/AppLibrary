package com.base.library.ui.values.style;

import com.base.library.ui.values.GenerateFileTool;

/**
 * @author reber
 */
public class GenerateLayoutStyle {

    private GenerateLayoutStyle() {
        throw new ExceptionInInitializerError(
                getClass().getSimpleName() + " can't created,this is a tool file.");
    }

    public static void main(String[] args) {
        generateLayoutStyle();
    }

    private static void generateLayoutStyle() {
        String[] layoutArray = GenerateFileTool.getLayoutArray();

        // 如果没有默认的Dimens.xml,不设置值
        if (!GenerateFileTool.isFileExist(GenerateFileTool.getDefaultResValuePath())) {
            return;
        }

        final StringBuilder sBuilder = GenerateFileTool.getStartStringBuilder();

        sBuilder.append("\n\r")
                .append("    <style name=\"Layout\"/>");

        for (String layoutValue : layoutArray) {
            sBuilder.append("\n\r");
            insertLayoutItem(sBuilder, layoutValue);
        }
        sBuilder.append("\n");
        String content = GenerateFileTool.getEndStringBuilder(sBuilder).toString();
        GenerateFileTool.saveContentToFile(GenerateFileTool.getDefaultResValuePath(),
                GenerateFileTool.getLayoutStyleFileName(), content);
    }

    /**
     * <color name="appWhite">#FFFFFF</color>
     */
    private static void insertLayoutItem(StringBuilder stringBuilder, String layoutValue) {
        String[] paramArray = layoutValue.split("_");
        stringBuilder
                .append("    <style name=\"Layout.")
                .append(paramArray[0])
                .append(paramArray[1])
                .append("\">")
                .append("\n");

        stringBuilder
                .append("        <item name=\"android:layout_width\">")
                .append(GenerateFileTool.getParamValue(paramArray[0]))
                .append("</item>")
                .append("\n");

        stringBuilder
                .append("        <item name=\"android:layout_height\">")
                .append(GenerateFileTool.getParamValue(paramArray[1]))
                .append("</item>")
                .append("\n");

        stringBuilder.append("    </style>");
    }
}

