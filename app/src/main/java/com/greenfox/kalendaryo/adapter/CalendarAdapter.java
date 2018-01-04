package com.greenfox.kalendaryo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.CalendarListEntry;
import com.greenfox.kalendaryo.R;
import com.greenfox.kalendaryo.models.Kalendar;

import java.util.List;

/**
 * Created by barba on 02/01/2018.
 */

public class CalendarAdapter extends ArrayAdapter<Kalendar> {

        public CalendarAdapter(@NonNull Context context) {
            super(context, 0);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.calendar_name, parent, false);

            Kalendar kalendar = getItem(position);

            TextView calendarNameView = convertView.findViewById(R.id.calendarname);
            calendarNameView.setText(kalendar.getSummary());

            return convertView;
        }

    }
