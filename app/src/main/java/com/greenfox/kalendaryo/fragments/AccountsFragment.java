package com.greenfox.kalendaryo.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.greenfox.kalendaryo.R;
import com.greenfox.kalendaryo.adapter.AccountAdapter;
import com.greenfox.kalendaryo.models.KalPref;

public class AccountsFragment extends Fragment {

    RecyclerView accountNamesView;
    KalPref kalpref;
    Button sendToBackend;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_choose_account, container, false);
        kalpref = new KalPref(getActivity());
        sendToBackend = view.findViewById(R.id.sendtobackend);
        accountNamesView = view.findViewById(R.id.accountNames);
        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(getActivity());
        accountNamesView.setLayoutManager(recyclerLayoutManager);
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(accountNamesView.getContext(),
                        recyclerLayoutManager.getOrientation());
        accountNamesView.addItemDecoration(dividerItemDecoration);
        AccountAdapter accountAdapter = new
                AccountAdapter(kalpref.getKalAuths(),getActivity());
        accountNamesView.setAdapter(accountAdapter);
        return view;
    }
}
