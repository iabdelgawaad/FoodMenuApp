package com.insta2apps.ibrahim.mfoodmenuapplication;

import android.app.Application;

/**
 * Created by Ibrahim AbdelGawad on 3/11/2018.
 */

public class MFoodMenuApplication extends Application {
    private static MFoodMenuApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static MFoodMenuApplication getInstance() {
        return instance;
    }
}
