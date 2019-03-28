package com.hangzhou.santa.cheese.normal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.hangzhou.santa.cheese.R;

/**
 * Created by santa on 2019/3/13.
 */
public class NormalViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);


        new NormalViewHolder(findViewById(R.id.root_content));
    }


}
