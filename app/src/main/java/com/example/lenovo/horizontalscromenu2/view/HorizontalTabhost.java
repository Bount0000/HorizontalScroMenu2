package com.example.lenovo.horizontalscromenu2.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lenovo.horizontalscromenu2.CategoryBean;
import com.example.lenovo.horizontalscromenu2.R;

import java.util.List;

/**
 * Created by lenovo on 2017/8/31.
 */

public class HorizontalTabhost extends LinearLayout implements ViewPager.OnPageChangeListener {
    private Context context;
      private List<CategoryBean> list;
       private List<Fragment> fragments;
    private ViewPager vp;
    private LinearLayout lt;
    private int color;

    public HorizontalTabhost(Context context) {
        this(context,null);
    }

    public HorizontalTabhost(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public HorizontalTabhost(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
        init(context,attrs);
    }
    private void init(Context context,AttributeSet attrs)
    {
        TypedArray typedArray=context.obtainStyledAttributes(attrs,R.styleable.HorizontalTabhost);
        color = typedArray.getColor(R.styleable.HorizontalTabhost_top_backgroup, 0xffffff);
        typedArray.recycle();
        initView();
    }

    private void initView() {
        View  view= LayoutInflater.from(context).inflate(R.layout.horizontal_tabhost,this,true);
       HorizontalScrollView horizon_ht= view.findViewById(R.id.horizon_ht);
        lt = view.findViewById(R.id.lt);
        vp = findViewById(R.id.vp);
       vp.addOnPageChangeListener(this);
    }
    public void diaplay(List<CategoryBean> list, List<Fragment> fragments)
    {
        this.list = list;
        this.fragments= fragments;
        drawui();
    }

    private void drawui() {
        drawHorizontal();

        drawViewpager();
    }

    private void drawViewpager() {
        MyAdapter adapter=new MyAdapter(((FragmentActivity) context).getSupportFragmentManager());
        vp.setAdapter(adapter);
    }

    private void drawHorizontal() {
      lt.setBackgroundColor(color);
        for (int i = 0; i <list.size() ; i++) {
            CategoryBean bean = (CategoryBean) list.get(i);
            TextView tv = (TextView) View.inflate(context, R.layout.news_top_tv_item, null);
            tv.setText(bean.name);
            lt.addView(tv);
            int finalI=i;
            final int finalI1 = i;
            tv.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                 vp.setCurrentItem(finalI1);
                }
            });
        }
    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
    if(lt!=null&&lt.getChildCount()>0)
    {
        for (int i = 0; i <lt.getChildCount() ; i++) {
            if(position==i)
            {
                lt.getChildAt(i).setSelected(true);
            }
            else
                {
                 lt.getChildAt(i).setSelected(false);
            }
        }
    }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
    class  MyAdapter extends FragmentPagerAdapter
    {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}

