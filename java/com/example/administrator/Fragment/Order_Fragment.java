package com.example.administrator.Fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.Adapter.Order_L_Adapter;
import com.example.administrator.Object.Cart;
import com.example.administrator.Object.CartList;
import com.example.administrator.Object.Order_Left;
import com.example.administrator.Object.Order_Right;
import com.example.administrator.Object.RName;
import com.example.administrator.Object.Restaurant;
import com.example.administrator.Object.ToTalCount;
import com.example.administrator.Object.TotalPrice;
import com.example.administrator.maininterface.R;
import com.example.administrator.maininterface.Settle_Accounts;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.google.android.gms.analytics.internal.zzy.d;
import static com.google.android.gms.analytics.internal.zzy.n;
import static com.google.android.gms.analytics.internal.zzy.p;
import static com.google.android.gms.analytics.internal.zzy.t;
import static com.google.android.gms.analytics.internal.zzy.v;


/**
 * Created by Administrator on 2018/12/13.
 */

public class Order_Fragment extends Fragment {

    private ListView Left_listView, Right_listView, popuplistView;
    private List<Order_Left> typeList = new ArrayList<>();
    private List<Order_Right> foodInfoList = new ArrayList<>();
    private List<Cart> cartList = new ArrayList<>();
    private Order_L_Adapter L_adapter;
    private Order_R_Adapter R_adapter;
    private CartAdapter cartAdapter;
    private TextView selected_Type;
    private TextView totalCount, totalPrice, settlement;
    private ImageView jia;
    private ImageView jian;
    private ImageView Cart;
    private int flag1 = 0, flag2 = 0, flag_cart = 0;
    private PopupWindow popupWindow;
    private View CartView, popupView;
    private ViewGroup anim_layout;    //动画层
    private String web = "http://192.168.43.13:8080/ELM/";

    private List<Order_Right> fList = new ArrayList<>();


    //上次选中的左侧列表项
    private View lastView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //初始化餐品类型数据
        if (typeList.size() == 0) {
            initType();
        }

        //初始化餐品信息
        if (foodInfoList.size() == 0) {
            initFoodInfo();
        }

        SystemClock.sleep(300);

        for (int i = 0; i < typeList.size(); i++) {
            for (int j = 0; j < fList.size(); j++) {
                if (typeList.get(i).getType().equals(fList.get(j).getFoodType())) {
                    foodInfoList.add(fList.get(j));
                }
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.order, null);

        initView(view);

        ToTalCount.setTotalCount(0);
        TotalPrice.setTotalPrice(0);

        if (Integer.parseInt(totalCount.getText().toString()) <= 0) {
            totalCount.setVisibility(View.GONE);
        }

        SetOrder_L_Adapter(view);
        SetOrder_R_Adapter(view);
        initPopWindow();

        Order_L_Click();
        Order_R_Click();

        CartClick();

        Order_R_Scroll();

        SettleClick();

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    public void initView(View view) {
        selected_Type = (TextView) view.findViewById(R.id.Selected_LeftType);
        totalCount = (TextView) view.findViewById(R.id.totalCount);
        jia = (ImageView) view.findViewById(R.id.jia);
        jian = (ImageView) view.findViewById(R.id.jian);
        Cart = (ImageView) view.findViewById(R.id.cart);
        totalPrice = (TextView) view.findViewById(R.id.TotalPrice);
        settlement = (TextView) view.findViewById(R.id.Settlement);
        CartView = (View) view.findViewById(R.id.bottom_cart);
    }

    //结算
    private void SettleClick() {
        settlement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ToTalCount.getTotalCount() != 0) {
                    CartList.setCartList(cartList);
                    Intent intent = new Intent(getActivity(), Settle_Accounts.class);
                    startActivity(intent);
                }
            }
        });
    }

    //给点餐页面左侧列表配置适配器
    private void SetOrder_L_Adapter(View view) {
        L_adapter = new Order_L_Adapter(getActivity(), R.layout.left_item, typeList);
        Left_listView = (ListView) view.findViewById(R.id.left_list);
        Left_listView.setAdapter(L_adapter);
        /*lastView = getViewByPosition(1, Left_listView);
        lastView.setBackgroundColor(getResources().getColor(R.color.White));*/
    }

    //点餐页面左侧列表点击事件
    private void Order_L_Click() {
        Left_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (lastView != null) {
                    lastView.setBackgroundColor(getResources().getColor(R.color.colorGray));
                }
                view.setBackgroundColor(getResources().getColor(R.color.White));

                int i = Left_listView.getPositionForView(view);
                String m = typeList.get(i).getType();
                selected_Type.setText(m);
                int location = R_adapter.getPositionForType(m);
                R_adapter.Show();
                if (location != -1) {
                    Right_listView.setSelection(location);
                }

                lastView = view;
            }
        });
    }

    //点餐页面右侧列表点击事件
    private void Order_R_Click() {

    }

    //点餐页面右侧列表滚动事件
    private void Order_R_Scroll() {
        Right_listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                try {
                    String SelectType = R_adapter.getTypeForPosition(firstVisibleItem);
                    selected_Type.setText(SelectType);
                    int location = L_adapter.getPositionForTypeName(SelectType);
                    if (location != -1) {
                        Left_listView.setSelection(location);
                    }
                    if (lastView != null) {
                        lastView.setBackgroundColor(getResources().getColor(R.color.colorGray));
                    }
                    lastView = Left_listView.getChildAt(1);
                    lastView = Left_listView.getChildAt(location);
                    lastView.setBackgroundColor(getResources().getColor(R.color.White));
                } catch (NullPointerException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    //给点餐页面右侧列表配置适配器
    private void SetOrder_R_Adapter(View view) {

        R_adapter = new Order_R_Adapter(getActivity(), R.layout.right_item, foodInfoList);
        Right_listView = (ListView) view.findViewById(R.id.right_list);
        Right_listView.setAdapter(R_adapter);
    }

    //初始化餐品类型数据
    private void initType() {
        QueryFTForRest(RName.getRName());

        //flag1 = 1;
    }

    //初始化餐品信息
    private void initFoodInfo() {
        QueryFoodForRest(RName.getRName());

        //flag2 = 1;
    }

    //获取listview的item子项的view
    public View getViewByPosition(int pos, ListView listView) {
        final int firstListItemPosition = listView.getFirstVisiblePosition();
        final int lastListItemPosition = firstListItemPosition + listView.getChildCount() - 1;

        if (pos < firstListItemPosition || pos > lastListItemPosition) {
            return listView.getAdapter().getView(pos, null, listView);
        } else {
            final int childIndex = pos - firstListItemPosition;
            return listView.getChildAt(childIndex);
        }
    }

    //创建动画层
    private ViewGroup createAnimLayout() {
        ViewGroup rootView = (ViewGroup) getActivity().getWindow().getDecorView();
        LinearLayout animLayout = new LinearLayout(getActivity());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        animLayout.setLayoutParams(lp);
        animLayout.setId(Integer.MAX_VALUE - 1);
        animLayout.setBackgroundResource(android.R.color.transparent);
        rootView.addView(animLayout);
        return animLayout;
    }

    private View addViewToAnimLayout(final ViewGroup parent, final View view, int[] location) {
        int x = location[0];
        int y = location[1];
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.leftMargin = x;
        lp.topMargin = y;
        view.setLayoutParams(lp);
        return view;
    }

    //设置动画
    public void setAnim(final View v,  int[] startLocation) {
        anim_layout = null;
        anim_layout = createAnimLayout();
        anim_layout.addView(v);//把图片添加到动画层
        final View view =  addViewToAnimLayout(anim_layout, v, startLocation);
        int[] endLocation = new int[2];//存储动画结束位置的x、y坐标
        Cart.getLocationInWindow(endLocation);//把购物车所在位置添加到结束位置中去

        //计算位移
        int endX = endLocation[0] - startLocation[0] + 80;
        int endY = endLocation[1] - startLocation[1];

        TranslateAnimation translateAnimationX = new TranslateAnimation(0, endX, 0, 0);
        translateAnimationX.setInterpolator(new LinearInterpolator());//让动画以均匀的速度改变
        translateAnimationX.setRepeatCount(0);//动画重复执行0次
        translateAnimationX.setFillAfter(true);//动画结束后留在结束位置

        TranslateAnimation translateAnimationY = new TranslateAnimation(0, 0, 0, endY);
        translateAnimationY.setInterpolator(new AccelerateInterpolator());//让动画以均匀的速度改变
        translateAnimationY.setRepeatCount(0);//动画重复执行0次
        translateAnimationY.setFillAfter(true);//动画结束后留在结束位置

        AnimationSet set = new AnimationSet(false);
        set.setFillAfter(false);
        set.addAnimation(translateAnimationY);
        set.addAnimation(translateAnimationX);
        set.setDuration(500);//动画执行时间
        view.setAnimation(set);

        //动画监听事件
        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                v.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                v.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    //点餐页面右侧列表适配器
    class Order_R_Adapter extends ArrayAdapter<Order_Right> {
        private int resourceId;
        private int selectedCount;

        public Order_R_Adapter(Context context, int textViewResourceId, List<Order_Right> objects) {
            super(context, textViewResourceId, objects);
            resourceId = textViewResourceId;
        }

        @NonNull
        @Override
        public View getView(final int position, View convertView, final ViewGroup parent) {
            final Order_Right order_right = getItem(position);
            final View view;
            final ViewHolder viewHolder;
            if (convertView == null) {
                view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
                viewHolder = new ViewHolder();
                viewHolder.foodImage = (ImageView) view.findViewById(R.id.right_image);
                viewHolder.foodName = (TextView) view.findViewById(R.id.right_foodname);
                viewHolder.foodType = (TextView) view.findViewById(R.id.Food_LeftType) ;
                viewHolder.price = (TextView) view.findViewById(R.id.price);
                viewHolder.salesCount = (TextView) view.findViewById(R.id.salesCount);
                viewHolder.selectedCount = (TextView) view.findViewById(R.id.selectedCount);
                viewHolder.jian = (ImageView) view.findViewById(R.id.jian);
                viewHolder.jia = (ImageView) view.findViewById(R.id.jia);
                view.setTag(viewHolder);
            } else {
                view = convertView;
                viewHolder = (ViewHolder) view.getTag();
            }
            viewHolder.foodImage.setImageResource(order_right.getImageId());
            viewHolder.foodName.setText(order_right.getFoodName());
            viewHolder.foodType.setText(order_right.getFoodType());
            viewHolder.price.setText("¥"+order_right.getPrice());
            viewHolder.salesCount.setText("已售  "+ order_right.getSalesCount());
            if (order_right.getSelectedCount() > 0) {
                viewHolder.selectedCount.setText(order_right.getSelectedCount()+"");
                viewHolder.selectedCount.setVisibility(view.VISIBLE);
                viewHolder.jian.setVisibility(view.VISIBLE);
            } else  {
                viewHolder.selectedCount.setText(0+"");
                viewHolder.selectedCount.setVisibility(view.INVISIBLE);
                viewHolder.jian.setVisibility(view.INVISIBLE);
            }

            //增加商品
            viewHolder.jia.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectedCount = Integer.parseInt(viewHolder.selectedCount.getText().toString());
                    selectedCount++;//该商品现在已选择的数量+1
                    viewHolder.selectedCount.setText(selectedCount+"");
                    viewHolder.selectedCount.setVisibility(View.VISIBLE);
                    viewHolder.jian.setVisibility(View.VISIBLE);
                    order_right.setSelectedCount(selectedCount);

                    //添加商品动画
                    int[] startLocation = new int[2];
                    v.getLocationInWindow(startLocation);
                    ImageView Jia = new ImageView(getContext());
                    Jia.setImageResource(R.drawable.jia);
                    setAnim(Jia, startLocation);

                    //购物车商品增加
                    int pos = cartAdapter.getPositionForName(getItem(position).getFoodName());//根据商品名称找到在购物车列表中有相同名称的商品
                    if (pos != -1) {
                        cartList.get(pos).setFoodCount(selectedCount);//如果存在相同名称的商品，就把外面已选择的商品数量赋值给购物车列表中相应项
                    } else {
                        Cart cart = new Cart(getItem(position).getFoodName(),
                                getItem(position).getPrice(), selectedCount);
                        cartList.add(cart);//如果没有找到就往购物车列表中添加一条数据
                    }

                    //选择商品总数量增加
                    ToTalCount.setTotalCount(ToTalCount.getTotalCount()+1);
                    TotalPrice.setTotalPrice(TotalPrice.getTotalPrice()+order_right.getPrice());
                    if (ToTalCount.getTotalCount() > 0) {
                        totalCount.setText(ToTalCount.getTotalCount()+"");
                        DecimalFormat df = new DecimalFormat("#.00");
                        totalPrice.setText("¥  "+df.format(TotalPrice.getTotalPrice()));
                        totalCount.setVisibility(View.VISIBLE);
                        if (flag_cart == 0) {
                            flag_cart = 1;
                            Cart.setBackgroundResource(R.drawable.cart2);
                            totalPrice.setTextColor(Color.parseColor("#ffffff"));
                            settlement.setBackgroundColor(Color.parseColor("#EEAD0E"));
                            settlement.setText("去结算");
                            settlement.setTextColor(Color.parseColor("#ffffff"));
                        }
                    } else {
                        totalCount.setText(0+"");
                        totalCount.setVisibility(View.GONE);
                    }
                }
            });
            //删除商品
            viewHolder.jian.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectedCount = Integer.parseInt(viewHolder.selectedCount.getText().toString());
                    selectedCount--;
                    if (selectedCount <= 0) {
                        viewHolder.selectedCount.setText(0+"");
                        viewHolder.selectedCount.setVisibility(View.INVISIBLE);
                        viewHolder.jian.setVisibility(View.INVISIBLE);
                        order_right.setSelectedCount(0);
                    } else {
                        viewHolder.selectedCount.setText(selectedCount+"");
                        order_right.setSelectedCount(selectedCount);
                    }

                    //购物车商品减少
                    if (cartList.size() != 0) {
                        int pos = cartAdapter.getPositionForName(getItem(position).getFoodName());
                        if (selectedCount > 0) {
                            cartList.get(pos).setFoodCount(selectedCount);
                        } else {
                            cartList.remove(pos);
                        }
                    }

                    //选择商品总数量减少
                    ToTalCount.setTotalCount(ToTalCount.getTotalCount()-1);
                    TotalPrice.setTotalPrice(TotalPrice.getTotalPrice()-order_right.getPrice());
                    if (ToTalCount.getTotalCount() > 0) {
                        totalCount.setText(ToTalCount.getTotalCount()+"");
                        totalCount.setVisibility(View.VISIBLE);
                        DecimalFormat df = new DecimalFormat("#.00");
                        totalPrice.setText("¥  "+df.format(TotalPrice.getTotalPrice()));

                    } else {
                        totalCount.setText(0+"");
                        totalCount.setVisibility(View.GONE);
                        if (flag_cart == 1) {
                            flag_cart = 0;
                            Cart.setBackgroundResource(R.drawable.cart1);
                            totalPrice.setText("未选购商品");
                            totalPrice.setTextColor(Color.parseColor("#C2C2C2"));
                            settlement.setBackgroundColor(Color.parseColor("#666666"));
                            settlement.setText("¥0起送");
                            settlement.setTextColor(Color.parseColor("#CCCCCC"));
                        }
                    }
                }
            });
            return view;
        }

        public class ViewHolder {
            ImageView foodImage;
            ImageView jia;
            ImageView jian;
            TextView foodName;
            TextView foodType;
            TextView salesCount;
            TextView price;
            TextView selectedCount;
        }

        public String getTypeForPosition(int position) {
            return getItem(position).getFoodType();
        }

        public int getPositionForType(String type) {
            for (int i = 0; i < getCount(); i++) {
                String foodType = getItem(i).getFoodType();
                if (foodType.equals(type)) {
                    return i;
                }
            }
            return -1;
        }

        public int getPositionForName(String foodName) {
            for (int i = 0; i < getCount(); i++) {
                if (getItem(i).getFoodName().equals(foodName)) {
                    return i;
                }
            }
            return -1;
        }

        public void Show() {
            for (int i = 0; i < getCount(); i++) {
                Log.d("getActivity()", getItem(i).getFoodType());
            }
        }

        /*public int getSelectedCountForPosition(int position) {
            return getItem(position).getSelectedCount();
        }

        public void setSelectedCountForPosition(int position, int count) {
            if (count > 0) {
                getItem(position).setSelectedCount(count);
            } else {
                getItem(position).setSelectedCount(0);
            }

        }*/
    }

    //购物车图标点击事件
    private void CartClick() {
        Cart.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            /*String s = String.valueOf(popupWindow.isShowing());
            Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();*/
            if (popupWindow.isShowing()) {
                popupWindow.dismiss();
            } else {
                popupWindow.showAsDropDown(CartView);
            }
        }
    });
    }

    //初始化购物车弹出界面
    private void initPopWindow() {
        popupView = getActivity().getLayoutInflater().inflate(R.layout.cart_popwindow, null);
        popuplistView = (ListView) popupView.findViewById(R.id.popCart);
        cartAdapter = new CartAdapter(getActivity(), R.layout.cart_item, cartList);
        popuplistView.setAdapter(cartAdapter);
        //创建PopupWindow对象，指定宽度和高度
        popupWindow = new PopupWindow(popupView, 1100, 600);
        //设置动画
        popupWindow.setAnimationStyle(R.style.mypopwindow_anim_style);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        popupWindow.setFocusable(true);
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);

        //清空全部商品
        View clearView = popupView.findViewById(R.id.emptyCart);
        clearView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                //builder.setIcon(R.drawable.cast_ic_notification_small_icon);
                builder.setMessage("清空购物车？");
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("getActivity()", "取消清空购物车");
                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        cartList.clear();
                        for (int i = 0; i < foodInfoList.size(); i++) {
                            foodInfoList.get(i).setSelectedCount(0);
                        }
                        cartAdapter.notifyDataSetChanged();
                        R_adapter.notifyDataSetChanged();

                        TotalPrice.setTotalPrice(0.00);
                        ToTalCount.setTotalCount(0);
                        totalCount.setText(0+"");
                        totalCount.setVisibility(View.GONE);
                        if (flag_cart == 1) {
                            flag_cart = 0;
                            Cart.setBackgroundResource(R.drawable.cart1);
                            totalPrice.setText("未选购商品");
                            totalPrice.setTextColor(Color.parseColor("#C2C2C2"));
                            settlement.setBackgroundColor(Color.parseColor("#666666"));
                            settlement.setText("¥0起送");
                            settlement.setTextColor(Color.parseColor("#CCCCCC"));
                        }
                        popupWindow.dismiss();
                    }
                });
                AlertDialog b = builder.create();
                b.show();

            }
        });
        popupWindow.update();
        //popupWindow.getBackground().setAlpha(100);
    }

    //购物车列表适配器
    class CartAdapter extends ArrayAdapter<Cart> {
        private int resourceId;

        public CartAdapter (Context context, int textViewResourceId, List<Cart> objects) {
            super(context, textViewResourceId, objects);
            resourceId = textViewResourceId;
        }

        @NonNull
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final Cart cart = getItem(position);
            final View view;
            final ViewHolder viewHolder;
            if (convertView == null) {
                view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
                viewHolder = new ViewHolder();
                viewHolder.foodName = (TextView) view.findViewById(R.id.food_name);
                viewHolder.foodPrice = (TextView) view.findViewById(R.id.food_price);
                viewHolder.selectedCount = (TextView) view.findViewById(R.id.food_count);
                viewHolder.add = (ImageView) view.findViewById(R.id.add_food);
                viewHolder.reduce = (ImageView) view.findViewById(R.id.reduce_food);
                view.setTag(viewHolder);
            } else {
                view = convertView;
                viewHolder = (ViewHolder) view.getTag();
            }
            viewHolder.foodName.setText(cart.getFoodName());
            viewHolder.foodPrice.setText("¥"+cart.getFoodPrice());
            viewHolder.selectedCount.setText(cart.getFoodCount()+"");

            //购物车内增加商品
            viewHolder.add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //购物车内选择数量增加
                    cart.setFoodCount(cart.getFoodCount()+1);
                    viewHolder.selectedCount.setText(cart.getFoodCount()+"");
                    cartAdapter.notifyDataSetChanged();

                    //外部显示商品数量增加
                    int pos = R_adapter.getPositionForName(cart.getFoodName());
                    foodInfoList.get(pos).setSelectedCount(foodInfoList.get(pos).getSelectedCount()+1);
                    R_adapter.notifyDataSetChanged();

                    //选择商品总数量、价格增加
                    ToTalCount.setTotalCount(ToTalCount.getTotalCount()+1);
                    TotalPrice.setTotalPrice(TotalPrice.getTotalPrice()+foodInfoList.get(pos).getPrice());
                    if (ToTalCount.getTotalCount() > 0) {
                        totalCount.setText(ToTalCount.getTotalCount()+"");
                        DecimalFormat df = new DecimalFormat("#.00");
                        totalPrice.setText("¥  "+df.format(TotalPrice.getTotalPrice()));
                        totalCount.setVisibility(View.VISIBLE);
                        if (flag_cart == 0) {
                            flag_cart = 1;
                            Cart.setBackgroundResource(R.drawable.cart2);
                            totalPrice.setTextColor(Color.parseColor("#ffffff"));
                            settlement.setBackgroundColor(Color.parseColor("#EEAD0E"));
                            settlement.setText("去结算");
                            settlement.setTextColor(Color.parseColor("#ffffff"));
                        }
                    } else {
                        totalCount.setText(0+"");
                        totalCount.setVisibility(View.GONE);
                    }
                }
            });

            //购物车内删除商品
            viewHolder.reduce.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //购物车内选择数量减少
                    cart.setFoodCount(cart.getFoodCount()-1);
                    if (cart.getFoodCount() > 0) {
                        viewHolder.selectedCount.setText(cart.getFoodCount()+"");
                    } else {
                        cartList.remove(position);
                        cartAdapter.notifyDataSetChanged();
                        if (cartList.size() == 0) {
                            popupWindow.dismiss();
                        }
                    }

                    //外部显示商品数量减少
                    int pos = R_adapter.getPositionForName(cart.getFoodName());
                    foodInfoList.get(pos).setSelectedCount(foodInfoList.get(pos).getSelectedCount()-1);
                    R_adapter.notifyDataSetChanged();

                    //选择商品总数量、价格减少
                    ToTalCount.setTotalCount(ToTalCount.getTotalCount()-1);
                    TotalPrice.setTotalPrice(TotalPrice.getTotalPrice()-foodInfoList.get(pos).getPrice());
                    if (ToTalCount.getTotalCount() > 0) {
                        totalCount.setText(ToTalCount.getTotalCount()+"");
                        totalCount.setVisibility(View.VISIBLE);
                        DecimalFormat df = new DecimalFormat("#.00");
                        totalPrice.setText("¥  "+df.format(TotalPrice.getTotalPrice()));

                    } else {
                        totalCount.setText(0+"");
                        totalCount.setVisibility(View.GONE);
                        if (flag_cart == 1) {
                            flag_cart = 0;
                            Cart.setBackgroundResource(R.drawable.cart1);
                            totalPrice.setText("未选购商品");
                            totalPrice.setTextColor(Color.parseColor("#C2C2C2"));
                            settlement.setBackgroundColor(Color.parseColor("#666666"));
                            settlement.setText("¥0起送");
                            settlement.setTextColor(Color.parseColor("#CCCCCC"));
                        }
                    }
                }
            });

            return view;
        }

        class ViewHolder {
            TextView foodName;
            TextView foodPrice;
            TextView selectedCount;
            ImageView add;
            ImageView reduce;
        }

        public int getPositionForName(String foodName) {
            if (getCount() == 0) {
                return -1;
            } else {
                for (int i = 0; i < getCount(); i++) {
                    if (getItem(i).getFoodName() == foodName) {
                        return i;
                    }
                }
                return -1;
            }
        }

        public String getNameForPosition(int position) {
            return getItem(position).getFoodName();
        }
    }

    //根据选取的商家名查询餐品
    private void QueryFoodForRest(final String RName) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();

                    //上传RName
                    RequestBody requestBody = new FormBody.Builder()
                            .add("restName", RName).build();

                    Request request = new Request.Builder().url(web + "QueryFoodForRest")
                            .post(requestBody)
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    removeBOM(responseData);
                    parseJSONWithJSONObject_Food(responseData);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //根据选取的商家名查询餐品种类
    private void QueryFTForRest(final String RName) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();

                    //上传RName
                    RequestBody requestBody = new FormBody.Builder()
                            .add("restName", RName).build();

                    Request request = new Request.Builder().url(web + "QueryFTForRest")
                            .post(requestBody)
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    removeBOM(responseData);
                    parseJSONWithJSONObject_FoodType(responseData);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static final String removeBOM(String data) {
        if (TextUtils.isEmpty(data)) {
            return data;
        }
        if (data.startsWith("\ufeff")) {
            return data.substring(1);
        } else {
            return data;
        }
    }

    private void parseJSONWithJSONObject_Food(String jsonData) {
        try {
            JSONArray jsonArray = new JSONArray(jsonData);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String foodName = jsonObject.getString("foodName");
                String foodType = jsonObject.getString("foodType");
                int salesCount = jsonObject.getInt("salesCount");
                double foodPrice = jsonObject.getDouble("foodPrice");

                if (i == 0 && foodInfoList.size() != 0) {
                    foodInfoList.clear();
                    fList.clear();
                }
                Order_Right foodInfo = new Order_Right(R.drawable.rest_1,
                        foodName, foodType, foodPrice, salesCount);
                fList.add(foodInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parseJSONWithJSONObject_FoodType(String jsonData) {
        try {
            JSONArray jsonArray = new JSONArray(jsonData);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String typeName = jsonObject.getString("typeName");

                if (i == 0 && typeList.size() != 0) {
                    typeList.clear();
                }
                Order_Left type = new Order_Left(typeName);
                typeList.add(type);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
