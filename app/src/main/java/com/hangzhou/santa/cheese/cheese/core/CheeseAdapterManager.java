package com.hangzhou.santa.cheese.cheese.core;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by santa on 2019/3/11.
 */
public class CheeseAdapterManager<T> implements ICheeseManager{
    private SparseArray<Class<? extends AbsCheesePresenter>> presenterClasses = new SparseArray<>();
    private SparseArray<AbsCheeseAdapterView<T>> views = new SparseArray<>();

    public void bind(AbsCheeseAdapterView adapterView, Class<? extends AbsCheesePresenter> presenterClass) {
        if (adapterView == null) {
            throw new IllegalArgumentException("CheeseAdapterManager: adapterView can't be null!");
        }

        int index = views.size();
        views.put(index, adapterView);
        if (presenterClass != null) {
            presenterClasses.put(index, presenterClass);
        }
    }

    public AbsCheeseAdapterView<T> getDelegate(int itemViewType) {
        return views.get(itemViewType);
    }

    public int getItemViewType(@NonNull List<T> items, int position) {
        if (items == null) {
            throw new IllegalArgumentException("CheeseAdapterManager: DataSource can't be null!");
        }
        int delegatesCount = views.size();
        int index = 0;
        while (index < delegatesCount) {
            AbsCheeseAdapterView adapterDelegate = views.valueAt(index);
            if (adapterDelegate.equalViewType(items, position)) {
                return index;
            }
            index += 1;
        }

        throw new IllegalArgumentException("CheeseAdapterManager: No AbsCheeseAdapterView added that matches position=" + position);
    }

    public void onBindViewHolder(@NonNull List<T> items, int type, @NonNull RecyclerView.ViewHolder viewHolder) {
        AbsCheeseAdapterView adapterDelegate = views.get(viewHolder.getItemViewType());

        adapterDelegate.onBindViewHolder(items, type, viewHolder);
    }

    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        AbsCheeseAdapterView adapterDelegate =  views.get(viewType);
        Class presenterClass = presenterClasses.get(viewType);
        if (presenterClass != null) {
            try {
                adapterDelegate.setPresenter((AbsCheesePresenter) presenterClass.newInstance());
            } catch (IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
                throw new IllegalArgumentException("CheeseAdapterManager: onCreateViewHolder newInstance fail!");
            }
        }
        RecyclerView.ViewHolder viewHolder = adapterDelegate.onCreateViewHolder(parent);
        if (viewHolder == null) {
            throw new IllegalArgumentException("CheeseAdapterManager: onCreateViewHolder return null!");
        }
        return viewHolder;
    }


}
