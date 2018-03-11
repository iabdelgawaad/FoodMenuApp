package com.insta2apps.ibrahim.mfoodmenuapplication.di.modules;

import android.app.Application;
import android.content.Context;

import com.insta2apps.ibrahim.mfoodmenuapplication.data.source.database.RealmManager;
import com.insta2apps.ibrahim.mfoodmenuapplication.data.source.network.RequestManager;
import com.insta2apps.ibrahim.mfoodmenuapplication.domain.LoadFoodMenuUseCase;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.realm.Realm;

/**
 * Created by Ibrahim AbdelGawad on 3/11/2018.
 */
@Module
public class ApplicationModule {
    private Application mFoodMenuApplication;

    public ApplicationModule(Application mFoodMenuApplication) {
        this.mFoodMenuApplication = mFoodMenuApplication;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return mFoodMenuApplication;
    }

    @Provides
    @Named("executor_thread")
    io.reactivex.Scheduler provideExecutorThread() {
        return io.reactivex.schedulers.Schedulers.io();
    }

    @Provides
    @Named("ui_thread")
    io.reactivex.Scheduler provideUiThread() {
        return AndroidSchedulers.mainThread();
    }

    @Provides
    @Singleton
    RequestManager provideRequestManager() {
        return RequestManager.getInstance();
    }

    @Provides
    Realm provideRealm() {
        return Realm.getDefaultInstance();
    }

    @Provides
    @Singleton
    RealmManager provideRealmManager() {
        return RealmManager.getRealmManagerInstance();
    }
}
