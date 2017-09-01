package com.example.lenovo.horizontalscromenu2.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.horizontalscromenu2.R;

/**
 * Created by lenovo on 2017/8/31.
 */

public class RightFragment extends Fragment{
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(view==null)
        {
     view=View.inflate(getContext(), R.layout.right_fragment,null);
        }
        return view;
    }
}
