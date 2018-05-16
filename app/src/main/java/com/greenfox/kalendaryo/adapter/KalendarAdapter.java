package com.greenfox.kalendaryo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.greenfox.kalendaryo.R;
import com.greenfox.kalendaryo.models.responses.GetKalendarResponse;

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

    public List<GetKalendarResponse> getGetKalendarResponses() {
        return getKalendarResponses;
    }

    public void addKalendarResponse(List<GetKalendarResponse> newGetKalendarResponses) {
        this.getKalendarResponses.addAll(newGetKalendarResponses);
        notifyDataSetChanged();
    }

    @Override
    public KalendarAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.kalendar_item, parent, false);
        return new KalendarAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(KalendarAdapter.ViewHolder holder, int position) {
        GetKalendarResponse getKalendarResponse = getKalendarResponses.get(position);
        holder.kalendarDescription.setText(getKalendarResponse.getOutputCalendarId());
        holder.kalendarName.setText(getKalendarResponse.getOutputGoogleAuthId());
    }

    @Override
    public int getItemCount() {
        return getKalendarResponses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView kalendarName;
        private TextView kalendarDescription;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            kalendarName = itemView.findViewById(R.id.mergedcalendarname);
            kalendarDescription = itemView.findViewById(R.id.mergedcalendardescription);
        }

        @Override
        public void onClick(View view) {
        }
    }
    public interface ListChange {
        void saveCalendar(String calendarTitle);
        void removeCalendar(String calderTitle);
    }

    public void removeAt(int position) {
        getKalendarResponses.remove(position);
        notifyItemRemoved(position);
        notifyItemChanged(0,getKalendarResponses.size());
    }
}
