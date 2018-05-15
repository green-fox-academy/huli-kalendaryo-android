package com.greenfox.kalendaryo.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.greenfox.kalendaryo.MainActivity;
import com.greenfox.kalendaryo.R;
import com.greenfox.kalendaryo.SelectCalendarActivity;
import com.greenfox.kalendaryo.adapter.KalendarAdapter;
import com.greenfox.kalendaryo.components.DaggerApiComponent;
import com.greenfox.kalendaryo.http.backend.BackendApi;
import com.greenfox.kalendaryo.models.KalPref;
import com.greenfox.kalendaryo.models.responses.GetKalendarListResponse;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KalendarFragment extends Fragment {

    KalPref kalPref;
    FloatingActionButton floatingActionButton;
    private KalendarAdapter adapter;
    private RecyclerView recyclerView;

    @Inject
    BackendApi backendApi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.kalendarlist, container, false);
        adapter = new KalendarAdapter(getActivity());
        DaggerApiComponent.builder().build().inject(this);
        floatingActionButton = view.findViewById(R.id.choosecalendar);
        recyclerView = view.findViewById(R.id.apilistcalendars);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(recyclerLayoutManager);
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(recyclerView.getContext(),
                        recyclerLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        kalPref = new KalPref(this.getContext());
        getKalendarResponse(kalPref.clientToken());

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new ClickListener() {
            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(getContext(), "Long press on pos: " +position,Toast.LENGTH_LONG).show();
            }
        }));


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), SelectCalendarActivity.class);
                startActivity(i);
            }
        });
        return view;
    }

    private void getKalendarResponse(String clientToken) {
        backendApi.getCalendar(clientToken).enqueue(new Callback<GetKalendarListResponse>() {

            @Override
            public void onResponse(Call<GetKalendarListResponse> call, Response<GetKalendarListResponse> response) {
                adapter.addKalendarResponse(response.body().getKalendars());
            }

            @Override
            public void onFailure(Call<GetKalendarListResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public static interface ClickListener{
        public void onLongClick(View view,int position);
    }

    class RecyclerTouchListener implements RecyclerView.OnItemTouchListener{

        private ClickListener clickListener;
        private GestureDetector gestureDetector;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView,
                                     final ClickListener clickListener){
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){
               @Override
               public void onLongPress(MotionEvent event){
                   View child = recyclerView.findChildViewUnder(event.getX(),event.getY());
                   if(child != null && clickListener != null){
                       clickListener.onLongClick(child,recyclerView.getChildAdapterPosition(child));
                   }
               }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child=rv.findChildViewUnder(e.getX(),e.getY());
            if(child!=null && clickListener!=null && gestureDetector.onTouchEvent(e)){
                clickListener.onLongClick(child,rv.getChildAdapterPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        }
    }

}