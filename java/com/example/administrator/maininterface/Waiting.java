package com.example.administrator.maininterface;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.example.administrator.Adapter.ListInfoAdapter;
import com.example.administrator.Object.CartList;
import com.example.administrator.Object.Order_Right;
import com.example.administrator.Object.RName;
import com.example.administrator.Object.SettleMent;
import com.example.administrator.Object.Temp;
import com.example.administrator.Object.ToTalCount;
import com.example.administrator.Object.TotalPrice;
import com.example.administrator.Object.UserData;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.R.attr.negativeButtonText;
import static android.R.attr.primaryContentAlpha;
import static android.R.attr.queryHint;
import static android.R.attr.track;
import static android.R.attr.width;

public class Waiting extends AppCompatActivity {

    private ListView listInfoView;
    private ListInfoAdapter listInfoAdapter;
    private List<SettleMent> settleMentList = new ArrayList<>();
    private TextView totalPrice;
    private TextView WaitInfo;
    private Button confirm;//确认送达按钮
    private Button cancel;//取消订单
    private Button BackToMain;//返回首页
    private LinearLayout Before;
    private LinearLayout After;

    private String web = "http://192.168.43.13:8080/ELM/";

    private int width;
    private int height;

    public LocationClient mLocationClient;
    //private TextView positionText;
    private MapView mapView;
    private BaiduMap baiduMap;
    private boolean isFirstLocate = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting);

        Toast.makeText(this, "支付成功！" +
                "商家已接单，请耐心等待！", Toast.LENGTH_SHORT).show();

        initMap();
        initView();
        initData();
        ListInfo_Adapter();

        Click();

        DisplayMetrics dm = getResources().getDisplayMetrics();
        width = dm.widthPixels;
        height = dm.heightPixels;
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) listInfoView.getLayoutParams();
        params.height = CartList.getCartList().size() * 180;
        listInfoView.setLayoutParams(params);
    }

    public void initView() {
        totalPrice = (TextView) findViewById(R.id.listinfo_TotalPrice);
        WaitInfo = (TextView) findViewById(R.id.waitInfo);
        confirm = (Button) findViewById(R.id.confirm);
        cancel = (Button) findViewById(R.id.cancel);
        BackToMain = (Button) findViewById(R.id.backToMain);
        Before = (LinearLayout) findViewById(R.id.before);
        After = (LinearLayout) findViewById(R.id.after);

    }

    private void initMap() {
        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(new MyLocationListener());
        //SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_waiting);
        mapView = (MapView) findViewById(R.id.bmap_View);
        baiduMap = mapView.getMap();
        baiduMap.setMyLocationEnabled(true);
        //positionText = (TextView) findViewById(R.id.position_text_view);
        List<String> permissionList = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(Waiting.this, android.Manifest.
                permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(android.Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(Waiting.this, android.Manifest.
                permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(android.Manifest.permission.READ_PHONE_STATE);
        }
        if (ContextCompat.checkSelfPermission(Waiting.this, android.Manifest.
                permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!permissionList.isEmpty()) {
            String[] permissions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(Waiting.this, permissions, 1);
        } else {
            requestLocation();
        }
    }


    public  void initData() {
        for (int i = 0; i < CartList.getCartList().size(); i++) {
            SettleMent settleMent = new SettleMent(R.drawable.rest_1,
                    CartList.getCartList().get(i).getFoodName(),
                    CartList.getCartList().get(i).getFoodCount(),
                    CartList.getCartList().get(i).getFoodPrice());
            settleMentList.add(settleMent);
        }

        DecimalFormat df = new DecimalFormat("#.00");
        totalPrice.setText("¥" + df.format(TotalPrice.getTotalPrice()));
    }

    public void ListInfo_Adapter() {
        listInfoAdapter = new ListInfoAdapter(this, R.layout.listinfo_item, settleMentList);
        listInfoView = (ListView) findViewById(R.id.listInfo);
        listInfoView.setAdapter(listInfoAdapter);
    }

    //点击事件
    public void Click() {
        //确认订单
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(Waiting.this);
                builder.setMessage("确认收货？");
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("Waiting", "取消确认");
                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        updateSalesCount_Food();
                        updateSalesCount_Rest();
                        updateYuE_User();
                        updateJinBi_User();
                        UpdateDingdan();
                        UserData.setJinBi(UserData.getJinBi() +
                                (int)TotalPrice.getTotalPrice() / 10);
                        WaitInfo.setText("订单已送达");
                        Before.setVisibility(View.GONE);
                        After.setVisibility(View.VISIBLE);
                    }
                });
                AlertDialog b = builder.create();
                b.show();
            }
        });

        //取消订单
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Waiting.this);
                builder.setMessage("您要取消订单吗？");
                builder.setNegativeButton("不", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("Waiting", "否");
                    }
                });
                builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        UserData.setYuE(UserData.getYuE() + TotalPrice.getTotalPrice());
                        UserData.setJinBi(UserData.getJinBi() + Temp.getReduce_jinBi());
                        Temp.setReduce_jinBi(0);
                        Intent intent = new Intent(Waiting.this, Ordering_interface.class);
                        startActivity(intent);
                        finish();
                        SystemClock.sleep(200);
                    }
                });
                AlertDialog b = builder.create();
                b.show();
            }
        });

        //返回首页
        //Toast.makeText(Waiting.this, "" + After.getVisibility(), Toast.LENGTH_SHORT).show();
        if (After.getVisibility() == View.GONE) {
            BackToMain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Waiting.this, MainInterface.class);
                    startActivity(intent);
                    finish();
                }
            });
        }
    }

    //更新数据库中商品销售数量信息
    private void updateSalesCount_Food() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                String fName;
                String salesCount;
                for (int i = 0; i < CartList.getCartList().size(); i++) {
                    fName = CartList.getCartList().get(i).getFoodName();
                    salesCount = String.valueOf(CartList.getCartList().get(i).getFoodCount());

                    try {
                        RequestBody requestBody = new FormBody.Builder()
                                .add("foodName", fName)
                                .add("addCount", salesCount)
                                .build();

                        //声明请求
                        Request request = new Request.Builder()
                                .url(web + "UpdateSalesCount")
                                .post(requestBody)
                                .build();

                        //客户端发起请求
                        Response response = client.newCall(request).execute();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();
    }

    //更新数据库中商家销售数量信息
    private void updateSalesCount_Rest() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                String RName = com.example.administrator.Object.RName.getRName();
                String salesCount = String.valueOf(ToTalCount.getTotalCount());
                try {
                    RequestBody requestBody = new FormBody.Builder()
                            .add("restName", RName)
                            .add("addCount", salesCount)
                            .build();

                    //声明请求
                    Request request = new Request.Builder()
                            .url(web + "UpdateSalesCount_Rest")
                            .post(requestBody)
                            .build();

                    //客户端发起请求
                    Response response = client.newCall(request).execute();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //更新数据库中顾客余额信息
    private void updateYuE_User() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                String username = UserData.getUsername();
                String updateCount = String.valueOf(TotalPrice.getTotalPrice());
                int addCount = (int) TotalPrice.getTotalPrice() / 10;
                String addCount_jinBi = String.valueOf(addCount);
                try {
                    RequestBody requestBody = new FormBody.Builder()
                            .add("username", username)
                            .add("updateCount", updateCount)
                            .add("addCount_jinBi", addCount_jinBi)
                            .build();

                    //声明请求
                    Request request = new Request.Builder()
                            .url(web + "UserUpdateYuE")
                            .post(requestBody)
                            .build();

                    //客户端发起请求
                    Response response = client.newCall(request).execute();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //更新数据库中顾客金币信息
    private void updateJinBi_User() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                String username = UserData.getUsername();
                String updateCount = String.valueOf(Temp.getReduce_jinBi());
                try {
                    RequestBody requestBody = new FormBody.Builder()
                            .add("username", username)
                            .add("updateCount", updateCount)
                            .build();

                    //声明请求
                    Request request = new Request.Builder()
                            .url(web + "UserUpdateJinBi")
                            .post(requestBody)
                            .build();

                    //客户端发起请求
                    Response response = client.newCall(request).execute();

                    Temp.setReduce_jinBi(0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    //关于Map的函数
    private void navigateTo(BDLocation location) {
        if (isFirstLocate) {
            LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
            MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(ll);
            baiduMap.animateMapStatus(update);
            update = MapStatusUpdateFactory.zoomTo(16f);
            baiduMap.animateMapStatus(update);
            isFirstLocate = false;
        }
        MyLocationData.Builder locationBuilder = new MyLocationData.Builder();
        locationBuilder.latitude(location.getLatitude());
        locationBuilder.longitude(location.getLongitude());
        MyLocationData locationData = locationBuilder.build();
        baiduMap.setMyLocationData(locationData);
    }

    private void requestLocation() {
        initLocation();
        mLocationClient.start();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) {
                    for (int result : grantResults) {
                        if (result != PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(this, "必须同意所有权限才能使用本程序",
                                    Toast.LENGTH_SHORT).show();
                            finish();
                            return;
                        }
                    }
                    requestLocation();
                } else {
                    Toast.makeText(this, "发生未知错误", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
        }
    }

    public class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(final BDLocation bdLocation) {
            if (bdLocation.getLocType() == BDLocation.TypeGpsLocation
                    || bdLocation.getLocType() == BDLocation.TypeNetWorkLocation) {
                navigateTo(bdLocation);
            }
            /*runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    StringBuilder currentPosition = new StringBuilder();
                    currentPosition.append("纬度：").append(bdLocation.getLatitude()).
                            append("\n");
                    currentPosition.append("经线：").append(bdLocation.getLongitude()).
                            append("\n");
                    currentPosition.append("国家：").append(bdLocation.getCountry()).
                            append("\n");
                    currentPosition.append("省：").append(bdLocation.getProvince()).
                            append("\n");
                    currentPosition.append("市：").append(bdLocation.getCity()).
                            append("\n");
                    currentPosition.append("区：").append(bdLocation.getDistrict()).
                            append("\n");
                    currentPosition.append("街道：").append(bdLocation.getStreet()).
                            append("\n");
                    currentPosition.append("定位方式：");
                    if (bdLocation.getLocType() == BDLocation.TypeGpsLocation) {
                        currentPosition.append("GPS");
                    } else if (bdLocation.getLocType() == BDLocation.TypeNetWorkLocation) {
                        currentPosition.append("网络");
                    }
                    positionText.setText(currentPosition);
                }
            });*/
        }
    }

    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setScanSpan(5000);
        option.setLocationMode(LocationClientOption.LocationMode.Device_Sensors);
        option.setIsNeedAddress(true);
        mLocationClient.setLocOption(option);
    }


    private void UpdateDingdan() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();

                    String O_user = UserData.getUsername();
                    String O_restName = RName.getRName();
                    String O_food = "";
                    String s;
                    for (int i = 0; i < CartList.getCartList().size(); i++) {
                        s = CartList.getCartList().get(i).getFoodName();
                        O_food = O_food + "+" + s;
                    }
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    Date curDate = new Date(System.currentTimeMillis());
                    String O_date = formatter.format(curDate);

                    double d = TotalPrice.getTotalPrice();
                    String O_totalPrice = String.valueOf(d);

                    //上传RName
                    RequestBody requestBody = new FormBody.Builder()
                            .add("O_user", O_user)
                            .add("O_restName", O_restName)
                            .add("O_food", O_food)
                            .add("O_date", O_date)
                            .add("O_totalPrice", O_totalPrice).build();

                    Request request = new Request.Builder().url(web + "AddDingDan")
                            .post(requestBody)
                            .build();
                    Response response = client.newCall(request).execute();
                    //String responseData = response.body().string();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.stop();
        mapView.onDestroy();
        baiduMap.setMyLocationEnabled(false);
    }
}
