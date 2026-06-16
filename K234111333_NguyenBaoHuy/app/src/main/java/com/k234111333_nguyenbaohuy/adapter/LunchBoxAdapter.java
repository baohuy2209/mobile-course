package com.k234111333_nguyenbaohuy.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.k234111333_nguyenbaohuy.model.LunchBox;
import com.k234111333_nguyenbaohuy.test.R;

import java.util.List;

public class LunchBoxAdapter extends BaseAdapter {
    Activity activity; // màn hình hiển thị listview
    int item_layout; // thành phần giao diện
    List<LunchBox> itemLunchBox; // data

    public LunchBoxAdapter(Activity activity, int item_layout, List<LunchBox> itemImages) {
        this.activity = activity;
        this.item_layout = item_layout;
        this.itemLunchBox = itemImages;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public int getItem_layout() {
        return item_layout;
    }

    public void setItem_layout(int item_layout) {
        this.item_layout = item_layout;
    }

    public List<LunchBox> getItemImages() {
        return itemLunchBox;
    }

    public void setItemImages(List<LunchBox> itemImages) {
        this.itemLunchBox = itemImages;
    }

    @Override
    public int getCount() {
        return itemLunchBox.size();
    }

    @Override
    public Object getItem(int i) {
        return itemLunchBox.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LunchBoxAdapter.ViewHolder viewHolder;
        if(view == null){
            viewHolder = new LunchBoxAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(item_layout, null);
            viewHolder.imgProduct = view.findViewById(R.id.imgProduct);
            viewHolder.txtPlaceName = view.findViewById(R.id.txtPlaceName);
            viewHolder.txtDishName = view.findViewById(R.id.txtDishName);
            viewHolder.txtRating = view.findViewById(R.id.txtRating);
            viewHolder.txtRatingCount = view.findViewById(R.id.txtRatingCount);
            view.setTag(viewHolder);
        }else{
            viewHolder = (LunchBoxAdapter.ViewHolder) view.getTag();
        }
        // Ánh xạ dữ liệu
        LunchBox item = itemLunchBox.get(i);
        viewHolder.imgProduct.setImageResource(item.getPhoto());
        viewHolder.txtPlaceName.setText(item.getPlaceName());
        viewHolder.txtDishName.setText(item.getDishName());
        viewHolder.txtRating.setText(item.getRatingValue().toString());
        viewHolder.txtRatingCount.setText(item.getRatingCount());
        return view;
    }
    public static class ViewHolder{
        ImageView imgProduct;
        TextView txtPlaceName;
        TextView txtDishName;

        TextView txtRating;
        TextView txtRatingCount;

    }
}
