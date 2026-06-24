package com.mobile_course.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mobile_course.asset_shared_preferences.R;

import java.util.List;

public class FontsAdapter extends BaseAdapter {
    Activity activity; // màn hình hiển thị listview
    int item_layout; // thành phần giao diện
    List<String> fontItems; // data

    public FontsAdapter(Activity activity, int item_layout, List<String> fontItems) {
        this.activity = activity;
        this.item_layout = item_layout;
        this.fontItems = fontItems;
    }

    @Override
    public int getCount() {
        return fontItems.size();
    }

    @Override
    public Object getItem(int i) {
        return fontItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(item_layout, viewGroup, false);
            holder = new ViewHolder();
            holder.txtFonts = view.findViewById(R.id.txtFonts);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        String item = fontItems.get(i);
        holder.txtFonts.setText(item);

        try {
            // Nạp font từ assets và áp dụng style vào chính item đó
            Typeface tf = Typeface.createFromAsset(activity.getAssets(), "fonts/" + item);
            holder.txtFonts.setTypeface(tf);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;
    }

    public static class ViewHolder {
        TextView txtFonts;
    }
}
