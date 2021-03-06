package com.insta2apps.ibrahim.mfoodmenuapplication.data.repository;

import android.support.annotation.NonNull;

import com.insta2apps.ibrahim.mfoodmenuapplication.data.source.FoodMenuModel;
import com.insta2apps.ibrahim.mfoodmenuapplication.data.source.Item;
import com.insta2apps.ibrahim.mfoodmenuapplication.data.source.database.room.FoodMenuItemDao;
import com.insta2apps.ibrahim.mfoodmenuapplication.data.source.network.API;
import com.insta2apps.ibrahim.mfoodmenuapplication.data.source.network.RequestManager;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Created by Ibrahim AbdelGawad on 3/11/2018.
 */

public class FoodMenuRepository {

    private final RequestManager requestManager;
    private final FoodMenuItemDao foodMenuItemDao;

    @Inject
    public FoodMenuRepository(@NonNull RequestManager requestManager, FoodMenuItemDao foodMenuItemDao) {
        this.requestManager = requestManager;
        this.foodMenuItemDao = foodMenuItemDao;
    }

    public Observable<FoodMenuModel> loadFoodMenuListRemotely() {
        return requestManager.startRequest(API.class).getFoodMenuList();
    }

    public Observable<List<Item>> loadFoodMenuListLocally() {
        return Observable.create(new ObservableOnSubscribe<List<Item>>() {
            @Override
            public void subscribe(ObservableEmitter<List<Item>> emitter) {
                List<Item> itemList = null;
                itemList = foodMenuItemDao.getAll();
                emitter.onNext(itemList);
                emitter.onComplete();
            }
        });
    }

    public Flowable<Long> getLocalFoodMenuItemsCount() {
        return foodMenuItemDao.countFoodMenuItems();
    }

    public void saveFoodMenuListLocally(List<Item> items) {
        for (Item item : items) {
            foodMenuItemDao.insertAll(item);
        }
    }
}
