package com.insta2apps.ibrahim.mfoodmenuapplication.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.insta2apps.ibrahim.mfoodmenuapplication.MFoodMenuApplication;
import com.insta2apps.ibrahim.mfoodmenuapplication.R;
import com.insta2apps.ibrahim.mfoodmenuapplication.data.source.Item;
import com.insta2apps.ibrahim.mfoodmenuapplication.view.activity.MainActivity;
import com.insta2apps.ibrahim.mfoodmenuapplication.view.adapter.ItemsAdapter;
import com.insta2apps.ibrahim.mfoodmenuapplication.view.base.BaseFragment;
import com.insta2apps.ibrahim.mfoodmenuapplication.view.presenter.HomePresenter;
import com.insta2apps.ibrahim.mfoodmenuapplication.view.util.NetworkConnectionUtil;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Ibrahim AbdelGawad on 3/12/2018.
 */

public class HomeFragment extends BaseFragment implements HomePresenter.View, ItemsAdapter.OnItemClickListener {

    @BindView(R.id.recycler_items)
    RecyclerView recyclerItems;

    @BindView(R.id.progressBar)
    ProgressBar loadingProgressBar;

    @BindView(R.id.error_layout)
    LinearLayout errorLayout;


    @BindView(R.id.error_btn_retry)
    Button btnRetry;

    @BindView(R.id.error_txt_cause)
    TextView txtError;

    @Inject
    HomePresenter mHomePresenter;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeDagger();
        setPresenter(mHomePresenter);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mHomePresenter.init();
        GridLayoutManager layoutManager = new GridLayoutManager(mContext, 2);
        recyclerItems.setLayoutManager(layoutManager);
    }

    private void initializeDagger() {
        MFoodMenuApplication.getInstance().getDaggerComponent().inject(this);
    }

    @Override
    public void onItemClicked(Item item) {
        mHomePresenter.onItemClicked(item);
    }

    @Override
    public void showItems(List<Item> itemList) {
        ItemsAdapter adapter = new ItemsAdapter(mContext, itemList, this);
        recyclerItems.setAdapter(adapter);
    }

    @Override
    public void openItemDetail(Item item) {
        ((MainActivity)getActivity()).pushFragment(FoodMenuItemDetailFragment.newInstance(item));
    }

    @Override
    public void showError(int errorMessage) {
        errorLayout.setVisibility(View.VISIBLE);
        recyclerItems.setVisibility(View.GONE);
        loadingProgressBar.setVisibility(View.GONE);
        txtError.setText(getString(errorMessage));
    }

    @OnClick(R.id.error_btn_retry)
    void onRetryClicked() {
        mHomePresenter.init();
    }

    @Override
    public boolean isConnected() {
        return NetworkConnectionUtil.isNetworkAvailable(mContext);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_home;
    }

    @Override
    public void showLoading() {
        recyclerItems.setVisibility(View.GONE);
        errorLayout.setVisibility(View.GONE);
        loadingProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showContent() {
        recyclerItems.setVisibility(View.VISIBLE);
        loadingProgressBar.setVisibility(View.GONE);
        errorLayout.setVisibility(View.GONE);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

}
