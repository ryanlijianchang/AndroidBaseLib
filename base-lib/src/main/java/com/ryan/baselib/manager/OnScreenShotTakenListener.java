package com.ryan.baselib.manager;

public interface OnScreenShotTakenListener {
    /**
     * 监听到截图
     * @param path 图片路径
     */
    void onScreenshotTaken(String path);
}