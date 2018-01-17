package com.greenfox.kalendaryo.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.greenfox.kalendaryo.R;
import com.greenfox.kalendaryo.SelectCalendarActivity;
import com.greenfox.kalendaryo.adapter.KalendarAdapter;
import com.greenfox.kalendaryo.httpconnection.ApiService;
import com.greenfox.kalendaryo.httpconnection.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KalendarFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.tab_fragment_1, container, false);

    }
}
