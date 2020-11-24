package com.example.servicebestpractice;

/**
 * Created by zl on 19-10-24.
 */
public interface DownloadListener {
    void onProgress (int progress);
    void onSuccess();
    void onFailed();
    void onPaused();
    void onCanceled();
}
