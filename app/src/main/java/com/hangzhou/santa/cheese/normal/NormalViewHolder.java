package com.hangzhou.santa.cheese.normal;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hangzhou.santa.cheese.DemoActions;
import com.hangzhou.santa.cheese.DemoLikePresenter;
import com.hangzhou.santa.cheese.ILikePresenter;
import com.hangzhou.santa.cheese.LikeIn;
import com.hangzhou.santa.cheese.LikeOut;
import com.hangzhou.santa.cheese.R;
import com.hangzhou.santa.cheese.cheese.core.AbsCheeseView;
import com.hangzhou.santa.cheese.cheese.core.CheeseActionListener;
import com.hangzhou.santa.cheese.cheese.extension.CheeseWapper;

/**
 * Created by santa on 2019/3/13.
 */
public class NormalViewHolder extends AbsCheeseView<ILikePresenter> {

    private Button mButtonLikeAsyn;
    private Button mButtonDislikeSyn;
    private Button mButtonLikeExt;
    private TextView mTextView;
    private LikeIn mLikeIn = new LikeIn(1);

    public NormalViewHolder(View itemView) {
        mButtonLikeAsyn = (Button) itemView.findViewById(R.id.button);
        mButtonDislikeSyn = (Button) itemView.findViewById(R.id.button2);
        mButtonLikeExt = (Button) itemView.findViewById(R.id.button3);
        mTextView = (TextView) itemView.findViewById(R.id.text_view);
        bindListener();
    }

    private void bindListener() {
        mButtonLikeAsyn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.like(22);
            }
        });


        mButtonDislikeSyn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });


        mButtonLikeExt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }


}
