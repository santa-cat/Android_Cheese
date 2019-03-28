package com.hangzhou.santa.cheese.cheese.core;

/**
 * Created by santa on 2019/3/11.
 */
public abstract class AbsCheeseView {

    private AbsCheesePresenter mPresenter;

    protected void setPresenter(AbsCheesePresenter presenter) {
        mPresenter = presenter;
    }

    public void dispatch(CheeseAction action) {
        if (null != mPresenter) {
            mPresenter.onAction(action);
        }
    }

    public <OUT> OUT dispatch(CheeseAction action, Class<OUT> clazz) {
        if (null != mPresenter) {
            return mPresenter.onAction(action, clazz);
        } else {
            return null;
        }
    }

}
