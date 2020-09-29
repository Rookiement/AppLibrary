package com.library.test.ui.recycler;

import android.graphics.Rect;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.base.library.common.activity.BaseAppActivity;
import com.base.library.common.helper.AppViewHelper;
import com.base.library.ui.recycler.AppRecyclerView;
import com.base.library.ui.recycler.adapter.AppMultiTypeAdapter;
import com.base.library.ui.recycler.decoration.AppLinearItemDecoration;
import com.library.test.R;
import com.library.test.ui.recycler.item.RecyclerMultiItemFirst;
import com.library.test.ui.recycler.item.RecyclerMultiItemFourth;
import com.library.test.ui.recycler.item.RecyclerMultiItemSecond;
import com.library.test.ui.recycler.item.RecyclerMultiItemThird;

/**
 * @author reber
 * on 2020/9/29 16:13
 */
public class RecyclerMultiActivity extends BaseAppActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_recycler_layout_vertical;
    }

    @Override
    protected void onInitCreateWithState(@NonNull AppViewHelper viewHelper, @Nullable Bundle savedInstanceState) {
        AppRecyclerView recyclerView = viewHelper.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new AppLinearItemDecoration.Builder()
                .setRVBoundaryValue(new Rect(10, 50, 10, 20))
                .setItemDivideValue(R.dimen.dp_2)
                .build(this));

        AppMultiTypeAdapter adapter = new AppMultiTypeAdapter();
        recyclerView.setAdapter(adapter);

        adapter.insertItem(new RecyclerMultiItemFirst());
        adapter.insertItem(new RecyclerMultiItemSecond());
        adapter.insertItem(new RecyclerMultiItemThird());
        adapter.insertItem(new RecyclerMultiItemThird());
        adapter.insertItem(new RecyclerMultiItemThird());
        adapter.insertItem(new RecyclerMultiItemFourth());
        adapter.notifyDataSetChanged();
    }
}
