package com.greenfox.kalendaryo.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.greenfox.kalendaryo.R;
import com.greenfox.kalendaryo.components.DaggerApiComponent;
import com.greenfox.kalendaryo.http.backend.BackendApi;
import com.greenfox.kalendaryo.models.KalPref;
import com.greenfox.kalendaryo.models.responses.GetKalendarResponse;
import com.greenfox.kalendaryo.models.responses.PostKalendarResponse;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tung on 2/28/18.
 */

public class KalendarAdapter extends RecyclerView.Adapter<KalendarAdapter.ViewHolder> {

    KalPref kalPref;
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

    public void addKalendarResponse(List<GetKalendarResponse> kalendarResponses) {
        this.kalendarResponses.addAll(kalendarResponses);
        notifyDataSetChanged();
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
                popUpWindow(view, position);
            }
        });
        kalendarSetText(holder, position);
        }

    @Override
    public int getItemCount() {
        return kalendarResponses.size();
    }

    public void removeAt(int position) {
        kalendarResponses.remove(position);
        notifyItemRemoved(position);
    }

    public void kalendarSetText(KalendarAdapter.ViewHolder holder, int position){
        GetKalendarResponse getKalendarResponse = kalendarResponses.get(position);
        holder.kalendarDescription.setText(getKalendarResponse.getOutputCalendarId());
        holder.kalendarName.setText(getKalendarResponse.getOutputGoogleAuthId());
    }

    public void popUpWindow(View view, int position){
        AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
        alert.setTitle("Delete Kalendar");
        alert.setMessage("Are you sure to delete the kalendar?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                kalPref = new KalPref(view.getContext());
                String clientToken = kalPref.clientToken();

                backendApi.deleteKalendar(clientToken, kalendarIdToDelete(position)).enqueue(new Callback<Void>() {

                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        removeAt(position);
                        toastMessage(view,"Kalendar deleted successfully");
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        t.printStackTrace();
                        toastMessage(view, "Ooops, couldn't delete the kalendar");
                    }
                });
                dialogInterface.dismiss();
            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alert.show();
    }

    public long kalendarIdToDelete (int position){
        List<GetKalendarResponse> kalendarList = getKalendarResponses();
        GetKalendarResponse kalendarToDelete = kalendarList.get(position);
        long deleteId = kalendarToDelete.getId();
        return deleteId;
    }

    public void toastMessage(View view, String input){
        Toast.makeText(view.getContext(),input,
        Toast.LENGTH_LONG).show();
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
