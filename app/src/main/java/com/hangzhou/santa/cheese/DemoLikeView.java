package com.hangzhou.santa.cheese;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.hangzhou.santa.cheese.cheese.core.AbsCheeseAdapterView;
import com.hangzhou.santa.cheese.cheese.core.CheeseActionListener;

import java.util.List;

/**
 * Created by santa on 2019/3/11.
 */
public class DemoLikeView extends AbsCheeseAdapterView<ILikePresenter, Object> {
    public DemoLikeView(RecyclerView.Adapter adapter) {
        super(adapter);
    }

    @Override
    public boolean equalViewType(@NonNull List<Object> param, int position) {
        return param.get(position) instanceof LikeIn && ((LikeIn) param.get(position)).type == 1;
    }

    @Override
    public void onBindViewHolder(@NonNull List<Object> param, int position, @NonNull final RecyclerView.ViewHolder holder) {
        final Object data = param.get(position);
        if (!(holder instanceof ItemViewHolder && data instanceof LikeIn)){
            return;
        }

        mPresenter.like(1);

        ((ItemViewHolder) holder).mButtonLikeAsyn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dispatch(DemoActions.like((LikeIn) data, new CheeseActionListener<LikeOut>() {
                    @Override
                    public void afterAction(LikeOut o) {
                        if (o.response) {
                            ((LikeIn) data).count += o.count;
                            ((ItemViewHolder) holder).mTextView.setText(((LikeIn) data).count+"");
                        }
                    }
                }));
            }
        });


        ((ItemViewHolder) holder).mButtonDislikeSyn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LikeOut likeOut = dispatch(DemoActions.disLike((LikeIn) data), LikeOut.class);
                if (likeOut.response) {
                    ((LikeIn) data).count -= likeOut.count;
                    ((ItemViewHolder) holder).mTextView.setText(((LikeIn) data).count+"");
                }
            }
        });


        ((ItemViewHolder) holder).mButtonLikeExt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dispatch(DemoActions.likeExt((LikeIn) data, new CheeseActionListener<LikeOut>() {
                    @Override
                    public void afterAction(LikeOut o) {
                        if (o.response) {
                            ((LikeIn) data).count += o.count;
                            ((ItemViewHolder) holder).mTextView.setText(((LikeIn) data).count+"");
                        }
                    }
                }, 4));
            }
        });
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup) {
        return new ItemViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.item_demo_like, viewGroup, false));
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private Button mButtonLikeAsyn;
        private Button mButtonDislikeSyn;
        private Button mButtonLikeExt;
        private TextView mTextView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            mButtonLikeAsyn = (Button) itemView.findViewById(R.id.button);
            mButtonDislikeSyn = (Button) itemView.findViewById(R.id.button2);
            mButtonLikeExt = (Button) itemView.findViewById(R.id.button3);
            mTextView = (TextView) itemView.findViewById(R.id.text_view);
        }
    }
}
