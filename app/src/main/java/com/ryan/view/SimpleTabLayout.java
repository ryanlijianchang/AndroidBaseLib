package com.ryan.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.ryan.androidbaselib.R;
import com.ryan.data.HomeDataSource;
import com.ryan.wrapper.OnTabSelectedListenerWrapper;

import java.util.List;

/**
 * @author RyanLee
 */
public class SimpleTabLayout extends TabLayout {
    public SimpleTabLayout(Context context) {
        this(context, null);
    }

    public SimpleTabLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SimpleTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
        listen();
    }

    private void init() {
        setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    private void listen() {
        addOnTabSelectedListener(new OnTabSelectedListenerWrapper() {
            @Override
            public void onTabSelected(Tab tab) {
                updateTabTextView(tab, true);
            }

            @Override
            public void onTabUnselected(Tab tab) {
                updateTabTextView(tab, false);
            }
        });
    }


    private void updateTabTextView(TabLayout.Tab tab, boolean isSelect) {
        View view = tab.getCustomView();
        if (view == null) {
            return;
        }

        if (isSelect) {
            //选中加粗
            TextView tabSelect = view.findViewById(R.id.tab_simple_tab_layout);
            tabSelect.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            tabSelect.setTextSize(TypedValue.COMPLEX_UNIT_SP , 18);
            tabSelect.setTextColor(Color.parseColor("#ffffff"));
        } else {
            TextView tabUnSelect = view.findViewById(R.id.tab_simple_tab_layout);
            tabUnSelect.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            tabUnSelect.setTextSize(TypedValue.COMPLEX_UNIT_SP , 16);
            tabUnSelect.setTextColor(Color.parseColor("#aaffffff"));
        }
    }

    @Override
    public void setupWithViewPager(@Nullable ViewPager viewPager) {
        super.setupWithViewPager(viewPager);

        setCustomView();
        Tab mDefaultTab = getTabAt(0);
        if (mDefaultTab != null) {
            updateTabTextView(mDefaultTab, true);
        }
    }

    private void setCustomView() {
        for (int i = 0; i < getTabCount(); i++) {
            TabLayout.Tab tab = getTabAt(i);
            if (tab != null) {
                tab.setCustomView(getTabView(i));
            }
        }
    }

    private View getTabView(int currentPosition) {
        List<String> mTitles = HomeDataSource.getHomeTags();
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_simple_tab_layout_tab, null);
        TextView textView = view.findViewById(R.id.tab_simple_tab_layout);
        textView.setText(mTitles.get(currentPosition));
        return view;
    }
}
