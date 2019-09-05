package com.hangzhou.santa.library.cheese.core;

import android.support.v7.widget.RecyclerView;

/**
 * Created by santa on 2019/3/11.
 */
public abstract class AbsActionCheeseAdapterView<T>  extends AbsCheeseAdapterView<IActionCheesePresenter, T> {

    public AbsActionCheeseAdapterView(RecyclerView.Adapter adapter) {
        super(adapter);
    }
}
