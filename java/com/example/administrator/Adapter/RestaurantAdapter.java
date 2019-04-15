package com.example.administrator.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.maininterface.R;
import com.example.administrator.Object.Restaurant;
import java.util.List;

/**
 * Created by Administrator on 2018/12/11.
 */

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ViewHolder> {
    private List<Restaurant> mRestaurant;
    private OnItemClickListener mOnItemClickListener;

    static class ViewHolder extends RecyclerView.ViewHolder {
        View restaurantView;
        ImageView restImage;
        TextView restName;
        TextView salesCount;

        public ViewHolder(View view) {
            super(view);
            restaurantView = view;
            restImage = (ImageView) view.findViewById(R.id.rest_icon);
            restName = (TextView) view.findViewById(R.id.rest_name);
            salesCount = (TextView) view.findViewById(R.id.rest_salesCount);
        }
    }

    public interface OnItemClickListener{
        void onClick(int position);
        void onLongClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener ){
        this.mOnItemClickListener = onItemClickListener;
    }

    public RestaurantAdapter(List<Restaurant> restaurantList) {
        mRestaurant = restaurantList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.restaurant_list, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.restaurantView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Restaurant restaurant = mRestaurant.get(position);
                Toast.makeText(v.getContext(), "sss", Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Restaurant restaurant = mRestaurant.get(position);

        setImage(holder, position, restaurant);

        holder.restName.setText(restaurant.getName());
        holder.salesCount.setText("已售 " + restaurant.getSalesCount());

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
    }

    private void setImage(ViewHolder holder, int position, Restaurant restaurant) {
        switch (position) {
            case 0:
                holder.restImage.setImageResource(R.drawable.r_1);
                restaurant.setImageId(R.drawable.r_1);
                break;
            case 1:
                holder.restImage.setImageResource(R.drawable.r_2);
                restaurant.setImageId(R.drawable.r_2);
                break;
            case 2:
                holder.restImage.setImageResource(R.drawable.r_4);
                restaurant.setImageId(R.drawable.r_4);
                break;
            case 3:
                holder.restImage.setImageResource(R.drawable.r_12);
                restaurant.setImageId(R.drawable.r_12);
                break;
            case 4:
                holder.restImage.setImageResource(R.drawable.r_3);
                restaurant.setImageId(R.drawable.r_3);
                break;
            case 5:
                holder.restImage.setImageResource(R.drawable.r_11);
                restaurant.setImageId(R.drawable.r_11);
                break;
            case 6:
                holder.restImage.setImageResource(R.drawable.r_10);
                restaurant.setImageId(R.drawable.r_10);
                break;
            case 7:
                holder.restImage.setImageResource(R.drawable.r_5);
                restaurant.setImageId(R.drawable.r_5);
                break;
            case 8:
                holder.restImage.setImageResource(R.drawable.r_6);
                restaurant.setImageId(R.drawable.r_6);
                break;
            case 9:
                holder.restImage.setImageResource(R.drawable.r_7);
                restaurant.setImageId(R.drawable.r_7);
                break;
            case 10:
                holder.restImage.setImageResource(R.drawable.r_8);
                restaurant.setImageId(R.drawable.r_8);
                break;
            case 11:
                holder.restImage.setImageResource(R.drawable.r_9);
                restaurant.setImageId(R.drawable.r_9);
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mRestaurant.size();
    }
}
