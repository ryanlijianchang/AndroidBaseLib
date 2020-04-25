package com.ryan.common.data

import com.ryan.androidbaselib.R
import com.ryan.baselib.util.ResourceUtils
import java.util.*
import kotlin.collections.ArrayList

val homeTags: List<String>
    get() {
        val tagArr = ResourceUtils.getStringArray(R.array.array_home_tags)
        return ArrayList(listOf(*tagArr))
    }

/**
 * UtilsFragment对应的TAG
 * @return list
 */
val utilsTags: List<String>
    get() {
        val tagArr = ResourceUtils.getStringArray(R.array.array_utils_tags)
        return ArrayList(listOf(*tagArr))
    }

/**
 * CustomViewFragment对应的TAG
 * @return list
 */
val customViewTitles: List<String>
    get() {
        val tagArr = ResourceUtils.getStringArray(R.array.array_custom_view_tags)
        return ArrayList(listOf(*tagArr))
    }

val toolsTags: List<String>
    get() {
        val tagArr = ResourceUtils.getStringArray(R.array.array_tools_tags)
        return ArrayList(listOf(*tagArr))
    }