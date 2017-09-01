package com.example.lenovo.horizontalscromenu2.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.horizontalscromenu2.R;
import com.example.lenovo.horizontalscromenu2.bean.XlvBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by lenovo on 2017/8/30.
 */

public class  XlvAdadpter extends BaseAdapter {
    private Context context;
    private List<XlvBean> list;
    public  XlvAdadpter (Context context, List<XlvBean> list) {
        this.context = context;
        this.list = list;
    }
    private final  int atype=0;
    private final  int btype=1;
    private  int num=2;
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemViewType(int position) {
        if(position%2==0)
        {
            return atype;
        }
        else
        {
            return btype;
        }
    }

    @Override
    public int getViewTypeCount() {
        return num;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        int type = getItemViewType(i);
        ViewHolder holder=null;
        ViewHolder2 holder2=null;
        if(view==null)
        {
            switch (type)
            {
                case atype:
                    holder=new ViewHolder();
                    view=View.inflate(context, R.layout.item1,null);
                    holder.iv_1 = view.findViewById(R.id.iv_1);
                    holder.tv_1 = view.findViewById(R.id.tv_1);
                    view.setTag(holder);
                    break;
                case btype:
                    holder2=new ViewHolder2();
                    view=View.inflate(context,R.layout.item2,null);
                    holder2.iv_2 = view.findViewById(R.id.iv_2);
                    holder2.tv_2 = view.findViewById(R.id.tv_2);
                    view.setTag(holder2);
                    break;
            }
        }
        else
        {
            switch (type)
            {
                case atype:
                    holder= (ViewHolder) view.getTag();
                    break;
                case btype:
                    holder2= (ViewHolder2) view.getTag();
                    break;
            }
        }
        switch (type)
        {
            case atype:
                holder.tv_1.setText(list.get(i).getTitle());
                ImageLoader.getInstance().displayImage(list.get(i).getPic(),holder.iv_1);
                break;
            case btype:
                holder2.tv_2.setText(list.get(i).getTitle());
                ImageLoader.getInstance().displayImage(list.get(i).getPic(),holder2.iv_2);
                break;
        }

        return view;
    }
    public class ViewHolder
    {public TextView tv_1;
        public ImageView iv_1;

    }
    public class ViewHolder2
    {public TextView tv_2;
        public ImageView iv_2;

    }
}
