package com.ryan.baselib.util;

import android.util.SparseArray;

import java.util.Collection;
import java.util.Map;

public class ListUtils {

    public static <T> boolean isEmpty(Collection<T> c) {
        return c == null || c.isEmpty();
    }

    public static <T> boolean isEmpty(SparseArray<T> sparseArray) {
        return sparseArray == null || sparseArray.size() == 0;
    }

    public static <K, V> boolean isEmpty(Map<K, V> map) {
        return map == null || map.size() == 0;
    }

    public static <T> boolean isEmpty(T[] array) {
        return array == null || array.length == 0;
    }
}
