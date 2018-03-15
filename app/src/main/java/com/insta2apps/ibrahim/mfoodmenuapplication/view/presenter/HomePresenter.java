package com.insta2apps.ibrahim.mfoodmenuapplication.view.presenter;

import android.support.annotation.StringRes;

import com.insta2apps.ibrahim.mfoodmenuapplication.R;
import com.insta2apps.ibrahim.mfoodmenuapplication.data.repository.FoodMenuRepository;
import com.insta2apps.ibrahim.mfoodmenuapplication.data.source.Item;
import com.insta2apps.ibrahim.mfoodmenuapplication.domain.LoadFoodMenuUseCase;
import com.insta2apps.ibrahim.mfoodmenuapplication.domain.UseCaseObserver;
import com.insta2apps.ibrahim.mfoodmenuapplication.domain.error.ErrorModel;
import com.insta2apps.ibrahim.mfoodmenuapplication.view.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Ibrahim AbdelGawad on 3/12/2018.
 */

public class HomePresenter extends BasePresenter<HomePresenter.View> {
    private final LoadFoodMenuUseCase loadFoodMenuUseCase;
    private final FoodMenuRepository foodMenuRepository;
    private List<Item> itemList;

    @Inject
    public HomePresenter(LoadFoodMenuUseCase loadFoodMenuUseCase, FoodMenuRepository foodMenuRepository) {
        this.loadFoodMenuUseCase = loadFoodMenuUseCase;
        this.foodMenuRepository = foodMenuRepository;
    }

    @Override
    public void init() {
        super.init();
        if (getView() == null) return;
        getView().showLoading();
        loadFoodMenuUseCase.execute(new UseCaseObserver<List<Item>>() {
            @Override
            public void onSuccess(List<Item> items) {
                if (getView() != null) {
                    getView().showContent();
                    getView().showItems(items);
                    itemList = items;
                }
            }

            @Override
            public void onFailed(ErrorModel errorModel) {
                if (getView() == null) {
                    return;
                }
                int strRes;
                switch (errorModel.getErrorType()) {
                    case ErrorModel.ErrorType.NETWORK_ERROR:
                        if (!getView().isConnected()) {
                            strRes = R.string.error_msg_no_internet;
                        } else {
                            strRes = R.string.error_msg_timeout;
                        }
                        break;
                    default:
                        strRes = R.string.error_msg_unknown;
                        break;
                }
                getView().showError(strRes);
                itemList = null;
            }
        }, null);

    }

    public void onItemClicked(Item item) {
        getView().openItemDetail(item);
    }

    public void setFoodMenuItemList(List<Item> foodMenuItemList) {
        this.itemList = foodMenuItemList;
    }

    public interface View extends BasePresenter.View {

        void showItems(List<Item> itemList);

        void openItemDetail(Item item);

        void showError(@StringRes int errorMessage);

        boolean isConnected();
    }
}
