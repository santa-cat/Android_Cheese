package com.hangzhou.santa.cheese.cheese.extension;

import android.support.annotation.NonNull;
import android.view.View;

import com.hangzhou.santa.cheese.cheese.core.AbsCheesePresenter;
import com.hangzhou.santa.cheese.cheese.core.AbsCheeseView;
import com.hangzhou.santa.cheese.cheese.core.CheeseAction;

/**
 * Created by santa on 2019/3/13.
 */
public class CheeseWapper {
    private CheeseCommManager mCheeseCommManager = new CheeseCommManager();


    public  <P> void bind(@NonNull AbsCheeseView<P> view, @NonNull Class<? extends P> presenterClass) {
        mCheeseCommManager.bind(view, presenterClass);
    }
//
//    public void dispatch(View view, CheeseAction action) {
//        mCheeseCommManager.dispatch(view, action);
//    }
//
//    public <OUT> OUT dispatch(View view, CheeseAction action, Class<OUT> clazz) {
//        return mCheeseCommManager.dispatch(view, action, clazz);
//    }
//
//    public void dispatch(CheeseAction action) {
//        mCheeseCommManager.dispatch( action);
//    }
//
//    public <OUT> OUT dispatch(CheeseAction action, Class<OUT> clazz) {
//        return mCheeseCommManager.dispatch(action, clazz);
//    }

}
