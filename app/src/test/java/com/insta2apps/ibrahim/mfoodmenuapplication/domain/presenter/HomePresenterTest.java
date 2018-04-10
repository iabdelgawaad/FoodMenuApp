package com.insta2apps.ibrahim.mfoodmenuapplication.domain.presenter;

import com.insta2apps.ibrahim.mfoodmenuapplication.data.repository.FoodMenuRepository;
import com.insta2apps.ibrahim.mfoodmenuapplication.data.source.Item;
import com.insta2apps.ibrahim.mfoodmenuapplication.domain.LoadFoodMenuUseCase;
import com.insta2apps.ibrahim.mfoodmenuapplication.view.presenter.HomePresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

/**
 * Created by Ibrahim AbdelGawad on 4/10/2018.
 */

@RunWith(MockitoJUnitRunner.class)
public class HomePresenterTest {

    @Mock
    private HomePresenter presenter;

    @Mock
    private HomePresenter.View view;
    @Mock
    FoodMenuRepository foodMenuRepository;
    @Mock
    LoadFoodMenuUseCase loadFoodMenuUseCase;


    @Before
    public void setup() {
        presenter = new HomePresenter(loadFoodMenuUseCase, foodMenuRepository);
        presenter.attachView(view);
    }

    @Test
    public void onItemClick() {
        Item item = new Item();
        presenter.onItemClicked(item);
        verify(view).openItemDetail(item);
    }
}
