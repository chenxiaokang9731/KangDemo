package com.chen.viewpager;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;

/**
 * Created by 陈小康 on 2017/7/30.
 */

public class CustomCardView extends CardView {

    public static float RATIO;  //缩放比例

    public CustomCardView(Context context) {
        this(context, null);
    }

    public CustomCardView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomCardView);
        RATIO = typedArray.getFloat(R.styleable.CustomCardView_card_ratio, 1.0f);
        typedArray.recycle();

        setPreventCornerOverlap(true);
        setUseCompatPadding(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if(RATIO > 0){
            int newHeightMeasureSpec = MeasureSpec.makeMeasureSpec((int) (MeasureSpec.getSize(widthMeasureSpec) * RATIO), MeasureSpec.EXACTLY);
            super.onMeasure(widthMeasureSpec, newHeightMeasureSpec);
        }else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }

    }
}
