package com.chen.viewpager;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by 陈小康 on 2017/7/29.
 */

public class MyPagerAdapter extends PagerAdapter {

    private List<ItemView> mItemViews;
    private boolean mIsLoop;

    public MyPagerAdapter(List<ItemView> itemViews, boolean isLoop){
        mItemViews = itemViews;
        mIsLoop = isLoop;
    }

    @Override
    public int getCount() {
        return mIsLoop? Integer.MAX_VALUE : getItems();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        if(mIsLoop){
            position = position % getItems();
        }

        container.addView(mItemViews.get(position).getView());
        return mItemViews.get(position).getView();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        if(mIsLoop){
            ViewPager vp = (ViewPager)container;
            int i = vp.getCurrentItem() % getItems();
            int j = position % getItems();
            if(i > j - 2 && i < j + 2){
                return;
            }
            container.removeView(mItemViews.get(j).getView());
        }

        container.removeView(mItemViews.get(position % getItems()).getView());
    }

    @Override
    public void startUpdate(ViewGroup container) {
        super.startUpdate(container);
        if(mIsLoop){
            ViewPager vp = (ViewPager)container;
            int position = vp.getCurrentItem();
            if(position == 0){
                position = Integer.MAX_VALUE / 2;
            }else if(position == getItems() - 1){
                position = Integer.MAX_VALUE / 2 - 1;
            }
            vp.setCurrentItem(position, false);
        }
    }

    public int getItems(){
        return mItemViews == null? 0:mItemViews.size();
    }
}
