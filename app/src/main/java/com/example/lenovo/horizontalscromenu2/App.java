package com.example.lenovo.horizontalscromenu2;

import android.app.Application;

import org.xutils.x;

/**
 * Created by lenovo on 2017/8/31.
 */

public class App extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
    }
}
