package com.hangzhou.santa.cheese.cheese.core;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by santa on 2019/3/11.
 */
public abstract class CheeseAdapter<T> extends RecyclerView.Adapter {
    private CheeseAdapterManager<T> mAdapterManager = new CheeseAdapterManager<>();
    public List<T> mItems = new ArrayList();

    public CheeseAdapter() {
        super();
        setBinds();
    }

    public CheeseAdapter(List<T> list) {
        this();
        mItems = list;
    }

    protected abstract void setBinds();

    protected CheeseAdapter<T> bind(AbsCheeseAdapterView<T> adapterDelegate, Class<? extends AbsCheesePresenter> presenterClass) {
        mAdapterManager.bind(adapterDelegate, presenterClass);
        return this;
    }

    @Override
    public int getItemViewType(int position) {
        return mAdapterManager.getItemViewType(mItems, position);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return mAdapterManager.onCreateViewHolder(viewGroup, i);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        mAdapterManager.onBindViewHolder(mItems, i, viewHolder);
    }

    @Override
    public int getItemCount() {
        if (mItems != null) {
            return mItems.size();
        }
        return 0;
    }
}
