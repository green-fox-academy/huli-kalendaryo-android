package com.greenfox.kalendaryo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
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
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


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

    public void removeInputCalendarIds() {
        inputCalendarIds.clear();
    }

    @Override
    public SharingOptionsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.calendar_name_sharing_options, parent, false);
        SharingOptionsAdapter.ViewHolder viewHolder = new SharingOptionsAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SharingOptionsAdapter.ViewHolder holder, int position) {

        CalendarId kalendar = inputCalendarIds.get(position);
        SharingOptions sharingOptions = kalendar.getSharingOptions();

        holder.calendarName.setText(kalendar.getId());

        ArrayList<CheckBox> checkBoxes = new ArrayList(Arrays.asList(holder.check_title, holder.check_description, holder.check_attendants, holder.check_location, holder.check_organizer));

        for (int i = 0; i < checkBoxes.size(); i++) {
            int index = i;
            checkBoxes.get(i).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b ) {
                    if (b) {
                        sharingOptions.setOption(checkBoxes.get(index).getText().toString());
                    } else {
                        sharingOptions.removeOption(checkBoxes.get(index).getText().toString());
                    }
                }
            });
        }
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


