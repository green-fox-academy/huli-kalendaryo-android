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
import android.widget.ListView;

import com.greenfox.kalendaryo.R;
import com.greenfox.kalendaryo.adapter.AccountAdapter;
import com.greenfox.kalendaryo.adapter.AccountsList;
import com.greenfox.kalendaryo.adapter.KalendarAdapter;
import com.greenfox.kalendaryo.models.KalAuth;
import com.greenfox.kalendaryo.models.KalPref;

import java.util.List;

public class AccountsFragment extends Fragment {

    ListView accountNamesView;
    AccountsList adapter;
    KalPref kalpref;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.listaccounts, container, false);
        kalpref = new KalPref(getActivity());

        adapter = new AccountsList(view.getContext());

        accountNamesView = view.findViewById(R.id.allaccounts);

        accountNamesView.setAdapter(adapter);

        List<KalAuth> auths = kalpref.getKalAuths();

        adapter.addAll(auths);


        return view;
    }
}
