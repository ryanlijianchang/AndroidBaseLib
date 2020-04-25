package com.ryan.baselib.widget;

import android.content.Context;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.view.animation.LinearInterpolator;
import android.widget.Scroller;

import androidx.appcompat.widget.AppCompatTextView;

/**
 * 非常规走马灯控件
 * 文案超过控件宽度，会让文案滚动到文案尾部，然后停下
 * @author RyanLee
 */
public class SimpleScrollTextView extends AppCompatTextView {

    private Scroller mScroller;

    private int mDuration = 2000;


    public SimpleScrollTextView(Context context) {
        this(context, null);
    }

    public SimpleScrollTextView(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.textViewStyle);
    }

    public SimpleScrollTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setSingleLine();
        setEllipsize(null);
    }

    /**
     * 开始滚动
     */
    public void startScroll() {
        setHorizontallyScrolling(true);

        mScroller = new Scroller(this.getContext(), new LinearInterpolator());
        setScroller(mScroller);

        int scrollingLen = calculateContentLen();
        int distance = scrollingLen - (getWidth() - getCompoundPaddingLeft() - getCompoundPaddingRight());

        setVisibility(VISIBLE);

        if (distance > 0) {
            mScroller.startScroll(0, 0, distance, 0, mDuration);
            invalidate();
        }
    }

    /**
     * 复位
     */
    public void stopScroll() {
        if (mScroller != null) {
            setScrollX(0);
            invalidate();
        }
    }

    /**
     * 计算内容总长度(像素)
     *
     * @return int
     */
    private int calculateContentLen() {
        // 内容总长度
        int contentLen;
        // 获取内容charSequence
        CharSequence charSequence = getText();
        // 先记录内容长度
        contentLen = (int) getPaint().measureText(getText().toString());

        // 如果内容是一个Spanned
        if (charSequence instanceof Spanned) {
            Spanned sp = (Spanned) charSequence;
            // 获取全部Span
            Object[] spans = sp.getSpans(0, charSequence.length(), Object.class);

            if (spans == null || spans.length <= 0) {
                return contentLen;
            }

            // ImageSpan的图片总宽度
            int imageSpanWidth = 0;
            // ImageSpan替换的文字总宽度
            int imageSpanReplaceWidth = 0;

            for (Object span1 : spans) {
                // 遍历每一个ImageSpan
                if (span1 instanceof ImageSpan) {
                    ImageSpan span = (ImageSpan) span1;
                    // 获取ImageSpan的宽度，并累加到imageSpanWidth
                    imageSpanWidth += span.getDrawable().getIntrinsicWidth();

                    // 获取ImageSpan替换内容的位置
                    int start = sp.getSpanStart(span);
                    int end = sp.getSpanEnd(span);

                    // 获取被替换内容的总宽度
                    int len = (int) getPaint().measureText(getText().toString(), start, end);
                    imageSpanReplaceWidth += len;
                }
            }
            // 内容总长度 = 纯文字的内容总长度 + 所有ImageSpan图片总宽度 - 所有ImageSpan替换的文字总宽度
            contentLen = contentLen + imageSpanWidth - imageSpanReplaceWidth;

        }
        return contentLen;
    }


    public int getDuration() {
        return mDuration;
    }

    public void setDuration(int duration) {
        this.mDuration = duration;
    }
}
