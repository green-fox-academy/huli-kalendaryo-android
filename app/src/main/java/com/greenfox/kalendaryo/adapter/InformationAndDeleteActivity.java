package com.greenfox.kalendaryo.adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Toast;

import com.greenfox.kalendaryo.R;
import com.greenfox.kalendaryo.http.backend.BackendApi;
import com.greenfox.kalendaryo.models.KalPref;
import com.greenfox.kalendaryo.models.responses.GetKalendarResponse;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InformationAndDeleteActivity extends AppCompatActivity {

  private int position;
  KalPref kalPref;
  private List<GetKalendarResponse> kalendarResponses;

  @Inject
  BackendApi backendApi;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_information_and_delete);

    Intent i = getIntent();
    position = i.getIntExtra(KalendarAdapter.POSITION, 0);
   // position = Integer.parseInt(i.getStringExtra(KalendarAdapter.POSITION));

    DisplayMetrics dm = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(dm);

    int width = (int) (dm.widthPixels*0.85);
    int height = (int) (dm.heightPixels*0.85);

    getWindow().setLayout(width, height);
  }

  public void deleteKalendarPopUp(View view){
    AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
    alert.setTitle("Delete Kalendar");
    alert.setMessage("Are you sure to delete the kalendar?");
    alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialogInterface, int i) {
        kalPref = new KalPref(view.getContext());
        String clientToken = kalPref.clientToken();

        long idToDelete = kalendarIdToDelete(position);

        backendApi.deleteKalendar(clientToken, idToDelete).enqueue(new Callback<Void>() {

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

  public void toastMessage(View view, String input){
    Toast.makeText(view.getContext(),input,
        Toast.LENGTH_LONG).show();
  }

  public long kalendarIdToDelete (int position){
    GetKalendarResponse kalendarToDelete = kalendarResponses.get(position);
    long deleteId = kalendarToDelete.getId();
    return deleteId;
  }

  public void setKalendarResponses(List<GetKalendarResponse> kalendarResponses) {
    this.kalendarResponses = kalendarResponses;
  }

  public void removeAt(int position) {
    kalendarResponses.remove(position);
  }
}
