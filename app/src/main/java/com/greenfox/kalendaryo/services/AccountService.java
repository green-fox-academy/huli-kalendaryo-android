package com.greenfox.kalendaryo.services;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;

import com.greenfox.kalendaryo.adapter.AccountAdapter;
import com.greenfox.kalendaryo.components.DaggerApiComponent;
import com.greenfox.kalendaryo.http.backend.BackendApi;
import com.greenfox.kalendaryo.models.KalPref;
import com.greenfox.kalendaryo.models.Kalendar;
import com.greenfox.kalendaryo.models.responses.GetAccountResponse;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountService {

    private AccountAdapter accountAdapter;
    private Kalendar kalendar;

    @Inject
    BackendApi backendApi;

    public void listAccountsFromBackend(RecyclerView recycler, boolean onFragment, Intent intent) {
        DaggerApiComponent.builder().build().inject(this);
        KalPref kalPref = new KalPref(recycler.getContext());
        backendApi.getAccount(kalPref.clientToken()).enqueue(new Callback<GetAccountResponse>() {
            @Override
            public void onResponse(Call<GetAccountResponse> call, Response<GetAccountResponse> response) {
                GetAccountResponse getAccountResponse = response.body();
                accountAdapter = new AccountAdapter(getAccountResponse.getGoogleAuths(), recycler.getContext());
                recycler.setAdapter(accountAdapter);
                if (!onFragment) {
                    kalendar = (Kalendar) intent.getSerializableExtra("list");
                    accountAdapter.setEmailChange(new AccountAdapter.EmailChange() {
                        @Override
                        public void emailChanged(String email) {
                            kalendar.setOutputGoogleAuthId(email);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<GetAccountResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
