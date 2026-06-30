package com.mobile_course.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile_course.models.Product;
import com.mobile_course.sqllite_ex2.MainActivity;
import com.mobile_course.sqllite_ex2.R;

import java.util.ArrayList;

public class ProductAdapter extends BaseAdapter {
    Context context;
    int item_layout;
    ArrayList<Product> products;

    public ProductAdapter(Context context, int item_layout, ArrayList<Product> products) {
        this.context = context;
        this.item_layout = item_layout;
        this.products = products;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int i) {
        return products.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(item_layout, null);
            holder.txtInfo = view.findViewById(R.id.txtInfo);
            holder.imvEdit = view.findViewById(R.id.imvEdit);
            holder.imvDelete = view.findViewById(R.id.imvDelete);

            holder.imvEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Dialog dialog = new Dialog(MainActivity.this);

                }
            });
            holder.imvDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Product p = products.get(i);
        holder.txtInfo.setText(p.getpName() + " - " + Math.round(p.getpPrice()) + "đ");
        return view;
    }
    public class ViewHolder{
        TextView txtInfo;
        ImageView imvEdit, imvDelete;
    }
}
