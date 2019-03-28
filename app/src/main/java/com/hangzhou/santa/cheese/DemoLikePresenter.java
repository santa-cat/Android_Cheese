package com.hangzhou.santa.cheese;

import com.hangzhou.santa.cheese.cheese.core.AbsCheesePresenter;
import com.hangzhou.santa.cheese.cheese.core.CheeseAction;

/**
 * Created by santa on 2019/3/11.
 */
public class DemoLikePresenter extends AbsCheesePresenter {
    @Override
    public void onAction(CheeseAction action) {
        switch (action.getType()) {
            case DemoActions.LIKE: onActionLike(action);break;
            case DemoActions.EXTLIKE: onActionExtLike(action);break;
            default:
        }
    }

    @Override
    public <T> T onAction(CheeseAction action, Class<T> clazz) {
        switch (action.getType()) {
            case DemoActions.DISLIKE: return onActionDisLike(action, clazz);
            default:
        }
        return null;
    }


    private void onActionLike(CheeseAction action) {
        LikeIn likeIn = (LikeIn) action.getPayload();
        action.getListener().afterAction(new LikeOut(true, 1));
    }


    private void onActionExtLike(CheeseAction action) {
        if (action instanceof DemoAction) {
            LikeIn likeIn = (LikeIn) action.getPayload();
            action.getListener().afterAction(new LikeOut(true, ((DemoAction) action).getExtensionInput()+1));
        }
    }

    private <T> T onActionDisLike(CheeseAction action, Class<T> clazz) {
        if (clazz != LikeOut.class) {
            return null;
        }

        LikeIn likeIn = (LikeIn) action.getPayload();
        return (T) new LikeOut(true, 1);
    }

}
