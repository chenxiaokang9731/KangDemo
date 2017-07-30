package com.narkang;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chen.viewpager.ItemViewHandler;


/**
 * Created by 陈小康 on 2017/7/29.
 */

public class MyItemViewHandler implements ItemViewHandler<String> {

    @Override
    public View bind(Context context, String url) {

        View view = View.inflate(context, R.layout.item_view, null);
        ImageView iv = (ImageView) view.findViewById(R.id.iv);
        Glide.with(context).load(url).into(iv);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do something other
            }
        });

        return view;
    }
}
