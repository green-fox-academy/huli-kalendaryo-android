package com.greenfox.kalendaryo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.greenfox.kalendaryo.R;
import com.greenfox.kalendaryo.models.GetKalendarResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tung on 2/28/18.
 */

public class KalendarAdapter extends RecyclerView.Adapter<KalendarAdapter.ViewHolder> {
    private Context context;
    private List<GetKalendarResponse> getKalendarResponses;

    public KalendarAdapter(Context context) {
        this.context = context;
        this.getKalendarResponses = new ArrayList<>();
    }

    public void setGetKalendarResponses(List<GetKalendarResponse> getKalendarResponses) {
        this.getKalendarResponses = getKalendarResponses;
        notifyDataSetChanged();
    }
    public void addKalendarResponse(List<GetKalendarResponse> newGetKalendarResponses) {
        this.getKalendarResponses.addAll(newGetKalendarResponses);
        notifyDataSetChanged();
    }

    @Override
    public KalendarAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.merged_calemdar_item, parent, false);
        return new KalendarAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(KalendarAdapter.ViewHolder holder, int position) {
        GetKalendarResponse getKalendarResponse = getKalendarResponses.get(position);
        holder.calendarDescription.setText(getKalendarResponse.getOutputCalendarId());
        holder.kalendarName.setText(getKalendarResponse.getOutputAccountId());
    }

    @Override
    public int getItemCount() {
        return getKalendarResponses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView kalendarName;
        private TextView calendarDescription;

        public ViewHolder(View itemView) {
            super(itemView);
            kalendarName = itemView.findViewById(R.id.mergedcalendarname);
            calendarDescription = itemView.findViewById(R.id.mergedcalendardescription);
        }
    }

}
