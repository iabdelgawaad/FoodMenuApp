package com.insta2apps.ibrahim.mfoodmenuapplication.domain;

import com.insta2apps.ibrahim.mfoodmenuapplication.data.repository.FoodMenuRepository;
import com.insta2apps.ibrahim.mfoodmenuapplication.domain.error.ErrorModel;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

/**
 * Created by Ibrahim AbdelGawad on 3/11/2018.
 */

public class LoadFoodMenuUseCase extends UseCase<List<ErrorModel>> {

    private FoodMenuRepository foodMenuRepository;

    @Inject
    LoadFoodMenuUseCase(@Named("executor_thread") Scheduler executorThread,
                        @Named("ui_thread") Scheduler uiThread, FoodMenuRepository foodMenuRepository) {
        super(executorThread, uiThread);
        this.foodMenuRepository = foodMenuRepository;
    }

    @Override
    protected Observable<List<ErrorModel>> createObservableUseCase(Map<String, Object> map) {
        return null;
    }
}
