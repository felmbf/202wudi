package com.example.administrator.Adapter;

/**
 * Created by Administrator on 2018/12/21.
 */

import android.content.Intent;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import com.example.administrator.Object.DataOrder;
import com.example.administrator.Object.TotalPrice;
import com.example.administrator.maininterface.Order;
import com.example.administrator.maininterface.R;
import com.example.administrator.maininterface.pinglun;

import java.text.DecimalFormat;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    private List<DataOrder> mOrder;
    private OnItemClickListener mOnItemClickListener;
    static class ViewHolder extends RecyclerView.ViewHolder{
        View orderView;
        ImageView orderImage;
        TextView orderDate;
        TextView ordernei;
        Button button;
        TextView restName;
        TextView tPrice;

        public ViewHolder(View view){
            super(view);
            orderImage = (ImageView) view.findViewById(R.id.order_image);
            orderDate = (TextView) view.findViewById(R.id.order_date);
            ordernei = (TextView) view.findViewById(R.id.order_nei);
            button = (Button) view.findViewById(R.id.button2);
            restName = (TextView) view.findViewById(R.id.Order_rName);
            tPrice = (TextView) view.findViewById(R.id.order_tPrice);
        }
    }
    public OrderAdapter(List<DataOrder> orderList) {
        mOrder = orderList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_order_list, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }
    public interface OnItemClickListener{
        void onClick(int position);
        void onLongClick(int position);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        DataOrder dataorder = mOrder.get(position);
        holder.orderImage.setImageResource(dataorder.getImageId());
        holder.orderDate.setText(dataorder.getDate());
        holder.ordernei.setText(dataorder.getNeirong());
        holder.restName.setText(dataorder.getO_rest());

        DecimalFormat df = new DecimalFormat("#.00");
        holder.tPrice.setText("¥" + df.format(dataorder.getO_totalPrice()));

        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onClick(position);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnItemClickListener.onLongClick(position);
                    return false;
                }
            });
        }
        /*holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/

    }
    @Override
    public int getItemCount() {
        return mOrder.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener ){
        this.mOnItemClickListener = onItemClickListener;
    }



}
