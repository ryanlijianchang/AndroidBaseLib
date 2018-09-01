package com.ryan.demo.utils.sub;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.ryan.androidbaselib.R;
import com.ryan.baselib.util.ResourceUtils;
import com.ryan.baselib.util.TelephonyUtils;
import com.ryan.common.base.BaseActivity;

/**
 * @author RyanLee
 */
public class TelephonyUtilsDemoAty extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telephony_utils_demo_aty);

        Toolbar mToolbar = findViewById(R.id.toolbar_telephony_utils);
        TextView mContent = findViewById(R.id.tv_telephony_utils);

        setUpToolbar(mToolbar);


        String stringBuilder = ResourceUtils.getString(R.string.imei) +
                TelephonyUtils.getImei() +
                "\n";

        mContent.setText(stringBuilder);
    }
}
