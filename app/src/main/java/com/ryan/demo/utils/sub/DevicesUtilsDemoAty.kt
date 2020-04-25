package com.ryan.demo.utils.sub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ryan.androidbaselib.R
import com.ryan.baselib.util.DeviceUtils
import kotlinx.android.synthetic.main.activity_devices_utils_demo_aty.*

class DevicesUtilsDemoAty : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_devices_utils_demo_aty)

        val str = "${DeviceUtils.getTotalMemory().toFloat() / 1024}GB"
        tv_system_ram_value.text = str
    }
}
