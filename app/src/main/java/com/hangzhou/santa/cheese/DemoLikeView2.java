package com.hangzhou.santa.cheese;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hangzhou.santa.library.cheese.core.AbsCheeseAdapterView;
import com.hangzhou.santa.library.cheese.core.CheeseActionListener;

import java.util.List;

/**
 * Created by santa on 2019/3/11.
 */
public class DemoLikeView2 extends AbsCheeseAdapterView<ILikePresenter, Object> {
    public DemoLikeView2(RecyclerView.Adapter adapter) {
        super(adapter);
    }

    @Override
    public boolean equalViewType(@NonNull List<Object> param, int position) {
        return param.get(position) instanceof LikeIn && ((LikeIn) param.get(position)).type == 2;
    }

    @Override
    public void onBindViewHolder(@NonNull List<Object> param, int position, @NonNull final RecyclerView.ViewHolder holder) {
        final Object data = param.get(position);
        if (!(holder instanceof ItemViewHolder && data instanceof LikeIn)){
            return;
        }
//
//        ((ItemViewHolder) holder).mIcon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                dispatch(DemoActions.like((LikeIn) data, new CheeseActionListener<LikeOut>() {
//                    @Override
//                    public void afterAction(LikeOut o) {
//                        if (o.response) {
//                            ((LikeIn) data).count += o.count;
//                            ((ItemViewHolder) holder).mTextView.setText(((LikeIn) data).count+"");
//
//                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                                (((ItemViewHolder) holder).mIcon).setImageTintList(ColorStateList.valueOf(Color.RED));
//                            }
//                            ((ItemViewHolder) holder).mIcon.setOnClickListener(null);
//                        }
//                    }
//                }));
//            }
//        });
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup) {
        return new ItemViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.item_demo_like2, viewGroup, false));
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView mIcon;
        private TextView mTextView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            mIcon = (ImageView) itemView.findViewById(R.id.icon);
            mTextView = (TextView) itemView.findViewById(R.id.text_view);
        }
    }
}
