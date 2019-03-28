package com.hangzhou.santa.cheese.cheese.extension;

import android.support.annotation.NonNull;
import android.util.SparseArray;
import android.view.View;

import com.hangzhou.santa.cheese.cheese.core.AbsCheesePresenter;
import com.hangzhou.santa.cheese.cheese.core.AbsCheeseView;
import com.hangzhou.santa.cheese.cheese.core.CheeseAction;

import java.util.HashMap;

/**
 * Created by santa on 2019/3/13.
 */
public class CheeseCommManager {

    private static final String TAG = "CheeseCommManager";
    private SparseArray<AbsCheeseView> mCheeseViews = new SparseArray<>();
    private HashMap<View, Integer> viewsMap = new HashMap<>();

    public synchronized CheeseCommManager bind(@NonNull View view, @NonNull Class<? extends AbsCheesePresenter> presenterClass) {
        if (view == null) {
            throw new IllegalArgumentException(TAG + ": view can't be null!");
        }

        try {
            Object presenter = presenterClass.newInstance();
            int index = viewsMap.size();
            viewsMap.put(view, index);
            mCheeseViews.put(index, new CheeseCommonView((AbsCheesePresenter) presenter));
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(TAG + ": bind newInstance fail!");
        }

        return this;
    }

    private AbsCheeseView getCheeseView(View view) {
        AbsCheeseView absCheeseView = null;
        if (mCheeseViews.size() == 0) {
            return null;
        }
        if (view == null) {
            absCheeseView = mCheeseViews.valueAt(0);
        } else {
            Integer integer = viewsMap.get(view);
            if (integer != null) {
                absCheeseView = mCheeseViews.get(integer);
            }
        }
        return absCheeseView;
    }

    public synchronized void dispatch(View view, CheeseAction action) {
        if (view == null && viewsMap.size() > 1) {
            throw new IllegalArgumentException(TAG + ": view can be null when map size > 1!");
        }

        AbsCheeseView absCheeseView = getCheeseView(view);
        if (null != absCheeseView) {
            absCheeseView.dispatch(action);
        }
    }

    public synchronized <OUT> OUT dispatch(View view, CheeseAction action, Class<OUT> clazz) {
        if (view == null && viewsMap.size() > 1) {
            throw new IllegalArgumentException(TAG + ": view can be null when map size > 1!");
        }

        AbsCheeseView absCheeseView = getCheeseView(view);
        if (null != absCheeseView) {
            return absCheeseView.dispatch(action, clazz);
        } else {
            return null;
        }
    }

    public synchronized void dispatch(CheeseAction action) {
        dispatch(null, action);
    }

    public synchronized <OUT> OUT dispatch(CheeseAction action, Class<OUT> clazz) {
        return dispatch(null, action, clazz);
    }
}
