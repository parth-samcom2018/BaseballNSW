package com.nsw.baseballnsw;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.nsw.baseballnsw.models.Group;


public class LaddersVC extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    Group group;
    private LinearLayout ll_orientation,ll_second,ll_landscape;
    int click = 0;

    public LaddersVC() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        inflater = (LayoutInflater)this.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.fragment_ladders,container,false);

        ll_orientation = view.findViewById(R.id.ll_orientation);
        ll_second = view.findViewById(R.id.ll_second);
        ll_landscape = view.findViewById(R.id.ll_landscape);

        ll_orientation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (click == 0){
                    ll_second.setVisibility(View.GONE);
                    ll_landscape.setVisibility(View.VISIBLE);
                    getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    click+=1;
                    return;
                }

                if (click == 1){
                    ll_second.setVisibility(View.VISIBLE);
                    ll_landscape.setVisibility(View.GONE);
                    getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    click-=1;
                }

            }
        });



        return view;
    }

    private boolean initialLoaded = false;
    public void loadIfUnloaded(){

        if(initialLoaded == false) loadData();
    }

    private void loadData()
    {
        initialLoaded = true;

        Toast.makeText(LaddersVC.this.getActivity(), "Ladders", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh() {

    }
}
