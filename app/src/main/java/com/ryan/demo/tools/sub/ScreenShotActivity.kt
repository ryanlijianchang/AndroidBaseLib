package com.ryan.demo.tools.sub

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.ryan.androidbaselib.R
import com.ryan.baselib.manager.OnScreenShotTakenListener
import com.ryan.baselib.manager.ScreenShotListenManager
import com.ryan.baselib.util.ThreadUtils
import com.ryan.demo.tools.ToolsFragment
import kotlinx.android.synthetic.main.activity_screen_shot.*
import java.io.File
import java.lang.StringBuilder

class ScreenShotActivity : AppCompatActivity() {
    private var mScreenShotListenManager: ScreenShotListenManager? = null
    private var str: StringBuilder? = StringBuilder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screen_shot)


        btn_start_listen.setOnClickListener {
            str?.clear()
            str?.append("开始监听...\n")
            tv_content.text = str
            iv_screen.setImageResource(0)
            if (mScreenShotListenManager == null) {
                mScreenShotListenManager = ScreenShotListenManager.newInstance(this)
            }
            mScreenShotListenManager?.startListen { path ->
                ThreadUtils.runOnUiThread {
                    str?.append("捕获到截图，截图文件路径是：\n$path\n")
                    tv_content.text = str
                    Log.i(ToolsFragment.TAG, "onScreenshotTaken path=$path")
                    val uri = Uri.fromFile(File(path))
                    iv_screen.setImageURI(uri)
                }
            }
        }
        btn_cancel_listen.setOnClickListener {
            mScreenShotListenManager?.stopListen()
            str?.append("结束监听...\n")
            tv_content.text = str
        }
    }
}
