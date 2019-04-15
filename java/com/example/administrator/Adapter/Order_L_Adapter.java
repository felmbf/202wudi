package com.example.administrator.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.administrator.Object.Order_Left;
import com.example.administrator.maininterface.R;

import java.util.List;

/**
 * Created by Administrator on 2018/12/13.
 */

public class Order_L_Adapter extends ArrayAdapter<Order_Left> {

    private int resourceId;
    private String TypeName;

    public Order_L_Adapter(Context context, int textViewResourceId, List<Order_Left> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }


    @Nullable
    @Override
    public Order_Left getItem(int position) {
        return super.getItem(position);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Order_Left order_left = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.type = (TextView) view.findViewById(R.id.left_type);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.type.setText(order_left.getType());
        TypeName = order_left.getType();
        return view;
    }

    class ViewHolder {
        TextView type;
    }

    public String getTypeName() {
        return TypeName;
    }

    public int getPositionForTypeName(String typeName) {
        for (int i = 0; i < getCount(); i++) {
            if (getItem(i).getType().equals(typeName)) {
                return i;
            }
        }
        return  -1;
    }
}
