package com.hangzhou.santa.cheese;

import com.hangzhou.santa.cheese.cheese.core.CheeseActionListener;
import com.hangzhou.santa.cheese.cheese.core.CheeseAction;

/**
 * Created by santa on 2019/3/13.
 */
public class DemoAction<IN, OUT> extends CheeseAction<IN, OUT> {

    private int extensionInput;

    public DemoAction(int type, IN payload, CheeseActionListener<OUT> listener, int extensionInput) {
        super(type, payload, listener);
        this.extensionInput = extensionInput;
    }

    public int getExtensionInput() {
        return extensionInput;
    }
}
