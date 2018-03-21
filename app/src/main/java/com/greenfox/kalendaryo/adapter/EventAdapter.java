package com.greenfox.kalendaryo.adapter;


import android.content.Context;
import android.widget.CompoundButton;

import com.greenfox.kalendaryo.models.event.GoogleEvent;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EventAdapter {

    private List<GoogleEvent> events;
    private Context context;

    public EventAdapter(Context context) {
        this.context = context;
        this.events = new ArrayList<>();
    }

    public void addEvents(List<GoogleEvent> newEvents) {
        this.events.addAll(newEvents);
        //notifyDataSetChanged();
    }

    /**
    @Override
    public void onBindViewHolder(EventAdapter.ViewHolder holder, int position) {
        GoogleEvent event = events.get(position);
        holder.calendarName.setText(event.getSummary());
        holder.checkBox.setOnCheckedChangeListener(null);

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                    listChange.saveCalendar((String)holder.calendarName.getText());
                else {
                    listChange.removeCalendar((String)holder.calendarName.getText());
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return kalendars.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView calendarName;
        private CheckBox checkBox;


        public ViewHolder(View itemView) {
            super(itemView);
            calendarName = itemView.findViewById(R.id.calendarname);
            checkBox = itemView.findViewById(R.id.checkBox1);
        }

    }*/
}
