package com.example.pentagon.appbar.AdapterClass;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

public class HorizontalViewPager extends ViewPager {



    public HorizontalViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HorizontalViewPager(Context context) {
        super(context);
    }

    @Override
    public boolean canScrollVertically(int direction) {
        return false;
    }

    @Override
    public boolean canScrollHorizontally(int direction) {
        return false;
    }



}