package com.ryan.demo.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.ryan.baselib.util.ListUtils;
import com.ryan.common.data.HomeDataSource;
import com.ryan.demo.customview.CustomViewFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author RyanLee
 */
public class TabFragmentAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> fmList = new ArrayList<>();


    public TabFragmentAdapter(FragmentManager fm) {
        super(fm);

    }


    @Override
    public Fragment getItem(int position) {
        Fragment mCurFragment = null;
        if (position < fmList.size()) {
            mCurFragment = fmList.get(position);
        }
        if (mCurFragment == null) {
            switch (position) {
                case 0:
                    mCurFragment = new CustomViewFragment();
                    break;
                case 1:
                    mCurFragment = new CustomViewFragment();
                    break;
                default:
                    break;
            }
            if (!fmList.contains(mCurFragment)) {
                fmList.add(mCurFragment);
            }
        }
        return mCurFragment;
    }

    @Override
    public int getCount() {
        List<String> mTitles = HomeDataSource.getHomeTags();
        return ListUtils.isEmpty(mTitles) ? 0 : mTitles.size();
    }
}
