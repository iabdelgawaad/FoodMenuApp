package com.insta2apps.ibrahim.mfoodmenuapplication.view.presenter;

import com.insta2apps.ibrahim.mfoodmenuapplication.data.repository.FoodMenuRepository;
import com.insta2apps.ibrahim.mfoodmenuapplication.data.source.Item;
import com.insta2apps.ibrahim.mfoodmenuapplication.view.base.BasePresenter;

import javax.inject.Inject;

/**
 * Created by Ibrahim AbdelGawad on 3/12/2018.
 */

public class FoodMenuItemDetailPresenter extends BasePresenter<FoodMenuItemDetailPresenter.View> {
    private final FoodMenuRepository menuRepository;
    private Item mItem;

    @Inject
    public FoodMenuItemDetailPresenter(FoodMenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public void init(Item item) {
        mItem = item;
        getView().showItem(item);
    }

    public interface View extends BasePresenter.View {

        void showItem(Item item);
    }
}
