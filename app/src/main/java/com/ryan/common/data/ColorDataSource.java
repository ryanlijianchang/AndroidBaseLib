package com.ryan.common.data;

import com.ryan.androidbaselib.R;
import com.ryan.baselib.util.AppUtils;
import com.ryan.baselib.util.ResourceUtils;

import java.util.Random;

public class ColorDataSource {

    public static String getRandomMaterialColor() {
        String[] colorArr = ResourceUtils.getStringArray(R.array.materialColorArr);
        int ran = new Random().nextInt(colorArr.length);
        return colorArr[ran];
    }
}
