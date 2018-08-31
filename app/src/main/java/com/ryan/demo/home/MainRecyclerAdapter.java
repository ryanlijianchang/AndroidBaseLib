package com.ryan.demo.home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ryan.androidbaselib.R;
import com.ryan.baselib.util.AppUtils;
import com.ryan.baselib.util.ListUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.MainHolder> {
    private String[] mItemStrArr = {"Custom Widget", "Utils"};
    private List<String> mDatas;

    public MainRecyclerAdapter() {
        mDatas = new ArrayList<>();
        mDatas.addAll(Arrays.asList(mItemStrArr));
    }

    @Override
    public MainHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(AppUtils.getContext()).inflate(R.layout.item_main, parent, false);
        return new MainHolder(rootView);
    }

    @Override
    public void onBindViewHolder(MainHolder holder, int position) {
        String name = mDatas.get(position);
        holder.bindView(name);
    }

    @Override
    public int getItemCount() {
        return ListUtils.isEmpty(mDatas) ? 0 : mDatas.size();
    }

    static class MainHolder extends RecyclerView.ViewHolder {
        private Button mItemName;

        MainHolder(View itemView) {
            super(itemView);

            mItemName = itemView.findViewById(R.id.tv_item_main);
        }

        public void bindView(String text) {
            mItemName.setText(text);
        }
    }
}
