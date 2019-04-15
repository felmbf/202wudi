package com.example.administrator.maininterface;

import android.content.Intent;
import android.media.Image;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.example.administrator.Adapter.MainType_PagerAdapter;
import com.example.administrator.Fragment.MainType_Fragment1;
import com.example.administrator.Fragment.MainType_Fragment2;
import com.example.administrator.Object.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainInterface extends AppCompatActivity implements ViewPager.OnPageChangeListener{

    //public int flag = 0;

    private TextView Query;
    private LinearLayout Wode;
    private LinearLayout Dingdan;
    private LinearLayout Faxian;

    //private LinearLayout Meishi;

    private ViewPager viewPager;
    private MainType_Fragment1 fragment1;
    private MainType_Fragment2 fragment2;
    private List<Fragment> viewList = new ArrayList<>();

    //首页图片轮播
    private ViewPager image_vp;
    private LinearLayout _point;
    private int[] imageResIds;
    private ArrayList<ImageView> imageViews = new ArrayList<>();
    private int lastPosition;
    private boolean isRunning = false;

    //第一次点击事件发生的时间
    private long mExitTime;

    private static MainInterface instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_interface);

        SDKInitializer.initialize(getApplicationContext());
        Data.setFlag(0);

        initViews();
        initData();
        initImageVPAdapter();

        viewList.add(fragment1);
        viewList.add(fragment2);

        viewPager.setAdapter(new MainType_PagerAdapter(getSupportFragmentManager(), viewList));
        viewPager.setCurrentItem(0);

        new Thread() {
            @Override
            public void run() {
                isRunning = true;
                while (isRunning) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            image_vp.setCurrentItem(image_vp.getCurrentItem() + 1);
                        }
                    });
                }
            }
        }.start();

        Click();
    }

    //初始化控件
    public void initViews() {
        Query = (TextView) findViewById(R.id.edit_query);
        Wode = (LinearLayout) findViewById(R.id.wode);
        Dingdan = (LinearLayout) findViewById(R.id.dingdan);
        Faxian = (LinearLayout) findViewById(R.id.faxian);

        //ViewPager
        viewPager = (ViewPager) findViewById(R.id.vp_2);
        fragment1 = new MainType_Fragment1();
        fragment2 = new MainType_Fragment2();

        //image_ViewPager
        image_vp = (ViewPager) findViewById(R.id.image_vp);
        image_vp.addOnPageChangeListener(this);
        _point = (LinearLayout) findViewById(R.id.image_point);
    }

    public void Click() {
        //进入搜索界面
        Query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Data.setFlag(1);
                Intent intent = new Intent(MainInterface.this, QueryActivity.class);
                startActivity(intent);
            }
        });

        //进入我的信息界面
        Wode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainInterface.this, MyInfo.class);
                startActivity(intent);
                //overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right);
                overridePendingTransition(0, 0);
                finish();
            }
        });

        //进入订单界面
        Dingdan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainInterface.this, Order.class);
                startActivity(intent);
                //overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right);
                overridePendingTransition(0, 0);
                finish();
            }
        });

        //进入发现界面
        Faxian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainInterface.this, FaXian.class);
                startActivity(intent);
                //overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_right);
                overridePendingTransition(0, 0);
                finish();
            }
        });
    }

    //初始化数据
    public void initData() {
        imageResIds = new int[]{R.drawable.vp_image1, R.drawable.vp_image2,
                                R.drawable.vp_image3, R.drawable.vp_image4,
                                R.drawable.vp_image5};

        ImageView imageView;
        View pointView;
        for (int i = 0; i < imageResIds.length; i++) {
            imageView = new ImageView(this);
            imageView.setBackgroundResource(imageResIds[i]);
            imageViews.add(imageView);

            pointView = new View(this);
            pointView.setBackgroundResource(R.drawable.point_selector);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(32, 4);
            if (i != 0) {
                layoutParams.leftMargin = 10;
            }
            pointView.setEnabled(false);
            _point.addView(pointView, layoutParams);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isRunning = false;
    }

    //给image_vp设置适配器
    public void initImageVPAdapter() {
        _point.getChildAt(0).setEnabled(true);
        lastPosition = 0;
        image_vp.setAdapter(new ImagePagerAdapter());
        image_vp.setCurrentItem(50000);
    }

    //图片轮播适配器
    class ImagePagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            int newPosition = position % 5;
            ImageView imageView = imageViews.get(newPosition);

            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        int newPosition = position % 5;
        _point.getChildAt(lastPosition).setEnabled(false);
        _point.getChildAt(newPosition).setEnabled(true);
        lastPosition = newPosition;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public static MainInterface getInstance() {
        return instance;
    }

    //提示退出程序
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Object mHelperUtils;
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
