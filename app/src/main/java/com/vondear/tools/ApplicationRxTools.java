package com.vondear.tools;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.vondear.rxtools.RxTool;
import com.vondear.tools.tools.LogT;

/**
 * Created by vonde on 2016/12/23.
 */

public class ApplicationRxTools extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        RxTool.init(this);
        LogT.init(true, Log.VERBOSE);//不输出到文件
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
