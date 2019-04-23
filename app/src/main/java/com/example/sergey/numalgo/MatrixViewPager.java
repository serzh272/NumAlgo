package com.example.sergey.numalgo;

import android.content.Context;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

public class MatrixViewPager extends ViewPager {

    public MatrixViewPager(@NonNull Context context) {
        super(context);
    }

    public MatrixViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            widthMeasureSpec = getDefaultSize(getSuggestedMinimumWidth(),widthMeasureSpec);
            heightMeasureSpec = widthMeasureSpec;
        }else {
            heightMeasureSpec = getDefaultSize(getSuggestedMinimumHeight(),heightMeasureSpec);
            widthMeasureSpec = heightMeasureSpec;
        }
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }
}
