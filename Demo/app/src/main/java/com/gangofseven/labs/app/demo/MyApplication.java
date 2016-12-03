package com.gangofseven.labs.app.demo;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by Administrador on 03/12/2016.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //Fresco init
        Fresco.initialize(this);
    }
}
