package com.example.lenovo.horizontalscromenu2.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.horizontalscromenu2.R;
import com.example.lenovo.horizontalscromenu2.adapter.XlvAdadpter;
import com.example.lenovo.horizontalscromenu2.api.Api;
import com.example.lenovo.horizontalscromenu2.bean.Bean;
import com.example.lenovo.horizontalscromenu2.bean.XlvBean;
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
public class Fragment_shehui extends Fragment {
    private View view;
    private XListView xlv;
    private List<XlvBean> list;

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
        //String url= "http://v.juhe.cn/toutiao/index?key=22a108244dbb8d1f49967cd74a0c144d";
           RequestParams params=new RequestParams(Api.URL_shehui);
            // params.addParameter();
            x.http().get(params,new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson=new Gson();
                Bean bean = gson.fromJson(result, Bean.class);
                Bean.ResultBean result1 = bean.getResult();
                List<Bean.ResultBean.DataBean> data = result1.getData();
                for (int i = 0; i <data.size() ; i++) {
                    Bean.ResultBean.DataBean dataBean = data.get(i);
                    String title = dataBean.getTitle();
                    String pic = dataBean.getThumbnail_pic_s();
                    list.add(new XlvBean(title,pic));
                }
                   setDate();
            }

              private void setDate() {
                  XlvAdadpter adapter=new XlvAdadpter(getContext(),list);
                  xlv.setAdapter(adapter);
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
}
