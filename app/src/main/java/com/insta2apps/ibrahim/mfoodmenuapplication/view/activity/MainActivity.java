package com.insta2apps.ibrahim.mfoodmenuapplication.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.insta2apps.ibrahim.mfoodmenuapplication.MFoodMenuApplication;
import com.insta2apps.ibrahim.mfoodmenuapplication.R;
import com.insta2apps.ibrahim.mfoodmenuapplication.data.source.Item;
import com.insta2apps.ibrahim.mfoodmenuapplication.domain.LoadFoodMenuUseCase;
import com.insta2apps.ibrahim.mfoodmenuapplication.domain.UseCaseObserver;
import com.insta2apps.ibrahim.mfoodmenuapplication.domain.error.ErrorModel;
import com.insta2apps.ibrahim.mfoodmenuapplication.view.base.BaseActivity;
import com.insta2apps.ibrahim.mfoodmenuapplication.view.base.OnBackPressedInterface;
import com.insta2apps.ibrahim.mfoodmenuapplication.view.fragment.HomeFragment;
import com.insta2apps.ibrahim.mfoodmenuapplication.view.util.FragmentUtil;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (fragment == null) {
            // Get the fragment from dagger
            FragmentUtil.replaceFragment(
                    getSupportFragmentManager(), R.id.fragment_container, HomeFragment.newInstance(), null);
        }
    }

    @Override
    public void onBackPressed() {
        Fragment currentFragment = FragmentUtil.getCurrentFragment(getSupportFragmentManager(), R.id.fragment_container);
        if (currentFragment != null && currentFragment instanceof OnBackPressedInterface) {
            if (((OnBackPressedInterface) currentFragment).onBackPressed()) {
                // ignore BACK key press
                return;
            }
        }
        super.onBackPressed();
    }

    public void pushFragment(Fragment fragment) {
        FragmentUtil.addFragment(
                getSupportFragmentManager(), R.id.fragment_container, fragment, fragment.getClass().getName());
    }
}