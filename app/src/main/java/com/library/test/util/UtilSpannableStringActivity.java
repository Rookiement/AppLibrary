package com.library.test.util;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.base.library.common.activity.BaseAppActivity;
import com.base.library.common.helper.AppViewHelper;
import com.base.library.ui.recycler.AppRecyclerView;
import com.base.library.ui.recycler.adapter.AppSingleTypeAdapter;
import com.base.library.ui.recycler.decoration.AppLinearItemDecoration;
import com.base.library.ui.recycler.holder.AppViewHolder;
import com.base.library.ui.values.NameValueModel;
import com.library.test.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author reber
 * on 2020/10/13 11:49
 */
public class UtilSpannableStringActivity extends BaseAppActivity {

    private static final String CONTENT = "选择优惠券页面不使用优惠券按钮优化";
    private static final String MATCHER = "优惠券";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_util_spannable_string;
    }

    @Override
    protected void onInitCreateWithState(@NonNull AppViewHelper viewHelper, @Nullable Bundle savedInstanceState) {
        AppRecyclerView recyclerView = viewHelper.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new AppLinearItemDecoration.Builder()
                .setItemDivideValue(R.dimen.dp_10)
                .build(this));
        recyclerView.setAdapter(new AppSingleTypeAdapter<NameValueModel>(getLifecycle(),
                R.layout.item_util_spannable_string, getDataList()) {
            @Override
            public void onUpdateViewHolderWhenBind(@NonNull AppViewHolder<NameValueModel> holder, NameValueModel item, int position) {
                holder.setText(R.id.title, item.getName())
                        .setText(R.id.button, item.getValue())
                        .setOnClickListener(R.id.button, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                            }
                        });
            }
        });
    }

    private List<NameValueModel> getDataList() {
        List<NameValueModel> dataList = new ArrayList<>();
        dataList.add(new NameValueModel(CONTENT, "BackgroundColorSpan"));
        dataList.add(new NameValueModel(CONTENT, "ForegroundColorSpan"));
        dataList.add(new NameValueModel(CONTENT, "MaskFilterSpan"));
        dataList.add(new NameValueModel(CONTENT, "RasterizerSpan"));
        dataList.add(new NameValueModel(CONTENT, "StrikeThroughSpan"));
        dataList.add(new NameValueModel(CONTENT, "SuggestionSpan"));
        dataList.add(new NameValueModel(CONTENT, "UnderlineSpan"));
        dataList.add(new NameValueModel(CONTENT, "AbsoluteSizeSpan"));
        dataList.add(new NameValueModel(CONTENT, "DynamicDrawableSpan"));
        dataList.add(new NameValueModel(CONTENT, "ImageSpan"));
        dataList.add(new NameValueModel(CONTENT, "RelativeSizeSpan"));
        dataList.add(new NameValueModel(CONTENT, "ScaleXSpan"));
        dataList.add(new NameValueModel(CONTENT, "StyleSpan"));
        dataList.add(new NameValueModel(CONTENT, "SubscriptSpan"));
        dataList.add(new NameValueModel(CONTENT, "SuperscriptSpan"));
        dataList.add(new NameValueModel(CONTENT, "TextAppearanceSpan"));
        dataList.add(new NameValueModel(CONTENT, "TypefaceSpan"));
        dataList.add(new NameValueModel(CONTENT, "URLSpan"));
        dataList.add(new NameValueModel(CONTENT, "ClickableSpan"));
        return dataList;
    }
}
