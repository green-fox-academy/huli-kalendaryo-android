package com.greenfox.kalendaryo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.greenfox.kalendaryo.R;
import com.greenfox.kalendaryo.models.CalendarId;
import com.greenfox.kalendaryo.models.KalMerged;
import com.greenfox.kalendaryo.models.Kalendar;
import com.greenfox.kalendaryo.models.SharingOptions;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by barba on 02/01/2018.
 */

public class SharingOptionsAdapter extends RecyclerView.Adapter<SharingOptionsAdapter.ViewHolder> {

    private List<CalendarId> inputCalendarIds;
    private Context context;
    KalMerged kalMerged;

    public SharingOptionsAdapter(Context context, KalMerged kalMerged) {
        this.context = context;
        this.kalMerged = kalMerged;
        this.inputCalendarIds = kalMerged.getInputCalendarIds();
    }

    @Override
    public SharingOptionsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.calendar_name_sharing_options, parent, false);
        SharingOptionsAdapter.ViewHolder viewHolder = new SharingOptionsAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SharingOptionsAdapter.ViewHolder holder, int position) {
        SharingOptions sharingOptions = new SharingOptions();
        String kalendar = inputCalendarIds.get(position).getId();

        holder.calendarName.setText(kalendar);
        holder.check_title.setOnCheckedChangeListener(null);
        holder.check_description.setOnCheckedChangeListener(null);
        holder.check_attendants.setOnCheckedChangeListener(null);
        holder.check_location.setOnCheckedChangeListener(null);
        holder.check_organizer.setOnCheckedChangeListener(null);

        holder.check_title.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    sharingOptions.setTitle(true);
                } else {
                    sharingOptions.setTitle(false);
                }
                inputCalendarIds.get(holder.getAdapterPosition()).setSharingOptions(sharingOptions);
                kalMerged.setInputCalendarIds(inputCalendarIds);
                System.out.println("FIRST CALENDAR TITLE SHARING: " + kalMerged.getInputCalendarIds().get(0).getSharingOptions().getTitle());
            }
        });

        holder.check_description.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                sharingOptions.setDescription(true);
                inputCalendarIds.get(holder.getAdapterPosition()).setSharingOptions(sharingOptions);
            }
        });

        holder.check_attendants.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                sharingOptions.setAttendants(true);
                inputCalendarIds.get(holder.getAdapterPosition()).setSharingOptions(sharingOptions);
            }
        });

        holder.check_location.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                sharingOptions.setLocation(true);
                inputCalendarIds.get(holder.getAdapterPosition()).setSharingOptions(sharingOptions);
            }
        });

        holder.check_organizer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                sharingOptions.setOrganizer(true);
                inputCalendarIds.get(holder.getAdapterPosition()).setSharingOptions(sharingOptions);
            }
        });
    }

    @Override
    public int getItemCount() {
        return inputCalendarIds.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

            private TextView calendarName;
            private CheckBox check_title;
            private CheckBox check_organizer;
            private CheckBox check_location;
            private CheckBox check_attendants;
            private CheckBox check_description;


        public ViewHolder(View itemView) {
            super(itemView);
            calendarName = itemView.findViewById(R.id.calendarname);
            check_title = itemView.findViewById(R.id.check_title);
            check_organizer = itemView.findViewById(R.id.check_organizer);
            check_location = itemView.findViewById(R.id.check_location);
            check_attendants = itemView.findViewById(R.id.check_attendants);
            check_description = itemView.findViewById(R.id.check_description);
        }

    }
}


