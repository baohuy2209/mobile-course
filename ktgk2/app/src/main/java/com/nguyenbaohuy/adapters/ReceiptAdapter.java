package com.nguyenbaohuy.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nguyenbaohuy.ktgk2.R;
import com.nguyenbaohuy.models.Receipt;

import java.util.ArrayList;

public class ReceiptAdapter extends BaseAdapter {
    Activity activity;
    int layout;
    ArrayList<Receipt> receipts;

    public ReceiptAdapter(Activity activity, int layout, ArrayList<Receipt> receipts) {
        this.activity = activity;
        this.layout = layout;
        this.receipts = receipts;
    }

    @Override
    public int getCount() {
        return receipts.size();
    }

    @Override
    public Object getItem(int i) {
        return receipts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Viewholder holder;
        Receipt item = receipts.get(i);
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder = new Viewholder();
            holder.imvReceiptImage = view.findViewById(R.id.imvReceiptImage);
            holder.txtReceiptName = view.findViewById(R.id.txtReceiptName);
            holder.txtReceiptNat = view.findViewById(R.id.txtReceiptNat);
            holder.txtReceiptLevel = view.findViewById(R.id.txtReceiptLevel);
            view.setTag(holder);
        }else{
            holder = (Viewholder) view.getTag();
        }
//        Bitmap bm = (Bitmap) item.getReceipt_image();
//        holder.imvReceiptImage.setImageBitmap(bm);
        holder.txtReceiptName.setText("Tên món: "+item.getReceipt_name());
        holder.txtReceiptName.setText("Nguyên liệu: "+item.getReceipt_nat());
        holder.txtReceiptLevel.setText("Độ khó"+ item.getLevel());
        return view;
    }

    public static class Viewholder{
        ImageView imvReceiptImage;
        TextView txtReceiptName;
        TextView txtReceiptLevel;
        TextView txtReceiptNat;
    }
}
