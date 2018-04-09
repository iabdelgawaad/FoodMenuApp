package com.insta2apps.ibrahim.mfoodmenuapplication.view.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.insta2apps.ibrahim.mfoodmenuapplication.R;
import com.insta2apps.ibrahim.mfoodmenuapplication.data.source.Item;
import com.insta2apps.ibrahim.minddownloaderlib.MindDownLoader;
import com.insta2apps.ibrahim.minddownloaderlib.interfaces.Target;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ibrahim AbdelGawad on 3/12/2018.
 */

public class ItemsAdapter extends BaseAdapter<Item> {

    private OnItemClickListener mListener;

    public ItemsAdapter(Context objView, List<Item> itemList, OnItemClickListener listener) {
        super(objView);
        setDataList(itemList);
        mListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClicked(Item item);
    }

    @Override
    protected BaseViewHolder getHolderInstance(View view) {
        return new ItemViewHolder(view);
    }

    @Override
    protected int getHolderResourceId() {
        return R.layout.item_home_menu;
    }

    class ItemViewHolder extends BaseViewHolder<Item> {
        @BindView(R.id.imv_item)
        ImageView imvItem;
        @BindView(R.id.txt_item_name)
        TextView txtItemName;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null) {
                        mListener.onItemClicked(mBusinessObject);
                    }
                }
            });
        }

        @Override
        public void bindViewData(Item foodMenu, int position) {
            if (!TextUtils.isEmpty(foodMenu.getPhotoUrl())) {
                MindDownLoader.Request.Builder load = MindDownLoader.Request.getBitmap(mContext).load(foodMenu.getPhotoUrl());
                load.into(new Target<Bitmap>() {
                    @Override
                    public void success(Bitmap value) {
                        imvItem.setImageBitmap(value);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        imvItem.setImageResource(R.drawable.item_default);
                        Toast.makeText(mContext, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            } else {
                imvItem.setImageResource(R.drawable.item_default);
            }

            txtItemName.setText(foodMenu.getName());
        }
    }

}
