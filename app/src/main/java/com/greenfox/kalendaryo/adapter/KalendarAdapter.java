package com.greenfox.kalendaryo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.greenfox.kalendaryo.InformationAndDeleteActivity;
import com.greenfox.kalendaryo.R;
import com.greenfox.kalendaryo.components.DaggerApiComponent;
import com.greenfox.kalendaryo.http.backend.BackendApi;
import com.greenfox.kalendaryo.models.responses.GetKalendarResponse;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by tung on 2/28/18.
 */

public class KalendarAdapter extends RecyclerView.Adapter<KalendarAdapter.ViewHolder> {

    public static final String GETKALENDARRESPONSE = "com.greenfox.kalendaryo.adapter.GETKALENDARRESPONSE";

    private Context context;
    private List<GetKalendarResponse> kalendarResponses;

    @Inject
    BackendApi backendApi;

    public KalendarAdapter(Context context) {
        this.context = context;
        this.kalendarResponses = new ArrayList<>();
    }

    public void setKalendarResponses(List<GetKalendarResponse> kalendarResponses) {
        this.kalendarResponses = kalendarResponses;
        notifyDataSetChanged();
    }

    public List<GetKalendarResponse> getKalendarResponses() {
        return kalendarResponses;
    }

    @Override
    public KalendarAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.kalendar_item, parent, false);
        return new KalendarAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(KalendarAdapter.ViewHolder holder, int position) {
        DaggerApiComponent.builder().build().inject(this);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                informationAndDeleteActivity(view, position);
            }
        });
        kalendarSetText(holder, position);
        }

    private void informationAndDeleteActivity(View view, int position) {
        Intent i = new Intent(context, InformationAndDeleteActivity.class);
        GetKalendarResponse getKalendarResponse = kalendarResponses.get(position);
        i.putExtra(GETKALENDARRESPONSE, getKalendarResponse);
        context.startActivity(i);
    }

    @Override
    public int getItemCount() {
        return kalendarResponses.size();
    }


    public void kalendarSetText(KalendarAdapter.ViewHolder holder, int position){
        GetKalendarResponse getKalendarResponse = kalendarResponses.get(position);
        holder.kalendarDescription.setText(getKalendarResponse.getOutputCalendarId());
        holder.kalendarName.setText(getKalendarResponse.getOutputGoogleAuthId());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView kalendarName;
        private TextView kalendarDescription;

        public ViewHolder(View itemView) {
            super(itemView);
            kalendarName = itemView.findViewById(R.id.mergedcalendarname);
            kalendarDescription = itemView.findViewById(R.id.mergedcalendardescription);
        }
    }
}
