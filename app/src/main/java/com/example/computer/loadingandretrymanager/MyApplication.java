package com.example.computer.loadingandretrymanager;

import android.app.Application;

import com.lib.loadingandretrymanager.base.LoadingAndRetryManager;


/**
 * Created by zhy on 15/8/27.
 */
public class MyApplication extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();
        LoadingAndRetryManager.BASE_RETRY_LAYOUT_ID = R.layout.base_retry;
        LoadingAndRetryManager.BASE_LOADING_LAYOUT_ID = R.layout.base_loading;
        LoadingAndRetryManager.BASE_EMPTY_LAYOUT_ID = R.layout.base_empty;
    }
}
