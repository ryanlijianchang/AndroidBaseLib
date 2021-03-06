package com.ryan.demo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.ryan.androidbaselib.R;
import com.ryan.common.view.SimpleTabLayout;

/**
 * @author RyanLee
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SimpleTabLayout mTab = findViewById(R.id.tab_main);
        ViewPager mViewPager = findViewById(R.id.vp_main);

        mViewPager.setAdapter(new TabFragmentAdapter(getSupportFragmentManager()));
        mTab.setupWithViewPager(mViewPager);
    }
}
