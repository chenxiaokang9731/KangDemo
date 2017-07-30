package com.chen.viewpager;

import android.view.View;

/**
 * Created by 陈小康 on 2017/7/29.
 */

public class ItemView {

    public View view;

    public ItemView(View view){

        if(view == null){
            throw new RuntimeException("View can not be null!!");
        }

        this.view = view;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }


}
