package com.greenfox.kalendaryo.services;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.greenfox.kalendaryo.adapter.AccountAdapter;
import com.greenfox.kalendaryo.http.backend.BackendApi;
import com.greenfox.kalendaryo.models.responses.GetAccountResponse;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountService {

    private AccountAdapter accountAdapter;

    @Inject
    BackendApi backendApi;

    public void listAccountsFromBackend(String clientToken, Context ctx, RecyclerView recyclerView) {
        backendApi.getAccount(clientToken).enqueue(new Callback<GetAccountResponse>() {
            @Override
            public void onResponse(Call<GetAccountResponse> call, Response<GetAccountResponse> response) {
                GetAccountResponse getAccountResponse = response.body();
                accountAdapter = new AccountAdapter(getAccountResponse.getGoogleAuths(), ctx);
                recyclerView.setAdapter(accountAdapter);
            }

            @Override
            public void onFailure(Call<GetAccountResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
