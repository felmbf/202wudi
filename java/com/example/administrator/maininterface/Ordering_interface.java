package com.example.administrator.maininterface;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.text.TextDirectionHeuristicCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;
import com.example.administrator.Adapter.Type_PagerAdapter;
import com.example.administrator.Fragment.Order_Fragment;
import com.example.administrator.Fragment.RestInfo_Fragment;
import com.example.administrator.Fragment.Review_Fragment;
import com.example.administrator.Object.RName;
import com.example.administrator.Object.RestaurantName;

import java.util.ArrayList;
import java.util.List;

public class Ordering_interface extends AppCompatActivity {

    private String[] oiTitles = {"点餐", "评价", "商家"};
    private PagerSlidingTabStrip Titles;
    private ViewPager pager;
    private List<Fragment> list = new ArrayList<>();
    private TextView restaurantName;
    private ImageView Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordering_interface);

        restaurantName = (TextView) findViewById(R.id.restaurantName);
        Back = (ImageView) findViewById(R.id.back2);
        restaurantName.setText(RName.getRName());

        PagerSetAdapter();

        //返回
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Ordering_interface.this, Delicious_food.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });
    }

    private void PagerSetAdapter() {
        Order_Fragment fragment1 = new Order_Fragment();
        Review_Fragment fragment2 = new Review_Fragment();
        RestInfo_Fragment fragment3 = new RestInfo_Fragment();
        list.add(fragment1);
        list.add(fragment2);
        list.add(fragment3);

        Titles = (PagerSlidingTabStrip) findViewById(R.id.oi_title);
        pager = (ViewPager) findViewById(R.id._oi);

        pager.setAdapter(new Type_PagerAdapter(getSupportFragmentManager(), oiTitles, list));
        pager.setCurrentItem(0);
        pager.setOffscreenPageLimit(2);

        Titles.setViewPager(pager);

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}
