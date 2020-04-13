# Cheese

> 这是一个轻量的视图和逻辑分层的库，ViewHolder和Adapter解耦，View和Presenter解耦、灵活组装。

## 框架图

![](https://github.com/santa-cat/Android_Cheese/blob/master/pic/Cheese_frame.png)
![](https://github.com/santa-cat/Android_Cheese/blob/master/pic/Cheese_frame_action.png)

**ViewHolder = View + Presenter(可选) + Actions(可选)**

1. ViewHolder和Adapter解耦，ViewHolder可以复用在不同的Adapter；
2. View和Presenter解耦，可以自由组装；
    * 不同的View可以绑定同一个Presenter；
    * 不同的Presenter方便替换绑定同一个View；
3. View和Presenter通信，支持两种方式：
    1. 通过Action通信： 
        更加灵活，可以选择关心的Action去处理；Presenter和ViewHolder更加解耦。
        * 同步Action：可以同步返回一个对象；
        * 异步Action：提供一个异步的回调；
    2. 接口方式通信：
       接口可以自己定义，调用更加直观。

## 完整示例
> 框架支持两种View和Presenter的通信方式，下面两种分别举例。

### Action通信方式

`Adapter`

```java
public class DemoAdapter extends CheeseAdapter<Object> {

    @Override
    protected void setBinds() {
        bind(new DemoLikeView(this),    DemoLikePresenter.class);
        bind(new DemoLikeView2(this),   DemoLikePresenter.class);
        bind(new DemoHeaderView(this),  DemoHeaderPresenter.class);
        bind(new DemoDetailView(this),  DemoDetailPresenter.class);
        bind(new DemoCommentView(this), DemoCommentPresenter.class);
    }
}
```

* **一行代码新增一种ViewHolder，拆分getViewType，去Adapter和ViewHolder耦合**
* **ViewHolder = View + Presenter(可选)**

`View`
> 继承AbsActionCheeseAdapterView

```java
public class DemoLikeView extends AbsActionCheeseAdapterView<Object> {

    //getViewType下放
    @Override
    public boolean equalViewType(@NonNull List<Object> param, int position) {
        return param.get(position) instanceof LikeIn;
    }
    
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup) {
        return new ItemViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.item_demo_like, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull List<Object> param, int position, @NonNull final RecyclerView.ViewHolder holder) {

      //...省略...以下用KT当成伪代码
      //dispatch - 异步
      holder.mButtonLikeAsyn.setOnClickListener {
            dispatch(DemoActions.like(data) { o ->
                if (o.response) {
                    data.count += o.count
                    holder.mTextView.text = data.count.toString() + ""
                }
            })
        }

        //dispatch - 同步
        holder.mButtonDislikeSyn.setOnClickListener {
            val likeOut = dispatch<LikeOut>(DemoActions.disLike(data), LikeOut::class.java)
            if (likeOut!!.response) {
                data.count -= likeOut.count
                holder.mTextView.text = data.count.toString() + ""
            }
        }
    }
}

```

`Presenter`
> 继承AbsCheesePresenter

```java
public class DemoLikePresenter extends AbsCheesePresenter {

    //接收异步
    @Override
    public void onAction(CheeseAction action) {
        switch (action.getType()) {
            case DemoActions.LIKE: onActionLike(action);break;
            case DemoActions.EXTLIKE: onActionExtLike(action);break;
            default:
        }
    }

    //接收同步
    @Override
    public <T> T onAction(CheeseAction action, Class<T> clazz) {
        switch (action.getType()) {
            case DemoActions.DISLIKE: return onActionDisLike(action, clazz);
            default:
        }
        return null;
    }

    //...省略...以下为具体逻辑
    private void onActionLike(CheeseAction action) {}

    private void onActionExtLike(CheeseAction action) {}

    private <T> T onActionDisLike(CheeseAction action, Class<T> clazz) {}
}
```

`Actions`

```java
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
```

### Interface通信方式
`Adapter`
> 和Action方式的一样

```java
public class DemoAdapter extends CheeseAdapter<Object> {

    @Override
    protected void setBinds() {
        bind(new DemoLikeView(this), LikePresenterImpl.class);
    }
}
```

`Interface`

```java
public interface ILikePresenter {

    void like(int count, Callback callback);
    
    void disLike(int count, Callback callback);

}
```

`View`
> 继承AbsCheeseAdapterView

```java
public class DemoLikeView extends AbsCheeseAdapterView<ILikePresenter, Object> {

    //getViewType下放
    @Override
    public boolean equalViewType(@NonNull List<Object> param, int position) {
        return param.get(position) instanceof LikeIn;
    }
    
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup) {
        return new ItemViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.item_demo_like, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull List<Object> param, int position, @NonNull final RecyclerView.ViewHolder holder) {
        holder.mButtonLikeAsyn.setOnClickListener {
           mPresenter.like(1, null);
        }

        holder.mButtonDislikeSyn.setOnClickListener {
            mPresenter.disLike(1, {xxxx});
        }
    }
}

```

==Presenter==

```java
public class LikePresenterImpl implements ILikePresenter {
    @Override
    public void like(int count,  Callback callback) {

    }
    
      @Override
    public void disLike(int count, Callback callback) {

    }
}
```

## 其他

### 绑定

```java
    //不同的View可以绑定同一个Presenter；
    bind(new DemoLikeView(this),    DemoLikePresenter.class);
    bind(new DemoLikeView2(this),   DemoLikePresenter.class);
```

```java
    //不同的Presenter方便替换绑定同一个View；
    bind(new DemoLikeView(this),    DemoLikePresenter.class);
    bind(new DemoLikeView(this),    DemoLikePresenter2.class);
```

灵活的组装模式，除了代码解耦复用，顺便能较方便的进行某些场景下的==ABTest==。

### 扩展Action
如果觉得`CheeseAction`不够用，可以自己继承扩展。

```java
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
```

### 普通View
也可以和普通的View绑定同一个Presenter。

```java
public class NormalViewHolder {
    private CheeseWapper mCheeseWapper = new CheeseWapper();

    public NormalViewHolder(View itemView) {
        mCheeseWapper.bind(itemView, DemoLikePresenter.class);
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
    }
}
```

