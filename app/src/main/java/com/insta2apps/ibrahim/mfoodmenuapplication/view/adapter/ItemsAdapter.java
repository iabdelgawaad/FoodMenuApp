package com.insta2apps.ibrahim.mfoodmenuapplication.view.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.insta2apps.ibrahim.mfoodmenuapplication.R;
import com.insta2apps.ibrahim.mfoodmenuapplication.data.source.Item;
import com.squareup.picasso.Picasso;

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
                Picasso.with(mContext).load(foodMenu.getPhotoUrl()).placeholder(R.drawable.item_default).into(imvItem);
            } else {
                Picasso.with(mContext).load(R.drawable.item_default).into(imvItem);
            }
            txtItemName.setText(foodMenu.getName());
        }
    }

}
