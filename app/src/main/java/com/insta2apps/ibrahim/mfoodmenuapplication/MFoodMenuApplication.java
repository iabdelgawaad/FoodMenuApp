package com.insta2apps.ibrahim.mfoodmenuapplication;

import android.app.Application;

import com.insta2apps.ibrahim.mfoodmenuapplication.di.components.ApplicationComponent;
import com.insta2apps.ibrahim.mfoodmenuapplication.di.components.DaggerApplicationComponent;
import com.insta2apps.ibrahim.mfoodmenuapplication.di.modules.ApplicationModule;

/**
 * Created by Ibrahim AbdelGawad on 3/11/2018.
 */

public class MFoodMenuApplication extends Application {
    private static MFoodMenuApplication instance;
    protected ApplicationComponent daggerComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initDagger();
    }

    public static MFoodMenuApplication getInstance() {
        return instance;
    }

    protected void initDagger() {
        daggerComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
    }
    public ApplicationComponent getDaggerComponent() {
        return daggerComponent;
    }

}
