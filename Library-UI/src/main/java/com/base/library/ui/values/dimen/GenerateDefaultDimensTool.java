package com.base.library.ui.values.dimen;

import com.base.library.ui.values.GenerateFileTool;

import static com.base.library.ui.values.GenerateFileTool.DEFAULT_RES_VALUE_PATH;

/**
 * @author reber
 * <p>
 * 生成默认的dp/sp数据
 */
public class GenerateDefaultDimensTool {
    /**
     * dimens的文件名称
     */
    private final static String DIMENS_FILE_NAME = "dimens.xml";

    private GenerateDefaultDimensTool() {
        throw new ExceptionInInitializerError(
                getClass().getSimpleName() + " can't created,this is a tool file.");
    }

    public static void main(String[] args) {
        // dp:0-400,sp:4-50
        generateDefaultDimens(400, 50, 4);
    }

    private static void generateDefaultDimens(int dpCount, int spCount, int spStartValue) {
        StringBuilder sBuilder = GenerateFileTool.getStartStringBuilder();
        sBuilder.append("\n");
        if (dpCount > 0) {
            //    <dimen name="dp_0">0dp</dimen>
            for (int value = 0; value <= dpCount; value++) {
                insertDpValueLine(sBuilder, value, "dp");
            }
        }

        if (spCount > 0) {
            sBuilder.append("\n");
            //    <dimen name="sp_0">0sp</dimen>
            for (int value = spStartValue; value <= spCount; value++) {
                insertDpValueLine(sBuilder, value, "sp");
            }
        }

        String content = GenerateFileTool.getEndStringBuilder(sBuilder).toString();
        GenerateFileTool.saveContentToFile(DEFAULT_RES_VALUE_PATH, DIMENS_FILE_NAME, content);
    }

    /**
     * <dimen name="dp_0">0dp</dimen>
     * <dimen name="sp_0">0sp</dimen>
     *
     * @param value     dp/sp 的值
     * @param valueUnit 区分dp/sp
     */
    private static void insertDpValueLine(StringBuilder stringBuilder, int value, String valueUnit) {
        stringBuilder.append("    <dimen name=\"")
                .append(valueUnit)
                .append("_")
                .append(value)
                .append("\">")
                .append(value)
                .append(valueUnit)
                .append("</dimen>")
                .append("\n");
    }
}
