package com.nguyenbaohuy.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nguyenbaohuy.models.Dish;
import com.nguyenbaohuy.self_learning_2.R;

import java.util.ArrayList;

public class DishAdapter extends BaseAdapter {
    Activity activity;
    int layout;
    ArrayList<Dish> dishes;

    public DishAdapter(Activity activity, int layout, ArrayList<Dish> dishes) {
        this.activity = activity;
        this.layout = layout;
        this.dishes = dishes;
    }

    @Override
    public int getCount() {
        return dishes.size();
    }

    @Override
    public Object getItem(int i) {
        return dishes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Viewholder holder;
        Dish item = dishes.get(i);
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder = new Viewholder();

            holder.imvItem = view.findViewById(R.id.imvItem);
            holder.txtDishName = view.findViewById(R.id.txtDisName);
            holder.txtPlaceName = view.findViewById(R.id.txtPlaceName);
            holder.txtRating = view.findViewById(R.id.txtRating);
            holder.txtRatingCount = view.findViewById(R.id.txtRatingCount);
            view.setTag(holder);
        }else{
            holder = (Viewholder) view.getTag();
        }
        holder.txtPlaceName.setText(item.getPlaceName());
        holder.txtDishName.setText(item.getDishName());
        holder.txtRating.setText(String.valueOf(item.getRatingValue()));
        holder.txtRatingCount.setText("("+item.getRatingCount()+")");

        // ✅ Sửa 1: getPackageName() → activity.getPackageName()
        int resId = activity.getResources().getIdentifier(
                item.getPhoto(),
                "drawable",
                activity.getPackageName() // ✅ thêm activity. vào đây
        );

        // ✅ Sửa 2: truyền resId thay vì item.getPhoto()
        if (resId != 0) {
            holder.imvItem.setImageResource(resId);
        } else {
            holder.imvItem.setImageResource(R.drawable.ic_launcher_background);
        }

        return view;
    }

    static class Viewholder{
        ImageView imvItem;
        TextView txtPlaceName, txtDishName, txtRating, txtRatingCount;
    }
}
