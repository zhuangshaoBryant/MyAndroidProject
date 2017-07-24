package com.example.zhuang.downloadtest;

/**
 * Created by zhuang on 17-7-23.
 */

public interface DownloadListener {
    void onProgress(int progress);
    void onSuccess();
    void onFailed();
    void onPause();
    void onCancle();
}
