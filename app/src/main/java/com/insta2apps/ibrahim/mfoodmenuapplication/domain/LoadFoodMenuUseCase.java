package com.insta2apps.ibrahim.mfoodmenuapplication.domain;

import com.insta2apps.ibrahim.mfoodmenuapplication.data.repository.FoodMenuRepository;
import com.insta2apps.ibrahim.mfoodmenuapplication.data.source.FoodMenuModel;
import com.insta2apps.ibrahim.mfoodmenuapplication.data.source.Item;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.functions.Function;

/**
 * Created by Ibrahim AbdelGawad on 3/11/2018.
 */

public class LoadFoodMenuUseCase extends UseCase<List<Item>> {

    private FoodMenuRepository foodMenuRepository;

    @Inject
    LoadFoodMenuUseCase(@Named("executor_thread") Scheduler executorThread,
                        @Named("ui_thread") Scheduler uiThread, FoodMenuRepository foodMenuRepository) {
        super(executorThread, uiThread);
        this.foodMenuRepository = foodMenuRepository;
    }

    @Override
    protected Observable<List<Item>> createObservableUseCase(Map<String, Object> map) {
        return foodMenuRepository.getLocalFoodMenuItemsCount().toObservable().flatMap(new Function<Long, Observable<List<Item>>>() {
            @Override
            public Observable<List<Item>> apply(Long aLong) throws Exception {
                if (aLong == 0) {
                    return foodMenuRepository.loadFoodMenuListRemotely().map(new Function<FoodMenuModel, List<Item>>() {
                        @Override
                        public List<Item> apply(FoodMenuModel foodMenuModel) throws Exception {
                            List<Item> itemList = foodMenuModel.getItems();
                            foodMenuRepository.saveFoodMenuListLocally(itemList);
                            return itemList;
                        }
                    });
                } else {
                    return foodMenuRepository.loadFoodMenuListLocally();
                }
            }
        });
    }
}
