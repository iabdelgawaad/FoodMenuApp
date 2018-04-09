package com.insta2apps.ibrahim.mfoodmenuapplication.view.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.insta2apps.ibrahim.mfoodmenuapplication.MFoodMenuApplication;
import com.insta2apps.ibrahim.mfoodmenuapplication.R;
import com.insta2apps.ibrahim.mfoodmenuapplication.data.source.Item;
import com.insta2apps.ibrahim.mfoodmenuapplication.view.base.BaseFragment;
import com.insta2apps.ibrahim.mfoodmenuapplication.view.presenter.FoodMenuItemDetailPresenter;
import com.insta2apps.ibrahim.minddownloaderlib.MindDownLoader;
import com.insta2apps.ibrahim.minddownloaderlib.interfaces.Target;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Ibrahim AbdelGawad on 3/12/2018.
 */

public class FoodMenuItemDetailFragment extends BaseFragment implements FoodMenuItemDetailPresenter.View {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.item_details_title)
    TextView titleTextView;
    @BindView(R.id.item_details_description)
    TextView descriptionTextView;
    @BindView(R.id.item_details_image)
    ImageView itemImageView;

    @Inject
    FoodMenuItemDetailPresenter mFoodMenuItemDetailPresenter;

    private static final String ITEM_ARG = "ITEM_ARG";

    public static FoodMenuItemDetailFragment newInstance(Item item) {
        FoodMenuItemDetailFragment fragment = new FoodMenuItemDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ITEM_ARG, item);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeDagger();
        setPresenter(mFoodMenuItemDetailPresenter);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Item item = (Item) getArguments().getSerializable(ITEM_ARG);
        mFoodMenuItemDetailPresenter.init(item);
        toolbar.setNavigationIcon(R.drawable.back_arrow);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
    }

    private void initializeDagger() {
        MFoodMenuApplication.getInstance().getDaggerComponent().inject(this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_item_deatil;
    }

    @Override
    public void showItem(Item item) {
        titleTextView.setText(item.getName());
        if (!TextUtils.isEmpty(item.getPhotoUrl())) {
            MindDownLoader.Request.getBitmap(mContext).load(item.getPhotoUrl())
                    .into(new Target<Bitmap>() {
                        @Override
                        public void success(Bitmap values) {
                            itemImageView.setImageBitmap(values);
                        }

                        @Override
                        public void onError(Throwable throwable) {
                            itemImageView.setImageResource(R.drawable.item_default);
                        }
                    });

        } else {
            itemImageView.setImageResource(R.drawable.item_default);
        }

        descriptionTextView.setText(item.getDescription());
        descriptionTextView.setMovementMethod(new ScrollingMovementMethod());
    }
}
