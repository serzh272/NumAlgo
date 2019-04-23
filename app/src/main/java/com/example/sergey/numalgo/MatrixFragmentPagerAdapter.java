package com.example.sergey.numalgo;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MatrixFragmentPagerAdapter extends FragmentPagerAdapter {
    private FragmentManager fm;
    private List<MatrixFragment> mMatrixFragments;
    public MatrixFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        this.fm = fm;
        mMatrixFragments = new ArrayList<>();
    }

    public Fragment getFragment(int id){
        mMatrixFragments.add(MatrixFragment.newInstance(id));
        return mMatrixFragments.get(id);
    }

    @Override
    public Fragment getItem(int i) {
        return getFragment(i);
    }

    public List<MatrixFragment> getMatrixFragments(){
        return mMatrixFragments;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "M1";
            case 1:
                return "M2";
            case 2:
                return "result";
            default:
                return "Matrix";
        }
    }
}
