package com.example.lenovo.horizontalscromenu2.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.lenovo.horizontalscromenu2.R;
import com.example.lenovo.horizontalscromenu2.XiangqingActivity;
import com.example.lenovo.horizontalscromenu2.adapter.XlvAdadpter;
import com.example.lenovo.horizontalscromenu2.api.Api;
import com.example.lenovo.horizontalscromenu2.bean.Bean;
import com.example.lenovo.horizontalscromenu2.bean.SqBean;
import com.example.lenovo.horizontalscromenu2.bean.XlvBean;
import com.example.lenovo.horizontalscromenu2.dao.Mydao;
import com.example.lenovo.horizontalscromenu2.util.NetWorkInfoUtils;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import view.xlistview.XListView;

/**
 * Created by lenovo on 2017/8/31.
 */
public class Fragment_caijing extends Fragment {
    private View view;
    private XListView xlv;
    private List<XlvBean> list;
    private XlvAdadpter adapter;
    private String url;
    private String json;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(view==null)
        {
           view=View.inflate(getContext(), R.layout.fragment_layout,null);
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
          xlv = view.findViewById(R.id.xlv);
          list = new ArrayList<XlvBean>();
           setImage();
           initDate();
          NetWorkInfoUtils utils=new NetWorkInfoUtils();
          utils.verify(getContext(), new NetWorkInfoUtils.Network() {

            @Override
            public void netWifiVisible() {

                loadShuju();
            }

            @Override
            public void netMobieVisble() {
                loadShuju();
            }

            @Override
            public void netUnVisible() {

                Mydao dao =new Mydao(getContext());
                List<SqBean> query = dao.query();
                for (int i = 0; i <query.size() ; i++) {
                    SqBean sqBean = query.get(i);
                    String type = sqBean.type;
                    if(type.equals("caijing"))
                    {
                        json = sqBean.json;
                    }
                }
                if(json!=null)
                {
                    Jiexi(json);
                    setDate();
                }

            }
        });
    }

    private void setImage() {
        DisplayImageOptions option=new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        ImageLoaderConfiguration config=new ImageLoaderConfiguration.Builder(getContext())
                .defaultDisplayImageOptions(option)
                .build();
        ImageLoader.getInstance().init(config);

    }
    private void initDate() {
        loadShuju();
    }

    private void loadShuju() {
        RequestParams params=new RequestParams(Api.URL_caijing);
        x.http().get(params,new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Jiexi(result);
                setDate();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }
            @Override
            public void onCancelled(CancelledException cex) {

            }
            @Override
            public void onFinished() {

            }
        });
    }
    private void Jiexi(String result) {
        Gson gson=new Gson();
        Bean bean = gson.fromJson(result, Bean.class);
        Bean.ResultBean result1 = bean.getResult();
        List<Bean.ResultBean.DataBean> data = result1.getData();
        for (int i = 0; i <data.size() ; i++) {
            Bean.ResultBean.DataBean dataBean = data.get(i);
            String title = dataBean.getTitle();
            url = dataBean.getUrl();
            String pic = dataBean.getThumbnail_pic_s();
            list.add(new XlvBean(title,pic,url));
        }
    }
    private void setDate() {
        if(adapter==null)
        {
            adapter = new XlvAdadpter(getContext(),list);
            xlv.setAdapter(adapter);
            xlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent=new Intent(getContext(),XiangqingActivity.class);
                    intent.putExtra("url",list.get(i-1).getUrl());
                    startActivity(intent);
                }
            });
        }
        else
        {
            adapter.notifyDataSetChanged();
        }
    }
}
