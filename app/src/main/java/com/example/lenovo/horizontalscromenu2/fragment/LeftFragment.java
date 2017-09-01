package com.example.lenovo.horizontalscromenu2.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lenovo.horizontalscromenu2.MoreLoginActivity;
import com.example.lenovo.horizontalscromenu2.R;

/**
 * Created by lenovo on 2017/8/31.
 */

public class LeftFragment extends Fragment{
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(view==null)
        {
         view=View.inflate(getContext(),R.layout.left_fragment,null);
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
         TextView login_style= view.findViewById(R.id.login_style);
        login_style.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), MoreLoginActivity.class);
                startActivity(intent);
            }
        });

    }
}
