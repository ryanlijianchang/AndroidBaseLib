package com.ryan.demo.tools

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.ryan.androidbaselib.R
import com.ryan.common.wrapper.OnRecyclerItemClickListener
import com.ryan.demo.tools.sub.ScreenShotActivity
import kotlinx.android.synthetic.main.fragment_tools.*

class ToolsFragment : Fragment() {

    companion object {
        const val TAG = "ToolsFragment"
        fun newInstance(): ToolsFragment {
            return ToolsFragment()
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tools, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_tools.layoutManager = GridLayoutManager(context, 2)
        rv_tools.adapter = ToolsRecyclerAdapter(OnRecyclerItemClickListener { _, position ->
            Log.i(TAG, "click position=$position")
            val intent = Intent(activity, ScreenShotActivity::class.java)
            startActivity(intent)
        })

    }

}