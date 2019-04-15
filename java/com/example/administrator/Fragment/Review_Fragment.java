package com.example.administrator.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.maininterface.R;

/**
 * Created by Administrator on 2018/12/13.
 */

public class Review_Fragment extends Fragment {

    private TextView review;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.review, null);

        review = (TextView) view.findViewById(R.id.review);

        review.setText("这里暂时没有东西哦");

        return view;
    }
}
