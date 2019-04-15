package com.example.administrator.Adapter;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.Fragment.Review_Fragment;
import com.example.administrator.Object.Order_Right;
import com.example.administrator.Object.ToTalCount;
import com.example.administrator.maininterface.R;

import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * Created by Administrator on 2018/12/13.
 */

public class Order_R_Adapter /*extends ArrayAdapter<Order_Right> */{
    /*private int resourceId;
    private int selectedCount;

    public Order_R_Adapter(Context context, int textViewResourceId, List<Order_Right> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        final Order_Right order_right = getItem(position);
        final View view;
        final ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.foodImage = (ImageView) view.findViewById(R.id.right_image);
            viewHolder.foodName = (TextView) view.findViewById(R.id.right_foodname);
            viewHolder.foodType = (TextView) view.findViewById(R.id.Food_LeftType) ;
            viewHolder.price = (TextView) view.findViewById(R.id.price);
            viewHolder.salesCount = (TextView) view.findViewById(R.id.salesCount);
            viewHolder.selectedCount = (TextView) view.findViewById(R.id.selectedCount);
            viewHolder.jian = (ImageView) view.findViewById(R.id.jian);
            viewHolder.jia = (ImageView) view.findViewById(R.id.jia);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (Order_R_Adapter.ViewHolder) view.getTag();
        }
        viewHolder.foodImage.setImageResource(order_right.getImageId());
        viewHolder.foodName.setText(order_right.getFoodName());
        viewHolder.foodType.setText(order_right.getFoodType());
        viewHolder.price.setText("¥"+order_right.getPrice());
        viewHolder.salesCount.setText("已售  "+order_right.getSalesCount());
        if (order_right.getSelectedCount() > 0) {
            viewHolder.selectedCount.setText(order_right.getSelectedCount()+"");
            viewHolder.selectedCount.setVisibility(view.VISIBLE);
            viewHolder.jian.setVisibility(view.VISIBLE);
        } else  {
            viewHolder.selectedCount.setText(0+"");
            viewHolder.selectedCount.setVisibility(view.INVISIBLE);
            viewHolder.jian.setVisibility(view.INVISIBLE);
        }

        //增加商品
        viewHolder.jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedCount = Integer.parseInt(viewHolder.selectedCount.getText().toString());
                selectedCount++;
                viewHolder.selectedCount.setText(selectedCount+"");
                viewHolder.selectedCount.setVisibility(View.VISIBLE);
                viewHolder.jian.setVisibility(View.VISIBLE);
                order_right.setSelectedCount(selectedCount);

                //选择商品总数量增加
                ToTalCount.setTotalCount(ToTalCount.getTotalCount()+1);

                /*LayoutInflater inflater = LayoutInflater.from(getContext());
                View v1 = inflater.inflate(R.layout.order, parent, false);
                TextView total = (TextView) v1.findViewById(R.id.totalCount);
                if (ToTalCount.getTotalCount() > 0) {
                    String s = String.valueOf(ToTalCount.getTotalCount());
                    Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
                    //viewHolder.total.setText(ToTalCount.getTotalCount()+"");
                    total.setVisibility(View.VISIBLE);
                } else {
                    /*viewHolder.total.setText(0+"");
                    viewHolder.total.setVisibility(View.GONE);*/
                //}
                /*String s = String.valueOf(ToTalCount.getTotalCount()).toString();
                Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();*/
           /* }
        });
        //删除商品
        viewHolder.jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedCount = Integer.parseInt(viewHolder.selectedCount.getText().toString());
                selectedCount--;
                if (selectedCount <= 0) {
                    viewHolder.selectedCount.setText(0+"");
                    viewHolder.selectedCount.setVisibility(View.INVISIBLE);
                    viewHolder.jian.setVisibility(View.INVISIBLE);
                    order_right.setSelectedCount(0);
                } else {
                    viewHolder.selectedCount.setText(selectedCount+"");
                    order_right.setSelectedCount(selectedCount);
                }

                //选择商品总数量减少
                ToTalCount.setTotalCount(ToTalCount.getTotalCount()-1);
            }
        });
        return view;
    }

    class ViewHolder {
        ImageView foodImage;
        ImageView jia;
        ImageView jian;
        TextView foodName;
        TextView foodType;
        TextView salesCount;
        TextView price;
        TextView selectedCount;
    }

    public String getTypeForPosition(int position) {
        return getItem(position).getFoodType();
    }

    public int getPositionForType(String type) {
        for (int i = 0; i < getCount(); i++) {
            if (getItem(i).getFoodType() == type) {
                return i;
            }
        }
        return -1;
    }

    public int getSelectedCountForPosition(int position) {
        return getItem(position).getSelectedCount();
    }

    public void setSelectedCountForPosition(int position, int count) {
        if (count > 0) {
            getItem(position).setSelectedCount(count);
        } else {
            getItem(position).setSelectedCount(0);
        }

    }*/
}
