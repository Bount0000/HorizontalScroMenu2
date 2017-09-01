package com.example.lenovo.horizontalscromenu2;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.lenovo.horizontalscromenu2.fragment.Fragment_caijing;
import com.example.lenovo.horizontalscromenu2.fragment.Fragment_jushi;
import com.example.lenovo.horizontalscromenu2.fragment.Fragment_keji;
import com.example.lenovo.horizontalscromenu2.fragment.Fragment_shehui;
import com.example.lenovo.horizontalscromenu2.fragment.Fragment_shishang;
import com.example.lenovo.horizontalscromenu2.fragment.Fragment_tiyu;
import com.example.lenovo.horizontalscromenu2.fragment.Fragment_yule;
import com.example.lenovo.horizontalscromenu2.fragment.LeftFragment;
import com.example.lenovo.horizontalscromenu2.fragment.RightFragment;
import com.example.lenovo.horizontalscromenu2.view.HorizontalTabhost;
import com.kson.slidingmenu.SlidingMenu;
import com.kson.slidingmenu.app.SlidingFragmentActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends SlidingFragmentActivity{

    private HorizontalTabhost tabhost;
    private List<CategoryBean> list;
    private List<Fragment> fragments;
    private SlidingMenu menu;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabhost = (HorizontalTabhost) findViewById(R.id.tabhost);
        ImageView iv_1 = (ImageView) findViewById(R.id.iv_1);
        ImageView iv_2 = (ImageView) findViewById(R.id.iv_2);
        iv_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menu.showMenu();
            }
        });
        iv_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menu.showSecondaryMenu();
            }
        });
        initDate();
        initMenu();
    }
      private void initMenu() {
        setBehindContentView(R.layout.left_layout);
        getSupportFragmentManager().beginTransaction().replace(R.id.left_fl,new LeftFragment()).commit();

          menu = getSlidingMenu();
        menu.setMode(SlidingMenu.LEFT_RIGHT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        menu.setBehindOffsetRes(R.dimen.BehindOffsetRes);

        menu.setSecondaryMenu(R.layout.right_layout);
        getSupportFragmentManager().beginTransaction().replace(R.id.right_fl,new RightFragment()).commit();

    }

    private void initDate() {
        list = new ArrayList<CategoryBean>();
        fragments = new ArrayList<Fragment>();
        CategoryBean bean = new CategoryBean();
        bean.id = "top";
        bean.name = "头条";
       list.add(bean);
        bean = new CategoryBean();
        bean.name = "娱乐";
        bean.id = "yule";
        list.add(bean);
        bean = new CategoryBean();
        bean.name = "社会";
        bean.id = "shehui";
        list.add(bean);;
        bean = new CategoryBean();
        bean.name = "体育";
        bean.id = "tiyu";
        list.add(bean);
        bean = new CategoryBean();
        bean.name = "科技";
        bean.id = "keji";
        list.add(bean);
        bean = new CategoryBean();
        bean.name = "财经";
        bean.id = "caijing";
        list.add(bean);
        bean = new CategoryBean();
        bean.name = "时尚";
        bean.id = "shishang";
        list.add(bean);
        bean = new CategoryBean();
        bean.name = "军事";
        bean.id = "junshi";
        list.add(bean);
        fragments.add(new MyFragment());
        fragments.add(new Fragment_yule());
        fragments.add(new Fragment_shehui());
        fragments.add(new Fragment_tiyu());
        fragments.add(new Fragment_keji());
        fragments.add(new Fragment_caijing());
        fragments.add(new Fragment_shishang());
        fragments.add(new Fragment_jushi());
        tabhost.diaplay(list,fragments);
    }
}
