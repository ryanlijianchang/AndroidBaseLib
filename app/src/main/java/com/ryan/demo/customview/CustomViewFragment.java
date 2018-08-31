package com.ryan.demo.customview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ryan.androidbaselib.R;

public class CustomViewFragment extends Fragment {
    public static final String TAG_CUSTOM_VIEEW = "tag_custom_view";
    public static final String TAG_UTILS = "tag_utils";
    public static final String TAG_DEFAULT = "tag_default";

    private String mTag = "";


    public CustomViewFragment() {
    }

    public static CustomViewFragment newInstance(String fragmentName) {
        CustomViewFragment fragment = new CustomViewFragment();
        Bundle args = new Bundle();
        args.putString("mTag", fragmentName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mTag = getArguments().getString("mTag");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_custom_view, container, false);
    }
}
