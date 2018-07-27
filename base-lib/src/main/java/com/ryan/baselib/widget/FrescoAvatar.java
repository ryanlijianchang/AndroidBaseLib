package com.ryan.baselib.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.ryan.baselib.R;

public class FrescoAvatar extends SimpleDraweeView {
    private int mDefaultAvatarRes;

    public FrescoAvatar(Context context) {
        this(context, null);
    }

    public FrescoAvatar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FrescoAvatar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FrescoAvatar, defStyle, 0);
        mDefaultAvatarRes = a.getResourceId(R.styleable.FrescoAvatar_default_img, R.drawable.ic_default_avatar);
        a.recycle();

        initView();
    }

    private void initView() {
        setIsCircle(true);
        setDefaultImageResId(mDefaultAvatarRes);
    }

    public void setIsCircle(boolean isCircle) {
        GenericDraweeHierarchy dh = getHierarchy();
        if (dh == null) {
            return;
        }
        RoundingParams rp = dh.getRoundingParams();
        if (rp == null) {
            if (isCircle) {
                rp = new RoundingParams();
                rp.setRoundAsCircle(isCircle);
                getHierarchy().setRoundingParams(rp);
            }
        } else {
            rp.setRoundAsCircle(isCircle);
        }
    }

    public void setDefaultImageResId(int defaultImageResId) {
        getHierarchy().setPlaceholderImage(getContext().getResources().getDrawable(defaultImageResId), ScalingUtils.ScaleType.FIT_CENTER);
    }

    public void setErrorImageResId(int errorImageId) {
        getHierarchy().setFailureImage(getContext().getResources().getDrawable(errorImageId), ScalingUtils.ScaleType.FIT_CENTER);
    }
}
