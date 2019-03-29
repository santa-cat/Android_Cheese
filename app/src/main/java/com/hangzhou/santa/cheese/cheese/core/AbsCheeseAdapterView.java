package com.hangzhou.santa.cheese.cheese.core;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by santa on 2019/3/11.
 */
public abstract class AbsCheeseAdapterView<P, T>  extends AbsCheeseView<P> {
    private RecyclerView.Adapter adapter;


    public AbsCheeseAdapterView(RecyclerView.Adapter adapter) {
        this.adapter = adapter;
    }

    public abstract boolean equalViewType(@NonNull List<T> param, int position);

    protected RecyclerView.Adapter getAdapter() {
        return this.adapter;
    }

    public abstract void onBindViewHolder(@NonNull List<T> param, int position, @NonNull RecyclerView.ViewHolder viewHolder);

    public abstract RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup);

}
