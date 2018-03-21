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
import com.greenfox.kalendaryo.models.KalMerged;
import com.greenfox.kalendaryo.models.Kalendar;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by barba on 02/01/2018.
 */

public class KalendarAdapter extends RecyclerView.Adapter<KalendarAdapter.ViewHolder> {

    private List<Kalendar> kalendars;
    private Context context;
    private ListChange listChange;

    public ListChange getListChange() {
        return listChange;
    }

    public void setListChange(ListChange listChange) {
        this.listChange = listChange;
    }

    public KalendarAdapter(Context context) {
        this.context = context;
        this.kalendars = new ArrayList<>();
    }

    public void setKalendars(List<Kalendar> kalendars) {
        this.kalendars = kalendars;
        notifyDataSetChanged();
    }

    public void addKalendars(List<Kalendar> newKalendars) {
        this.kalendars.addAll(newKalendars);
        notifyDataSetChanged();
    }

    @Override
    public KalendarAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.calendar_name, parent, false);
        KalendarAdapter.ViewHolder viewHolder = new KalendarAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(KalendarAdapter.ViewHolder holder, int position) {
        Kalendar kalendar = kalendars.get(position);
        holder.calendarName.setText(kalendar.getSummary());
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

    }

    public interface ListChange {
        void saveCalendar(String calendarTitle);
        void removeCalendar(String calderTitle);
    }
}


