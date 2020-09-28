package com.base.library.ui.values.color;

import com.base.library.ui.values.GenerateFileTool;
import com.base.library.ui.values.NameValueModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author reber
 */
public class GenerateDefaultColor {

    private static final String TAG_SPACE = "space";

    private GenerateDefaultColor() {
        throw new ExceptionInInitializerError(
                getClass().getSimpleName() + " can't created,this is a tool file.");
    }

    public static void main(String[] args) {
        generateDefaultColor();
    }

    private static List<NameValueModel> getDefaultColors() {
        List<NameValueModel> mColors = new ArrayList<>();
        mColors.add(new NameValueModel("color_FFFFFF", "#FFFFFF"));
        mColors.add(new NameValueModel("color_0000000", "#000000"));
        mColors.add(new NameValueModel("color_F14880", "#F14880"));
        mColors.add(new NameValueModel("color_999999", "#999999"));
        mColors.add(new NameValueModel("color_666666", "#666666"));
        mColors.add(new NameValueModel("color_333333", "#333333"));
        mColors.add(new NameValueModel("color_55_333333", "#55333333"));

        mColors.add(new NameValueModel(TAG_SPACE, TAG_SPACE));

        mColors.add(new NameValueModel("appTextWhite", "@color/color_FFFFFF"));
        mColors.add(new NameValueModel("appTextBlack", "@color/color_333333"));
        mColors.add(new NameValueModel("appTextRed", "@color/color_F14880"));
        mColors.add(new NameValueModel("appTextGray", "@color/color_666666"));
        return mColors;
    }

    private static void generateDefaultColor() {
        List<NameValueModel> mColors = getDefaultColors();

        // 如果没有默认的values目录,不设置值
        if (!GenerateFileTool.isFileExist(GenerateFileTool.getDefaultResValuePath())) {
            System.out.println("--------------请先创建values目录--------------");
            return;
        }

        final StringBuilder sBuilder = GenerateFileTool.getStartStringBuilder();
        sBuilder.append("\n");
        for (NameValueModel model : mColors) {
            if (TAG_SPACE.equals(model.getName())) {
                sBuilder.append("\n");
            } else {
                insertColorLine(sBuilder, model.getName(), model.getValue());
            }
        }
        String content = GenerateFileTool.getEndStringBuilder(sBuilder).toString();
        GenerateFileTool.saveContentToFile(GenerateFileTool.getDefaultResValuePath(), GenerateFileTool.getColorFileName(), content);
    }

    /**
     * <color name="appWhite">#FFFFFF</color>
     */
    private static void insertColorLine(StringBuilder stringBuilder, String colorName, String colorValue) {
        stringBuilder
                .append("    <color name=\"")
                .append(colorName)
                .append("\">")
                .append(colorValue)
                .append("</color>")
                .append("\n");
    }
}
