package com.hangzhou.santa.cheese.cheese.extension;

import com.hangzhou.santa.cheese.cheese.core.AbsCheesePresenter;
import com.hangzhou.santa.cheese.cheese.core.AbsCheeseView;

/**
 * Created by santa on 2019/3/13.
 */
public class CheeseCommonView extends AbsCheeseView {

    public CheeseCommonView(AbsCheesePresenter presenter) {
        setPresenter(presenter);
    }
}
