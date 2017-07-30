package com.chen.viewpager;

import android.content.Context;
import android.view.View;

/**
 * Created by 陈小康 on 2017/7/29.
 * 绑定ItemView将其转化为View
 */
public interface ItemViewHandler<T> {

    View bind(Context context, T data);

}
