package com.greenfox.kalendaryo.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.greenfox.kalendaryo.LoginActivity;
import com.greenfox.kalendaryo.R;
import com.greenfox.kalendaryo.SelectCalendarActivity;
import com.greenfox.kalendaryo.adapter.KalendarAdapter;
import com.greenfox.kalendaryo.adapter.MergedCalendarAdapter;
import com.greenfox.kalendaryo.httpconnection.ApiService;
import com.greenfox.kalendaryo.httpconnection.RetrofitClient;
import com.greenfox.kalendaryo.models.KalMerged;
import com.greenfox.kalendaryo.models.KalPref;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KalendarFragment extends Fragment {

    KalPref kalpref;
    FloatingActionButton floatingActionButton;
    MergedCalendarAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.kalendarlist, container, false);
        floatingActionButton = view.findViewById(R.id.choosecalendar);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), SelectCalendarActivity.class);
                startActivity(i);
            }
        });

        adapter = new MergedCalendarAdapter(getContext());

        // List<KalMerged> kalMergeds = kalpref.getKalAuths();

        // adapter.addAll(kalMergeds);

        kalpref = new KalPref(getActivity());
        return view;

    }
}