package com.ryan.demo.customview;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ryan.androidbaselib.R;
import com.ryan.baselib.util.AppUtils;
import com.ryan.baselib.util.ListUtils;
import com.ryan.common.data.ColorDataSource;
import com.ryan.common.data.DataSourceKt;
import com.ryan.common.wrapper.OnRecyclerItemClickListener;

import java.util.List;

public class CustomViewAdapter extends RecyclerView.Adapter<CustomViewAdapter.CustomViewHolder> {
    private List<String> mDatas;
    private OnRecyclerItemClickListener mListener;

    public CustomViewAdapter(OnRecyclerItemClickListener listener) {
        this.mDatas = DataSourceKt.getCustomViewTitles();
        this.mListener = listener;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(AppUtils.getContext()).inflate(R.layout.item_custom_fragment, parent, false);
        return new CustomViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        String name = mDatas.get(position);
        holder.bindView(name);
    }

    @Override
    public int getItemCount() {
        return ListUtils.isEmpty(mDatas) ? 0 : mDatas.size();
    }

    static class CustomViewHolder extends RecyclerView.ViewHolder {
        private Button mItemName;
        private OnRecyclerItemClickListener mListener;

        CustomViewHolder(View itemView, OnRecyclerItemClickListener listener) {
            super(itemView);
            this.mListener = listener;
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
