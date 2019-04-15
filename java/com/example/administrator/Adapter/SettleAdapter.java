package com.example.administrator.Adapter;

import android.app.SearchManager;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.Object.SettleMent;
import com.example.administrator.maininterface.R;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Administrator on 2018/12/20.
 */

public class SettleAdapter extends ArrayAdapter<SettleMent> {
    private int resourceId;

    public SettleAdapter(Context context, int textViewResourceId, List<SettleMent> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Nullable
    @Override
    public SettleMent getItem(int position) {
        return super.getItem(position);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        SettleMent settleMent = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.fImage = (ImageView) view.findViewById(R.id.f_image);
            viewHolder.fName = (TextView) view.findViewById(R.id.f_name);
            viewHolder.fCount = (TextView) view.findViewById(R.id.f_count);
            viewHolder.fPrice = (TextView) view.findViewById(R.id.f_price);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.fImage.setImageResource(settleMent.getImageId());
        viewHolder.fName.setText(settleMent.getfNmae());
        viewHolder.fCount.setText("×" + settleMent.getfCount());
        DecimalFormat df = new DecimalFormat("#.00");
        viewHolder.fPrice.setText("¥" + df.format(settleMent.getfPrice() * settleMent.getfCount()));
        return view;
    }

    class ViewHolder {
        ImageView fImage;
        TextView fName;
        TextView fCount;
        TextView fPrice;
    }
}
