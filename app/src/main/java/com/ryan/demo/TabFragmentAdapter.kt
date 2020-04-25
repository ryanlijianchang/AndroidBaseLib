package com.ryan.demo

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.ryan.demo.customview.CustomViewFragment
import com.ryan.demo.tools.ToolsFragment
import com.ryan.demo.utils.UtilsFragment

/**
 * @author RyanLee
 */
class TabFragmentAdapter internal constructor(fm: FragmentManager?) : FragmentStatePagerAdapter(fm!!) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                CustomViewFragment.newInstance()
            }
            1 -> {
                UtilsFragment.newInstance()
            }
            2 -> {
                ToolsFragment.newInstance()
            }
            else -> {
                CustomViewFragment()
            }
        }
    }

    override fun getCount(): Int {
        return TAB_NUM
    }

    companion object {
        private const val TAB_NUM = 3
    }
}