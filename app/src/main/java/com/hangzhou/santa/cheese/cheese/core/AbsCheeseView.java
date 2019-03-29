package com.hangzhou.santa.cheese.cheese.core;

/**
 * Created by santa on 2019/3/11.
 */
public abstract class AbsCheeseView<P> {

    protected P mPresenter;

    public void setPresenter(P presenter) {
        mPresenter = presenter;
    }

    public void dispatch(CheeseAction action) {
        if (null != mPresenter && mPresenter instanceof AbsCheesePresenter) {
            ((AbsCheesePresenter)mPresenter).onAction(action);
        } else {
            throw new IllegalArgumentException("AbsCheeseView: if use dispatch view need extend AbsCheesePresenter!");
        }
    }

    public <OUT> OUT dispatch(CheeseAction action, Class<OUT> clazz) {
        if (null != mPresenter && mPresenter instanceof AbsCheesePresenter) {
            return ((AbsCheesePresenter)mPresenter).onAction(action, clazz);
        } else {
            throw new IllegalArgumentException("AbsCheeseView: if use dispatch view need extend AbsCheesePresenter!");
        }
    }

}
