package com.greenfox.kalendaryo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.greenfox.kalendaryo.R;
import com.greenfox.kalendaryo.models.GoogleCalendar;
import com.greenfox.kalendaryo.models.Kalendar;

import java.util.List;


public class SharingOptionsAdapter extends RecyclerView.Adapter<SharingOptionsAdapter.ViewHolder> {

    private List<GoogleCalendar> inputCalendarIds;
    private Context context;
    Kalendar kalMerged;

    public SharingOptionsAdapter(Context context, Kalendar kalMerged) {
        this.context = context;
        this.kalMerged = kalMerged;
        this.inputCalendarIds = kalMerged.getInputGoogleCalendars();
    }

    public void removeInputCalendarIds() {
        inputCalendarIds.clear();
    }

    @Override
    public SharingOptionsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sharing_options, parent, false);
        SharingOptionsAdapter.ViewHolder viewHolder = new SharingOptionsAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SharingOptionsAdapter.ViewHolder holder, int position) {
        GoogleCalendar calendar = inputCalendarIds.get(position);

        holder.calendarName.setText(calendar.getSummary());

        Spinner spinner = holder.dropdown;


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                calendar.setSharingOptions(spinner.getSelectedItem().toString());
            }
            public void onNothingSelected(AdapterView<?> adapterView) {
                calendar.setSharingOptions("Default visibility");
            }
        });
    }

    @Override
    public int getItemCount() {
        return inputCalendarIds.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView calendarName;
        private Spinner dropdown;


        public ViewHolder(View itemView) {
            super(itemView);
            calendarName = itemView.findViewById(R.id.calendarname);
            dropdown = (Spinner)itemView.findViewById(R.id.set_visibility);
            String[] items = new String[]{"Default visibility", "Public", "Private"};
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, items);
            dropdown.setAdapter(adapter);
        }
    }
}