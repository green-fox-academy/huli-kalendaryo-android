package com.greenfox.kalendaryo.adapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;

import com.greenfox.kalendaryo.R;

public class InformationAndDeleteActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_information_and_delete);

    DisplayMetrics dm = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(dm);

    int width = (int) (dm.widthPixels*0.7);
    int height = (int) (dm.heightPixels*0.7);

    getWindow().setLayout(width, height);
  }
}
