package com.hangzhou.santa.cheese.normal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.hangzhou.santa.cheese.LikePresenterImpl;
import com.hangzhou.santa.cheese.R;
import com.hangzhou.santa.library.cheese.extension.CheeseWapper;

/**
 * Created by santa on 2019/3/13.
 */
public class NormalViewActivity extends AppCompatActivity {
    private CheeseWapper mCheeseWapper = new CheeseWapper();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);


        mCheeseWapper.bind(new NormalViewHolder(findViewById(R.id.root_content)), LikePresenterImpl.class);
    }


}
