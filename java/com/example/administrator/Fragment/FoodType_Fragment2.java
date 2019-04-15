package com.example.administrator.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.administrator.Adapter.RestaurantAdapter;
import com.example.administrator.maininterface.R;
import com.example.administrator.Object.Restaurant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/12/11.
 */

public class FoodType_Fragment2 extends Fragment {
    private List<Restaurant> restaurantList = new ArrayList<>();
    private int flag = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.food_type2, null);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //餐家列表
        if (flag == 0) {
            initRestaurants();    //初始化商家数据
        }
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_rest2) ;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        RestaurantAdapter restaurantAdapter = new RestaurantAdapter(restaurantList);
        recyclerView.setAdapter(restaurantAdapter);

        //排序类型
        Spinner spinner = (Spinner) view.findViewById(R.id.Spinner_ZH2);
        String[] mItems = getResources().getStringArray(R.array.sort_order);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, mItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //TextView tv = new view;
            }
        });

    }
    private void initRestaurants() {
        /*Restaurant demo1 = new Restaurant("商家6", R.drawable.cast_album_art_placeholder);
        restaurantList.add(demo1);
        Restaurant demo2 = new Restaurant("商家2", R.drawable.cast_album_art_placeholder);
        restaurantList.add(demo2);
        Restaurant demo3 = new Restaurant("商家3", R.drawable.cast_album_art_placeholder);
        restaurantList.add(demo3);
        Restaurant demo4 = new Restaurant("商家4", R.drawable.cast_album_art_placeholder);
        restaurantList.add(demo4);
        Restaurant demo5 = new Restaurant("商家5", R.drawable.cast_album_art_placeholder);
        restaurantList.add(demo5);
        Restaurant demo6 = new Restaurant("商家6", R.drawable.cast_album_art_placeholder);
        restaurantList.add(demo6);
        Restaurant demo7 = new Restaurant("商家7", R.drawable.cast_album_art_placeholder);
        restaurantList.add(demo7);
        Restaurant demo8 = new Restaurant("商家8", R.drawable.cast_album_art_placeholder);
        restaurantList.add(demo8);
        Restaurant demo9 = new Restaurant("商家9", R.drawable.cast_album_art_placeholder);
        restaurantList.add(demo9);*/

        flag = 1;
    }
}
