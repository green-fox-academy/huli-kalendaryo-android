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
import com.greenfox.kalendaryo.models.MergedCalendarResponse;

/**
 * Created by tung on 2/28/18.
 */

public class MergedKalendarAdapter extends ArrayAdapter<MergedCalendarResponse> {

    public MergedKalendarAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.onemergedcal, parent, false);
        }
        MergedCalendarResponse mergedCalendarResponse = getItem(position);

        TextView mergedCalendarName = convertView.findViewById(R.id.mergedcalendarname);
        mergedCalendarName.setText(mergedCalendarResponse.getOutputAccountId());

        TextView calendarId = convertView.findViewById(R.id.mergedcalendardescription);
        calendarId.setText(mergedCalendarResponse.getOutputCalendarId());

        return convertView;
    }
}
