package com.example.administrator.Fragment;

import android.content.Intent;
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

public class RestInfo_Fragment extends Fragment {

    private TextView Info;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rest_info, null);

        Info = (TextView) view.findViewById(R.id.info);

        Info.setText("这里暂时没有东西哦");

        return view;
    }
}
