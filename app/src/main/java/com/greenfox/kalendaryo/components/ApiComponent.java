package com.greenfox.kalendaryo.components;

import com.greenfox.kalendaryo.ChooseAccountActivity;
import com.greenfox.kalendaryo.LoginActivity;
import com.greenfox.kalendaryo.SelectCalendarActivity;
import com.greenfox.kalendaryo.http.RetrofitClient;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by a- on 20/03/2018.
 */

@Component(modules = RetrofitClient.class)
@Singleton
public interface ApiComponent {
    void inject(LoginActivity loginActivity);
    void inject(ChooseAccountActivity chooseAccountActivity);
    void inject(SelectCalendarActivity selectCalendarActivity);
}
