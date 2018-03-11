package com.insta2apps.ibrahim.mfoodmenuapplication.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.insta2apps.ibrahim.mfoodmenuapplication.MFoodMenuApplication;
import com.insta2apps.ibrahim.mfoodmenuapplication.R;
import com.insta2apps.ibrahim.mfoodmenuapplication.data.source.Item;
import com.insta2apps.ibrahim.mfoodmenuapplication.domain.LoadFoodMenuUseCase;
import com.insta2apps.ibrahim.mfoodmenuapplication.domain.UseCaseObserver;
import com.insta2apps.ibrahim.mfoodmenuapplication.domain.error.ErrorModel;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    LoadFoodMenuUseCase loadFoodMenuUseCase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeDagger();

        loadFoodMenuUseCase.execute(new UseCaseObserver<List<Item>>() {
            @Override
            public void onSuccess(List<Item> items) {
                Log.d("success" , items.size() +"");
            }

            @Override
            public void onFailed(ErrorModel errorModel) {
                Log.d("failed" , errorModel.getErrorDes());
            }
        }, null);
    }

    private void initializeDagger() {
        MFoodMenuApplication.getInstance().getDaggerComponent().inject(this);
    }
}
