package com.example.administrator.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.administrator.maininterface.Delicious_food;
import com.example.administrator.maininterface.MainInterface;
import com.example.administrator.maininterface.R;

/**
 * Created by Administrator on 2018/12/12.
 */

public class MainType_Fragment1 extends Fragment {
    private LinearLayout Meishi;
    private Activity activity;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_type1, null);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Meishi = (LinearLayout) view.findViewById(R.id.meishi);
        //进入美食界面
        Meishi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Delicious_food.class);
                startActivity(intent);
                activity.overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right);
            }
        });
    }

    @Override
    public Context getContext() {
        activity = getActivity();

        if (activity == null) {
            return MainInterface.getInstance();
        }
        return activity;
    }
}
