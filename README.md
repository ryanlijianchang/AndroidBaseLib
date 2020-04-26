## 版本信息 ##

### V0.0.9 ###

- 1、支持kotlin
- 2、增加截图工具类 ScreenShotListenManager
```
val mScreenShotListenManager = ScreenShotListenManager.newInstance(this)
mScreenShotListenManager?.startListen { path ->
    Log.i(TAG, "onScreenshotTaken path=$path")
}

// when unuse
mScreenShotListenManager?.stopListen()
```




### V0.0.8 ###

- 1、适配Androidx
- 2、获取系统RAM DeviceUtils.getTotalMemory