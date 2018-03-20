package com.abhibus.mvvm.arch;

import android.app.Application;

/**
 * Created by yakub on 15/03/18.
 */

public class ABApplication extends Application {


    private static ABApplication instance;

    public static ABApplication getGlobalContext() {
        return instance;
    }

    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();



    }
}
