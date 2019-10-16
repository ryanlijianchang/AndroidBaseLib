package com.ryan.baselib.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

import com.ryan.baselib.util.DensityUtils;

public class SimpleIndicatorView extends View implements ViewPager.OnPageChangeListener {

    private static final int MIN_PAGE_NUM = 1;
    private static final int DEFAULT_SELECT_PAGE_NUM = 1;

    private static final int SIZE_OF_CORNER = DensityUtils.dp2px(1);

    /**
     * 选中框的大小为9dp
     */
    private static final int SIZE_OF_SELECT_VIEW = DensityUtils.dp2px(9);
    /**
     * 页码器在wrap_content时最长宽度为屏幕的一半
     */
    private static final int MAX_WIDTH_OF_INDICATOR_IN_WRAP_CONTENT = DensityUtils.getScreenWidth() / 2;
    /**
     * 页码器在wrap_content时的高度为2dp
     */
    private static final int INDICATOR_HEIGHT_IN_WRAP_CONTENT = DensityUtils.dp2px(2);


    int mCurrentPage = DEFAULT_SELECT_PAGE_NUM;
    int mPageNum = MIN_PAGE_NUM;

    private Paint mPaint;
    private RectF mRectF;

    public SimpleIndicatorView(Context context) {
        this(context, null);
    }

    public SimpleIndicatorView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SimpleIndicatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRectF = new RectF();
    }

    public void bindViewPager(@NonNull ViewPager viewPager) {
        mPageNum = viewPager.getAdapter() == null ? MIN_PAGE_NUM : viewPager.getAdapter().getCount();
        mCurrentPage = DEFAULT_SELECT_PAGE_NUM;
        viewPager.addOnPageChangeListener(this);
        requestLayout();
        invalidate();
    }

    private void updateCurrentPage(int currentPage) {
        this.mCurrentPage = currentPage;
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
    }

    private int measureWidth(int measureSpec) {
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        int result = SIZE_OF_SELECT_VIEW;
        if (specMode == MeasureSpec.AT_MOST) {
            //wrap_content时，宽度 = 页码数 * 单个选中框的宽度
            result = result * mPageNum;
            // 如果此时设置的宽度超过屏幕一半，使用屏幕一半的宽度
            result = result > MAX_WIDTH_OF_INDICATOR_IN_WRAP_CONTENT ? MAX_WIDTH_OF_INDICATOR_IN_WRAP_CONTENT : result;

        } else if (specMode == MeasureSpec.EXACTLY) {//相当于我们设置为match_parent或者为一个具体的值
            result = specSize;
        }
        return result;
    }

    private int measureHeight(int measureSpec) {
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        int result = INDICATOR_HEIGHT_IN_WRAP_CONTENT;
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setColor(Color.parseColor("#33FFFFFF"));
        canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);

        mPaint.setColor(Color.WHITE);
        if (getWidth() >= mPageNum * SIZE_OF_SELECT_VIEW) {
            // 说明能放得下
            int leftMargin = (mCurrentPage - 1) * SIZE_OF_SELECT_VIEW;
            mRectF.set(leftMargin, 0, leftMargin + SIZE_OF_SELECT_VIEW, getHeight());
            canvas.drawRoundRect(mRectF, SIZE_OF_CORNER, SIZE_OF_CORNER, mPaint);

        } else {
            if (mPageNum <= MIN_PAGE_NUM) {
                canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);
            } else {
                float leftMargin = (getWidth() - SIZE_OF_SELECT_VIEW) / (mPageNum - 1);
                float left = (mCurrentPage - 1) * leftMargin;
                mRectF.set(left, 0, SIZE_OF_SELECT_VIEW + left, getHeight());
                canvas.drawRoundRect(mRectF, SIZE_OF_CORNER, SIZE_OF_CORNER, mPaint);
            }
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        updateCurrentPage(position + 1);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
