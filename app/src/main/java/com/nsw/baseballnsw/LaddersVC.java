package com.nsw.baseballnsw;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.nsw.baseballnsw.models.Event;
import com.nsw.baseballnsw.models.Group;
import com.nsw.baseballnsw.models.GroupResponse;
import com.nsw.baseballnsw.models.Ladders;
import com.nsw.baseballnsw.models.LaddersResponse;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Vector;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class LaddersVC extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    public static Ladders ladder;
    private ListView listView;
    private ArrayAdapter<Ladders> listadapter;
    private SwipeRefreshLayout refreshLayout;
    private ImageView emptyIV;


    private List<Ladders> ladders = new Vector<Ladders>();
    public LaddersVC() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        inflater = (LayoutInflater) this.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View v = inflater.inflate(R.layout.fragment_ladders, container, false);


        emptyIV = v.findViewById(R.id.empty);

        final ProgressDialog pd = DM.getPD(getActivity(),"Loading Ladders...");
        if(this.isVisible())pd.show();

        listView = v.findViewById(R.id.list);
        listView.setDividerHeight(0);

        listadapter= new ArrayAdapter<Ladders>(this.getActivity(), R.layout.ladder_cell){


            @SuppressLint("NewApi")
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                if (convertView == null) {
                    try
                    {
                        convertView = LayoutInflater.from(LaddersVC.this.getActivity()).inflate(R.layout.ladder_cell, parent, false);
                    }

                    catch (NullPointerException e){
                        e.printStackTrace();
                    }
                }

                Ladders ladder = ladders.get(position);

                TextView textteam = convertView.findViewById(R.id.tvteam);
                textteam.setText(""+ladder.teamName);

                TextView textwin = convertView.findViewById(R.id.tvwin);
                textwin.setText(Integer.toString(ladder.gamesWon));

                TextView textlost = convertView.findViewById(R.id.tvlost);
                textlost.setText(Integer.toString(ladder.gamesLost));

                TextView textdrawn = convertView.findViewById(R.id.tvdrawn);
                textdrawn.setText(Integer.toString(ladder.gamesDrawn));

                TextView textplayed = convertView.findViewById(R.id.tvplayed);
                textplayed.setText(Integer.toString(ladder.gamesPlayed));

                TextView textpoints = convertView.findViewById(R.id.tvpoints);
                textpoints.setText(Integer.toString(ladder.totalPoints));

                return convertView;
            }

            @Override
            public int getCount() {
                return ladders.size();
            }
        };
        listView.setAdapter(listadapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        refreshLayout = v.findViewById(R.id.swiperefresh);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                loadData();
            }
        });



        return v;
    }

    private boolean initialLoaded = false;
    public void loadIfUnloaded(){

        if(initialLoaded == false) loadData();
    }

    private void loadData()
    {
        initialLoaded = true;

        final ProgressDialog pd = DM.getPD(getActivity(),"Loading Ladders...");
        if(this.isVisible())pd.show();


        String auth = DM.getAuthString();


        DM.getApi().getLadders(auth, new Callback<LaddersResponse>() {
            @Override
            public void success(LaddersResponse gs, Response response) {


                ladders = gs.getData();
                listadapter.notifyDataSetChanged();
                refreshLayout.setRefreshing(false);
                pd.dismiss();

                if(gs.getData().size()==0) emptyIV.setVisibility(View.VISIBLE);
                else emptyIV.setVisibility(View.GONE);

            }

            @Override
            public void failure(RetrofitError error) {
                refreshLayout.setRefreshing(false);
                pd.dismiss();

            }
        });
    }

    @Override
    public void onRefresh() {

    }
}
