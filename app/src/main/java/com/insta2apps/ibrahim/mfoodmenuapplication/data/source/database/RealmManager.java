package com.insta2apps.ibrahim.mfoodmenuapplication.data.source.database;

import com.insta2apps.ibrahim.mfoodmenuapplication.MFoodMenuApplication;
import com.insta2apps.ibrahim.mfoodmenuapplication.data.source.Item;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Ibrahim AbdelGawad on 3/11/2018.
 */

public class RealmManager {
    private static RealmManager instance;
    @Inject
    Realm mRealm;

    RealmManager() {
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(MFoodMenuApplication.getInstance())
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    public static synchronized RealmManager getRealmManagerInstance() {
        if (instance == null) {
            return new RealmManager();
        } else {
            return instance;
        }
    }

    public void closeRealm() {
        mRealm.close();
    }

    public void saveAllFoodMenuItems(List<Item> carList) {
    }
}
