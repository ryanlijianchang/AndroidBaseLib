package com.ryan.demo.tools

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.ryan.androidbaselib.R
import com.ryan.baselib.util.AppUtils
import com.ryan.baselib.util.ListUtils
import com.ryan.common.data.ColorDataSource
import com.ryan.common.data.toolsTags
import com.ryan.common.wrapper.OnRecyclerItemClickListener

/**
 * UtilsFragment的RecyclerViewAdapter
 * @author RyanLee
 */
class ToolsRecyclerAdapter internal constructor(listener: OnRecyclerItemClickListener) : RecyclerView.Adapter<ToolsRecyclerAdapter.UtilsHolder>() {
    private val mDatas: List<String> = toolsTags
    private val mListener: OnRecyclerItemClickListener = listener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UtilsHolder {
        val rootView = LayoutInflater.from(AppUtils.getContext()).inflate(R.layout.item_tools_fragment, parent, false)
        return UtilsHolder(rootView, mListener)
    }

    override fun onBindViewHolder(holder: UtilsHolder, position: Int) {
        val name = mDatas[position]
        holder.bindView(name)
    }

    override fun getItemCount(): Int {
        return if (ListUtils.isEmpty(mDatas)) 0 else mDatas.size
    }

    class UtilsHolder(itemView: View, private val mListener: OnRecyclerItemClickListener?) : RecyclerView.ViewHolder(itemView) {
        private val mItemName: Button = itemView.findViewById(R.id.tv_item_tools)
        fun bindView(text: String?) {
            mItemName.setBackgroundColor(Color.parseColor(ColorDataSource.getRandomMaterialColor()))
            mItemName.text = text
            mItemName.setOnClickListener { view -> mListener?.onItemClick(view, adapterPosition) }
        }
    }

}