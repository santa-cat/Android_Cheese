package com.hangzhou.santa.cheese.cheese.core;

/**
 * Created by santa on 2019/3/11.
 */
public interface IActionCheesePresenter {

    void onAction(CheeseAction action);

    <T> T onAction(CheeseAction action, Class<T> clazz);

}
