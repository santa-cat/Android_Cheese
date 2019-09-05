package com.hangzhou.santa.cheese;

import com.hangzhou.santa.library.cheese.core.CheeseAction;
import com.hangzhou.santa.library.cheese.core.CheeseActionListener;

/**
 * Created by santa on 2019/3/11.
 */



//
public class DemoActions {

    static final int LIKE = 1;
    static final int DISLIKE = 2;
    static final int EXTLIKE = 3;


    public static CheeseAction<LikeIn, LikeOut> like(LikeIn likeIn, CheeseActionListener<LikeOut> likeOutActionListener) {
        return new CheeseAction<>(LIKE, likeIn, likeOutActionListener);
    }

    public static CheeseAction<LikeIn, LikeOut> disLike(LikeIn likeIn) {
        return new CheeseAction<>(DISLIKE, likeIn, null);
    }

    public static CheeseAction<LikeIn, LikeOut> likeExt(LikeIn likeIn, CheeseActionListener<LikeOut> likeOutActionListener, int ext) {
        return new DemoAction<>(EXTLIKE, likeIn,likeOutActionListener, ext);
    }
}
