package com.example.administrator.maininterface;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.example.administrator.Adapter.Type_PagerAdapter;
import com.example.administrator.Fragment.FoodType_Fragment1;
import com.example.administrator.Fragment.FoodType_Fragment2;
import com.example.administrator.Fragment.FoodType_Fragment3;
import com.example.administrator.Fragment.FoodType_Fragment4;
import com.example.administrator.Fragment.FoodType_Fragment5;
import com.example.administrator.Fragment.FoodType_Fragment6;
import com.example.administrator.Object.Data;
import com.example.administrator.Object.FoodType;

import java.util.ArrayList;
import java.util.List;

public class Delicious_food extends AppCompatActivity {

    private List<FoodType> foodTypeList = new ArrayList<>();
    //private ToggleButton _Zh;

    String[] mTitles = {"全部", "简餐便当", "地方菜系", "面食粥点", "汉堡披萨", "香锅冒菜"};
    private PagerSlidingTabStrip tabStrip;
    private ViewPager pager;
    private List<Fragment> list = new ArrayList<>();
    //private String web = "http://192.168.43.13:8080/ELM/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delicious_food);

        PagerSetAdapter();
        Data.setFlag(0);

        //返回首页
        TextView _Back = (TextView) findViewById(R.id._back);
        _Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Delicious_food.this, MainInterface.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });

        //搜索
        ImageView _Query = (ImageView) findViewById(R.id._query);
        _Query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Data.setFlag(2);
                Intent intent = new Intent(Delicious_food.this, QueryActivity.class);
                startActivity(intent);
                //finish();
            }
        });
    }

    private void PagerSetAdapter() {
        FoodType_Fragment1 foodType_fragment1 = new FoodType_Fragment1();
        FoodType_Fragment2 foodType_fragment2 = new FoodType_Fragment2();
        FoodType_Fragment3 foodType_fragment3 = new FoodType_Fragment3();
        FoodType_Fragment4 foodType_fragment4 = new FoodType_Fragment4();
        FoodType_Fragment5 foodType_fragment5 = new FoodType_Fragment5();
        FoodType_Fragment6 foodType_fragment6 = new FoodType_Fragment6();
        list.add(foodType_fragment1);
        list.add(foodType_fragment2);
        list.add(foodType_fragment3);
        list.add(foodType_fragment4);
        list.add(foodType_fragment5);
        list.add(foodType_fragment6);

        tabStrip = (PagerSlidingTabStrip) findViewById(R.id.pager_titles);
        pager = (ViewPager) findViewById(R.id.vp);
        pager.setAdapter(new Type_PagerAdapter(getSupportFragmentManager(), mTitles, list));
        pager.setCurrentItem(0);

        //设置Tab底部分割线的颜色
        tabStrip.setUnderlineColor(Color.TRANSPARENT);
        //tab间的分割线
        tabStrip.setDividerColor(Color.TRANSPARENT);

        tabStrip.setViewPager(pager);

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Toast.makeText(Delicious_food.this, "Position:" + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}
