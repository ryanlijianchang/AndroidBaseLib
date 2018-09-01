package com.ryan.common.data;

import com.ryan.androidbaselib.R;
import com.ryan.baselib.util.AppUtils;
import com.ryan.baselib.util.ResourceUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataSource {

    public static List<String> getHomeTags() {
        String[] tagArr = ResourceUtils.getStringArray(R.array.array_home_tags);
        return new ArrayList<>(Arrays.asList(tagArr));
    }

    /**
     * UtilsFragment对应的TAG
     * @return list
     */
    public static List<String> getUtilsTags() {
        String[] tagArr = ResourceUtils.getStringArray(R.array.array_utils_tags);
        return new ArrayList<>(Arrays.asList(tagArr));
    }

    /**
     * CustomViewFragment对应的TAG
     * @return list
     */
    public static List<String> getCustomViewTitles() {
        String[] tagArr = ResourceUtils.getStringArray(R.array.array_custom_view_tags);
        return new ArrayList<>(Arrays.asList(tagArr));
    }
}
