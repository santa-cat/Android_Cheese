package com.hangzhou.santa.cheese;

import com.hangzhou.santa.library.cheese.core.CheeseAdapter;

/**
 * Created by santa on 2019/3/11.
 */
public class DemoAdapter extends CheeseAdapter<Object> {

    @Override
    protected void setBinds() {
        bind(new DemoLikeView(this), LikePresenterImpl.class);
        bind(new DemoLikeViewAction(this), DemoLikePresenter.class);
//        bind(new DemoLikeView2(this), DemoLikePresenter.class);
//        bind(new DemoLikeView(this), DemoLikePresenter2.class);
    }
}
