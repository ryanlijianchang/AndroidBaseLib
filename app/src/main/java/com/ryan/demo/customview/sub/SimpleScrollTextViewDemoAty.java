package com.ryan.demo.customview.sub;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.ryan.androidbaselib.R;
import com.ryan.baselib.util.BitmapUtils;
import com.ryan.baselib.util.DensityUtils;
import com.ryan.baselib.util.ThreadUtils;
import com.ryan.baselib.widget.CenteredImageSpan;
import com.ryan.baselib.widget.SimpleScrollTextView;
import com.ryan.common.base.BaseActivity;

import static android.view.animation.Animation.ABSOLUTE;
import static android.view.animation.Animation.RELATIVE_TO_PARENT;
import static android.view.animation.Animation.RELATIVE_TO_SELF;

/**
 * @author RyanLee
 */
public class SimpleScrollTextViewDemoAty extends BaseActivity {
    private SimpleScrollTextView mPanel1, mPanel2, mPanel3;
    private ImageView mLightView;
    private View mContainer;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_scroll_text_view_demo_aty);

        mToolbar = findViewById(R.id.toolbar_simple_scroll);
        setUpToolbar(mToolbar);

        final Button button = findViewById(R.id.btn_simple_scroll_start);
        mContainer = findViewById(R.id.cl_simple_scroll_container);
        mLightView = findViewById(R.id.iv_simple_scroll_light);
        mPanel1 = findViewById(R.id.tv_simple_scroll_panel_1);
        mPanel2 = findViewById(R.id.tv_simple_scroll_panel_2);
        mPanel3 = findViewById(R.id.tv_simple_scroll_panel_3);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPanel1();
                initPanel2();
                initPanel3();
                startAnimation();
            }
        });
    }



    private void initPanel1() {
        SpannableStringBuilder builder1 = new SpannableStringBuilder();
        String text = "This is a long string about testing the simple scroll text view!";
        String highText1 = "simple scroll text view";
        String format1 = "<font color=\"#fef500\">%1$s</font>";
        String replaceText1 = String.format(format1, highText1);
        text = text.replace(highText1, replaceText1);

        builder1.append(" ").append(Html.fromHtml(text));

        mPanel1.setText(Html.fromHtml(text));
    }

    private void initPanel2() {
        SpannableStringBuilder builder1 = new SpannableStringBuilder();
        builder1.append("0 ");

        Bitmap bitmap1 = BitmapUtils.drawTextToBitmap(getBaseContext(), BitmapUtils.decodeResToBitmap(R.drawable.label), "骑士", "#FED746");
        CenteredImageSpan label1 = new CenteredImageSpan(getBaseContext(), bitmap1);
        builder1.setSpan(label1, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


        String text = "Jack is Coming! Welcome Jack to be a new Fans!";
        String highText1 = "Jack";
        String format1 = "<font color=\"#fef500\">%1$s</font>";
        String replaceText1 = String.format(format1, highText1);
        text = text.replace(highText1, replaceText1);

        builder1.append(" ").append(Html.fromHtml(text));

        mPanel2.setText(builder1);
    }

    private void initPanel3() {
        SpannableStringBuilder builder1 = new SpannableStringBuilder();

        builder1.append("0 1 2");

        Bitmap bitmap1 = BitmapUtils.drawTextToBitmap(getBaseContext(), BitmapUtils.decodeResToBitmap(R.drawable.label), "骑士", "#FED746");
        Bitmap bitmap2 = BitmapUtils.drawTextToBitmap(getBaseContext(), BitmapUtils.decodeResToBitmap(R.drawable.label2), "伯爵", "#FFF255");
        Bitmap bitmap3 = BitmapUtils.drawTextToBitmap(getBaseContext(), BitmapUtils.decodeResToBitmap(R.drawable.label3), "公爵", "#FFEC55");


        CenteredImageSpan label1 = new CenteredImageSpan(getBaseContext(), bitmap1);
        CenteredImageSpan label2 = new CenteredImageSpan(getBaseContext(), bitmap2);
        CenteredImageSpan label3 = new CenteredImageSpan(getBaseContext(), bitmap3);


        builder1.setSpan(label1, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder1.setSpan(label2, 2, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder1.setSpan(label3, 4, 5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        String text = " James is Coming！";
        String highText1 = "James";
        String format1 = "<font color=\"#fef500\">%1$s</font>";
        String replaceText1 = String.format(format1, highText1);
        text = text.replace(highText1, replaceText1);

        builder1.append(" ").append(Html.fromHtml(text));

        mPanel3.setText(builder1);
    }

    private void startAnimation() {
        mContainer.setVisibility(View.VISIBLE);

        TranslateAnimation leftInAnimation = new TranslateAnimation(RELATIVE_TO_PARENT, -1, RELATIVE_TO_PARENT, 0, RELATIVE_TO_SELF, 0, RELATIVE_TO_SELF, 0);
        leftInAnimation.setDuration(500);
        leftInAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        leftInAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mLightView.setVisibility(View.VISIBLE);
                AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0f);
                TranslateAnimation translateAnimation = new TranslateAnimation(ABSOLUTE, 0, ABSOLUTE, DensityUtils.dp2px(275), RELATIVE_TO_SELF, 0, RELATIVE_TO_SELF, 0);
                AnimationSet animationSet = new AnimationSet(true);
                animationSet.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        mLightView.setVisibility(View.GONE);
                        mPanel1.startScroll();
                        mPanel2.startScroll();
                        mPanel3.startScroll();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                animationSet.setDuration(800);
                animationSet.addAnimation(alphaAnimation);
                animationSet.addAnimation(translateAnimation);
                mLightView.startAnimation(animationSet);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mContainer.startAnimation(leftInAnimation);

        ThreadUtils.runOnUiThread(mLeaveAnimTask, 3300);
    }

    private Runnable mLeaveAnimTask = new Runnable() {
        @Override
        public void run() {
            AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0f);
            alphaAnimation.setDuration(900);
            alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    mContainer.setVisibility(View.GONE);
                    mPanel1.stopScroll();
                    mPanel2.stopScroll();
                    mPanel3.stopScroll();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            mContainer.startAnimation(alphaAnimation);
        }
    };


}
