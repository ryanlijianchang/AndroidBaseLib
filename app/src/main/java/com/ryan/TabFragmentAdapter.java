package com.ryan;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.ryan.baselib.util.ListUtils;
import com.ryan.data.HomeDataSource;

import java.util.ArrayList;
import java.util.List;

/**
 * @author RyanLee
 */
public class TabFragmentAdapter extends FragmentStatePagerAdapter {
    private List<TabFragment> fmList = new ArrayList<>();


    public TabFragmentAdapter(FragmentManager fm) {
        super(fm);

        initTabFragments();
    }

    private void initTabFragments() {
        fmList.add(TabFragment.newInstance(TabFragment.TAG_CUSTOM_VIEEW));
        fmList.add(TabFragment.newInstance(TabFragment.TAG_UTILS));
    }

    @Override
    public Fragment getItem(int i) {
        return fmList.get(i);
    }

    @Override
    public int getCount() {
        List<String> mTitles = HomeDataSource.getHomeTags();
        return ListUtils.isEmpty(mTitles) ? 0 : mTitles.size();
    }
}
