package com.ryan.common.base;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.ryan.baselib.util.AppCompatUtils;

/**
 * @author RyanLee
 */
public abstract class BaseActivity extends AppCompatActivity {

    /**
     * 设置左上角back按钮
     */
    public void setUpToolbar(Toolbar toolbar) {
        AppCompatActivity subAty =  AppCompatUtils.getAppCompatActivity(this);
        if (subAty != null) {
            String titleName = subAty.getClass().getSimpleName();
            toolbar.setTitle(titleName);
        }

        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ActionBar actionBar = getSupportActionBar();
        if (actionBar == null) {
            return;
        }
        // 给左上角图标的左边加上一个返回的图标 。对应ActionBar.DISPLAY_HOME_AS_UP
        actionBar.setDisplayHomeAsUpEnabled(true);
        //设置actionBar的标题是否显示,对应ActionBar.DISPLAY_SHOW_TITLE。
        actionBar.setDisplayShowTitleEnabled(true);

    }
}
