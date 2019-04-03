package com.hangzhou.santa.cheese;

import com.hangzhou.santa.cheese.cheese.core.IActionCheesePresenter;
import com.hangzhou.santa.cheese.cheese.core.CheeseAction;

/**
 * Created by santa on 2019/3/11.
 */
public class DemoLikePresenter2 implements IActionCheesePresenter {
    @Override
    public void onAction(CheeseAction action) {
        if (action.getType() == DemoActions.LIKE) {
            //todo 简介 规范？？？
            LikeIn likeIn = (LikeIn) action.getPayload();
            action.getListener().afterAction(new LikeOut(true, 100));
        }
    }

    @Override
    public <T> T onAction(CheeseAction action, Class<T> clazz) {
        return null;
    }
}
