package com.greenfox.kalendaryo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.greenfox.kalendaryo.R;
import com.greenfox.kalendaryo.models.GoogleAuth;

/**
 * Created by barba on 2018. 01. 16..
 */

public class AccountsList extends ArrayAdapter<GoogleAuth> {

    public AccountsList(@NonNull Context context) {
        super(context, 0);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.oneaccount, parent, false);
        }

        GoogleAuth googleAuth = getItem(position);

        TextView calendarNameView = convertView.findViewById(R.id.oneaccountname);
        calendarNameView.setText(googleAuth.getEmail());

        return convertView;
    }
}
