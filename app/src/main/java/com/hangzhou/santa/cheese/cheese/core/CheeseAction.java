package com.hangzhou.santa.cheese.cheese.core;

/**
 * Created by santa on 2019/3/11.
 */
//todo 是否可以扩展
public class CheeseAction<IN, OUT> {
    private int type;
    private IN payload;
    private CheeseActionListener<OUT> listener;

    public CheeseAction(int type, IN payload, CheeseActionListener<OUT> listener) {
        this.type = type;
        this.payload = payload;
        this.listener = listener;
    }

    public int getType() {
        return type;
    }

    public IN getPayload() {
        return payload;
    }

    public CheeseActionListener<OUT> getListener() {
        return listener;
    }

}


