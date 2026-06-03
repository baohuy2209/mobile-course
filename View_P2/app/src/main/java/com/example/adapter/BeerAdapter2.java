package com.example.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.models.beer;
import com.example.view_p2.R;

import java.util.List;

public class BeerAdapter2 extends BaseAdapter {
    Activity activity; // màn hình hiển thị listview
    int item_layout; // thành phần giao diện
    List<beer> beers; // data

    public BeerAdapter2(Activity activity, List<beer> beers, int item_layout) {
        this.activity = activity;
        this.beers = beers;
        this.item_layout = item_layout;
    }

    @Override
    public int getCount() {
        return beers.size();
    }

    @Override
    public Object getItem(int i) {
        return beers.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    // đổ dữ liệu lên giao diện
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        // Thành phần giao diện đại diện cho các item trên list
        ViewHolder viewHolder;
        if(view == null){
            // Được thực hiện liên kết thành phần giao diện
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_list_grid_view, null);
            viewHolder.imgBeer = view.findViewById(R.id.imgBeerGV);
            viewHolder.txtBeerGv = view.findViewById(R.id.txtBeerGv);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        // Ánh xạ dữ liệu
        beer b = beers.get(i);
        String txtBeer = b.getBeerName();
        viewHolder.txtBeerGv.setText(txtBeer);
        viewHolder.imgBeer.setImageResource(b.getBeerThumb());
        return view;
    }
    // Class ViewHolder: tổ chức quản lí các view của item_list
    public class ViewHolder{
        ImageView imgBeer;
        TextView txtBeerGv;

    }
}
