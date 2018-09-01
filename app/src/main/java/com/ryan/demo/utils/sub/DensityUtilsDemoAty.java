package com.ryan.demo.utils.sub;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.ryan.androidbaselib.R;
import com.ryan.baselib.util.DensityUtils;
import com.ryan.baselib.util.ResourceUtils;
import com.ryan.common.base.BaseActivity;

/**
 * @author RyanLee
 */
public class DensityUtilsDemoAty extends BaseActivity {
    private Toolbar mToolbar;
    private TextView mContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_density_utils_demo_aty);

        mToolbar = findViewById(R.id.toolbar_density_utils);
        mContent = findViewById(R.id.tv_density_utils);

        setUpToolbar(mToolbar);

        String stringBuilder = ResourceUtils.getString(R.string.screen_width) +
                DensityUtils.getScreenWidth() +
                ResourceUtils.getString(R.string.pixel) +
                "\n" +
                ResourceUtils.getString(R.string.screen_height) +
                DensityUtils.getScreenHeight() +
                ResourceUtils.getString(R.string.pixel) +
                "\n" +
                ResourceUtils.getString(R.string.screen_density) +
                DensityUtils.getDensity() +
                ResourceUtils.getString(R.string.pixel) +
                "\n" +
                ResourceUtils.getString(R.string.screen_density_dpi) +
                DensityUtils.getDensityDPI() +
                ResourceUtils.getString(R.string.pixel) +
                "\n" +
                ResourceUtils.getString(R.string.screen_scaled_density) +
                DensityUtils.getScaledDensity() +
                ResourceUtils.getString(R.string.pixel);


        mContent.setText(stringBuilder);
    }
}
