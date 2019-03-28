package com.hangzhou.santa.cheese.normal;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hangzhou.santa.cheese.DemoActions;
import com.hangzhou.santa.cheese.DemoLikePresenter;
import com.hangzhou.santa.cheese.LikeIn;
import com.hangzhou.santa.cheese.LikeOut;
import com.hangzhou.santa.cheese.R;
import com.hangzhou.santa.cheese.cheese.core.CheeseActionListener;
import com.hangzhou.santa.cheese.cheese.extension.CheeseWapper;

/**
 * Created by santa on 2019/3/13.
 */
public class NormalViewHolder {

    private CheeseWapper mCheeseWapper = new CheeseWapper();
    private Button mButtonLikeAsyn;
    private Button mButtonDislikeSyn;
    private Button mButtonLikeExt;
    private TextView mTextView;
    private LikeIn mLikeIn = new LikeIn(1);

    public NormalViewHolder(View itemView) {
        mCheeseWapper.bind(itemView, DemoLikePresenter.class);
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

                mCheeseWapper.dispatch(DemoActions.like( mLikeIn, new CheeseActionListener<LikeOut>() {
                    @Override
                    public void afterAction(LikeOut o) {
                        if (o.response) {
                            ((LikeIn) mLikeIn).count += o.count;
                            mTextView.setText(mLikeIn.count+"");
                        }
                    }
                }));
            }
        });


        mButtonDislikeSyn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LikeOut likeOut = mCheeseWapper.dispatch(DemoActions.disLike(mLikeIn), LikeOut.class);
                if (likeOut.response) {
                    mLikeIn.count -= likeOut.count;
                    mTextView.setText(mLikeIn.count+"");
                }
            }
        });


        mButtonLikeExt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mCheeseWapper.dispatch(DemoActions.likeExt(mLikeIn, new CheeseActionListener<LikeOut>() {
                    @Override
                    public void afterAction(LikeOut o) {
                        if (o.response) {
                            mLikeIn.count += o.count;
                            mTextView.setText(mLikeIn.count+"");
                        }
                    }
                }, 4));
            }
        });
    }


}
