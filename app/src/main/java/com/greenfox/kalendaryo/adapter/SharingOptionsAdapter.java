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

    private List<GoogleCalendar> googleCalendars;
    private Context context;
    Kalendar kalendar;
    public enum VisibilityOptions{
        DEFAULT("Default visibility"),
        PUBLIC("Public"),
        PRIVATE("Private");

        private String visibilities;

        private VisibilityOptions(String visibilities){
            this.visibilities = visibilities;
        }

        @Override public String toString(){
            return visibilities;
        }
    }

    public SharingOptionsAdapter(Context context, Kalendar kalendar) {
        this.context = context;
        this.kalendar = kalendar;
        this.googleCalendars = kalendar.getInputGoogleCalendars();
    }

    @Override
    public SharingOptionsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sharing_options, parent, false);
        SharingOptionsAdapter.ViewHolder viewHolder = new SharingOptionsAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SharingOptionsAdapter.ViewHolder holder, int position) {
        GoogleCalendar calendar = googleCalendars.get(position);
        holder.calendarName.setText(calendar.getSummary());
        calendar.setSharingOptions(VisibilityOptions.valueOf("DEFAULT").toString());

        Spinner spinner = holder.dropdown;
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                calendar.setSharingOptions(spinner.getSelectedItem().toString());
            }
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return googleCalendars.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView calendarName;
        private Spinner dropdown;


        public ViewHolder(View itemView) {
            super(itemView);
            calendarName = itemView.findViewById(R.id.textView_calendarname);
            dropdown = (Spinner)itemView.findViewById(R.id.spinner_setVisibility);
            String[] items = new String[]{"Default visibility", "Public", "Private"};
            ArrayAdapter<VisibilityOptions> adapter = new ArrayAdapter<VisibilityOptions>(context, android.R.layout.simple_spinner_dropdown_item, VisibilityOptions.values());
            dropdown.setAdapter(adapter);
        }
    }
}