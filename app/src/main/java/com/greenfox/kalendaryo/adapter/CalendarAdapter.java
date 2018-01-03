package com.greenfox.kalendaryo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.api.services.calendar.model.Calendar;
import com.greenfox.kalendaryo.R;

/**
 * Created by barba on 02/01/2018.
 */

public class CalendarAdapter extends ArrayAdapter<Calendar> {

        public CalendarAdapter(@NonNull Context context) {
            super(context, 0);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View calendarView, @NonNull ViewGroup parent) {

            calendarView = LayoutInflater.from(getContext()).inflate(R.layout.calendar_name, parent, false);

            Calendar calendar = getItem(position);

            TextView calendarNameView = calendarView.findViewById(R.id.calendarname);
            calendarNameView.setText(calendar.getDescription());

            return calendarNameView;
        }

    }
