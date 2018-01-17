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
import com.greenfox.kalendaryo.models.KalMerged;

/**
 * Created by barba on 2018. 01. 17..
 */

public class MergedCalendarAdapter extends ArrayAdapter<KalMerged> {

    public MergedCalendarAdapter(@NonNull Context context) {
        super(context, 0);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.onemergedcal, parent, false);

        KalMerged merged = getItem(position);

        TextView mergedCalendarName = convertView.findViewById(R.id.mergedcalendarname);
        // mergedCalendarName.setText(merged.getOutputAccount());

        TextView mergedCalendarDescription = convertView.findViewById(R.id.mergedcalendardescription);
        // mergedCalendarDescription.setText(merged.getOutputAccount());

        return convertView;
    }

}
