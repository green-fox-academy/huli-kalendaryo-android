package com.greenfox.kalendaryo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.greenfox.kalendaryo.R;

import java.util.ArrayList;
import java.util.List;

public class KalendarSettingsAdapter extends RecyclerView.Adapter<KalendarSettingsAdapter.ViewHolder> {

  List<String> calendarNames;
  private Context context;

  public KalendarSettingsAdapter(Context context) {
    calendarNames = new ArrayList<>();
    this.context = context;
  }

  public void setCalendarNames(List<String> calendarNames) {
    this.calendarNames = calendarNames;
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.calendar_list_item, parent, false);
    ViewHolder viewHolder = new ViewHolder(view);
    return viewHolder;
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    holder.mergedCalendarName.setText(calendarNames.get(position));
  }

  @Override
  public int getItemCount() {
    return calendarNames.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {

    TextView mergedCalendarName;

    public ViewHolder(View itemView) {
      super(itemView);
      mergedCalendarName = itemView.findViewById(R.id.oneCalendar);
    }
  }
}
