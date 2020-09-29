package com.library.test.ui.recycler;

import android.graphics.Rect;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.base.library.common.activity.BaseAppActivity;
import com.base.library.common.helper.AppViewHelper;
import com.base.library.ui.recycler.AppRecyclerView;
import com.base.library.ui.recycler.adapter.AppSingleTypeAdapter;
import com.base.library.ui.recycler.decoration.AppLinearItemDecoration;
import com.base.library.ui.recycler.holder.AppViewHolder;
import com.library.test.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author reber
 * on 2020/9/29 16:09
 */
public class RecyclerLinearDecorationActivity extends BaseAppActivity {
    @Override
    protected int getLayoutId() {
//        return R.layout.activity_recycler_layout_vertical;
        return R.layout.activity_recycler_layout_horizontal;
    }

    @Override
    protected void onInitCreateWithState(@NonNull AppViewHelper viewHelper, @Nullable Bundle savedInstanceState) {
        AppRecyclerView recyclerView = viewHelper.findViewById(R.id.recycler_view);

//        AppSingleTypeAdapter<String> adapter = new AppSingleTypeAdapter<String>(R.layout.item_recycler_linear_vertical) {
        AppSingleTypeAdapter<String> adapter = new AppSingleTypeAdapter<String>(R.layout.item_recycler_linear_horizontal) {
            @Override
            public void onUpdateViewHolderWhenBind(@NonNull AppViewHolder<String> holder, String item, int position) {
                holder.setText(R.id.item_content, item);
            }
        };
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);

        recyclerView.addItemDecoration(new AppLinearItemDecoration.Builder()
//                .setOrientation(AppRecyclerView.VERTICAL)
                .setOrientation(AppRecyclerView.HORIZONTAL)
                .setItemDivideValue(R.dimen.dp_4)
                .setDividerColorResId(R.color.color_F14880)
                .setRVBoundaryValue(new Rect(50, 50, 50, 50))
                .build(this));

        adapter.insertItemListNotify(getItemList());
    }


    private List<String> getItemList() {
        List<String> itemList = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            itemList.add("Test-" + i);
        }
        return itemList;
    }
}
