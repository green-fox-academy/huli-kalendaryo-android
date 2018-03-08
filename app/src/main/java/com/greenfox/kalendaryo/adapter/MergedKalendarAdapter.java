package com.greenfox.kalendaryo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.greenfox.kalendaryo.R;
import com.greenfox.kalendaryo.fragments.KalendarFragment;
import com.greenfox.kalendaryo.models.Kalendar;
import com.greenfox.kalendaryo.models.MergedCalendarResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tung on 2/28/18.
 */

public class MergedKalendarAdapter extends RecyclerView.Adapter<MergedKalendarAdapter.ViewHolder> {
    private Context context;
    private List<MergedCalendarResponse> mergedCalendarResponses;


    public MergedKalendarAdapter(Context context) {
        this.context = context;
        this.mergedCalendarResponses = new ArrayList<>();
    }

    public void setMergedCalendarResponses(List<MergedCalendarResponse> mergedCalendarResponses) {
        this.mergedCalendarResponses = mergedCalendarResponses;
        notifyDataSetChanged();
    }
    public void addMergedCalendarResponse(List<MergedCalendarResponse>  newMergedCalendarResponses) {
        this.mergedCalendarResponses.addAll(newMergedCalendarResponses);
        notifyDataSetChanged();
    }


    @Override
    public MergedKalendarAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.onemergedcal, parent, false);
        return new MergedKalendarAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MergedKalendarAdapter.ViewHolder holder, int position) {
        MergedCalendarResponse mergedCalendarResponse = mergedCalendarResponses.get(position);
        holder.calendarDescription.setText(mergedCalendarResponse.getOutputCalendarId());
        holder.mergedCalendarName.setText(mergedCalendarResponse.getOutputAccountId());

    }

    @Override
    public int getItemCount() {
        return mergedCalendarResponses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mergedCalendarName;
        private TextView calendarDescription;


        public ViewHolder(View itemView) {
            super(itemView);
            mergedCalendarName = itemView.findViewById(R.id.mergedcalendarname);
            calendarDescription = itemView.findViewById(R.id.mergedcalendardescription);
        }
    }


}
