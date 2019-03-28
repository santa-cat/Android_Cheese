package com.hangzhou.santa.cheese.cheese.core;

/**
 * Created by santa on 2019/3/11.
 */
public abstract class AbsCheesePresenter {

    public abstract void onAction(CheeseAction action);

    public abstract <T> T onAction(CheeseAction action, Class<T> clazz);

}
