package com.greenfox.kalendaryo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import android.widget.CompoundButton;
import android.widget.TextView;

import com.greenfox.kalendaryo.R;


/**
 * Created by barba on 02/01/2018.
 */

public class KalendarAdapter extends RecyclerView.Adapter<KalendarAdapter.ViewHolder> {

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

        }


    @Override
    public KalendarAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(KalendarAdapter.ViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

            private TextView calendarName;
            private CheckBox checkBox;
            private boolean checked = true;



        public ViewHolder(View itemView) {
            super(itemView);
            calendarName = itemView.findViewById(R.id.calendarname);
            checkBox = itemView.findViewById(R.id.checkBox1);
            checked = checkBox.isChecked();

            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    listChange.
                }
            });
            switch(itemView.getId()) {
                case R.id.checkBox1:
                    if (checked)
                        listChange.saveCalendar((String)calendarName.getText());
                    else {
                        listChange.removeCalendar((String)calendarName.getText());
                    }
            }

        }

    }
    public interface ListChange {
        void saveCalendar(String calendarTitle);
        void removeCalendar(String calderTitle);
    }
}


