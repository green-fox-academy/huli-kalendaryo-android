package com.greenfox.kalendaryo.http.backend;

import java.io.IOException;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by BalazsSalfay on 2018. 02. 28..
 */

public abstract class ImplCall implements Call {

    @Override
    public Response execute() throws IOException {
        return null;
    }

    @Override
    public abstract void enqueue(Callback callback);

    @Override
    public boolean isExecuted() {
        return false;
    }

    @Override
    public void cancel() {

    }

    @Override
    public boolean isCanceled() {
        return false;
    }

    @Override
    public Call clone() {
        return null;
    }

    @Override
    public Request request() {
        return null;
    }
}
