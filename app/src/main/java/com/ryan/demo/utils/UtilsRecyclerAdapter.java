package com.ryan.demo.utils;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ryan.androidbaselib.R;
import com.ryan.baselib.util.AppUtils;
import com.ryan.baselib.util.ListUtils;
import com.ryan.common.data.ColorDataSource;
import com.ryan.common.data.DataSource;
import com.ryan.common.wrapper.OnRecyclerItemClickListener;

import java.util.List;

/**
 * UtilsFragment的RecyclerViewAdapter
 * @author RyanLee
 */
public class UtilsRecyclerAdapter extends RecyclerView.Adapter<UtilsRecyclerAdapter.UtilsHolder> {
    private List<String> mDatas;
    private OnRecyclerItemClickListener mListener;

    UtilsRecyclerAdapter(OnRecyclerItemClickListener listener) {
        mDatas = DataSource.getUtilsTags();
        mListener = listener;
    }

    @Override
    public UtilsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(AppUtils.getContext()).inflate(R.layout.item_main, parent, false);
        return new UtilsHolder(rootView, mListener);
    }

    @Override
    public void onBindViewHolder(UtilsHolder holder, int position) {
        String name = mDatas.get(position);
        holder.bindView(name);
    }

    @Override
    public int getItemCount() {
        return ListUtils.isEmpty(mDatas) ? 0 : mDatas.size();
    }

    static class UtilsHolder extends RecyclerView.ViewHolder {
        private Button mItemName;
        private OnRecyclerItemClickListener mListener;

        UtilsHolder(View itemView, OnRecyclerItemClickListener mListener) {
            super(itemView);
            this.mListener = mListener;
            mItemName = itemView.findViewById(R.id.tv_item_utils);
            mItemName.setBackgroundColor(Color.parseColor(ColorDataSource.getRandomMaterialColor()));
        }

        void bindView(String text) {
            mItemName.setText(text);
            mItemName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null) {
                        mListener.onItemClick(view, getAdapterPosition());
                    }
                }
            });
        }
    }
}
