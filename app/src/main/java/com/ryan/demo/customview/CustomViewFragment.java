package com.ryan.demo.customview;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ryan.androidbaselib.R;
import com.ryan.common.wrapper.OnRecyclerItemClickListener;
import com.ryan.demo.customview.sub.SimpleScrollTextViewDemoAty;

/**
 * @author RyanLee
 */
public class CustomViewFragment extends Fragment implements OnRecyclerItemClickListener {

    public CustomViewFragment() {
    }

    public static CustomViewFragment newInstance() {
        CustomViewFragment fragment = new CustomViewFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_custom_view, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView mRecyclerView = view.findViewById(R.id.rv_custom_view);
        CustomViewAdapter mAdapter = new CustomViewAdapter(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        switch (position) {
            case 0:
                startActivity(new Intent(getActivity(), SimpleScrollTextViewDemoAty.class));
                break;
            default:
                break;
        }
    }
}
