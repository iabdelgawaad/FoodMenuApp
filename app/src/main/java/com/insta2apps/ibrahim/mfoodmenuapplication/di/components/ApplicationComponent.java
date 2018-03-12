package com.insta2apps.ibrahim.mfoodmenuapplication.di.components;

import android.content.Context;

import com.insta2apps.ibrahim.mfoodmenuapplication.data.source.network.RequestManager;
import com.insta2apps.ibrahim.mfoodmenuapplication.di.modules.ApplicationModule;
import com.insta2apps.ibrahim.mfoodmenuapplication.view.activity.MainActivity;
import com.insta2apps.ibrahim.mfoodmenuapplication.view.fragment.HomeFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Ibrahim AbdelGawad on 3/11/2018.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    // Exposing the application Context
    Context context();
    RequestManager requestManager();
    void inject(MainActivity mainActivity);
    void inject(HomeFragment homeFragment);
}
