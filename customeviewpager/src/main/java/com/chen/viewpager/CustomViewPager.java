package com.chen.viewpager;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 陈小康 on 2017/7/29.
 */

public class CustomViewPager extends ViewPager {

    private Context mContext;
    private int max_offset;
    private boolean mIsLoop = false;

    public CustomViewPager(Context context) {
        this(context, null);
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.mContext = context;
        setClipToPadding(false);

        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomViewPager);

        int padding = (int) typedArray.getDimension(R.styleable.CustomViewPager_vp_padding, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60, metrics));
        setPadding(getPaddingLeft() + padding, getPaddingTop(), getPaddingRight() + padding, getPaddingBottom());

        int margin = (int) typedArray.getDimension(R.styleable.CustomViewPager_vp_margin, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, metrics));
        setPageMargin(margin);

        max_offset = (int) typedArray.getDimension(R.styleable.CustomViewPager_vp_max_offset, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 180, metrics));

        mIsLoop = typedArray.getBoolean(R.styleable.CustomViewPager_vp_loop, mIsLoop);

        typedArray.recycle();
    }

    /**
     *  必须调用 绑定数据
     */
    public <T> void bindView(List<T> data, ItemViewHandler<T> handler){
        List<ItemView> itemViews = getItemViews(data, handler);
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(itemViews, mIsLoop);
        setPageTransformer(false, new CustomPageTransforms(max_offset));
        setAdapter(myPagerAdapter);
    }

    private <T> List<ItemView> getItemViews(List<T> data, ItemViewHandler<T> handler) {
        if(data == null){
            throw new RuntimeException("data can not null");
        }

        List<ItemView> itemViews = new ArrayList<>();
        for (T t: data) {
            ItemView itemView = new ItemView(handler.bind(mContext, t));
            itemViews.add(itemView);
        }

        return itemViews;
    }
}
