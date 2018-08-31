package com.ryan.common.data;

import com.ryan.androidbaselib.R;
import com.ryan.baselib.util.AppUtils;
import com.ryan.baselib.util.ResourceUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeDataSource {

    public static List<String> getHomeTags() {
        String[] tagArr = ResourceUtils.getStringArray(AppUtils.getContext(), R.array.array_home_tags);
        return new ArrayList<>(Arrays.asList(tagArr));
    }
}
