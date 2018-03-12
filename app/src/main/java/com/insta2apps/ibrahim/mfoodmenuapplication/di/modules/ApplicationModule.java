package com.insta2apps.ibrahim.mfoodmenuapplication.di.modules;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.insta2apps.ibrahim.mfoodmenuapplication.data.source.database.room.AppDatabase;
import com.insta2apps.ibrahim.mfoodmenuapplication.data.source.database.room.FoodMenuItemDao;
import com.insta2apps.ibrahim.mfoodmenuapplication.data.source.network.RequestManager;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by Ibrahim AbdelGawad on 3/11/2018.
 */
@Module
public class ApplicationModule {
    private Application mFoodMenuApplication;
    private AppDatabase appDatabase;

    public ApplicationModule(Application mFoodMenuApplication) {
        this.mFoodMenuApplication = mFoodMenuApplication;
        appDatabase = Room.databaseBuilder(mFoodMenuApplication, AppDatabase.class, "mFood-db").allowMainThreadQueries().build();
    }

    @Provides
    @Singleton
    AppDatabase provideRoomDB() {
        return appDatabase;
    }

    @Provides
    @Singleton
    FoodMenuItemDao provideFoodMenuItemDao(AppDatabase appDatabase) {
        return appDatabase.getFoodMenuItemDao();
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

}
