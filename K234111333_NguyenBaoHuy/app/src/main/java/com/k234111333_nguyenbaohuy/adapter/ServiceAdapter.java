package com.k234111333_nguyenbaohuy.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.k234111333_nguyenbaohuy.test.R;

import java.util.List;

public class ServiceAdapter extends BaseAdapter {
    Activity activity; // màn hình hiển thị listview
    int item_layout; // thành phần giao diện
    List<Integer> itemImages; // data

    public ServiceAdapter(Activity activity, int item_layout, List<Integer> itemImages) {
        this.activity = activity;
        this.item_layout = item_layout;
        this.itemImages = itemImages;
    }

    @Override
    public int getCount() {
        return itemImages.size();
    }

    @Override
    public Object getItem(int i) {
        return itemImages.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(item_layout, null);
            viewHolder.imageService = view.findViewById(R.id.imageService);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        // Ánh xạ dữ liệu
        viewHolder.imageService.setImageResource(itemImages.get(i));
        return view;
    }
    public static class ViewHolder{
        ImageView imageService;

    }
}
