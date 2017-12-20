package com.greenfox.kalendaryo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.api.services.calendar.model.Calendar;

/**
 * Created by barba on 20/12/2017.
 */

public class CalendarAdapter extends ArrayAdapter<Calendar> {


        public CalendarAdapter(@NonNull Context context) {
            super(context, 0);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.message, parent, false);

            Calendar current = getItem(position);

            TextView calendar = convertView.findViewById(R.id.text);
            text.setText(current.getId());

            return convertView;
        }
}
