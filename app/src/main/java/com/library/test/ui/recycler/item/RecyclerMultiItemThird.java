package com.library.test.ui.recycler.item;

import com.base.library.ui.recycler.holder.AppViewHolder;
import com.base.library.ui.recycler.item.AppAdapterItem;
import com.library.test.R;

/**
 * @author reber
 * on 2020/9/29 17:33
 */
public class RecyclerMultiItemThird extends AppAdapterItem {
    @Override
    public int getLayoutResId() {
        return R.layout.item_recycler_multi_third;
    }

    @Override
    public <T> void onBindViewHolder(AppViewHolder<T> holder) {

    }
}
